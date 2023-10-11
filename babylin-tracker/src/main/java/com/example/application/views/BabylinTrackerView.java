package com.example.application.views;

import com.example.application.BabylinEventService;
import com.example.application.BabylinTrackerRepo;
import com.example.application.EventType;
import com.example.application.MealEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;


@Route(value = "/log", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)

public class BabylinTrackerView extends VerticalLayout {
    private final BabylinTrackerRepo repo;
    public BabylinTrackerView(BabylinTrackerRepo repo) {
        this.repo = repo;
        Button sleepButton = createButton("Sover", EventType.SLEEP);
        Button wakeUpButton = createButton("Vaknar", EventType.WAKEUP);
        Button mealButton = createButton("Äter", EventType.MEAL);

        add(
                sleepButton,
                wakeUpButton,
                mealButton
        );
    }

    /** A button that opens a popup dialog box enabling editing of the date and time
     * before saving the event */
    private Button createButton(String label, EventType eventType){
        Button button = new Button(label);
        var dateTimePickerPopup = createDialogPopup(eventType);
        button.addClickListener(click -> {
            dateTimePickerPopup.open();
        });
        dateTimePickerPopup.addDialogCloseActionListener(event -> dateTimePickerPopup.close());
        return button;
    }

    private Dialog createDialogPopup(EventType eventType) {
        Dialog dateTimePickerPopup = new Dialog();
        dateTimePickerPopup.setModal(true);

        VerticalLayout layout = new VerticalLayout();
        layout.addClassNames("no-padding-layout");

        DateTimePicker dateTimePicker = createDateTimePicker();
        Button saveButton = createSaveButton();

        layout.add(dateTimePicker);
        dateTimePickerPopup.add(dateTimePicker, saveButton);

        NumberField amountField = createAmountField();
        if (eventType == EventType.MEAL) {
            layout.add(amountField);
        }

        saveButton.addClickListener(click -> {
            var event = BabylinEventService.createEventByType(eventType);
            event.setEventTime(dateTimePicker.getValue());
            if (amountField.getValue() != null) {
                ((MealEvent) event).setAmount(amountField.getValue());
            }
            repo.save(event);
            dateTimePickerPopup.close();
        });

        dateTimePickerPopup.add(layout);

        return dateTimePickerPopup;
    }

    private DateTimePicker createDateTimePicker() {
        DateTimePicker dateTimePicker = new DateTimePicker("Välj dag och tid");
        dateTimePicker.setValue(LocalDateTime.now());
        dateTimePicker.setStep(Duration.ofMinutes(30));
        dateTimePicker.setLocale(Locale.UK); // to ensure 24h format
        return dateTimePicker;
    }

    private Button createSaveButton() {
        Button saveButton = new Button("Spara");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.addClassName("custom-margin-button");
        return saveButton;
    }

    private NumberField createAmountField() {
        var amountField = new NumberField("Mängd (ml)");
        amountField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        return amountField;
    }
}
