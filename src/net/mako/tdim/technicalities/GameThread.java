package net.mako.tdim.technicalities;

import java.util.function.Consumer;

public class GameThread extends Thread {

    private Runnable startCallback;
    private Runnable renderCallback;
    private Runnable stopCallback;
    private Consumer<Double> updateCallback;

    private volatile boolean running;
    private volatile boolean paused;

    private final int targetFPS;

    private double deltaTime;
    private double fps;

    public GameThread() {
        this(60);
    }

    public GameThread(int targetFPS) {
        this.targetFPS = targetFPS;
    }

    public void onStart(Runnable callback) {
        this.startCallback = callback;
    }

    public void onUpdate(Consumer<Double> callback) {
        this.updateCallback = callback;
    }

    public void onRender(Runnable callback) {
        this.renderCallback = callback;
    }

    public void onStop(Runnable callback) {
        this.stopCallback = callback;
    }

    public void pauseGame() {
        paused = true;
    }

    public void resumeGame() {
        paused = false;
    }

    public void stopGame() {
        running = false;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isRunning() {
        return running;
    }

    public double getFPS() {
        return fps;
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    @Override
    public void run() {

        running = true;

        if (startCallback != null) {
            startCallback.run();
        }

        long lastTime = System.nanoTime();
        long frameDuration = 1_000_000_000L / targetFPS;

        while (running) {

            long frameStart = System.nanoTime();

            deltaTime = (frameStart - lastTime) / 1_000_000_000.0;
            lastTime = frameStart;

            if (!paused) {

                if (updateCallback != null) {
                    updateCallback.accept(deltaTime);
                }

                if (renderCallback != null) {
                    renderCallback.run();
                }
            }

            fps = 1.0 / Math.max(deltaTime, 0.000001);

            long frameTime = System.nanoTime() - frameStart;
            long sleepTime = (frameDuration - frameTime) / 1_000_000;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }

        if (stopCallback != null) {
            stopCallback.run();
        }
    }
}