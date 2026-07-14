package net.mako.rendering.utilities;

import net.mako.rendering.components.Triangle;
import net.mako.rendering.components.Vertex;

public class TriangleTransform {
    public static void translateX(double amount, Triangle triangle) {
        triangle.vertexes.get(0).setX(triangle.vertexes.get(0).getX() + amount);
        triangle.vertexes.get(1).setX(triangle.vertexes.get(1).getX() + amount);
        triangle.vertexes.get(2).setX(triangle.vertexes.get(2).getX() + amount);
    }

    public static void translateY(double amount, Triangle triangle) {
        triangle.vertexes.get(0).setY(triangle.vertexes.get(0).getY() + amount);
        triangle.vertexes.get(1).setY(triangle.vertexes.get(1).getY() + amount);
        triangle.vertexes.get(2).setY(triangle.vertexes.get(2).getY() + amount);
    }

    public static void translate(double amount_x,double amount_y, Triangle triangle) {
        triangle.vertexes.get(0).setX(triangle.vertexes.get(0).getX() + amount_x);
        triangle.vertexes.get(1).setX(triangle.vertexes.get(1).getX() + amount_x);
        triangle.vertexes.get(2).setX(triangle.vertexes.get(2).getX() + amount_x);

        triangle.vertexes.get(0).setY(triangle.vertexes.get(0).getY() + amount_y);
        triangle.vertexes.get(1).setY(triangle.vertexes.get(1).getY() + amount_y);
        triangle.vertexes.get(2).setY(triangle.vertexes.get(2).getY() + amount_y);
    }

    public static void rotate(double angleDegrees, Triangle t) {

        double angle = Math.toRadians(angleDegrees);

        double centerX = (
                t.vertexes.get(0).getX() +
                        t.vertexes.get(1).getX() +
                        t.vertexes.get(2).getX()
        ) / 3.0;

        double centerY = (
                t.vertexes.get(0).getY() +
                        t.vertexes.get(1).getY() +
                        t.vertexes.get(2).getY()
        ) / 3.0;

        for (Vertex v : t.vertexes) {

            double dx = v.getX() - centerX;
            double dy = v.getY() - centerY;

            double newX = dx * Math.cos(angle) - dy * Math.sin(angle);
            double newY = dx * Math.sin(angle) + dy * Math.cos(angle);

            v.setX(newX + centerX);
            v.setY(newY + centerY);
        }
    }

    public static void scaleUp(double amount, Triangle t) {

        double centerX = (t.vertexes.get(0).getX() + t.vertexes.get(1).getX() + t.vertexes.get(2).getX()) / 3.0;
        double centerY = (t.vertexes.get(0).getY() + t.vertexes.get(1).getY() + t.vertexes.get(2).getY()) / 3.0;

        for (Vertex v : t.vertexes) {

            double dx = v.x - centerX;
            double dy = v.y - centerY;

            v.x = centerX + dx * amount;
            v.y = centerY + dy * amount;
        }
    }

    public static void scaleDown(double amount, Triangle t) {

        double centerX = (t.vertexes.get(0).getX() + t.vertexes.get(1).getX() + t.vertexes.get(2).getX()) / 3.0;
        double centerY = (t.vertexes.get(0).getY() + t.vertexes.get(1).getY() + t.vertexes.get(2).getY()) / 3.0;

        for (Vertex v : t.vertexes) {

            double dx = v.x - centerX;
            double dy = v.y - centerY;

            v.x = centerX + dx / amount;
            v.y = centerY + dy / amount;
        }
    }


}
