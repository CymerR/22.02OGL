package ru.cmr.io.engine.utils;

import java.util.ArrayList;

public abstract class Render {
     ArrayList models ;
     public abstract void render();
     Render(ArrayList models){
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