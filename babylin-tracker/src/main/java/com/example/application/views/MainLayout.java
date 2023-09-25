package com.example.application.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class MainLayout extends AppLayout {

    public MainLayout() {
        setPrimarySection(Section.DRAWER);

        H1 appName = new H1("Babylin Tracker \uD83D\uDC76");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        Header header = new Header(appName);
        Scroller scroller = new Scroller(createNavigationMenu());
        DrawerToggle toggle = new DrawerToggle();

        addToDrawer(header, scroller);
        addToNavbar(true, toggle);


    }

    private SideNav createNavigationMenu() {
        SideNav nav = new SideNav();
        nav.addItem(new SideNavItem("Logga", BabylinTrackerView.class, LineAwesomeIcon.CLOCK_SOLID.create()));
        nav.addItem(new SideNavItem("Överblick", HistoryView.class, LineAwesomeIcon.CALENDAR_ALT_SOLID.create()));
        return nav;
    }

}

