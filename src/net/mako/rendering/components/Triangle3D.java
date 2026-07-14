package net.mako.rendering.components;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Triangle3D {

    public List<Vertex3D> vertexes;
    public Color color;
    public boolean filled;
    private String tag;
    public Runnable onTagChanged;
    private int v_num = 0;

    public Triangle3D(Vertex3D v1, Vertex3D v2, Vertex3D v3,boolean filled) {
        this.color = Color.WHITE;
        this.filled = filled;
        this.tag = "Triangle";
        this.vertexes = new ArrayList<Vertex3D>();
        vertexes.add(v1);
        vertexes.add(v2);
        vertexes.add(v3);
    }

    public Mesh3D toMesh() {
        return new Mesh3D(
                java.util.List.of(
                        this.copy()
                )
        );
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
        if(onTagChanged != null) {
            onTagChanged.run();
        }
    }

    public int getV_num() {
        return v_num;
    }

    public void setV_num(int v_num) {
        this.v_num = v_num;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Vertex3D> getVertexes() {
        return vertexes;
    }

    public void setVertexes(List<Vertex3D> vertexes) {
        this.vertexes = vertexes;
    }

    public Triangle3D copy() {
        Triangle3D copy = new Triangle3D(vertexes.get(0).copy(),vertexes.get(1).copy(),vertexes.get(2).copy(),filled);

        copy.setTag(getTag());
        copy.setColor(getColor());

        return copy;
    }
}
