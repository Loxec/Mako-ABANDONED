package net.mako.tdim.technicalities;

import net.mako.tdim.rendering.RenderPanel;

import javax.swing.*;
import java.awt.*;

public class Utils {

    public static JFrame getBasicJFrame(String title) {
        // WINDOW
        Dimension screenSize = new Dimension(800,600);
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(screenSize);
        frame.setSize(screenSize);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        return frame;
    }

    public static void closeWindow(JFrame frame) {
        frame.setVisible(false);
        frame.dispose();
    }

    public static void setBasicRenderPanelParameter(RenderPanel rp){
        rp.setBackground(Color.BLACK);
        rp.setLayout(null);
        rp.setDoubleBuffered(true);
        rp.setFocusable(true);
        rp.requestFocus();
        rp.requestFocusInWindow();
        rp.setName("default");
        rp.setVisible(true);
    }

}
