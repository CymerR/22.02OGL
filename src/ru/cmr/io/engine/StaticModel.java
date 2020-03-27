package ru.cmr.io.engine;

import org.joml.Vector3f;
import ru.cmr.models.Model;

import java.util.ArrayList;

public class StaticModel {
    public static ArrayList<Model> getSimple() {
        ArrayList<Model> curr = new ArrayList<>();

        float[] vert = {
                0.5f,0.5f,0,
                -.5f,0.5f,0,
                0,0,0,
        };
        float[] col = {
                0,1,1,
                1,1,0,
                1,0,1
        };

        int[] ind = {
                0,1,2
        };

        Model model = Model.getModel(vert,col,ind);
        model.setProp(new Vector3f(0,0,-1), new Vector3f(0,0,0), new Vector3f(1,1,1));
        curr.add(model);
        return curr;
    }
}
