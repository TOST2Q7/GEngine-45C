import com.tost.Q207.Window;
import com.tost.Q207.graphics.shapes.Quad;
import com.tost.Q207.graphics.shapes.Triangle;
import com.tost.Q207.renderer.Shader;
import com.tost.Q207.input.Mouse;
import com.tost.Q207.input.Keyboard;
import com.tost.Q207.util.MemoryManagement;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL41C.*;
import static org.lwjgl.opengl.GL45C.glCreateBuffers;


public class Game {
    public static final int
            WIDTH = 1341,
            HEIGHT = 761;
    public static final String TITLE = "Game Engine 0.0.0.1";
    public Window window;
    public Shader
        shader,
        shader2;

    public void run()
    {
        this.init();
        this.game();
    }

    public void init()
    {
        this.window = new Window(WIDTH, HEIGHT, TITLE);
        this.window.isGLFWInit();
        this.window.create();

        this.shader = new Shader(
            "res/shaders/tmp2.vert",
            "res/shaders/tmp2.frag");
        this.shader2 = new Shader(
            "res/shaders/tmp1.vert",
            "res/shaders/tmp1.frag");
    }



    public void game()
    {
        float[] v_triangle = { -0.7f, 0.7f, 0,   0.3f, 0.7f, 0,   -0.3f, -0.3f, 0 };

        float[] v_quad = { -0.5f, 0.5f, 0,  0.5f, 0.5f, 0,  -0.5f, -0.5f, 0,   0.5f, -0.5f, 0 };

        int[] v_indices = {0, 1, 2,  1, 2, 3};

        float[] v_romb = {
            0.0f, 0.5f, 0f,  -0.5f, 0.0f, 0f,  0.5f, 0.0f, 0f,
            0.5f, 0.0f, 0f,  -0.5f, 0.0f, 0f,  0.0f, -0.5f, 0f
        };

        Quad quad1 = new Quad();
        Quad quad_2 = new Quad();
        Triangle triangle = new Triangle();

        triangle.create(v_triangle);
        quad1.create(v_quad);

        float[] v_colors = {
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
        };

        int vertexBufferC = glCreateBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferC);
        glBufferData(
                GL_ARRAY_BUFFER,
                MemoryManagement.putData(v_colors),
                GL_STATIC_DRAW
        );
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);

        while(!this.window.isClosedRequest())
        {
            this.window.clearColor(new int[] {10, 10, 10});


            if (Keyboard.keyPreesed(GLFW_KEY_1)) {
                System.out.println("up");
            }

            Keyboard.handleKeyboardInput();
            Mouse.handleMouseInput();


            triangle.drow(shader);
            quad1.drow(shader2);


            this.window.update();
        }

        this.window.destroy();
        // test
    }
}
