package ru.cmr.utils;

import org.joml.Vector3f;
import ru.cmr.models.utils.Vertex;

import java.util.ArrayList;

public class VertexLoader {
    public static ArrayList<Vertex> loadVertices(float[] coordinates){
        /**
         * #Create a list and fulfill it with triads of data in input array
         */
//        if (coordinates.length != colors.length) {
//            System.err.println("There is some data issue! [VERTEXLOADER]");
//        }
        ArrayList<Vertex> vertices = new ArrayList<>();
        for (int i= 0;i<coordinates.length;i+=3){
            Vertex current = new Vertex(coordinates[i],coordinates[i+1],coordinates[i+2]);
//            current.setColors(new Vector3f(colors[i],colors[i+1],colors[i+2]));
            vertices.add(current);
        }
        return vertices;
    }
}
