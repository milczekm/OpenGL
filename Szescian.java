package opengl;

import org.lwjgl.input.Keyboard;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Szescian {

    //obracanie trójwymiarowe sześcianu za pomocą strzałek
    static float[] vertices = {
        -0.5f, -0.5f, 0.5f, // 0
        0.5f, -0.5f, 0.5f, // 1
        0.5f, 0.5f, 0.5f, // 2
        -0.5f, 0.5f, 0.5f, // 3
        -0.5f, -0.5f, -0.5f, // 4
        0.5f, -0.5f, -0.5f, // 5
        0.5f, 0.5f, -0.5f, // 6
        -0.5f, 0.5f, -0.5f // 7
    };
    static int[] indices1 = {
        0, 1, 2, 3 // n: 0 0 1
    };

    static int[] indices2 = {
        5, 4, 7, 6 // n: 0 0 -1
    };
    static int[] indices3 = {
        0, 4, 5, 1 // n: 0 -1 0
    };
    static int[] indices4 = {
        3, 2, 6, 7 // n: 0 1 0
    };
    static int[] indices5 = {
        1, 5, 6, 2 // n: 1 0 0
    };
    static int[] indices6 = {
        0, 3, 7, 4 // n: -1 0 0
    };

    static IntBuffer bufferI1 = BufferUtils.createIntBuffer(4);
    static IntBuffer bufferI2 = BufferUtils.createIntBuffer(4);
    static IntBuffer bufferI3 = BufferUtils.createIntBuffer(4);
    static IntBuffer bufferI4 = BufferUtils.createIntBuffer(4);
    static IntBuffer bufferI5 = BufferUtils.createIntBuffer(4);
    static IntBuffer bufferI6 = BufferUtils.createIntBuffer(4);

    static FloatBuffer bufferV = BufferUtils.createFloatBuffer(24);
    static float xrot = 0f;
    static float yrot = 0f;

    static void init() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glMatrixMode(GL_PROJECTION);
        glOrtho(-1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);

        glMatrixMode(GL_MODELVIEW);

        glEnableClientState(GL_VERTEX_ARRAY);
        glVertexPointer(3, 0, bufferV);

        bufferV.put(vertices);
        bufferV.rewind();

        bufferI1.put(indices1);
        bufferI1.rewind();
        bufferI2.put(indices2);
        bufferI2.rewind();
        bufferI3.put(indices3);
        bufferI3.rewind();
        bufferI4.put(indices4);
        bufferI4.rewind();
        bufferI5.put(indices5);
        bufferI5.rewind();
        bufferI6.put(indices6);
        bufferI6.rewind();

        glEnable(GL_DEPTH_TEST);

        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        glEnable(GL_LIGHTING); //włączenie światła
        glEnable(GL_LIGHT0);

        float[] ambient = {0.2f, 0.2f, 0.2f, 0};
        float[] diffuse = {0.5f, 0.5f, 0.5f, 0};
        float[] specular = {1, 0, 1, 0};
        float[] position = {5, 5, 5, 0};

        FloatBuffer bufA = BufferUtils.createFloatBuffer(4);
        bufA.put(ambient).rewind();

        FloatBuffer bufD = BufferUtils.createFloatBuffer(4);
        bufD.put(diffuse).rewind();

        FloatBuffer bufS = BufferUtils.createFloatBuffer(4);
        bufS.put(specular).rewind();

        FloatBuffer bufP = BufferUtils.createFloatBuffer(4);
        bufP.put(position).rewind();

        glLight(GL_LIGHT0, GL_AMBIENT, bufA);
        glLight(GL_LIGHT0, GL_DIFFUSE, bufD);
        glLight(GL_LIGHT0, GL_SPECULAR, bufS);
        glLight(GL_LIGHT0, GL_POSITION, bufP);

        //sposób odbijania składowych światła przez materiał
        float[] mat_ambient = {1, 1, 1, 0};
        float[] mat_diffuse = {1, 1, 1, 0};
        float[] mat_specular = {1, 1, 1, 0};
        float shininess = 128;

        FloatBuffer mat_bufA = BufferUtils.createFloatBuffer(4);
        mat_bufA.put(mat_ambient).rewind();

        FloatBuffer mat_bufD = BufferUtils.createFloatBuffer(4);
        mat_bufD.put(mat_diffuse).rewind();

        FloatBuffer mat_bufS = BufferUtils.createFloatBuffer(4);
        mat_bufS.put(mat_specular).rewind();

        //ustawia właściwości materiału obiektu w podziale na trzy źródła światła
        glMaterial(GL_FRONT_AND_BACK, GL_AMBIENT, mat_bufA);
        glMaterial(GL_FRONT_AND_BACK, GL_DIFFUSE, mat_bufD);
        glMaterial(GL_FRONT_AND_BACK, GL_SPECULAR, mat_bufS);
        glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, shininess);

    }

    static void display() {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); //włączenie głębokości
        glLoadIdentity();

        glColor3f(1, 1, 1);

        glRotatef(yrot, 0, 1, 0);
        glRotatef(xrot, 1, 0, 0);

        glNormal3f(0, 0, 1);
        glDrawElements(GL_QUADS, bufferI1);

        glNormal3f(0, 0, -1);
        glDrawElements(GL_QUADS, bufferI2);

        glNormal3f(1, 0, 0);
        glDrawElements(GL_QUADS, bufferI3);

        glNormal3f(-1, 0, 0);
        glDrawElements(GL_QUADS, bufferI4);

        glNormal3f(0, -1, 0);
        glDrawElements(GL_QUADS, bufferI5);

        glNormal3f(0, 1, 0);
        glDrawElements(GL_QUADS, bufferI6);

    }

    static void input() {
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            yrot -= 0.01f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            yrot += 0.01f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            xrot -= 0.01f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            xrot += 0.01f;
        }
    }

    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(300, 300));
            Display.setTitle("Szescian");
            Display.setLocation(-1, -1);
            Display.create();
            init();
            while (!Display.isCloseRequested()) {
                input();
                display();
                Display.update();
            }
        } catch (LWJGLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
