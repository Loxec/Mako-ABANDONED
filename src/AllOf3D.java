import net.mako.rendering.Animator;
import net.mako.rendering.Camera;
import net.mako.rendering.RenderPanel;
import net.mako.rendering.components.Mesh3D;
import net.mako.rendering.components.Triangle3D;
import net.mako.rendering.components.Vertex3D;
import net.mako.rendering.utilities.Mesh3DTransform;
import net.mako.technicalities.GameThread;
import net.mako.technicalities.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AllOf3D {

    public static void main(String[] args) {

        // WINDOW
        JFrame window = Utils.getBasicJFrame("Mako Renderer");
        window.setLayout(new BorderLayout());

        // RENDER PANEL
        RenderPanel panel = new RenderPanel();
        Utils.setBasicRenderPanelParameter(panel);

        window.add(panel, BorderLayout.CENTER);
        window.setVisible(true);
        panel.requestFocusInWindow();

        // CAMERA
        Camera camera = new Camera(0,0,0,500);

        // TRIANGLES

        Vertex3D v000 = new Vertex3D(-100,-100,200);
        Vertex3D v001 = new Vertex3D(-100,-100,400);
        Vertex3D v010 = new Vertex3D(-100, 100,200);
        Vertex3D v011 = new Vertex3D(-100, 100,400);

        Vertex3D v100 = new Vertex3D( 100,-100,200);
        Vertex3D v101 = new Vertex3D( 100,-100,400);
        Vertex3D v110 = new Vertex3D( 100, 100,200);
        Vertex3D v111 = new Vertex3D( 100, 100,400);

        Triangle3D f1 = new Triangle3D(v001, v101, v111,false);
        Triangle3D f2 = new Triangle3D(v001, v111, v011,false);

        Triangle3D b1 = new Triangle3D(v000, v110, v100,false);
        Triangle3D b2 = new Triangle3D(v000, v010, v110,false);

        Triangle3D t1 = new Triangle3D(v010, v011, v111,false);
        Triangle3D t2 = new Triangle3D(v010, v111, v110,false);

        Triangle3D d1 = new Triangle3D(v000, v100, v101,false);
        Triangle3D d2 = new Triangle3D(v000, v101, v001,false);

        List<Triangle3D> triangles = new ArrayList<>();
        triangles.add(f1);
        triangles.add(f2);
        triangles.add(b1);
        triangles.add(b2);
        triangles.add(d1);
        triangles.add(d2);
        triangles.add(t1);
        triangles.add(t2);
        Mesh3D mesh = new Mesh3D(triangles);

        panel.add(mesh);

        // GAME THREAD

        GameThread game = new GameThread(60);

        Animator animator = new Animator();

        game.onUpdate(dt -> {

            animator.update(dt);

            Mesh3DTransform.rotateY(60 * dt, mesh);

            double y = animator.bounce(3,-0.5,1.5);
            double scale = animator.breathing(1,0.002);

            Mesh3DTransform.translate(0,y,0,mesh);
            Mesh3DTransform.scale(scale,mesh);

            panel.refresh();
        });

        game.onRender(panel::repaint);

        // START

        game.start();
    }

}