package com.safenar.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
//import java.util.Comparator;

public class MainPanel extends JPanel{
    JButton addAlarm =new JButton("Add");
    JButton config=new JButton("Config");
    GridBagLayout layout=new GridBagLayout();
    GridBagConstraints constraints = new GridBagConstraints();
    ArrayList<AlarmComp> alarmComps=new ArrayList<>();
    Dimension dim;
    public MainPanel(Dimension d) {
        super();
        dim=d;
        setLayout(layout);
        setSize(dim.width,50);
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        addAlarm.setSize(dim.width/4,50);
        config.setSize(dim.width/4,50);

        constraints.gridheight=getHeight();
        constraints.gridwidth=addAlarm.getWidth();

        constraints.anchor=GridBagConstraints.SOUTHWEST;
        layout.setConstraints(addAlarm, constraints);
        add(addAlarm);
        addAlarm.addActionListener(e -> {
            if (e.getSource().equals(addAlarm)) SwingUtilities.invokeLater(() -> {
                EditFrame frame = new EditFrame("Create new alarm...");
                frame.setVisible(true);
                if (frame.ready) alarmComps.add(frame.getComp());
                alarms();
            });
        });

        constraints.anchor=GridBagConstraints.SOUTHEAST;
        layout.setConstraints(config, constraints);
        add(config);
    }

    private void alarms(){
//        alarmComps.sort(Comparator.comparing(o -> o.alarm));
        for (AlarmComp alarmComp : alarmComps) {
            if (alarmComp != null) {
                constraints.gridwidth = GridBagConstraints.REMAINDER;
                constraints.gridheight = 100;
                layout.setConstraints(alarmComp, constraints);
                add(alarmComp);
            }
        }
    }
}
