package ru.cmr.models;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL30.*;

/**
 * Альтернатива для Mesh
 * @author CymerR
 */
public class Model {
    /**
     * Имеет массив атрибутов и список буферов в памяти ГПУ
     * Так же создается с помощью списка вершин
     * todo: поддержка текстур, через встроенную stdlib
     */
    int vao;
    ArrayList<Integer> buffers;
    private Model(){
        vao = glGenVertexArrays();
        //
        buffers = new ArrayList<>();
    }

    /**
     * Работает как фабрика моделей
     * @return model instance
     */
    static public Model getModel(float[] vertices, float[] colors, int[] indices){
        Model current = new Model();
        current.addAttribute(0,vertices, 3);
        current.addAttribute(1,  colors, 3);
        current.addElements(indices);

        return current;
    }

    void addAttribute(int index, float[] data, int dimension){
        int vbo = glGenBuffers();
        buffers.add(vbo);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER,data,GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
    void addElements(int[] data){
        int ebo = glGenBuffers();
        buffers.add(ebo);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,data,GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
    }

    public void clean(){
        for (int buffer: buffers) // for each buffer in memory we clean space
            glDeleteBuffers(buffer);
        glDeleteVertexArrays(vao);
    }
}
