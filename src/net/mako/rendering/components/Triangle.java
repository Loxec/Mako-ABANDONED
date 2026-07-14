package net.mako.rendering.components;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Triangle {

    public List<Vertex> vertexes;
    public Color color;
    public boolean filled;
    private String tag;
    public Runnable onTagChanged;
    private int v_num = 0;

    public Triangle(Vertex v1, Vertex v2, Vertex v3,boolean filled) {
        this.color = Color.WHITE;
        this.filled = filled;
        this.tag = "Triangle";
        this.vertexes = new ArrayList<Vertex>();
        vertexes.add(v1);
        vertexes.add(v2);
        vertexes.add(v3);
    }

    public Mesh toMesh() {
        return new Mesh(
                java.util.List.of(
                        this
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

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setVertexes(List<Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    public Triangle copy() {

        Triangle copy = new Triangle(
                vertexes.get(0).copy(),
                vertexes.get(1).copy(),
                vertexes.get(2).copy(),
                filled
        );

        copy.setTag(getTag());
        copy.setColor(getColor());

        return copy;
    }
}
