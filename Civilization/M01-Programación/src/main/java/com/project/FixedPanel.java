package com.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class FixedPanel extends JPanel {

    private JButton madera;
    private JButton comida;
    private JButton hierro;
    private JButton maná;

    private String qmadera = "1233334";
    private String qcomida = "2345444";
    private String qhierro = "2345446";
    private String qmaná = "1244434";

    public FixedPanel() {
        setOpaque(false); // Hace que el panel sea transparente
        setPreferredSize(new Dimension(200, 200)); // Establece el tamaño del panel

        madera = createButton("Madera: " + qmadera, "images/madera.png");
        comida = createButton("Comida: " + qcomida, "images/naranja.png");
        hierro = createButton("Hierro: " + qhierro, "images/minerals.png");
        maná = createButton("Maná: " + qmaná, "images/mana.png");

        // Cambia el layout a BoxLayout con orientación vertical
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(madera);
        add(comida);
        add(hierro);
        add(maná);
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        
        // Carga la imagen del icono y la escala a 24x24 píxeles
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledIcon = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon scaledIconImage = new ImageIcon(scaledIcon);
        
        // Establece el icono a la izquierda del botón
        button.setIcon(scaledIconImage);
        button.setHorizontalTextPosition(SwingConstants.RIGHT); // Coloca el texto a la derecha del icono
        
        // Ajusta el tamaño del botón para adaptarse al contenido
        button.setPreferredSize(new Dimension(200, 30)); // Altura de 40 píxeles

        button.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(new Color(0,0,0));

        button.setBorder(new EmptyBorder(7,0,0,0));
        
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja un fondo semitransparente para el panel
        g.setColor(new Color(255, 255, 255, 100)); // Color negro semitransparente
        g.fillRect(210, 20, 140, 110);
    }
}
