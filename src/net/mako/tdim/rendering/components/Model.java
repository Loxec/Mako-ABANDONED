package net.mako.tdim.rendering.components;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public List<Mesh> meshes;

    public Model(List<Mesh> mesh) {
        this.meshes = mesh;
    }

    public List<Mesh> getMeshes() {
        return meshes;
    }

    public void setMeshes(List<Mesh> meshes) {
        this.meshes = meshes;
    }

    public Model copy() {

        List<Mesh> copiedMeshes = new ArrayList<>();

        for (Mesh mesh : meshes) {
            copiedMeshes.add(mesh.copy());
        }

        return new Model(copiedMeshes);
    }

}
