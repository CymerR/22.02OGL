package ru.cmr.io.engine;

import ru.cmr.io.engine.utils.Render;
import ru.cmr.io.engine.utils.RenderFactory;

import java.util.ArrayList;

/**
 * That class represents our Physics Engine
 * which should do math for calculations
 * @author CymerR
 */
public class Engine{
    public ArrayList models;
    Render rend;

    public Engine() {
        rend = RenderFactory.createSimple(models);
    }

    public void render(){
        rend.render();
        System.out.println("engine render");
    }
}
