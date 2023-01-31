package com.tost.Q207.input;

import com.tost.Q207.Window;
import org.lwjgl.glfw.GLFW;


public class Keyboard
{
    private static final boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];

    public static boolean keyDown(int keyId){
        return GLFW.glfwGetKey(Window.getWindow().id_window, keyId) == 1;
    }

    public static boolean keyPreesed(int keyId){
        return keyDown(keyId) && !keys[keyId];
    }

    public static boolean keyReleased(int keyId)
    {
        return !keyDown(keyId) && keys[keyId];
    }

    public static void handleKeyboardInput()
    {

        for (int i = 0; i < GLFW.GLFW_KEY_LAST; i++)
        {
            keys[i] = keyDown(i);
        }
    }
}