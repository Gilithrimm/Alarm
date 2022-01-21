package com.safenar.ui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainFrame extends JFrame {
    public MainFrame(String title) throws Exception {
        super(title);
        setSize(300,400);
        MainPanel panel= new MainPanel(getSize());
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printList(panel.alarmComps);
            }

            private void printList(List<AlarmComp> alarmComps) {
                System.out.println("START LIST");
                for (AlarmComp comp : alarmComps) System.out.println(comp);
                System.out.println("END LIST");
            }
        });
    }
}
