package com.tost.Q207;

import com.tost.Q207.input.Mouse;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40C.*;

public class Window {
    private int
            width, height,
            xStartPos = -1,
            yStartPos = -1;
    public long id_window;
    private String title;
    public GLFWVidMode videoMode;
    public IntBuffer bufferedWidth, bufferedHeight;
    public static Window instans;

    public Window(int width, int height, String title)
    {
        instans = this;
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public Window(int width, int height, String title, int xStartPos, int yStartPos)
    {
        instans = this;
        this.width = width;
        this.height = height;
        this.title = title;
        this.xStartPos = xStartPos;
        this.yStartPos = yStartPos;
    }

    public boolean isGLFWInit() {
        return glfwInit();
    }

    public void create()
    {
        Binder.width = this.width;
        Binder.height = this.height;

        this.id_window = GLFW.glfwCreateWindow(this.width, this.height, this.title, 0, 0);

        if(this.id_window == 0)
        {
            System.err.println("[ERR] \t; [td:tm:ts] \t; [ QVE ] \t; [Window.create] \t: Window is 0");
            System.exit(-1);
        }

        try (MemoryStack mem = MemoryStack.stackPush())
        {
            this.bufferedWidth = BufferUtils.createIntBuffer(1);

            this.bufferedHeight = BufferUtils.createIntBuffer(1);
            GLFW.glfwGetWindowSize(this.id_window, this.bufferedWidth, this.bufferedHeight);
        }
        catch (Exception e)
        {
        }

        this.videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

        GLFW.glfwSetWindowTitle(this.id_window, this.title);
        GLFW.glfwSetWindowSize(this.id_window, this.width, this.height);
        GLFW.glfwSetWindowAspectRatio(this.id_window, this.width, this.height); // width / height
        GLFW.glfwSetWindowPos(this.id_window,
                ((this.videoMode.width() - this.bufferedWidth.get(0)) / 2),
                (this.videoMode.height() - this.bufferedHeight.get(0)) / 2);
        GLFW.glfwSetWindowSizeLimits(this.id_window, this.width, this.height, 1600, 900);

        GLFW.glfwMakeContextCurrent(this.id_window);
        GL.createCapabilities();
        GL40.glViewport(0, 0, this.bufferedWidth.get(0), this.bufferedHeight.get(0));

        Mouse.setMouseCallbacks(this.id_window);
    }
    public static Window getWindow() {
        return instans;
    }

    public void clearColor(float r, float g, float b, float a) {
        glClearColor(r, g, b, a);
    }
    public void clearColor(int[] arg) {
        try {
            glClearColor(
                    (float) arg[0] * 0.01f,
                    (float) arg[1] * 0.01f,
                    (float) arg[2] * 0.01f,
                    (float) arg[3] * 0.01f);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            glClearColor(
                    (float) arg[0] * 0.01f,
                    (float) arg[1] * 0.01f,
                    (float) arg[2] * 0.01f,
                    0.1f);
        }
    }

    public void update() {
        GLFW.glfwPollEvents();
        GLFW.glfwSwapBuffers(this.id_window);
    }

    public void destroy() {
        glfwDestroyWindow(this.id_window);
    }

    public boolean isClosedRequest() {
        return glfwWindowShouldClose(this.id_window);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
