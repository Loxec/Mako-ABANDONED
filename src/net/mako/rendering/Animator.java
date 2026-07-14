package net.mako.rendering;

public class Animator {

    private double time = 0;


    public void update(double dt) {
        time += dt;
    }


    public double getTime() {
        return time;
    }



    // =========================
    // MOUVEMENTS
    // =========================


    // Monte et descend comme un item Minecraft
    public double bounce(double speed, double height,double multiplier) {

        return (Math.sin(time * speed) * multiplier) * height;
    }


    // Mouvement de vague gauche/droite
    public double wave(double speed, double distance) {

        return Math.sin(time * speed) * distance;
    }



    // Tremblement rapide
    public double shake(double speed, double power) {

        return Math.sin(time * speed * 10) * power;
    }

    // =========================
    // SCALE
    // =========================


    // Respiration (grossit/rétrécit)
    public double breathing(double speed, double amount) {

        return 1 + Math.sin(time * speed) * amount;
    }



    // Pulse rapide
    public double pulse(double speed, double amount) {

        return 1 + Math.abs(Math.sin(time * speed)) * amount;
    }



    // =========================
    // SPECIAL
    // =========================


    // Bob Minecraft (item + rotation)
    public double itemBob(double speed, double height) {

        return Math.sin(time * speed) * height;
    }



    // Mouvement pendule
    public double pendulum(double speed, double angle) {

        return Math.sin(time * speed) * angle;
    }



    // Mouvement élastique
    public double spring(double speed, double power) {

        return Math.sin(time * speed)
                * Math.exp(-time * 0.5)
                * power;
    }



    // Retourne entre 0 et 1
    public double normalizedSin(double speed) {

        return (Math.sin(time * speed) + 1) / 2;
    }
}