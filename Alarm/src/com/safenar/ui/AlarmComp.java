package com.safenar.ui;

import com.safenar.core.Alarm;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

//responsibility: display Alarm objects
public class AlarmComp extends JComponent {
    Alarm alarm;
    Label time;
    Label alarmLabel;
    Label repeat;

    public AlarmComp(Alarm alarm, Dimension d) {
        super();
        this.alarm=alarm;
        repeat=new Label();
        setSize(d.width,100);
        setBorder(BorderFactory.createLineBorder(Color.RED));
        setup();
        add(time);

    }

    void setup() {
        time = new Label(alarm.getTime().toString());
        alarmLabel=new Label(alarm.getLabel());
//        repeat=new Label(Alarm.Repeat.toShortString(alarm.getRepeat()));
    }

    @Override
    public String toString() {
        return "AlarmComp{" +
                "alarm=" + alarm +
                ", time=" + time.getText() +
                ", alarmLabel=" + alarmLabel.getText() +
//                ", repeat=" + repeat +
                '}';
    }
}
