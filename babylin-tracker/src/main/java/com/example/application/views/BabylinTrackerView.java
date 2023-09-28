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
        var sleepButton = new Button("Sover");
        var wakeUpButton = new Button("Vaknar");
        var mealButton = new Button("Äter");

        addButtonClickListener(sleepButton, EventType.SLEEP);
        addButtonClickListener(wakeUpButton, EventType.WAKEUP);
        addButtonClickListener(mealButton, EventType.MEAL);

        add(
                sleepButton,
                wakeUpButton,
                mealButton
        );
    }

    private void addButtonClickListener(Button button, EventType eventType){
        var dateTimePickerPopup = dateTimePickerPopup(eventType);
        button.addClickListener(click -> {
            dateTimePickerPopup.open();
        });
        dateTimePickerPopup.addDialogCloseActionListener(event -> dateTimePickerPopup.close());
    }

    private Dialog dateTimePickerPopup(EventType eventType){
        Dialog dateTimePickerPopup = new Dialog();
        dateTimePickerPopup.setModal(true);

        VerticalLayout layout = new VerticalLayout();
        layout.addClassNames("no-padding-layout");

        DateTimePicker dateTimePicker = new DateTimePicker("Välj dag och tid");
        dateTimePicker.setValue(LocalDateTime.now());
        dateTimePicker.setStep(Duration.ofMinutes(30));
        dateTimePicker.setLocale(Locale.UK); // to ensure 24h format
        layout.add(dateTimePicker);

        Button saveButton = new Button("Spara");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.addClassName("custom-margin-button");

        dateTimePickerPopup.add(dateTimePicker, saveButton);

        var amountField = new NumberField("Mängd (ml)");
        amountField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        if (eventType == EventType.MEAL) {
            layout.add(amountField);
        }

        saveButton.addClickListener(click -> {
            var event = BabylinEventService.createEventByType(eventType);
            event.setEventTime(dateTimePicker.getValue());
            if (amountField.getValue() != null)
                ((MealEvent) event).setAmount(amountField.getValue());
            repo.save(event);
            dateTimePickerPopup.close();
        });

        dateTimePickerPopup.add(layout);
        return dateTimePickerPopup;
    }

}
