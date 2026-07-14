package net.mako.rendering.components;

public class Vertex3D {

    public double x;
    public double y;
    public double z;


    public Vertex3D(double x, double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    public void setX(double newX){
        this.x = newX;
    }

    public void setY(double newY){
        this.y = newY;
    }

    public void setZ(double newZ){
        this.z = newZ;
    }

    public Vertex3D copy() {
        return new Vertex3D(x, y,z);
    }

}
