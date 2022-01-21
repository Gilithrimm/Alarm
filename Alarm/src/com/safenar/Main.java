package com.safenar;

import com.safenar.ui.MainFrame;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        EventQueue.invokeLater(() -> {
            try {
                new MainFrame("Alarm");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        });
    }

    public static void println(Object msg){
        System.out.println(msg);
    }

}
