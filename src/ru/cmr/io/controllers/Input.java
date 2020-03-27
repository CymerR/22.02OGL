package ru.cmr.io.controllers;

public  class Input {
    public static boolean[] KEY_CODE = new boolean[256*64]; // for keyboard input
    public static boolean[] BTN_CODE = new boolean[12];
    public static boolean isKeyPressed(int keyCode){
        return KEY_CODE[keyCode];
    }
    public static boolean isButtonPressed(int keyCode){
        return BTN_CODE[keyCode];
    }

    //todo: cursor input handling
}
