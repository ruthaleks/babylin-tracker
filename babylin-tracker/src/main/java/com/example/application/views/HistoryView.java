package com.example.application.views;

import com.example.application.BabylinTracker;
import com.example.application.BabylinTrackerRepo;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/history")
public class HistoryView extends VerticalLayout {
    private BabylinTrackerRepo repo;
    public HistoryView(BabylinTrackerRepo repo) {
        this.repo = repo;
        var history = new VerticalLayout();
        var button = new Button("Tillbaka");

        button.addClickListener(click -> button.getUI().ifPresent(ui -> ui.navigate("")));

        repo.findAll().forEach(record -> history.add(createHistoryField(record)));
        add(
                button,
                history
        );
    }
    public static Component createHistoryField(BabylinTracker record) {
        return new Paragraph(record.toString());
    }


}
