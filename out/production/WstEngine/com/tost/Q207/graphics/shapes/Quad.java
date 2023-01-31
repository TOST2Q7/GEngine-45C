package com.tost.Q207.graphics.shapes;

import com.tost.Q207.renderer.Shader_example;
import com.tost.Q207.util.MemoryManagement;

import static org.lwjgl.opengl.GL45C.*;

public class Quad {

    int[] v_indices = {0, 1, 2, 1, 2, 3};
    int vertexArray;

    public void create(float[] coord) {
        this.vertexArray = glCreateVertexArrays();
        glBindVertexArray(vertexArray);

        int indexBuffer = glCreateBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, MemoryManagement.putData(this.v_indices), GL_STATIC_DRAW);

        int vertexBuffer = glCreateBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(
                GL_ARRAY_BUFFER,
                MemoryManagement.putData(coord),
                GL_STATIC_DRAW
        );
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBindVertexArray(vertexArray);
    }

    public void drow(Shader_example shader) {
        glBindVertexArray(vertexArray);
        shader.bind();
        glDrawElements(GL_TRIANGLES, v_indices.length, GL_UNSIGNED_INT, 0);
        shader.unBind();
        glBindVertexArray(vertexArray);
    }
}
