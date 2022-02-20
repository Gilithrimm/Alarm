package com.safenar.ui;

import com.safenar.core.AlarmBuilder;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class EditFrame extends JFrame {
    JPanel p;
    private AlarmComp comp;
    JSpinner hr =new JSpinner(new SpinnerNumberModel(LocalTime.now().getHour(),0,23,1));
    JSpinner min =new JSpinner(new SpinnerNumberModel(LocalTime.now().getMinute(),0,59,1));
    JTextField label=new JTextField("Alarm");
    JButton save=new JButton("Save");
    public boolean ready;
    public EditFrame(String title) throws HeadlessException {
        super(title);
//        setSize(100,100);
        p=new JPanel();
        p.setPreferredSize(new Dimension(100,100));
        setContentPane(p);
        p.add(hr);
        p.add(min);
        ((JLabel) add(new JLabel("Label: "))).setLabelFor(label);
        p.add(label);
        save.addActionListener(e-> {
            System.out.println(comp = new AlarmBuilder()
                    .init()
                    .time(((int) hr.getValue()),((int) min.getValue()))
                    .label(label.getText())
                    .build().toComponent(new Dimension(0,100)));
            ready=true;
        });
        p.add(save);
        pack();
    }

    public AlarmComp getComp() {
        return comp;
    }
}
