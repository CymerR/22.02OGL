package ru.cmr.io.engine.utils;

import java.util.ArrayList;

public class RenderFactory {
    public static Render createSimple(ArrayList models){
        Render r = new Render(models) {
            @Override
            public void render() {
//                for (model:models) {
//                    //do rendering
//                }
                System.out.println("Renderer workS");
            }
        };
        return r;
    }
}
