package com.example.application.views;

import com.example.application.BabylinTracker;
import com.example.application.BabylinTrackerRepo;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "/history", layout = MainLayout.class)
public class HistoryView extends VerticalLayout {
    private BabylinTrackerRepo repo;
    public HistoryView(BabylinTrackerRepo repo) {
        this.repo = repo;
        var history = new VerticalLayout();
        repo.findAll().forEach(record -> history.add(createHistoryField(record)));
        add(
                history
        );
    }
    public static Component createHistoryField(BabylinTracker record) {
        return new Paragraph(record.toString());
    }


}
