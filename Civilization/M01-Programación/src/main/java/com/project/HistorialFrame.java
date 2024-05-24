package com.project;

import javax.swing.*;
import java.awt.*;

public class HistorialFrame extends JPanel {

    public JButton exitButtonHistorial;

    public HistorialFrame() {
        // Establece el diseño del panel como BorderLayout
        setLayout(new BorderLayout());
        
        // Crea un JLabel con el texto "Estadísticas"
        JLabel titleLabel = new JLabel("Historial");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto horizontalmente
        
        // Agrega el JLabel al centro del panel
        add(titleLabel, BorderLayout.CENTER);

        // Crea un JButton con el texto "Salir"
        exitButtonHistorial = new JButton("Salir");

        // Agrega el botón "Salir" al sur del panel
        add(exitButtonHistorial, BorderLayout.SOUTH);
    }
}
