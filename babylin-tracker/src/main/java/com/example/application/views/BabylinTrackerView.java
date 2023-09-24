package com.example.application.views;

import com.example.application.BabylinTracker;
import com.example.application.BabylinTrackerRepo;
import com.example.application.EventType;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;


@Route("")
public class BabylinTrackerView extends VerticalLayout {

    private BabylinTrackerRepo repo;
    public BabylinTrackerView(BabylinTrackerRepo repo) {
        this.repo = repo;
        var sleepButton = new Button("Somnade");
        var wakeUpButton = new Button("Vaknade");
        var eatButton = new Button("Åt");
        var history = new VerticalLayout();

        addButtonClickListener(sleepButton, EventType.SLEEP, history);
        addButtonClickListener(wakeUpButton, EventType.WAKEUP, history);
        addButtonClickListener(eatButton, EventType.EAT, history);

        repo.findAll().forEach(record -> history.add(createHistoryField(record)));

        add(
                sleepButton,
                wakeUpButton,
                eatButton,
                history
        );
    }

    private void addButtonClickListener(Button button, EventType eventType, VerticalLayout history){
        button.addClickListener(click -> {
            var record = repo.save(new BabylinTracker(eventType, LocalDateTime.now()));
            history.add(createHistoryField(record));
        });
    }
    private Component createHistoryField(BabylinTracker record) {
        return new Paragraph(record.toString());
    }


}
