package net.mako.tdim.technicalities.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.function.Consumer;

public class MouseHandler implements MouseListener, MouseMotionListener {

    public Consumer<MouseEvent> onMouseClicked;
    public Consumer<MouseEvent> onMousePressed;
    public Consumer<MouseEvent> onMouseReleased;
    public Consumer<MouseEvent> onMouseEntered;
    public Consumer<MouseEvent> onMouseExited;

    public Consumer<MouseEvent> onMouseDragged;
    public Consumer<MouseEvent> onMouseMoved;

    @Override
    public void mouseClicked(MouseEvent e) {
        if(onMouseClicked != null) {
            onMouseClicked.accept(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(onMousePressed != null) {
            onMousePressed.accept(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(onMouseReleased != null) {
            onMouseReleased.accept(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(onMouseEntered != null) {
            onMouseEntered.accept(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(onMouseExited != null) {
            onMouseExited.accept(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(onMouseDragged != null) {
            onMouseDragged.accept(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(onMouseMoved != null) {
            onMouseMoved.accept(e);
        }
    }
}
