package ru.cmr.io.engine.utils;

import org.joml.Vector3f;
import ru.cmr.Shaders.Shader;
import ru.cmr.models.Model;

import java.util.ArrayList;

public class RenderFactory {
    public static Render createSimple(ArrayList<Model> models){
        return new Render(models) {
            @Override
            public void render(Shader shaderProgram) {
                for (Model model:models) {
                    //do rendering
                    shaderProgram.sendUniformMat4("transformation",model.getWorld());
                    model.render();
                    model.testRotate(new Vector3f(0,0.011f,-0.001f));
                }
            }
        };
    }
}
