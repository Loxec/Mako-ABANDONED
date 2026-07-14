package net.mako.rendering.utilities;

import net.mako.rendering.components.Triangle3D;
import net.mako.rendering.components.Vertex3D;

public class Triangle3DTransform {

    public static void translateX(double amount, Triangle3D triangle) {
        for (Vertex3D v : triangle.vertexes) {
            v.setX(v.getX() + amount);
        }
    }

    public static void translateY(double amount, Triangle3D triangle) {
        for (Vertex3D v : triangle.vertexes) {
            v.setY(v.getY() + amount);
        }
    }

    public static void translateZ(double amount, Triangle3D triangle) {
        for (Vertex3D v : triangle.vertexes) {
            v.setZ(v.getZ() + amount);
        }
    }


    public static void translate(double x, double y, double z, Triangle3D triangle) {
        for (Vertex3D v : triangle.vertexes) {
            v.setX(v.getX() + x);
            v.setY(v.getY() + y);
            v.setZ(v.getZ() + z);
        }
    }


    // Rotation autour de l'axe X
    public static void rotateX(double angleDegrees, Triangle3D t) {

        double angle = Math.toRadians(angleDegrees);

        double centerY = (
                t.v1.getY() +
                        t.v2.getY() +
                        t.v3.getY()
        ) / 3.0;

        double centerZ = (
                t.v1.getZ() +
                        t.v2.getZ() +
                        t.v3.getZ()
        ) / 3.0;


        for(Vertex3D v : t.vertexes) {

            double dy = v.getY() - centerY;
            double dz = v.getZ() - centerZ;


            double newY = dy * Math.cos(angle) - dz * Math.sin(angle);
            double newZ = dy * Math.sin(angle) + dz * Math.cos(angle);


            v.setY(newY + centerY);
            v.setZ(newZ + centerZ);
        }
    }



    // Rotation autour de l'axe Y
    public static void rotateY(double angleDegrees, Triangle3D t) {

        double angle = Math.toRadians(angleDegrees);

        double centerX = (
                t.v1.getX() +
                        t.v2.getX() +
                        t.v3.getX()
        ) / 3.0;

        double centerZ = (
                t.v1.getZ() +
                        t.v2.getZ() +
                        t.v3.getZ()
        ) / 3.0;


        for(Vertex3D v : t.vertexes) {

            double dx = v.getX() - centerX;
            double dz = v.getZ() - centerZ;


            double newX = dx * Math.cos(angle) + dz * Math.sin(angle);
            double newZ = -dx * Math.sin(angle) + dz * Math.cos(angle);


            v.setX(newX + centerX);
            v.setZ(newZ + centerZ);
        }
    }



    // Rotation autour de l'axe Z (comme en 2D)
    public static void rotateZ(double angleDegrees, Triangle3D t) {

        double angle = Math.toRadians(angleDegrees);

        double centerX = (
                t.v1.getX() +
                        t.v2.getX() +
                        t.v3.getX()
        ) / 3.0;

        double centerY = (
                t.v1.getY() +
                        t.v2.getY() +
                        t.v3.getY()
        ) / 3.0;


        for(Vertex3D v : t.vertexes) {

            double dx = v.getX() - centerX;
            double dy = v.getY() - centerY;


            double newX = dx * Math.cos(angle) - dy * Math.sin(angle);
            double newY = dx * Math.sin(angle) + dy * Math.cos(angle);


            v.setX(newX + centerX);
            v.setY(newY + centerY);
        }
    }



    public static void scale(double amount, Triangle3D t) {

        double centerX = (
                t.v1.getX() +
                        t.v2.getX() +
                        t.v3.getX()
        ) / 3.0;

        double centerY = (
                t.v1.getY() +
                        t.v2.getY() +
                        t.v3.getY()
        ) / 3.0;

        double centerZ = (
                t.v1.getZ() +
                        t.v2.getZ() +
                        t.v3.getZ()
        ) / 3.0;


        for(Vertex3D v : t.vertexes) {

            v.setX(centerX + (v.getX() - centerX) * amount);
            v.setY(centerY + (v.getY() - centerY) * amount);
            v.setZ(centerZ + (v.getZ() - centerZ) * amount);

        }
    }
}