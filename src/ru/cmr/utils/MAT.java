package ru.cmr.utils;


import org.joml.Matrix4f;

/**
 * This class responsible for all Math features.
 * @author CymerR
 */
public class MAT {
    /**
     *
     * @param FOV - Field of View angle. in degrees
     * @param aspect - ratio between Width and Height of the screen.
     * @return
     */
    public static Matrix4f getPerspective(float FOV, float aspect){
        Matrix4f result = new Matrix4f().identity();
        result.perspective(
                (float)Math.toRadians(FOV),
                aspect,
                0.01f,
                1000f
        );
        return result;
    }
}
