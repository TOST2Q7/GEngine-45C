package com.tost.Q207.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import com.tost.Q207.Window;

public class Mouse {

    private static final boolean[]
            buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    public static double
            mouseX,
            mouseY;

    public static boolean buttonDown(int key_id) {
        /* Event loop method
         * ! Use before ".handleMouseInput()"
         * Ex: if(Mouse.buttonDown(0)) {} */
        return GLFW.glfwGetMouseButton(Window.getWindow().id_window, key_id) == GLFW.GLFW_TRUE;
    }
    public static boolean buttonPressed(int key_id) {
        /* Event method */
        return buttonDown(key_id) && !buttons[key_id];
    }
    public static boolean buttonReleased(int key_id) {
        /* Event method */
        return !buttonDown(key_id) && buttons[key_id];
    }

    public static void setMouseCallbacks(long window_id) {
        setCursorPositionCallbaks(window_id);
    }

    public static void setCursorPositionCallbaks(long window_id) {
        GLFW.glfwSetCursorPosCallback(window_id, new GLFWCursorPosCallbackI() {

            @Override
            public void invoke(long id, double x, double y) {
                mouseX = x;
                mouseY = y;

            }
        });
    }


    public static void handleMouseInput()
    // Добавлять прямо перед обновлением экрана
    {
        for (int i=0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) {
            buttons[i] = buttonDown(i);
        }
    }
}
