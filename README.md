# Mako 2D & 3D Game Engine (Java)

A lightweight 2D/3D game engine built from scratch in Java.  
This project is the foundation of a custom game, created as a learning base for future game development
---

(ABANDONED)

## Current Features

## RENDERING

#### Geometry System
- Vertex (point structure)
- Triangle primitive
- Rectangle (composed of two triangles)
- Mesh grouping system

#### Rendering
- Custom RenderPanel based on JPanel
- Triangle rendering using Path2D
- Filled polygon support
- Color support per triangle
- Continuous render loop (basic game loop)

#### Engine Structure
- Simple mesh-based scene system
- Manual repaint loop
- Swing-based window system

## INPUT

#### Keyboard
- Simple keyListener
- Consumers 
- Custom consumers like backward forward etc

## UTILITIES

#### Triangle Transform
- you can rotate your triangle
- you can translate your triangle
- you can scaleUp/Down your triangle
#### Triangle3D Transform
- you can rotate your triangle3D
- you can translate your triangle3D
- you can scaleUp/Down your triangle3D
#### Mesh3D Transform
- you can rotate your mesh3D
- you can translate your mesh3D
- you can scaleUp/Down your mesh3D

## MSH
- a new file format to create easy model in 2d
- .msh loader
- .msh converter into mesh

---

## Project Structure

```

net.mako.rendering
├── RenderPanel   → Main rendering surface
├── Camera        → Needed for 3D
├── Animator      → Why not ?

````

```

net.mako.rendering.components
├── Model         → Collection of meshes
├── Rectangle     → Primitive shape
├── Mesh          → Collection of triangles
├── Mesh3D        → Collection of 3D triangles
├── Triangle      → Primitive shape
├── Triangle3D    → Primitive shape3D
├── Vertex        → 2D coordinate
├── Vertex3D      → 3D coordinate
├── Sprite        → You can now load images

````

```

net.mako.rendering.msh
├── FormatType     → Used to sort the files and their formating
├── ModelParser    → What help you at loading your model
├── ModelConverter → More for internal purpose

````

```
net.mako.technicalities
├── Utils         → Provide usefull function and variables
```

```

net.mako.technicalities.input
├── KeyHandler    → The keyboard listener
├── MouseHandler  → The mouse listener

```

```

net.mako.rendering.utilities

├── TriangleTransform   → the method that make your life simpler
├── Triangle3DTransform → the method that make your life simpler for 3D
├── Mesh3DTransform     → everything complicated already have been made

```

---

## Example usage for 2D

```java
import net.mako.rendering.RenderPanel;
import net.mako.rendering.components.*;
import net.mako.rendering.msh.ModelParser;
import net.mako.rendering.utilities.TriangleTransform;
import net.mako.technicalities.GameThread;
import net.mako.technicalities.Utils;
import net.mako.technicalities.input.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        // WINDOW
        JFrame window = Utils.getBasicJFrame("Mako Renderer");
        window.setLayout(new BorderLayout());

        // RENDER PANEL
        RenderPanel panel = new RenderPanel();
        Utils.setBasicRenderPanelParameter(panel);

        window.add(panel, BorderLayout.CENTER);
        window.setVisible(true);

        // TRIANGLES

        Triangle t1 = new Triangle(
                new Vertex(100, 100),
                new Vertex(150, 200),
                new Vertex(200, 100),
                true,
                "move"
        );

        Triangle t2 = new Triangle(
                new Vertex(100, 100),
                new Vertex(150, 200),
                new Vertex(200, 100),
                true,
                "rotate",
                new Color(255, 0, 0)
        );

        Triangle t3 = new Triangle(
                new Vertex(100, 100),
                new Vertex(150, 200),
                new Vertex(200, 100),
                true,
                "scale"
        );

        Mesh movingMesh = t1.toMesh();
        Mesh rotatingMesh = t2.toMesh();
        Mesh scalingMesh = t3.toMesh();

        panel.addMeshToPanel(movingMesh);
        panel.addMeshToPanel(scalingMesh);
        panel.addMeshToPanel(rotatingMesh);

        // MODELS

        ModelParser basicModel =
                new ModelParser("/assets/exemple_bt.msh");

        ModelParser indexedModel =
                new ModelParser("/assets/exemple_it.msh");

        ModelParser multimeshModel =
                new ModelParser("/assets/exemple_mt.msh");

        ModelParser indexedMultimeshModel =
                new ModelParser("/assets/exemple_mit.msh");

        panel.addModelToPanel(
                basicModel
                        .getModel(basicModel.getFormatType())
                        .copy(),
                0,
                0
        );

        panel.addModelToPanel(
                indexedModel
                        .getModel(indexedModel.getFormatType())
                        .copy(),
                0,
                50
        );

        panel.addModelToPanel(
                multimeshModel
                        .getModel(multimeshModel.getFormatType())
                        .copy(),
                300,
                0
        );

        panel.addModelToPanel(
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

        panel.addSpriteToPanel(sprite);

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

            // really important, you need to refresh the panel
            panel.refresh();
            //and use refresh3D() if you use 3D
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
````

## Example usage for 3D
```java
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

public class Main3D {

    public static void main(String[] args) {

        // WINDOW
        JFrame window = Utils.getBasicJFrame("Mako Renderer");
        window.setLayout(new BorderLayout());

        // RENDER PANEL
        RenderPanel panel = new RenderPanel();
        Utils.setBasicRenderPanelParameter(panel);

        window.add(panel, BorderLayout.CENTER);
        window.setVisible(true);

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

        Triangle3D f1 = new Triangle3D(v001, v101, v111);
        Triangle3D f2 = new Triangle3D(v001, v111, v011);

        Triangle3D b1 = new Triangle3D(v000, v110, v100);
        Triangle3D b2 = new Triangle3D(v000, v010, v110);

        Triangle3D t1 = new Triangle3D(v010, v011, v111);
        Triangle3D t2 = new Triangle3D(v010, v111, v110);

        Triangle3D d1 = new Triangle3D(v000, v100, v101);
        Triangle3D d2 = new Triangle3D(v000, v101, v001);

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

        panel.addMesh3DToPanel(mesh);

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

            panel.refresh3D(camera);
        });

        game.onRender(panel::repaint);

        // START

        game.start();
    }

}
```

---

## Status

Early prototype — not optimized.
Made for learning and experimentation.

---

## Author

Made with Java and curiosity.
