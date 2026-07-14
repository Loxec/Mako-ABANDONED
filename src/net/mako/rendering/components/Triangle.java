package net.mako.rendering.components;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Triangle {

    public Vertex v1;
    public Vertex v2;
    public Vertex v3;
    public List<Vertex> vertexes;
    public Color color;
    public boolean filled;
    private String tag;
    public Runnable onTagChanged;
    private int v_num = 0;

    public Triangle(Vertex v1, Vertex v2, Vertex v3,boolean filled) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = Color.WHITE;
        this.filled = filled;
        this.tag = "Triangle";
        this.vertexes = new ArrayList<Vertex>();
        vertexes.add(v1);
        vertexes.add(v2);
        vertexes.add(v3);
    }

    public Triangle(Vertex v1, Vertex v2, Vertex v3,boolean filled,Color color) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
        this.filled = filled;
        this.tag = "Triangle";
        this.vertexes = new ArrayList<Vertex>();
        vertexes.add(v1);
        vertexes.add(v2);
        vertexes.add(v3);
    }

    public Triangle(Vertex v1, Vertex v2, Vertex v3,boolean filled,String tag) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = Color.WHITE;
        this.filled = filled;
        this.tag = tag;
        this.vertexes = new ArrayList<Vertex>();
        vertexes.add(v1);
        vertexes.add(v2);
        vertexes.add(v3);
    }

    public Triangle(Vertex v1, Vertex v2, Vertex v3,boolean filled,Color color,String tag) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
        this.filled = filled;
        this.tag = tag;
        this.vertexes = new ArrayList<Vertex>();
        vertexes.add(v1);
        vertexes.add(v2);
        vertexes.add(v3);
    }

    public Triangle(Vertex v1, Vertex v2, Vertex v3,boolean filled,String tag,Color color) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
        this.filled = filled;
        this.tag = tag;
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

    public void reiterateVertexes(){
        vertexes.clear();
        vertexes.add(v1);
        vertexes.add(v2);
        vertexes.add(v3);
    }

    public Vertex getV1() {
        return v1;
    }

    public void setV1(Vertex v1) {
        this.v1 = v1;
        reiterateVertexes();
    }

    public Vertex getV2() {
        return v2;
    }

    public void setV2(Vertex v2) {
        this.v2 = v2;
        reiterateVertexes();
    }

    public Vertex getV3() {
        return v3;
    }

    public void setV3(Vertex v3) {
        this.v3 = v3;
        reiterateVertexes();
    }

    public void setNextVertex(Vertex v) {
        if(v_num == 0){
            this.v1 = v;
            v_num++;
        }else if(v_num == 1){
            this.v2 = v;
            v_num++;
        }else if(v_num == 2){
            this.v3 = v;
            v_num = 0;
        }

        reiterateVertexes();
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

    public void setVertexes(List<Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    public Triangle copy() {

        Triangle copy = new Triangle(
                v1.copy(),
                v2.copy(),
                v3.copy(),
                filled,
                color,
                tag
        );

        return copy;
    }
}
