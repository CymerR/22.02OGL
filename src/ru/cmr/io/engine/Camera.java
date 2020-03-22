package ru.cmr.io.engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * This class represents a camera object.
 * It can move and rotate.
 * It is controlled by user.
 * @author CymerR
 */
public class Camera {
    //fields
    /**
     * a vector(x,y,z) that tells us where our camera is.
     */
    private Vector3f position   = null;
    /**
     * a vector (pitch,yaw,roll) that inform us how our camera is located relative to Oxyz axis;
     */
    private Vector3f rotation   = null;



    //constructors

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Camera() {
        this(new Vector3f(),new Vector3f());
    }
    //methods

    /**
     * That method "moves" our camera.
     * Just change the position vector.
     */
    public void move(Vector3f delta){
        if (delta.x != 0) {
            this.position.x += (float) -Math.sin(Math.toRadians(rotation.y - 90)) * delta.x;
            this.position.z += (float)  Math.cos(Math.toRadians(rotation.y - 90)) * delta.x;
        }
        if (delta.z!=0) {
            this.position.x += (float) -Math.sin(Math.toRadians(rotation.y)) * delta.z;
            this.position.z += (float)  Math.cos(Math.toRadians(rotation.y)) * delta.z;
        }
            this.position.y += delta.y;
    }

    /**
     * That method 'rotates' camera instance.
     */
    public void rotate(Vector3f angles){
        this.rotation.add(angles);
    }

    /**
     * This method create a view matrix and return it.
     * @see <a href="https://www.habr.com/en/post/324968/">Model View Projection</a>
     * @return a view Matrix4f that is used in shader.
     */
    public Matrix4f getView(){
        Matrix4f viewMatrix = new Matrix4f()
                .identity()
                .rotateX((float) Math.toRadians(rotation.x))
                .rotateY((float) Math.toRadians(rotation.y))
                .rotateZ((float) Math.toRadians(rotation.z))
                .translate(
                        -position.x,
                        -position.y,
                        -position.z);

        return viewMatrix;
    }

    public void SetPos(Vector3f poss){
        this.position.set(poss);
    }

}
