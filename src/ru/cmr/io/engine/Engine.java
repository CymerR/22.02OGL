package ru.cmr.io.engine;

import ru.cmr.Shaders.Shader;
import ru.cmr.io.engine.utils.Render;
import ru.cmr.io.engine.utils.RenderFactory;
import ru.cmr.models.Model;

import java.util.ArrayList;

/**
 * That class represents our Physics Engine
 * which should do math for calculations
 * @author CymerR
 */
public class Engine{
    public ArrayList<Model> models;
    private Shader shaderProgram;
    Render rend;

    public Engine() {
        shaderProgram = new Shader();
        shaderProgram.setShaders("simple_vertex","simple_fragment");
        rend = RenderFactory.createSimple(StaticModel.getSimple());
    }

    public void render(){
        shaderProgram.bind();
        rend.render();
        shaderProgram.unbind();
//        System.out.println("engine render");
    }
}
