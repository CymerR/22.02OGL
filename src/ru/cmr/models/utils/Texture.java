package ru.cmr.models.utils;

import java.sql.Struct;

public class Texture {
    /**
     * Id of a texture in GPU
     */
    private int id = 0;
    /**
     * Type of a texture
     * Could be:
     * <ul>
     *     <li>Specular</li>
     *     <li>Diffusion</li>
     * </ul>
     */
    private String type;
}
