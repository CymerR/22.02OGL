package ru.cmr.models;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.lwjgl.system.CallbackI;
import org.lwjgl.system.MemoryUtil;
import ru.cmr.models.utils.Texture;
import ru.cmr.models.utils.Vertex;
import ru.cmr.utils.FileLoader;
import ru.cmr.utils.VertexLoader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;

/**
 * This class represents a mesh of a model.
 * Each model can have many meshes for: vertices, normals, some other values.
 * @author CymerR
 */
public class Mesh {
    /**
     *  OpenGL utils
     */
    private ArrayList<Vertex> vertices  = null;
    private int verticesCount = 0;
    private int[] indices;


    /**
     *  World utils..
     */

    private Vector3f translation, rotation;
    private Vector3f velocity, acceleration;
    private float mass = 20f;
    private Vector3f force;
    //Constructor

    public Mesh() {
        vertices = new ArrayList<>();



        init();
    }

    /**
     *
     * @param poses - array of coordinates (@perVertex)
     * @param colors - array of color data (@perVertex)
     * @param indices - indices is clear
     */
    public Mesh(float[] poses, float[] colors,int[] indices){
        vertices = VertexLoader.loadVertices(poses);
        this.indices = indices;
        init();
    }
    public Mesh(float[] poses,int[] indices){
        vertices = VertexLoader.loadVertices(poses);
        this.indices = indices;
        init();
    }
    public Mesh(ArrayList vertices,int[] indices){
        this.vertices = vertices;
        this.indices = indices;
        init();
    }

    //init method
    void init(){
        force = new Vector3f(0,-9.8f*mass/1000f,0);
        velocity = new Vector3f();
        acceleration = new Vector3f();
        translation = new Vector3f();
        rotation = new Vector3f();

        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        int colorBO = glGenBuffers();
        ebo = glGenBuffers();//doesnt work!!


        //FloatBuffers
        FloatBuffer VertCoordData = MemoryUtil.memAllocFloat(vertices.size()*3);
        FloatBuffer colorBuffer   = MemoryUtil.memAllocFloat(vertices.size()*3);
        for (Vertex current:vertices) {
            VertCoordData.put(current.coordinates.x);
            VertCoordData.put(current.coordinates.y);
            VertCoordData.put(current.coordinates.z);

            colorBuffer.put(current.colors.x);
            colorBuffer.put(current.colors.y);
            colorBuffer.put(current.colors.z);

        }
        VertCoordData.flip();
        colorBuffer.flip();



        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER,vbo);
        glBufferData(GL_ARRAY_BUFFER,VertCoordData,GL_STATIC_DRAW);
        glVertexAttribPointer(0,3,GL_FLOAT,false,0,0);

        glBindBuffer(GL_ARRAY_BUFFER,colorBO);
        glBufferData(GL_ARRAY_BUFFER,colorBuffer,GL_STATIC_DRAW);
        glVertexAttribPointer(1,3,GL_FLOAT,false,0,0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,indices,GL_STATIC_DRAW);

        glBindVertexArray(0);
    }

    private int vao,ebo,vbo;

    public void draw(){
        glBindVertexArray(vao);
        for(int i =0;i<2;i++) glEnableVertexAttribArray(i);
        glDrawElements(GL_TRIANGLES,verticesCount,GL_UNSIGNED_INT,0);
        for(int i =0;i<2;i++) glDisableVertexAttribArray(i);
        glBindVertexArray(0);
    }

    public Matrix4f getTransformation(){
        return new Matrix4f()
                .identity()
                .translate(this.translation)
                .rotateX(rotation.x)
                .rotateY(rotation.y)
                .rotateZ(rotation.z)
                ;

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Vertex vert : vertices){
            stringBuilder.append(vert + "\n");
        }
        return stringBuilder.toString();
    }

    public void increase(Vector3f translation, Vector3f rotation) {
        this.translation.add(translation);
        this.rotation.add(rotation);
    }

    public void setPosition(float x, float y, float z) {
        this.translation.set(x,y,z);
    }

    public void processing() {
        collision();
        this.translation.add(this.velocity);
        this.velocity.add(this.acceleration);
        acceleration.x = force.x / mass;
        acceleration.y = force.y / mass;
        acceleration.z = force.z / mass;
        force.add(0, -9.8f*mass/1000f,0);
    }
    private void collision(){
        if (this.translation.y <= 0){
            this.force.add(
                   -force.x*mass*9.8f,
                   -force.y*mass*9.8f,
                   -force.z*mass*9.8f
                    );
        }
    }
}
