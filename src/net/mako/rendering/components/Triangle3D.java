package net.mako.rendering.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle3D {

    public Vertex3D v1;
    public Vertex3D v2;
    public Vertex3D v3;

    public List<Vertex3D> vertexes;

    public Triangle3D(Vertex3D v1, Vertex3D v2, Vertex3D v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;

        this.vertexes = Arrays.asList(v1,v2,v3);
    }

    public Vertex3D getV1() {
        return v1;
    }
    public Vertex3D getV2() {
        return v2;
    }
    public Vertex3D getV3() {
        return v3;
    }

    public void setV1(Vertex3D v1) {
        this.v1 = v1;
    }
    public void setV2(Vertex3D v2) {
        this.v2 = v2;
    }
    public void setV3(Vertex3D v3) {
        this.v3 = v3;
    }
}