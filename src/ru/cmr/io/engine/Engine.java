package ru.cmr.io.engine;

import org.joml.Vector3f;
import org.lwjgl.nuklear.NkContext;
import ru.cmr.Shaders.Shader;
import ru.cmr.io.Window;
import ru.cmr.io.controllers.Input;
import ru.cmr.io.engine.utils.Render;
import ru.cmr.io.engine.utils.RenderFactory;
import ru.cmr.models.Model;
import ru.cmr.utils.MAT;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;


/**
 * That class represents our Physics Engine
 * which should do math for calculations
 * @author CymerR
 */
public class Engine{
    public ArrayList<Model> models;
    public static final Camera cameraObject = new Camera(new Vector3f(0,0,0),new Vector3f());
    public static Shader shaderProgram;
    public float sensitivity = 0.1f;
    Render rend;

    public Engine() {
        shaderProgram = new Shader();
        shaderProgram.setShaders("simple_vertex","simple_fragment");
        shaderProgram.bind();
        shaderProgram.sendUniformMat4("projection", MAT.getPerspective(45, Window.ASPECT));
        shaderProgram.unbind();
        rend = RenderFactory.createSimple(StaticModel.getSimple());
    }

    public void render(){
        shaderProgram.bind();
        shaderProgram.sendUniformMat4("view", cameraObject.getView());
        rend.render(shaderProgram);
        shaderProgram.unbind();

        //todo: GUI rendering
        GUIRendering();

        pollEvents();
    }

    private void pollEvents() {
        if (Input.isKeyPressed(GLFW_KEY_W)) cameraObject.move(new Vector3f(0,0,-.01f));
        if (Input.isKeyPressed(GLFW_KEY_S)) cameraObject.move(new Vector3f(0,0,.01f));
        if (Input.isKeyPressed(GLFW_KEY_A)) cameraObject.move(new Vector3f(-.01f,0,0));
        if (Input.isKeyPressed(GLFW_KEY_D)) cameraObject.move(new Vector3f(.01f,0,0));
        if (Input.isKeyPressed(GLFW_KEY_D)) cameraObject.move(new Vector3f(.01f,0,0));
        if (Input.isKeyPressed(GLFW_KEY_SPACE)) cameraObject.move(new Vector3f(0,0.01f,0));
        if (Input.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) cameraObject.move(new Vector3f(0,-.01f,0));


        if(Input.isButtonPressed(GLFW_MOUSE_BUTTON_LEFT)) cameraObject.rotate(new Vector3f(Input.getCursorMoving()[1] * sensitivity,Input.getCursorMoving()[0] * sensitivity,0));
    }


    private void GUIRendering(){
        //do rendering staff
        NkContext ctx = NkContext.create();
    }
}
