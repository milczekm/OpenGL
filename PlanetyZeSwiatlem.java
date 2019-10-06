package opengl;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.Sphere;

public class PlanetyZeSwiatlem {

    static float delta = 0.05f;
    static float kat = 0;
    static float promien = 8f;
    static float promien1 = 5.5f;
    static float promienk1 = 0.5f;
    static float promienk2 = 1;
    static float promienk3 = 2.2f;
    static float promienk4 = 2.0f;

    static void orbita(float promien) {

        glBegin(GL_POINTS);
        for (int i = 0; i < 360; i++) {
            glVertex3d((promien * Math.cos((float) (i * Math.PI / 180))), promien * Math.sin((float) (i * Math.PI / 180)), 0);
        }
        glEnd();
    }

    static void kula(float promienk) {

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glEnable(GL_DEPTH_TEST);
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        Sphere sfera = new Sphere();
        sfera.draw(promienk, 32, 32);
    }

    static void kulaL(float promienk) {

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glEnable(GL_LINE_STIPPLE);
        glLineStipple(1, (short) 0xAAAA);
        glLineWidth(1.2f);
        Sphere sfera = new Sphere();
        sfera.draw(promienk, 32, 32);
    }

    static void init() {
        glMatrixMode(GL_PROJECTION);
        glOrtho(-10, 10, -10, 10, -10, 10);
        glMatrixMode(GL_MODELVIEW);
    }

    static void display() {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        kat += 0.0009f;

        glLoadIdentity();
        glPushMatrix();

        glEnable(GL_DEPTH_TEST);

        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

        glEnable(GL_LIGHTING); //włączenie światła

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
        float[] diffuse1 = {1.0f, 1.0f, 0.0f, 0};
        float[] specular1 = {1, 1, 1, 0};
        float[] position1 = {1f, 1f, 4, 0};

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

        glColor3f(1f, 1f, 0f);
        glTranslatef(0, 0, 0);
        kula(promienk3);        //slonce

        glColor3f(0f, 0f, 0f);
        glRotatef(120, 1, 0, 0);
        kulaL(promienk3); //kula w linie
        glPopMatrix();

        glLoadIdentity();
        glPushMatrix();

        float[] mat_ambient2 = {0, 0, 0, 0};
        float[] mat_diffuse2 = {0, 1, 0, 0};
        float[] mat_specular2 = {0, 0, 0, 0};
        float shininess1 = 128;

        FloatBuffer mat_bufA2 = BufferUtils.createFloatBuffer(4);
        mat_bufA1.put(mat_ambient2).rewind();

        FloatBuffer mat_bufD2 = BufferUtils.createFloatBuffer(4);
        mat_bufD1.put(mat_diffuse2).rewind();

        FloatBuffer mat_bufS2 = BufferUtils.createFloatBuffer(4);
        mat_bufS1.put(mat_specular2).rewind();

        glMaterial(GL_FRONT_AND_BACK, GL_AMBIENT, mat_bufA1);
        glMaterial(GL_FRONT_AND_BACK, GL_DIFFUSE, mat_bufD1);
        glMaterial(GL_FRONT_AND_BACK, GL_SPECULAR, mat_bufS1);
        glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, shininess);

        glColor3f(1f, 0f, 0f);
        orbita(promien);    //orbita czerwona
        glColor3f(0f, 1f, 0f);
        glTranslatef(promien * (float) Math.sin(kat), promien * (float) Math.cos(kat), 0f);
        kula(promienk2);    //planeta zielona po orbicie czerwonej
        glColor3f(0f, 0f, 0f);
        glRotatef(120, 1, 0, 0);

        glPopMatrix();

        glLoadIdentity();
        glPushMatrix();

        float[] mat_ambient3 = {0, 0, 0, 0};
        float[] mat_diffuse3 = {1, 0, 0, 0};
        float[] mat_specular3 = {0, 0, 0, 0};
        float shininess3 = 128;

        FloatBuffer mat_bufA3 = BufferUtils.createFloatBuffer(4);
        mat_bufA1.put(mat_ambient3).rewind();

        FloatBuffer mat_bufD3 = BufferUtils.createFloatBuffer(4);
        mat_bufD1.put(mat_diffuse3).rewind();

        FloatBuffer mat_bufS3 = BufferUtils.createFloatBuffer(4);
        mat_bufS1.put(mat_specular3).rewind();

        glMaterial(GL_FRONT_AND_BACK, GL_AMBIENT, mat_bufA1);
        glMaterial(GL_FRONT_AND_BACK, GL_DIFFUSE, mat_bufD1);
        glMaterial(GL_FRONT_AND_BACK, GL_SPECULAR, mat_bufS1);
        glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, shininess);

        glTranslatef(-1f, 0, 0);
        glColor3f(0f, 0.5f, 1f);
        orbita(promien1);    //orbita niebieska
        glColor3f(1f, 0f, 1f);

        glTranslatef(promien1 * (float) Math.sin(kat), promien1 * (float) Math.cos(kat), 0f);
        kula(promienk1);    //planeta fioletowa po orbicie niebieskiej
        glColor3f(0f, 0f, 0f);
        glRotatef(120, 1, 0, 0);

        glColor3f(0f, 0f, 0f);
        orbita(promienk3);    //orbita ksiezyca
        glColor3f(1f, 0f, 0f);
        glTranslatef(promienk3 * (float) Math.cos(kat), promienk3 * (float) Math.sin(kat), 0f);
        kula(0.3f);    //planeta niebieska po orbicie ksiezyca
        glColor3f(0f, 0f, 0f);
        glRotatef(120, 1, 0, 0);

        glPopMatrix();

    }

    public static void main(String[] args) {

        try {

            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.setTitle("Planety");
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
