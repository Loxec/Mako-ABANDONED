package net.mako.rendering.components;

import java.util.ArrayList;
import java.util.List;

public class Mesh {

    public List<Triangle> triangles;

    public Mesh(List<Triangle> triangles) {
        this.triangles = triangles;
    }

    public Model getModel() {
        List<Mesh> meshes = new ArrayList<>();
        meshes.add(this);
        return new Model(meshes);
    }

    public List<Triangle> getTriangles() {
        return triangles;
    }

    public void setTriangles(List<Triangle> triangles) {
        this.triangles = triangles;
    }

    public Mesh copy() {

        List<Triangle> copiedTriangles = new ArrayList<>();

        for (Triangle triangle : triangles) {
            copiedTriangles.add(triangle.copy());
        }

        return new Mesh(copiedTriangles);
    }
}
