package com.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TecnologíasFrame extends JPanel {

    public JButton exitButtonTecnologías;

    public TecnologíasFrame() {

        setLayout(new BorderLayout());

        JLabel background = new JLabel(new ImageIcon("src\\main\\java\\com\\project\\images\\probar1.gif"));
        background.setLayout(new BorderLayout());
        add(background, BorderLayout.CENTER);

        // Agregar un título encima del panel
        JLabel titleLabel = new JLabel("Tecnologías");
        titleLabel.setFont(new Font("Reem Kufi", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(0,0,30,0));
        background.add(titleLabel, BorderLayout.NORTH);

        // Añadir un borde interno para el espacio alrededor del contenido principal
        background.setBorder(new EmptyBorder(50, 75, 50, 75));

        // Crear el panel izquierdo
        RoundedPanel leftPanel = new RoundedPanel();
        leftPanel.setCornerRadius(20);
        leftPanel.setPreferredSize(new Dimension(150, 175));
        leftPanel.setBackground(new Color(0, 0, 0, 175));
        background.add(leftPanel, BorderLayout.WEST);

        // Crear el panel derecho
        RoundedPanel rightPanel = new RoundedPanel();
        rightPanel.setCornerRadius(20);
        rightPanel.setPreferredSize(new Dimension(150, 100));
        rightPanel.setBackground(new Color(0, 0, 0, 175));
        background.add(rightPanel, BorderLayout.EAST);

        // Agregar un botón en la esquina inferior derecha
        RoundedPanel panelbuton = new RoundedPanel();
        panelbuton.setCornerRadius(20);

        exitButtonTecnologías = new JButton("Salir");
        
        exitButtonTecnologías.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        exitButtonTecnologías.setOpaque(true);
        exitButtonTecnologías.setContentAreaFilled(true);
        exitButtonTecnologías.setBorderPainted(false);
        exitButtonTecnologías.setFocusPainted(false);
        exitButtonTecnologías.setForeground(Color.BLACK);

        exitButtonTecnologías.setBackground(Color.green);

        panelbuton.add(exitButtonTecnologías);

        panelbuton.setBackground(new Color(255,0,0,100));

        background.add(panelbuton, BorderLayout.SOUTH);

    }
}
