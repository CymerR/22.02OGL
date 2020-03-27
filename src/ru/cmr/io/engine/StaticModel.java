package ru.cmr.io.engine;

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
                1,1,1,
                1,1,0,
                1,0,1
        };

        int[] ind = {
                0,1,2
        };

        Model model = Model.getModel(vert,col,ind);
        curr.add(model);
        return curr;
    }
}
