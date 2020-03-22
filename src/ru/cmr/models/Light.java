package ru.cmr.models;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL30.*;

/**
 * This class represents light
 */
public class Light {
    private Vector3f position;
    private Vector3f color;
    private float moveStep = 0.016f;

    public Light(Vector3f position, Vector3f color) {
        this.position = position;
        this.color = color;

        vao = glGenVertexArrays();
        int vbo = glGenBuffers();
        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER,vbo);
        glBufferData(GL_ARRAY_BUFFER,new float[]{0,0},GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER,0);
        glBindVertexArray(0);

        glDeleteBuffers(vbo);

    }

    public Vector3f getPosition() {
        return position;
    }

    public void move(Vector3f direction){
        this.position.add(direction.mul(moveStep));
    }

    int vao;
    public void render(){
        glBindVertexArray(vao);
        glDrawArrays(GL_POINT,0,1);
        glBindVertexArray(0);
    }

    public Matrix4f getTransfr(){
        return new Matrix4f().identity()
                .translate(position);
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }
}
