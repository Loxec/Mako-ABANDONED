package net.mako.rendering.components;

import net.mako.rendering.Camera;
import net.mako.technicalities.Utils;

import java.util.ArrayList;
import java.util.List;

public class Mesh3D {

    private List<Triangle3D> triangles;

    public Mesh3D(List<Triangle3D> triangles) {
        this.triangles = triangles;
    }

    public List<Triangle3D> getTriangles() {
        return triangles;
    }

    public void setTriangles(List<Triangle3D> triangles) {
        this.triangles = triangles;
    }

    public Mesh toMesh(Camera camera) {
        List<Triangle> triangles_new = new ArrayList<>();

        for (Triangle3D t : triangles) {
            triangles_new.add(Utils.T3dTT2d(t, camera));
        }
        return new Mesh(triangles_new);
    }

}