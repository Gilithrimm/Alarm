package com.safenar.ui;

import com.safenar.core.Alarm;

import java.io.File;

//responsibility: build alarms (Alarm objects OR AlarmComp objects)
/**
 * A builder class for {@link Alarm} components. Also, my take on
 * <a href="https://refactoring.guru/design-patterns/builder">Builder</a> design pattern.
 * @see AlarmComp
 * @see Alarm
 * */
public class AlarmBuilder {
    /**
     * All fields present in {@link Alarm} class.
     * */
    private int hour;
    private int minute;
    private String label;
    private File sound;

    /**
     * Resets all fields to the default state.
     * @return this builder for chaining methods
     * */
    public AlarmBuilder init(){
        this.hour = 0;
        this.minute = 0;
        this.label = "Alarm";
        this.sound=new File("resources/sounds/default.wav");
        return this;
    }

    /**
     * Sets the time of alarm.
     * @param hour the hour
     * @param minute the minute
     * @return this builder for chaining methods
     * */
    public AlarmBuilder time(int hour, int minute){
        this.hour=hour;
        this.minute=minute;
        return this;
    }

    /**
     * Sets the label of alarm.
     * @param label label used as custom message
     * @return this builder for chaining methods
     * */
    public AlarmBuilder label(String label){
        this.label=label;
        return this;
    }

    /**
     * Sets the alarm sound.
     * @param sound the sound played when the alarm goes off
     * @return this builder for chaining methods
     * */
    public AlarmBuilder sound(File sound){
        this.sound=sound;
        return this;
    }

    /**
     * Builds the {@link Alarm} object from values in this object.
     * @return new Alarm object
     * */
    public Alarm build(){
        return new Alarm(hour, minute,label,sound);
    }
}
