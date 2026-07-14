package net.mako.rendering;

import net.mako.technicalities.input.MouseHandler;
import net.mako.rendering.components.*;
import net.mako.technicalities.input.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RenderPanel extends JPanel {

    private final List<Mesh> meshes = Collections.synchronizedList(new ArrayList<>());
    private final List<Mesh3D> meshes3D = Collections.synchronizedList(new ArrayList<>());
    private final List<Sprite> sprites = Collections.synchronizedList(new ArrayList<>());

    private final List<Mesh> deferred_meshes_register = Collections.synchronizedList(new ArrayList<>());
    private final List<Mesh3D> deferred_meshes3D_register = Collections.synchronizedList(new ArrayList<>());
    private final List<Sprite> deferred_sprites_register = Collections.synchronizedList(new ArrayList<>());

    public Camera camera;

    public RenderPanel() {

        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setDoubleBuffered(true);

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.camera = new Camera(
                0,
                0,
                0,
                0,
                0,
                500
        );
    }

    public void add(Model model, double cx, double cy) {
        for (Mesh mesh : new ArrayList<>(model.getMeshes())) {

            for (Triangle t : mesh.getTriangles()) {

                for (Vertex v : t.getVertexes()) {
                    v.setX(v.getX() + cx);
                    v.setY(v.getY() + cy);
                }
            }

            deferred_meshes_register.add(mesh);
        }
    }

    public void add(Mesh mesh) {
        deferred_meshes_register.add(mesh);
    }

    public void add(Mesh3D mesh) {
        deferred_meshes3D_register.add(mesh);
    }

    public void add(Sprite sprite) {
        deferred_sprites_register.add(sprite);
    }

    public void remove(Mesh mesh) {
        deferred_meshes_register.remove(mesh);
    }

    public void remove(Mesh3D mesh) {
        deferred_meshes3D_register.remove(mesh);
    }

    public void remove(Sprite sprite) {
        deferred_sprites_register.remove(sprite);
    }

    public void removeAllMeshes() {
        deferred_meshes_register.clear();
    }

    public void removeAllMesh3Ds() {
        deferred_meshes3D_register.clear();
    }

    public void removeAllSprites() {
        deferred_sprites_register.clear();
    }

    public boolean contains(Mesh mesh) {
        return deferred_meshes_register.contains(mesh);
    }

    public boolean contains(Sprite sprite) {
        return deferred_sprites_register.contains(sprite);
    }

    public void clear() {
        deferred_meshes_register.clear();
        deferred_sprites_register.clear();
        deferred_meshes3D_register.clear();
    }

    public int getMeshCount() {
        return deferred_meshes_register.size();
    }

    public int getSpriteCount() {
        return deferred_sprites_register.size();
    }

    public int getMesh3DCount(){
        return deferred_meshes3D_register.size();
    }

    public void addKeyHandlerToPanel(KeyHandler keyHandler) {
        this.addKeyListener(keyHandler);
    }

    public void addMouseHandlerToPanel(MouseHandler mouseHandler) {
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    private void drawMeshes(Graphics2D g2d) {
        synchronized (this.meshes) {
            for (Mesh mesh : this.meshes) {
                for (Triangle t : mesh.getTriangles()) {
                    g2d.setColor(t.color);
                    Path2D path = new Path2D.Double();
                    path.moveTo(t.vertexes.get(0).getX(), t.vertexes.get(0).getY());
                    path.lineTo(t.vertexes.get(1).getX(), t.vertexes.get(1).getY());
                    path.lineTo(t.vertexes.get(2).getX(), t.vertexes.get(2).getY());
                    path.closePath();
                    if (t.filled) {
                        g2d.fill(path);
                    }

                    g2d.draw(path);
                }
            }
        }
    }

    private void drawMesh3D(
            Graphics2D g2d,
            Mesh3D mesh,
            Camera camera
    ) {

        for(Triangle3D t : mesh.getTriangles()) {

            g2d.setColor(t.color);

            Point p1 = camera.project(t.vertexes.get(0));
            Point p2 = camera.project(t.vertexes.get(1));
            Point p3 = camera.project(t.vertexes.get(2));

            Path2D path = new Path2D.Double();

            path.moveTo(p1.x,p1.y);
            path.lineTo(p2.x,p2.y);
            path.lineTo(p3.x,p3.y);
            path.closePath();

            g2d.draw(path);
        }
    }

    private void drawSprites(Graphics2D g2d) {
        synchronized (this.sprites) {
            for (Sprite sprite : this.sprites) {
                if (sprite.getImage() != null) {
                    AffineTransform at = new AffineTransform();

                    at.translate(sprite.getX(), sprite.getY());

                    at.rotate(
                            Math.toRadians(sprite.getRotation()),
                            sprite.getScaleX() / 2,
                            sprite.getScaleY() / 2
                    );

                    at.scale(
                            sprite.getScaleX() / sprite.getImage().getWidth(),
                            sprite.getScaleY() / sprite.getImage().getHeight()
                    );

                    g2d.drawImage(sprite.getImage(), at, null);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        drawMeshes(g2d);
        for(Mesh3D mesh : meshes3D) {
            drawMesh3D(g2d,mesh,camera);
        }
        drawSprites(g2d);
        g2d.dispose();
    }

    public void refresh() {

        meshes.clear();
        sprites.clear();
        meshes3D.clear();

        meshes.addAll(deferred_meshes_register);
        sprites.addAll(deferred_sprites_register);
        meshes3D.addAll(deferred_meshes3D_register);
    }

    public List<Mesh> getMeshes() {
        return deferred_meshes_register;
    }
    public List<Sprite> getSprites() {
        return deferred_sprites_register;
    }
    public List<Mesh3D> getMesh3Ds() {return deferred_meshes3D_register;}


    public Camera getCamera() {return camera;}

    public void setCamera(Camera camera) {this.camera = camera;}

}
