package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RoundedPanel extends JPanel {

    private Image backgroundImage = null;
    private int cornerRadius = 15;

    public RoundedPanel() {
        super();
        setOpaque(false);
    }

    public RoundedPanel(Image backgroundImage) {
        super();
        setOpaque(false);
        this.backgroundImage = backgroundImage;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (backgroundImage == null) {
            super.paintComponent(g);
        }

        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rounded panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint border

        // Draw the background image if present
        if (backgroundImage != null) {
            graphics.setClip(new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius));
            graphics.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
