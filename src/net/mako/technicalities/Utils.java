package net.mako.technicalities;

import net.mako.rendering.Camera;
import net.mako.rendering.RenderPanel;
import net.mako.rendering.components.*;
import net.mako.technicalities.input.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class Utils {

    public static JFrame getBasicJFrame(String title) {
        // WINDOW
        Dimension screenSize = new Dimension(800,600);
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(screenSize);
        frame.setSize(screenSize);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        return frame;
    }

    public static void closeWindow(JFrame frame) {
        frame.setVisible(false);
        frame.dispose();
    }

    public static void setBasicRenderPanelParameter(RenderPanel rp){
        rp.setBackground(Color.BLACK);
        rp.setLayout(null);
        rp.setDoubleBuffered(true);
        rp.setFocusable(true);
        rp.requestFocus();
        rp.requestFocusInWindow();
        rp.setName("default");
        rp.setVisible(true);
    }

    public static void setBasicMovementForComponent(KeyHandler kh, Sprite sprite,double speed,double deltaTime){
        kh.onForwardPressed = e -> {
            sprite.setPosition(sprite.getX(),  sprite.getY() - speed * deltaTime);
        };

        kh.onBackwardPressed = e -> {
            sprite.setPosition(sprite.getX(),  sprite.getY() + speed * deltaTime);
        };

        kh.onLeftPressed = e -> {
            sprite.setPosition(sprite.getX() - speed * deltaTime,  sprite.getY());
        };

        kh.onRightPressed = e -> {
            sprite.setPosition(sprite.getX() + speed * deltaTime,  sprite.getY());
        };
    }
    public static Vertex project(Vertex3D v, Camera cam) {

        double fov = cam.getFov();

        double relX = v.x - cam.getX();
        double relY = v.y - cam.getY();
        double relZ = v.z - cam.getZ();

        if(relZ <= 0.1)
            relZ = 0.1;

        double x = (relX * fov) / relZ;
        double y = (relY * fov) / relZ;

        x += 400;
        y += 300;

        return new Vertex(x, y);
    }

    public static Triangle T3dTT2d(Triangle3D t,Camera cam){
        Vertex v1 = project(t.vertexes.get(0),cam);
        Vertex v2 = project(t.vertexes.get(1),cam);
        Vertex v3 = project(t.vertexes.get(2),cam);

        return new Triangle(v1,v2,v3,false);
    }


}
