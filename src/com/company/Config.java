package com.company;

import java.awt.*;

public class Config {
    public static final int SIZE =20;
    public static final int WIDTH = 40;
    public static final int HEIGHT = 30;
    public static int SLEEPMS = 400;
    public static Color getColor (Status status){
        switch (status) {
            default:
            case NONE: return Color.WHITE;
            //case BORN: return Color.GRAY;
            case BORN: return Color.WHITE;
            case LIVE: return Color.BLACK;
            //case LIVE: return new Color((int)(Math.random() * 0x1000000));
            //case DIED: return Color.GRAY;
            case DIED: return Color.WHITE;

        }
    }
}
