package ru.cmr.utils;

import org.apache.commons.io.FileUtils;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.assimp.*;
import ru.cmr.models.Mesh;
import ru.cmr.models.utils.Vertex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This utility class should return value from the file you want to read from.
 * @author CymerR
 */
public class FileLoader {
    /**
     *
     * @param file which contains value
     * @return data from file as a string
     */
    public static String load(File file){
        try {
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Mesh loadMesh(String modelFile){
        AIScene scene = Assimp.aiImportFile(modelFile,
                Assimp.aiProcess_JoinIdenticalVertices | Assimp.aiProcess_Triangulate);
        if (scene == null) {
            System.err.println("Can't load a current file!" + modelFile);
        }

        AIMesh mesh = AIMesh.create(scene.mMeshes().get(0));
        int vertexCount = mesh.mNumVertices();

        AIVector3D.Buffer vertices  = mesh.mVertices();
        AIVector3D.Buffer normals   = mesh.mNormals();

        ArrayList<Vertex> verticesList  = new ArrayList<>();

        for (int i = 0; i < vertexCount; i++) {
            AIVector3D vertex = vertices.get(i);
            Vector3f meshVertex = new Vector3f(vertex.x(),vertex.y(),vertex.z());

            AIVector3D normal = vertices.get(i);
            Vector3f meshNormal = new Vector3f(normal.x(),normal.y(),normal.z());

            verticesList.add(new Vertex(meshVertex,meshNormal,new Vector2f()));
        }
        //face works
        int faceCount = mesh.mNumFaces();
        AIFace.Buffer indices = mesh.mFaces();

        int[] indicesArray = new int[faceCount*3];
        for (int i = 0; i < faceCount; i++) {
            AIFace currentFace = indices.get(i);
            indicesArray[i*3 +0] = currentFace.mIndices().get(0);
            indicesArray[i*3 +1] = currentFace.mIndices().get(1);
            indicesArray[i*3 +2] = currentFace.mIndices().get(2);
        }

        return new Mesh(verticesList,indicesArray);
    }
}
