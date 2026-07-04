package net.mako.tdim.rendering.components;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sprite {

    private final String path;
    private double x;
    private double y;
    private double rotation;
    private double scaleX = 64;
    private double scaleY = 64;

    private BufferedImage image;

    public Sprite(String path, int x, int y) {
        if(path.startsWith("/")) {
            this.path = path;
        }else{
            this.path = "/"+path;
        }
        this.x = x;
        this.y = y;

        try {
            URL url = getClass().getResource(path);

            if (url == null) {
                throw new RuntimeException("Sprite not found: " + path);
            }

            image = ImageIO.read(url);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite: " + path, e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public File getFile() {
        URL url = getClass().getResource(path);

        if (url == null) {
            return null;
        }

        return new File(url.getPath());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move(double dx, double dy,double speed) {
        this.x += dx - speed;
        this.y += dy + speed;
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }
}