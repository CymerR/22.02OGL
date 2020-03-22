package ru.cmr.Shaders;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;
import ru.cmr.utils.FileLoader;

import java.io.File;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

/**
 * This class represents basic shader mechanism.
 * @author CymerR
 */
public class Shader {
    /**
     * a shader program ID.
     */
    private int program;
    private int v_sh,f_sh;

    public Shader() {
        program = glCreateProgram();
    }

    public void setShaders(String fileNameVertex,String fileNameFragment){
        String vertexShader = FileLoader.load(new File("src/ru/cmr/Shaders/assets/"+fileNameVertex+".glsl"));
        String fragmentShader = FileLoader.load(new File("src/ru/cmr/Shaders/assets/"+fileNameFragment+".glsl"));
        f_sh = createShader(GL_FRAGMENT_SHADER,fragmentShader);
        v_sh = createShader(GL_VERTEX_SHADER,vertexShader);

        glLinkProgram(program);
    }

    private int createShader(int type, String data){
        int shader =  glCreateShader(type);
        glShaderSource(shader,data);
        glAttachShader(program,shader);
        glCompileShader(shader);
        int status = glGetShaderi(shader,GL_COMPILE_STATUS);
        if (status != GL_NO_ERROR){
            System.out.println(glGetShaderInfoLog(shader));
        }
        return shader;
    }

    /**
     * This method is used to send Uniform matrices to the GPU.
     */
    public void sendUniformMat4(String name,Matrix4f data){
        int loc = glGetUniformLocation(program,name);
        if (loc !=-1){
            FloatBuffer buffer = MemoryUtil.memAllocFloat(4*4);
            data.get(buffer);
            glUniformMatrix4fv(loc,false, buffer);
        }
    }
    public void sendUniform(String name, Vector3f data){
        int loc = glGetUniformLocation(program,name);
        glUniform3f(loc,data.x,data.y,data.z);

    }
    public void sendUniform(String name, float data){
        int loc = glGetUniformLocation(program,name);
        if (loc != -1){
            glUniform1f(loc,data);
        }
    }

    public void bind(){
        glUseProgram(program);
    }
    public void unbind(){
        glUseProgram(0);
    }
}
