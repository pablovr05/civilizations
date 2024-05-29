package com.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BatallaPanel extends JPanel {

    private ArrayList<ArrayList<String[]>> desarrolloBatalla;
    private int currentIndex = 0;
    private RoundedPanel logPanel;
    private JLabel logLabel;

    public BatallaPanel(ArrayList<ArrayList<String[]>> desarrolloBatalla) {
        this.desarrolloBatalla = desarrolloBatalla;
        setLayout(new BorderLayout());

        // Panel para mostrar el log
        logPanel = new RoundedPanel();
        logPanel.setLayout(new BorderLayout());
        
        // Crear y configurar la fuente
        Font logFont = new Font("Scheherazade", Font.ROMAN_BASELINE, 14); // Cambia "Comfortaa" y el tamaño 18 según sea necesario
        
        logLabel = new JLabel();
        logLabel.setFont(logFont); // Aplicar la fuente al JLabel
        logLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto horizontalmente
        logLabel.setVerticalAlignment(SwingConstants.TOP);
        logLabel.setBorder(new EmptyBorder(10,0,0,0));
        logPanel.add(logLabel, BorderLayout.CENTER);

        logPanel.setCornerRadius(20);
        logPanel.setBackground(new Color(255, 255, 255, 255));
        
        logPanel.setPreferredSize(new Dimension(725, 420));
        add(logPanel, BorderLayout.CENTER);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();

        // Crear botones con imágenes
        JButton prevButton = new JButton();
        prevButton.setOpaque(false);
        prevButton.setContentAreaFilled(false);
        prevButton.setBorder(null); // Establecer el borde como null para quitar el borde gris
        prevButton.setIcon(new ImageIcon("src\\main\\java\\com\\project\\images\\next.png")); // Ruta de la imagen

        JButton nextButton = new JButton();
        nextButton.setOpaque(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setBorder(null); // Establecer el borde como null para quitar el borde gris
        nextButton.setIcon(new ImageIcon("src\\main\\java\\com\\project\\images\\nextT.png")); // Ruta de la imagen

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex > 0) {
                    SoundPlayer.playSound("page.wav", 1);
                    currentIndex--;
                    updateLog();
                } else {
                    SoundPlayer.playSound("rename.wav", 1);
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < desarrolloBatalla.size() - 1) {
                    SoundPlayer.playSound("page.wav", 1);
                    currentIndex++;
                    updateLog();
                } else {
                    SoundPlayer.playSound("rename.wav", 1);
                }
            }
        });

        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        logPanel.setBackground(new Color(255,255,255,255));
        buttonPanel.setBackground(new Color(0, 0, 0, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        updateLog();
    }

    private void updateLog() {
        logLabel.setText("<html>" + crearLog(currentIndex).replace("\n", "<br>") + "</html>");
        logPanel.revalidate();
        logPanel.repaint();
    }

    private String crearLog(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("********************CHANGE ATTACKER********************<br>");
        sb.append("Attacks army Civilization: ").append(desarrolloBatalla.get(i).get(0)[0]).append(" attacks ").append(desarrolloBatalla.get(i).get(0)[1]).append("<br>");
        sb.append(desarrolloBatalla.get(i).get(0)[0]).append(" generates the damage = ").append(desarrolloBatalla.get(i).get(0)[2]).append("<br>");
        sb.append(desarrolloBatalla.get(i).get(0)[1]).append(" stays with armor = ").append(desarrolloBatalla.get(i).get(0)[3]).append("<br>");
        if (Integer.valueOf(desarrolloBatalla.get(i).get(0)[3]) <= 0) {
            sb.append("We eliminate ").append(desarrolloBatalla.get(i).get(0)[1]).append("<br>");
        }

        if (Integer.valueOf(desarrolloBatalla.get(i).get(0)[4]) == 1) {
            sb.append("We attack again<br>");
            sb.append("Attacks army Civilization: ").append(desarrolloBatalla.get(i).get(0)[0]).append(" attacks ").append(desarrolloBatalla.get(i).get(0)[1]).append("<br>");
            sb.append(desarrolloBatalla.get(i).get(0)[0]).append(" generates the damage = ").append(desarrolloBatalla.get(i).get(0)[2]).append("<br>");
            sb.append(desarrolloBatalla.get(i).get(0)[1]).append(" stays with armor = ").append(Integer.valueOf(desarrolloBatalla.get(i).get(0)[3]) - Integer.valueOf(desarrolloBatalla.get(i).get(0)[2])).append("<br>");
            if (Integer.valueOf(desarrolloBatalla.get(i).get(0)[3]) - Integer.valueOf(desarrolloBatalla.get(i).get(0)[2]) <= 0) {
                sb.append("We eliminate ").append(desarrolloBatalla.get(i).get(0)[1]).append("<br>");
            }
        }

        sb.append("********************CHANGE ATTACKER********************<br>");
        sb.append("Attacks army Enemy: ").append(desarrolloBatalla.get(i).get(1)[0]).append(" attacks ").append(desarrolloBatalla.get(i).get(1)[1]).append("<br>");
        sb.append(desarrolloBatalla.get(i).get(1)[0]).append(" generates the damage = ").append(desarrolloBatalla.get(i).get(1)[2]).append("<br>");
        sb.append(desarrolloBatalla.get(i).get(1)[1]).append(" stays with armor = ").append(desarrolloBatalla.get(i).get(1)[3]).append("<br>");
        if (Integer.valueOf(desarrolloBatalla.get(i).get(1)[3]) <= 0) {
            sb.append("Enemy eliminate ").append(desarrolloBatalla.get(i).get(1)[1]).append("<br>");
        }

        if (Integer.valueOf(desarrolloBatalla.get(i).get(1)[4]) == 1) {
            sb.append("Enemy attack again<br>");
            sb.append("Attacks army Enemy: ").append(desarrolloBatalla.get(i).get(1)[0]).append(" attacks ").append(desarrolloBatalla.get(i).get(1)[1]).append("<br>");
            sb.append(desarrolloBatalla.get(i).get(1)[0]).append(" generates the damage = ").append(desarrolloBatalla.get(i).get(1)[2]).append("<br>");
            sb.append(desarrolloBatalla.get(i).get(1)[1]).append(" stays with armor = ").append(Integer.valueOf(desarrolloBatalla.get(i).get(1)[3]) - Integer.valueOf(desarrolloBatalla.get(i).get(1)[2])).append("<br>");
            if (Integer.valueOf(desarrolloBatalla.get(i).get(1)[3]) - Integer.valueOf(desarrolloBatalla.get(i).get(1)[2]) <= 0) {
                sb.append("Enemy eliminate ").append(desarrolloBatalla.get(i).get(1)[1]).append("<br>");
            }
        }
        return sb.toString();
    }
}
