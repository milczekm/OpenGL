package opengl;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.util.glu.GLU.*;

public class RuchomyKwadrat {

    static float przesun = 0;
    static float delta = 0.0003f;

    static void init() {
        glMatrixMode(GL_PROJECTION);
        gluOrtho2D(-1f, 1f, -1f, 1f);
        glMatrixMode(GL_MODELVIEW);
        glViewport(0, 0, 300, 300);
        glClearColor(0f, 0f, 0f, 0f);
        glPolygonMode(GL_FRONT, GL_FILL);
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glEnable(GL_LINE_STIPPLE);
        glLineStipple(1, (short) 0xAAAA);
        glLineWidth(3);
    }

    static void kwadrat() {
        glBegin(GL_QUADS);
        glColor3f(0f, 0f, 1f);
        glVertex2f(-0.5f, -0.5f);
        glColor3f(0f, 0f, 1f);
        glVertex2f(0.5f, -0.5f);
        glColor3f(0f, 1f, 0f);
        glVertex2f(0.5f, 0.5f);
        glColor3f(1f, 0f, 0f);
        glVertex2f(-0.5f, 0.5f);
        glEnd();
    }

    static void display() {
        glClear(GL_COLOR_BUFFER_BIT);
        przesun += delta;
        glLoadIdentity();

        if (przesun > 0.5) {
            delta = -delta;
        }

        if (przesun < -0.5) {
            delta = -delta;
        }
        glTranslatef(przesun, 0, 0);
        kwadrat();

    }

    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(300, 300));
            Display.setTitle("Okienko");
            Display.setLocation(-1, -1);
            Display.create();
            init();

            while (!Display.isCloseRequested()) {
                display();
                Display.update();
            }
        } catch (LWJGLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
