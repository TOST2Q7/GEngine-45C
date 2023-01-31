package com.tost.Q207.graphics.shapes;

import com.tost.Q207.renderer.Shader_example;
import com.tost.Q207.util.MemoryManagement;

import static org.lwjgl.opengl.GL45C.*;

public class Triangle {

    private int vertexArray;
    private float[] coordinats;

    public void create(float[] coord) {
        coordinats = coord;

        vertexArray = glCreateVertexArrays();
        glBindVertexArray(vertexArray);

        int indexBuffer = glCreateBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, indexBuffer);
        glBufferData(GL_ARRAY_BUFFER, MemoryManagement.putData(coord), GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, indexBuffer);
        glBindVertexArray(vertexArray);
    }

    public void drow(Shader_example shader) {
        glBindVertexArray(vertexArray);
        shader.bind();
        glDrawArrays(GL_TRIANGLES, 0, coordinats.length / 3);
        shader.unBind();
        glBindVertexArray(vertexArray);
    }
}
