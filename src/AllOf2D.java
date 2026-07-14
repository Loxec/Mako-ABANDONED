import net.mako.rendering.RenderPanel;
import net.mako.rendering.components.*;
import net.mako.rendering.msh.ModelParser;
import net.mako.rendering.utilities.TriangleTransform;
import net.mako.technicalities.GameThread;
import net.mako.technicalities.Utils;
import net.mako.technicalities.input.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class AllOf2D {

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

        // TRIANGLES

        Triangle t1 = new Triangle(
                new Vertex(100, 100),
                new Vertex(150, 200),
                new Vertex(200, 100),
                true
        );
        t1.setTag("move");

        Triangle t2 = new Triangle(
                new Vertex(100, 100),
                new Vertex(150, 200),
                new Vertex(200, 100),
                true
        );

        t2.setTag("rotate");
        t2.setColor(new Color(255, 0, 0));

        Triangle t3 = new Triangle(
                new Vertex(100, 100),
                new Vertex(150, 200),
                new Vertex(200, 100),
                true
        );
        t3.setTag("scale");

        Mesh movingMesh = t1.toMesh();
        Mesh rotatingMesh = t2.toMesh();
        Mesh scalingMesh = t3.toMesh();

        panel.add(movingMesh);
        panel.add(scalingMesh);
        panel.add(rotatingMesh);

        // MODELS

        ModelParser basicModel =
                new ModelParser("/assets/exemple_bt.msh");

        ModelParser indexedModel =
                new ModelParser("/assets/exemple_it.msh");

        ModelParser multimeshModel =
                new ModelParser("/assets/exemple_mt.msh");

        ModelParser indexedMultimeshModel =
                new ModelParser("/assets/exemple_mit.msh");

        panel.add(
                basicModel
                        .getModel(basicModel.getFormatType())
                        .copy(),
                0,
                0
        );

        panel.add(
                indexedModel
                        .getModel(indexedModel.getFormatType())
                        .copy(),
                0,
                50
        );

        panel.add(
                multimeshModel
                        .getModel(multimeshModel.getFormatType())
                        .copy(),
                300,
                0
        );

        panel.add(
                indexedMultimeshModel
                        .getModel(indexedMultimeshModel.getFormatType())
                        .copy(),
                0,
                0
        );

        // SPRITE

        Sprite sprite =
                new Sprite("/assets/skybox_pearl.png", 300, 300);

        int scaleMultiplier = 1;

        sprite.setScaleX(
                sprite.getScaleX() * scaleMultiplier
        );

        sprite.setScaleY(
                sprite.getScaleY() * scaleMultiplier
        );

        sprite.setRotation(45);

        sprite.setPosition(350, 350);

        panel.add(sprite);

        // INPUT

        KeyHandler kh = new KeyHandler();

        panel.addKeyHandlerToPanel(kh);

        // GAME THREAD

        GameThread game = new GameThread(60);

        game.onUpdate(dt -> {

            sprite.setRotation(
                    sprite.getRotation() + 90 * dt
            );

            for (Mesh mesh : panel.getMeshes()) {

                for (Triangle triangle : mesh.getTriangles()) {

                    switch (triangle.getTag()) {

                        case "move" -> TriangleTransform.translate(
                                100 * dt,
                                0,
                                triangle
                        );

                        case "rotate" -> TriangleTransform.rotate(
                                90 * dt,
                                triangle
                        );

                        case "scale" -> TriangleTransform.scaleUp(
                                1.0 + (0.2 * dt),
                                triangle
                        );
                    }
                }
            }

            panel.refresh();
        });

        game.onRender(panel::repaint);

        // MOVEMENT

        int speed = 200;

        kh.onForwardPressed = e -> {

            sprite.setPosition(
                    sprite.getX(),
                    sprite.getY() - speed * game.getDeltaTime()
            );
        };

        kh.onBackwardPressed = e -> {

            sprite.setPosition(
                    sprite.getX(),
                    sprite.getY() + speed * game.getDeltaTime()
            );
        };

        kh.onRightPressed = e -> {

            sprite.setPosition(
                    sprite.getX() + speed * game.getDeltaTime(),
                    sprite.getY()
            );
        };

        kh.onLeftPressed = e -> {

            sprite.setPosition(
                    sprite.getX() - speed * game.getDeltaTime(),
                    sprite.getY()
            );
        };

        // START

        game.start();
    }
}