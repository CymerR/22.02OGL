package ru.cmr.models;

import org.joml.Matrix4f;
import org.joml.Vector3f;

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
    /**
     * VAO - Vertex Array Object
     * Масссив атрибутов модели
     * Хранится в памяти ГПУ
     */
    int vao;
    /**
     * Индексный буфер
     */
    int ebo;
    /**
     * Количество вершин для отрисовки
     */
    int iCount;
    /**
     *  Список идентификаторов всех буферов созданных на ГПУ
     */
    ArrayList<Integer> buffers;
    /**
     * Список аттрибутов модели(индексов массива атрибутов VAO)
     */
    ArrayList<Integer> indicesOfAttributes;
    /**
     * Мировая позиция модели
     */
    private Vector3f position;
    /**
     * Мировой поворот
     */
    private Vector3f rotation;
    /**
     * размер модели
     */
    private Vector3f scale;
    private Model(){
        vao = glGenVertexArrays();
        //
        buffers = new ArrayList<>();
        indicesOfAttributes = new ArrayList<>();

        position = new Vector3f();
        rotation = new Vector3f();
        scale = new Vector3f();
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
        indicesOfAttributes.add(index);
        buffers.add(vbo);
        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER,data,GL_STATIC_DRAW);
        glVertexAttribPointer(index,dimension,GL_FLOAT,false,0,0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
    void addElements(int[] data){
        ebo = glGenBuffers();
        iCount = data.length;
        buffers.add(ebo);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,data,GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
    }

    /**
     * Отрисовывает данные, которые присущи модели, на экране
     */
    public void render(){
        glBindVertexArray(vao);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        for(int index: indicesOfAttributes) glEnableVertexAttribArray(index);
        glDrawElements(GL_TRIANGLES,iCount,GL_UNSIGNED_INT,0);
        for(int index: indicesOfAttributes) glDisableVertexAttribArray(index);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void setProp(Vector3f pos,Vector3f rot,Vector3f scale){
        this.position = pos;
        this.rotation = rot;
        this.scale = scale;
    }

    public Matrix4f getWorld(){
        return new Matrix4f()
                .identity()
                .translate(position)
                .rotateX(rotation.x)
                .rotateY(rotation.y)
                .rotateZ(rotation.z)
                ;
    }

    public void clean(){
        for (int buffer: buffers) // for each buffer in memory we clean space
            glDeleteBuffers(buffer);
        glDeleteVertexArrays(vao);
    }

    public void testRotate(Vector3f vector3f) {
        this.rotation.add(vector3f);
    }
}
