package opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.Sphere;

public class DwiePlanetyZKsiezycemWokolSlonca {

    static float delta = 0.05f;
    static float kat = 0;
    static float promien = 8f;
    static float promien1 = 5.5f;
    static float promienk1 = 0.5f;
    static float promienk2 = 1;
    static float promienk3 = 1.5f;
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
        glColor3f(1f, 1f, 0f);
        glTranslatef(0.5f, 0, 0);
        kula(promienk3);        //slonce

        glColor3f(0f, 0f, 0f);
        glRotatef(120, 1, 0, 0);
        kulaL(promienk3); //kula w linie
        glPopMatrix();

        glLoadIdentity();
        glPushMatrix();
        glColor3f(1f, 0f, 0f);
        orbita(promien);    //orbita czerwona
        glColor3f(0f, 1f, 0f);
        glTranslatef(promien * (float) Math.sin(kat), promien * (float) Math.cos(kat), 0f);
        kula(promienk2);    //planeta zielona po orbicie czerwonej
        glColor3f(0f, 0f, 0f);
        glRotatef(120, 1, 0, 0);
        kulaL(promienk2); //kula w linie
        glPopMatrix();

        glLoadIdentity();
        glPushMatrix();
        glTranslatef(-1f, 0, 0);
        glColor3f(0f, 0.5f, 1f);
        orbita(promien1);    //orbita niebieska
        glColor3f(1f, 0f, 1f);

        glTranslatef(promien1 * (float) Math.sin(kat), promien1 * (float) Math.cos(kat), 0f);
        kula(promienk1);    //planeta fioletowa po orbicie niebieskiej
        glColor3f(0f, 0f, 0f);
        glRotatef(120, 1, 0, 0);
        kulaL(promienk1); //kula w linie 

        glColor3f(1f, 1f, 1f);
        orbita(promienk3);    //orbita ksiezyca
        glColor3f(1f, 0f, 0f);
        glTranslatef(promienk3 * (float) Math.cos(kat), promienk3 * (float) Math.sin(kat), 0f);
        kula(0.3f);    //planeta niebieska po orbicie ksiezyca
        glColor3f(0f, 0f, 0f);
        glRotatef(120, 1, 0, 0);
        kulaL(0.3f); //kula w linie
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
