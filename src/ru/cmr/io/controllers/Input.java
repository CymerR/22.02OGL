package ru.cmr.io.controllers;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import ru.cmr.io.Window;

public  class Input {
    public static boolean[] KEY_CODE = new boolean[256*256]; // for keyboard input
    public static boolean[] BTN_CODE = new boolean[12];
    public static boolean isKeyPressed(int keyCode){
        return KEY_CODE[keyCode];
    }
    public static boolean isButtonPressed(int keyCode){
        return BTN_CODE[keyCode];
    }

    //todo: cursor input handling
}
