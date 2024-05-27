package com.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InfoWindow extends JDialog {
    public InfoWindow(JFrame parentFrame, String title, String message) {
        super(parentFrame, "Información", true);
        // Configurar el JDialog
        setSize(300, 150);
        setLocationRelativeTo(parentFrame); // Centrar el JDialog con respecto al JFrame
        setLayout(new BorderLayout());

        // Añadir texto
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Scheherazade", Font.ROMAN_BASELINE, 18));
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 10));
        titleLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(titleLabel, BorderLayout.NORTH);
        add(messageLabel, BorderLayout.CENTER);

        // Añadir botón "Aceptar"
        RoundedPanel buttonPanel = new RoundedPanel();
        buttonPanel.setCornerRadius(12);
        buttonPanel.setBackground(new Color(255,255,255));
        JButton acceptButton = new JButton("Aceptar");
        acceptButton.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 10));
        acceptButton.setOpaque(false);
        acceptButton.setContentAreaFilled(false);
        acceptButton.setBorderPainted(false);
        acceptButton.setFocusPainted(false);
        
        // Agregar el MouseListener al botón
        acceptButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // El ratón está sobre el botón
                buttonPanel.setBackground(Color.YELLOW); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // El ratón ha salido del botón
                buttonPanel.setBackground(new Color(255,255,255)); 
            }
        });

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Se pulsó el botón Aceptar");
                SoundPlayer.playSound("clickselect.wav", 0.5f);
                dispose(); 
            }
        });
        buttonPanel.add(acceptButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
