package ru.cmr.io;


import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import ru.cmr.io.controllers.Input;
import ru.cmr.io.engine.Engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * This is window.
 * it is static mean it has no instances.
 * @author CymerR
 */
public class  Window{

    static class MThread extends Thread{
        @Override
        public void run() {
            Window.render();
        }
    }

    /**
     *  a long pointer to a current window made by GLFW lib.
     */
    private static long windowHandle;
    private static Thread thRenderThread;
    public static int WIDTH = 620, HEIGHT = 480;
    public static float ASPECT = (float) WIDTH/HEIGHT;

    private static Engine engine;


    /**
     * Initializes our GLFW lib and others...
     */
    public static void init(){
        GLFWErrorCallback.createPrint(System.err).set();
        if(!glfwInit()) throw new RuntimeException("GLFW Couldn't init");


        //set up OpenGL 4.0 version
        glfwDefaultWindowHints();
//        glfwWindowHint(GLFW_VERSION_MAJOR,4);
//        glfwWindowHint(GLFW_VERSION_MINOR,0);

        windowHandle = glfwCreateWindow(WIDTH,HEIGHT,"Sector Clear",0,0);

        //todo: Input handling functions will be here.
        glfwSetKeyCallback(windowHandle,(window, key, scancode, action, mods)->{
            if (action == GLFW_PRESS)   Input.KEY_CODE[key] = true;
            if (action == GLFW_RELEASE) Input.KEY_CODE[key] = false;
        });

        glfwSetMouseButtonCallback(windowHandle,(window,key,action,mods)->{
            if (action == GLFW_PRESS)   Input.BTN_CODE[key] = true;
            if (action == GLFW_RELEASE) Input.BTN_CODE[key] = false;
        });

        glfwSetWindowSizeCallback(windowHandle, (window, xwidth, yheight)->{
            glViewport(0,0,xwidth,yheight); //Это меняет размеры OpenGL контекста в зав. от изменения размера окна
        });

        glfwSetCursorPosCallback(windowHandle, (window, xpos,ypos)->{
            Input.updateCursor((float)xpos,(float)ypos);
        });

        //*****//
        makeCurrent();
        engine = new Engine();
    }

    public static void makeCurrent(){
        glfwMakeContextCurrent(windowHandle);
        glfwSwapInterval(1);
        GL.createCapabilities();
    }
    public static void render(){

        glClearColor(0,0,0,0);

        while(!glfwWindowShouldClose(windowHandle)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            engine.render();

            glfwSetWindowTitle(windowHandle, "Sector Clear  " + get_fps());
            glfwSwapBuffers(windowHandle);
            glfwPollEvents();
        }
        destroy();
    }


    public static void destroy(){
        glfwTerminate();
    }

    public static void start(){
      render();
    }
    private static long prevTime = 0, nextTime = 0;
    static float get_fps() {
        nextTime = System.currentTimeMillis();
        float deltaTime = nextTime - prevTime;
        prevTime = nextTime;
        return (float) Math.round((1000f/deltaTime)*10)/10;
    }

}
