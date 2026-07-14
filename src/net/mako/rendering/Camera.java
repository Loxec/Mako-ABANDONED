package net.mako.rendering;

import net.mako.rendering.components.Vertex;
import net.mako.rendering.components.Vertex3D;
import net.mako.technicalities.Utils;

import java.awt.*;

public class Camera {

    private double x;
    private double y;
    private double z;

    private double yaw;
    private double pitch;
    private double roll;

    private double fov;


    public Camera(
            double x,
            double y,
            double z,
            double yaw,
            double pitch,
            double fov
    ) {

        this.x = x;
        this.y = y;
        this.z = z;

        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = 0;

        this.fov = fov;
    }


    public Camera(
            double x,
            double y,
            double z,
            double fov
    ) {

        this.x = x;
        this.y = y;
        this.z = z;

        this.yaw = 0;
        this.pitch = 0;
        this.roll = 0;

        this.fov = fov;
    }


    // =====================
    // MOVEMENT
    // =====================


    public void move(
            double dx,
            double dy,
            double dz
    ) {

        this.x += dx;
        this.y += dy;
        this.z += dz;

    }


    public void rotate(
            double dyaw,
            double dpitch
    ) {

        this.yaw += dyaw;
        this.pitch += dpitch;

    }


    public void rotate(
            double dyaw,
            double dpitch,
            double droll
    ) {

        this.yaw += dyaw;
        this.pitch += dpitch;
        this.roll += droll;

    }



    // =====================
    // PROJECTION
    // =====================


    public Point project(Vertex3D vertex) {

        Vertex v = Utils.project(vertex,this);

        return new Point(
                (int)v.getX(),
                (int)v.getY()
        );

    }



    // =====================
    // GETTERS
    // =====================


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }


    public double getYaw() {
        return yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public double getRoll() {
        return roll;
    }


    public double getFov() {
        return fov;
    }



    // =====================
    // SETTERS
    // =====================


    public void setX(double x) {
        this.x = x;
    }


    public void setY(double y) {
        this.y = y;
    }


    public void setZ(double z) {
        this.z = z;
    }


    public void setYaw(double yaw) {
        this.yaw = yaw;
    }


    public void setPitch(double pitch) {
        this.pitch = pitch;
    }


    public void setRoll(double roll) {
        this.roll = roll;
    }


    public void setFov(double fov) {
        this.fov = fov;
    }

}