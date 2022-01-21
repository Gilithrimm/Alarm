package com.safenar.core;

import com.safenar.ui.AlarmComp;

import java.awt.*;
import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

//responsibility: app logic
public class Alarm implements Comparable<Alarm>{
    private String label;
    private File sound;
    private LocalTime time;
    private boolean on;
    private DayOfWeek[] repeat;
    private static final LocalDate TODAY = LocalDate.now();

    public Alarm(String label, File sound, LocalTime time, DayOfWeek... repeat) {
        this.label = label;
        this.sound = sound;
        this.time = time;
        this.repeat = repeat;
        on=true;
    }

    public Alarm(int hour, int minute, String label, File sound) {
        this(label,sound,LocalTime.of(hour,minute),TODAY.getDayOfWeek());
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public File getSound() {
        return sound;
    }

    public void setSound(File sound) {
        this.sound = sound;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn() {
        on=!on;
    }

    public static LocalDate getToday() {
        return TODAY;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public DayOfWeek[] getRepeat() {
        return repeat;
    }

    public void setRepeat(DayOfWeek[] repeat) {
        this.repeat = repeat;
    }

    public DayOfWeek[] addToRepeat(DayOfWeek date){
        var repeatList= Arrays.asList(repeat);
        repeatList.add(date);
        return repeatList.toArray(new DayOfWeek[]{});
    }

    public AlarmComp toComponent(Dimension d){
        return new AlarmComp(this,d);
    }

    public void playAlarm(){
        if (on&&isTime(LocalTime.now())) new AePlayWave(sound.getAbsolutePath()).start();
    }

    private boolean isTime(LocalTime now) {
        return time.compareTo(now)<=0;
    }

    @Override
    public int compareTo(Alarm alarm) {
        return getTime().compareTo(alarm.getTime());
    }


}
