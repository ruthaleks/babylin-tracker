package com.example.application.views;

import com.example.application.BabylinTracker;
import com.example.application.BabylinTrackerRepo;
import com.example.application.EventType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.time.LocalDateTime;


@Route(value = "/log", layout = MainLayout.class)
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
        button.addClickListener(click -> {
            repo.save(new BabylinTracker(eventType, LocalDateTime.now()));
            getUI().ifPresent((ui -> ui.navigate("/history")));

        });
    }

}
