package ru.cmr.io.engine.utils;

import ru.cmr.models.Model;

import java.util.ArrayList;

public class RenderFactory {
    public static Render createSimple(ArrayList<Model> models){
        return new Render(models) {
            @Override
            public void render() {
                for (Model model:models) {
                    //do rendering
                    model.render();
                }
//                System.out.println("Renderer workS");
            }
        };
    }
}
