package com.example.application.views;

import com.example.application.BabylinTracker;
import com.example.application.BabylinTrackerRepo;
import com.example.application.EventType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;


@Route(value = "/log", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)

public class BabylinTrackerView extends VerticalLayout {

    private BabylinTrackerRepo repo;
    public BabylinTrackerView(BabylinTrackerRepo repo) {
        this.repo = repo;
        var sleepButton = new Button("Somnade");
        var wakeUpButton = new Button("Vaknade");
        var eatButton = new Button("Åt");

        addButtonClickListener(sleepButton, EventType.SLEEP);
        addButtonClickListener(wakeUpButton, EventType.WAKEUP);
        addButtonClickListener(eatButton, EventType.EAT);

        add(
                sleepButton,
                wakeUpButton,
                eatButton
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

        DateTimePicker dateTimePicker = new DateTimePicker("Välj dag och tid");
        dateTimePicker.setValue(LocalDateTime.now());
        dateTimePicker.setStep(Duration.ofMinutes(30));
        dateTimePicker.setLocale(Locale.UK); // to ensure 24h format


        Button saveButton = new Button("Spara");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.addClassName("custom-margin-button");

        saveButton.addClickListener(click -> {
            repo.save(new BabylinTracker(eventType, dateTimePicker.getValue()));
            dateTimePickerPopup.close();
        });

        dateTimePickerPopup.add(dateTimePicker, saveButton);
        return dateTimePickerPopup;
    }

}
