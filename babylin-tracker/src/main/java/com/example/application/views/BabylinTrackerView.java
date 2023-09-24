package com.example.application.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class BabylinTrackerView extends VerticalLayout {
    public BabylinTrackerView() {
        add(new H1("Hello, world"));
    }
}
