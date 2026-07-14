package net.mako.rendering.utilities;

import net.mako.rendering.components.Mesh3D;
import net.mako.rendering.components.Triangle3D;
import net.mako.rendering.components.Vertex3D;

import java.util.HashSet;
import java.util.Set;

public class Mesh3DTransform {


    public static void rotateY(double angleDegrees, Mesh3D mesh) {

        double angle = Math.toRadians(angleDegrees);

        Set<Vertex3D> vertices = getVertices(mesh);


        double centerX = 0;
        double centerZ = 0;


        for(Vertex3D v : vertices) {
            centerX += v.getX();
            centerZ += v.getZ();
        }


        centerX /= vertices.size();
        centerZ /= vertices.size();



        for(Vertex3D v : vertices) {

            double dx = v.getX() - centerX;
            double dz = v.getZ() - centerZ;


            double newX =
                    dx * Math.cos(angle)
                            - dz * Math.sin(angle);


            double newZ =
                    dx * Math.sin(angle)
                            + dz * Math.cos(angle);


            v.setX(newX + centerX);
            v.setZ(newZ + centerZ);
        }
    }





    public static void rotateX(double angleDegrees, Mesh3D mesh) {

        double angle = Math.toRadians(angleDegrees);

        Set<Vertex3D> vertices = getVertices(mesh);


        double centerY = 0;
        double centerZ = 0;


        for(Vertex3D v : vertices) {
            centerY += v.getY();
            centerZ += v.getZ();
        }


        centerY /= vertices.size();
        centerZ /= vertices.size();



        for(Vertex3D v : vertices) {

            double dy = v.getY() - centerY;
            double dz = v.getZ() - centerZ;


            double newY =
                    dy * Math.cos(angle)
                            - dz * Math.sin(angle);


            double newZ =
                    dy * Math.sin(angle)
                            + dz * Math.cos(angle);


            v.setY(newY + centerY);
            v.setZ(newZ + centerZ);
        }
    }





    public static void rotateZ(double angleDegrees, Mesh3D mesh) {

        double angle = Math.toRadians(angleDegrees);

        Set<Vertex3D> vertices = getVertices(mesh);


        double centerX = 0;
        double centerY = 0;


        for(Vertex3D v : vertices) {
            centerX += v.getX();
            centerY += v.getY();
        }


        centerX /= vertices.size();
        centerY /= vertices.size();



        for(Vertex3D v : vertices) {

            double dx = v.getX() - centerX;
            double dy = v.getY() - centerY;


            double newX =
                    dx * Math.cos(angle)
                            - dy * Math.sin(angle);


            double newY =
                    dx * Math.sin(angle)
                            + dy * Math.cos(angle);


            v.setX(newX + centerX);
            v.setY(newY + centerY);
        }
    }





    public static void translate(double x, double y, double z, Mesh3D mesh) {

        Set<Vertex3D> vertices = getVertices(mesh);


        for(Vertex3D v : vertices) {

            v.setX(v.getX() + x);
            v.setY(v.getY() + y);
            v.setZ(v.getZ() + z);

        }
    }





    public static void scale(double scale, Mesh3D mesh) {

        Set<Vertex3D> vertices = getVertices(mesh);


        double centerX = 0;
        double centerY = 0;
        double centerZ = 0;


        for(Vertex3D v : vertices) {

            centerX += v.getX();
            centerY += v.getY();
            centerZ += v.getZ();

        }


        centerX /= vertices.size();
        centerY /= vertices.size();
        centerZ /= vertices.size();



        for(Vertex3D v : vertices) {

            v.setX(
                    centerX + (v.getX() - centerX) * scale
            );

            v.setY(
                    centerY + (v.getY() - centerY) * scale
            );

            v.setZ(
                    centerZ + (v.getZ() - centerZ) * scale
            );
        }
    }





    private static Set<Vertex3D> getVertices(Mesh3D mesh) {

        Set<Vertex3D> vertices = new HashSet<>();


        for(Triangle3D t : mesh.getTriangles()) {

            vertices.addAll(t.vertexes);

        }


        return vertices;
    }
}