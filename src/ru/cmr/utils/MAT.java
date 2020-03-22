package ru.cmr.utils;


import org.joml.Matrix4f;

/**
 * This class responsible for all Math features.
 * @author CymerR
 */
public class MAT {
    /**
     *
     * @param FOV - Field of View angle.
     * @param aspect - ratio between Width and Height of the screen.
     * @return
     */
    public static Matrix4f getPerspective(float FOV, float aspect){
        Matrix4f result = new Matrix4f().identity();
        result.perspective(
                FOV,
                aspect,
                0.001f,
                Float.POSITIVE_INFINITY
        );
        return result;
    }
}
