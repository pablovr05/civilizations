package com.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TecnologíasFrame extends JPanel {

    public JButton exitButtonTecnologías;
    public JButton upgradeAttack;
    public JButton upgradeDefense;

    public int comida1;
    public int madera1;
    public int hierro1;
    public int comida2;
    public int madera2;
    public int hierro2;

    public TecnologíasFrame() {

        setLayout(new BorderLayout());

        JLabel background = new JLabel(new ImageIcon("src\\main\\java\\com\\project\\images\\probar1.gif"));
        background.setLayout(new BorderLayout());
        add(background, BorderLayout.CENTER);

        // Agregar un título encima del panel
        JLabel titleLabel = new JLabel("Tecnologías");
        titleLabel.setFont(new Font("Reem Kufi", Font.BOLD, 34));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(0,0,10,0));
        background.add(titleLabel, BorderLayout.NORTH);

        // Añadir un borde interno para el espacio alrededor del contenido principal
        background.setBorder(new EmptyBorder(50, 75, 50, 75));

        // Crear el panel izquierdo
        RoundedPanel leftPanel = new RoundedPanel();
        leftPanel.setCornerRadius(20);
        leftPanel.setPreferredSize(new Dimension(150, 175));
        leftPanel.setBackground(new Color(0, 0, 0, 175));

        JLabel titleLeft = new JLabel("Ataque");
        titleLeft.setFont(new Font("Reem Kufi", Font.BOLD, 16));
        titleLeft.setForeground(Color.WHITE);
        leftPanel.add(titleLeft);

        JLabel material1 = createLabelWithImage(String.valueOf(comida1), "src\\main\\java\\com\\project\\images\\naranja.png");
        TooltipHelper.setCustomTooltip(material1, "Cantidad de comida necesaria para mejorar el ataque");
        JLabel material2 = createLabelWithImage(String.valueOf(madera1), "src\\main\\java\\com\\project\\images\\madera.png");
        TooltipHelper.setCustomTooltip(material2, "Cantidad de madera necesaria para mejorar el ataque");
        JLabel material3 = createLabelWithImage(String.valueOf(hierro1), "src\\main\\java\\com\\project\\images\\minerals.png");
        TooltipHelper.setCustomTooltip(material3, "Cantidad de hierro necesaria para mejorar el ataque");

        upgradeAttack = new JButton("Upgrade");
        upgradeAttack.setActionCommand("upgradeAttack");
        upgradeAttack.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        upgradeAttack.setOpaque(false);
        upgradeAttack.setContentAreaFilled(false);
        upgradeAttack.setBorderPainted(false);
        upgradeAttack.setFocusPainted(false);
        upgradeAttack.setForeground(Color.WHITE);

        upgradeAttack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                upgradeAttack.setForeground(Color.green); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                upgradeAttack.setForeground(Color.WHITE); // Restaura el color de fondo cuando el cursor sale
            }
        });

        leftPanel.add(material1);
        leftPanel.add(material2);
        leftPanel.add(material3);

        leftPanel.add(upgradeAttack);

        background.add(leftPanel, BorderLayout.WEST);

        // Crear el panel derecho
        RoundedPanel rightPanel = new RoundedPanel();
        rightPanel.setCornerRadius(20);
        rightPanel.setPreferredSize(new Dimension(150, 100));
        rightPanel.setBackground(new Color(0, 0, 0, 175));

        JLabel titleRight = new JLabel("Defensa");
        titleRight.setFont(new Font("Reem Kufi", Font.BOLD, 16));
        titleRight.setForeground(Color.WHITE);
        rightPanel.add(titleRight);

        JLabel material4 = createLabelWithImage(String.valueOf(comida2), "src\\main\\java\\com\\project\\images\\naranja.png");
        TooltipHelper.setCustomTooltip(material4, "Cantidad de comida necesaria para mejorar la defensa");
        JLabel material5 = createLabelWithImage(String.valueOf(madera2), "src\\main\\java\\com\\project\\images\\madera.png");
        TooltipHelper.setCustomTooltip(material5, "Cantidad de madera necesaria para mejorar la defensa");
        JLabel material6 = createLabelWithImage(String.valueOf(hierro2), "src\\main\\java\\com\\project\\images\\minerals.png");
        TooltipHelper.setCustomTooltip(material6, "Cantidad de hierro necesaria para mejorar la defensa");

        upgradeDefense = new JButton("Upgrade");
        upgradeDefense.setActionCommand("upgradeDefense");
        upgradeDefense.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        upgradeDefense.setOpaque(false);
        upgradeDefense.setContentAreaFilled(false);
        upgradeDefense.setBorderPainted(false);
        upgradeDefense.setFocusPainted(false);
        upgradeDefense.setForeground(Color.WHITE);

        upgradeDefense.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                upgradeDefense.setForeground(Color.green); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                upgradeDefense.setForeground(Color.WHITE); // Restaura el color de fondo cuando el cursor sale
            }
        });

        rightPanel.add(material4);
        rightPanel.add(material5);
        rightPanel.add(material6);

        rightPanel.add(upgradeDefense);

        background.add(rightPanel, BorderLayout.EAST);

        // Agregar un botón en la esquina inferior derecha
        JPanel panelbuton = new JPanel();
        exitButtonTecnologías = new JButton("Salir");
        exitButtonTecnologías.setFont(new Font("Reem Kufi", Font.ROMAN_BASELINE, 12));
        exitButtonTecnologías.setOpaque(true);
        exitButtonTecnologías.setContentAreaFilled(false);
        exitButtonTecnologías.setBorderPainted(false);
        exitButtonTecnologías.setFocusPainted(false);

        RoundedPanel panelr = new RoundedPanel();
        panelr.setCornerRadius(10);

        panelr.add(exitButtonTecnologías);

        panelbuton.add(panelr);

        panelr.setBackground(new Color(255,255,255,175));

        panelbuton.setBackground(new Color(0,0,0,0));

        panelbuton.setBorder(new EmptyBorder(10,342,0,0));

        background.add(panelbuton, BorderLayout.SOUTH);

    }

    private JLabel createLabelWithImage(String text, String imagePath) {
        // Crear un JLabel con un texto y un icono
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        JLabel label = new JLabel(text, icon, JLabel.LEFT);

        // Alinear el texto a la izquierda
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(Color.WHITE);

        label.setBorder(new EmptyBorder(3,30,3,30));

        return label;
    }
}
