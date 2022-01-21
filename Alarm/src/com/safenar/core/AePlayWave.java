package com.safenar.core;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AePlayWave extends Thread {
    private final String filename;
    private final Position curPosition;

    enum Position {
        LEFT, RIGHT, NORMAL
    }

    public AePlayWave(String wavFile) {
        this(wavFile,Position.NORMAL);
    }

    public AePlayWave(String wavFile, Position p) {
        filename = wavFile;
        curPosition = p;
    }

    public void run() {

        File soundFile = new File(filename);
        if (!soundFile.exists()) {
            System.err.println("Wave file not found: " + filename);
            return;
        }

        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException | IOException e1) {
            e1.printStackTrace();
            return;
        }

        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine sourceLine;
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (sourceLine.isControlSupported(FloatControl.Type.PAN)) {
            FloatControl pan = (FloatControl) sourceLine
                    .getControl(FloatControl.Type.PAN);
            if (curPosition == Position.RIGHT)
                pan.setValue(1.0f);
            else if (curPosition == Position.LEFT)
                pan.setValue(-1.0f);
        }

        sourceLine.start();
        int nBytesRead = 0;
        // 128Kb
        int EXTERNAL_BUFFER_SIZE = 524288;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0)
                    sourceLine.write(abData, 0, nBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sourceLine.drain();
            sourceLine.close();
        }

    }
}
