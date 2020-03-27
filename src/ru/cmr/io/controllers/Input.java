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
    /**
     *  Изменения положения курсора в окне.
     *  Будут переопределяться каждый вызов
     */
    public static float delta_x, delta_y;
    /**
     * Предыдущие значения положения курсора
     */
    public static float prev_x, prev_y;

    public static float[] getCursorMoving(){
           return new float[]{delta_x, delta_y};
    }
    public static void updateCursor(float curr_x,float curr_y){
        delta_x = curr_x - prev_x;
        delta_y = curr_y - prev_y;
        prev_x = curr_x;
        prev_y = curr_y;
    }
}
