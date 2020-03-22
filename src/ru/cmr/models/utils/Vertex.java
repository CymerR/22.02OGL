package ru.cmr.models.utils;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Vertex {
    @Override
    public String toString() {
        return
                "{  [" + coordinates.x + " : " + coordinates.y + " : " + coordinates.z + "] "+
                " [ " + colors.x + " : " + colors.y + " : " + colors.z + "] }";
    }

    public Vector3f getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Vector3f coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Vertex (x,y,z)       - position
     *        (nx,ny,nz)    - normal
     *        (u,v)         - texture
     */

    public Vector3f coordinates  = null;
    public Vector3f colors       = null;
    public Vector3f normals      = null;
    public Vector2f tCoords      = null;

    public Vertex(Vector3f coordinates, Vector3f normals, Vector2f tCoords) {
        this.coordinates = coordinates;
        this.normals = normals;
        this.tCoords = tCoords;
        colors = new Vector3f();
    }

    public Vector3f getColors() {
        return colors;
    }

    public void setColors(Vector3f colors) {
        this.colors = colors;
    }

    public Vertex(Vector3f coordinates) {
        this.coordinates = coordinates;
    }

    public Vertex(float x, float y, float z){
        this(new Vector3f(x,y,z));
    }

    public Vertex(){
        this(new Vector3f());
    }
}
