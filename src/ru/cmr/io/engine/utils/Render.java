package ru.cmr.io.engine.utils;

import ru.cmr.models.Model;

import java.util.ArrayList;

public abstract class Render {
     ArrayList<Model> models ;
     public abstract void render();
     Render(ArrayList<Model> models){
         this.models = models;
     }
}

/**
 * What similarity does have each renderer?
 * {
 *     list of models to render.(for future List of lights)
 *     render method
 *     i think that's all
 * }
 */