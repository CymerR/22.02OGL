package ru.cmr.io;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import ru.cmr.Shaders.Shader;
import ru.cmr.io.engine.Camera;
import ru.cmr.models.Light;
import ru.cmr.models.Mesh;
import ru.cmr.utils.MAT;

import java.util.ArrayList;
import java.util.List;

public class MeshRenderer{
    Camera camera;
    Shader shader;
    List<Light> light;
    List<Mesh> meshes;

    public MeshRenderer(ArrayList<Mesh> mesh, Camera cam, Shader shader) {
        this.meshes = mesh;
        camera = cam;
        init();
    }

    private void init(){


        /**************/
        shader.bind();
        shader.sendUniformMat4("projection", MAT.getPerspective(
                (float)Math.toRadians(45),
                Window.ASPECT
        ));
        shader.unbind();

        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK,GL11.GL_QUADS);
        GL11.glEnable(GL11.GL_DEPTH_TEST);


    }

    public void render(){
        prepare();
        shader.bind();
        shader.sendUniformMat4("view",camera.getView());
        for (Mesh meshv: meshes) {
        shader.sendUniformMat4("transform", meshv.getTransformation());
        meshv.draw();
    }
        shader.unbind();
    }

    private void prepare() {
        GL11.glClearColor(0.56f,0.5f,0.5f,0);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
    }

}
