package opengl;

import org.lwjgl.input.Keyboard;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.Sphere;

public class Kula {

    //obrót kuli za pomocą strzałek
    
    static float xrot = 0f;
    static float yrot = 0f;

    static void init() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glMatrixMode(GL_PROJECTION);
        glOrtho(-6.0f, 6.0f, -6.0f, 6.0f, -6.0f, 6.0f);

        glMatrixMode(GL_MODELVIEW);

        glEnable(GL_DEPTH_TEST);

        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

        glEnable(GL_LIGHTING); //włączenie światła

        glEnable(GL_LIGHT0);

        float[] ambient = {1.0f, 1.0f, 1.0f, 0};
        float[] diffuse = {1.0f, 0.0f, 0.0f, 0};
        float[] specular = {1, 1, 1, 0};
        float[] position = {-5, 0, 5, 0};

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
        float[] mat_ambient = {0, 0, 0, 0};
        float[] mat_diffuse = {1, 1, 1, 0};
        float[] mat_specular = {1, 1, 1, 0};
        float shininess = 128;

        FloatBuffer mat_bufA = BufferUtils.createFloatBuffer(4);
        mat_bufA.put(mat_ambient).rewind();

        FloatBuffer mat_bufD = BufferUtils.createFloatBuffer(4);
        mat_bufD.put(mat_diffuse).rewind();

        FloatBuffer mat_bufS = BufferUtils.createFloatBuffer(4);
        mat_bufS.put(mat_specular).rewind();

        glEnable(GL_LIGHT1);

        float[] ambient1 = {1.0f, 1.0f, 1.0f, 0};
        float[] diffuse1 = {0.0f, 0.0f, 1.0f, 0};
        float[] specular1 = {1, 1, 1, 0};
        float[] position1 = {1, 0, -1, 0};

        FloatBuffer bufA1 = BufferUtils.createFloatBuffer(4);
        bufA1.put(ambient1).rewind();

        FloatBuffer bufD1 = BufferUtils.createFloatBuffer(4);
        bufD1.put(diffuse1).rewind();

        FloatBuffer bufS1 = BufferUtils.createFloatBuffer(4);
        bufS1.put(specular1).rewind();

        FloatBuffer bufP1 = BufferUtils.createFloatBuffer(4);
        bufP1.put(position1).rewind();

        glLight(GL_LIGHT1, GL_AMBIENT, bufA1);
        glLight(GL_LIGHT1, GL_DIFFUSE, bufD1);
        glLight(GL_LIGHT1, GL_SPECULAR, bufS1);
        glLight(GL_LIGHT1, GL_POSITION, bufP1);

        FloatBuffer mat_bufA1 = BufferUtils.createFloatBuffer(4);
        mat_bufA1.put(mat_ambient).rewind();

        FloatBuffer mat_bufD1 = BufferUtils.createFloatBuffer(4);
        mat_bufD1.put(mat_diffuse).rewind();

        FloatBuffer mat_bufS1 = BufferUtils.createFloatBuffer(4);
        mat_bufS1.put(mat_specular).rewind();

        glMaterial(GL_FRONT_AND_BACK, GL_AMBIENT, mat_bufA1);
        glMaterial(GL_FRONT_AND_BACK, GL_DIFFUSE, mat_bufD1);
        glMaterial(GL_FRONT_AND_BACK, GL_SPECULAR, mat_bufS1);
        glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, shininess);

    }

    static void display() {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();

        glColor3f(1, 1, 1);

        glRotatef(yrot, 0, 1, 0);
        glRotatef(xrot, 1, 0, 0);

        Sphere sfera = new Sphere();
        sfera.draw(3, 32, 32);

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
            Display.setTitle("Kula");
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
