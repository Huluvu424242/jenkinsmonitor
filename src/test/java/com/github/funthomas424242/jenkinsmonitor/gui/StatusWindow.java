package com.github.funthomas424242.jenkinsmonitor.gui;

import java.awt.BorderLayout;

import javax.swing.*;

public class StatusWindow extends JWindow {

//    JScrollPane scrollpane;

    public StatusWindow() {
//        super("JScrollPane Demonstration");
//        setSize(300, 200);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        aktualisiereContentPane();
    }

    private JScrollPane createContent() {
        String categories[] = { "Household", "Office", "Extended Family",
            "Company (US)", "Company (World)", "Team", "Will",
            "Birthday Card List", "High School", "Country", "Continent",
            "Planet" };
        JList list = new JList(categories);
        return new JScrollPane(list);
    }

    public void aktualisiereContentPane(){
        getContentPane().add( createContent(), BorderLayout.CENTER);
    }

    public static void main(String args[]) {
        StatusWindow window = new StatusWindow();
        window.pack();
        window.setAlwaysOnTop(true);
        window.setLocationByPlatform(false);
        window.setVisible(true);

    }
}
