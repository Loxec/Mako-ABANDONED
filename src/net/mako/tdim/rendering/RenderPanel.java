package net.mako.tdim.rendering;

import net.mako.tdim.technicalities.input.MouseHandler;
import net.mako.tdim.rendering.components.*;
import net.mako.tdim.technicalities.input.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RenderPanel extends JPanel {

    private final List<Mesh> meshes = Collections.synchronizedList(new ArrayList<>());
    private final List<Sprite> sprites = Collections.synchronizedList(new ArrayList<>());

    public RenderPanel() {

        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setVisible(true);

    }

    public void addMeshToPanel(Mesh mesh) {
        meshes.add(mesh);
    }
    public void addSpriteToPanel(Sprite sprite) {
        sprites.add(sprite);
    }
    public void addModelToPanel(Model model, double cx, double cy) {
        for (Mesh mesh : new ArrayList<>(model.getMeshes())) {

            for (Triangle t : mesh.getTriangles()) {

                for (Vertex v : new ArrayList<>(t.getVertexes())) {

                    Vertex newV = new Vertex(cx + v.getX(), cy + v.getY());

                    t.setNextVertex(newV);
                }
            }

            meshes.add(mesh);
        }
    }

    public void removeMeshFromPanel(Mesh mesh) {
        meshes.remove(mesh);
    }

    public void removeSpriteFromPanel(Sprite sprite) {
        sprites.remove(sprite);
    }

    public void removeAllMeshes() {
        meshes.clear();
    }

    public void removeAllSprites() {
        sprites.clear();
    }

    public boolean containsMesh(Mesh mesh) {
        return meshes.contains(mesh);
    }

    public boolean containsSprite(Sprite sprite) {
        return sprites.contains(sprite);
    }

    public void clear() {
        meshes.clear();
        sprites.clear();
    }

    public int getMeshCount() {
        return meshes.size();
    }

    public int getSpriteCount() {
        return sprites.size();
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
                    path.moveTo(t.v1.getX(), t.v1.getY());
                    path.lineTo(t.v2.getX(), t.v2.getY());
                    path.lineTo(t.v3.getX(), t.v3.getY());
                    path.closePath();
                    g2d.draw(path);

                    if (t.filled) {
                        g2d.fill(path);
                    }
                }
            }
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
        drawSprites(g2d);
        g2d.dispose();
    }

    public List<Mesh> getMeshes() {
        return this.meshes;
    }
    public List<Sprite> getSprites() {
        return this.sprites;
    }

}
