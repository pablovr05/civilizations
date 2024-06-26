package com.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class EjércitoFrame extends JPanel {

    public JTextField textFieldEjército;
    public JButton cancelButtonEjército;
    public JButton acceptButtonEjército;
    public JComboBox<String> optionsComboBoxEjército;
    public JButton gifButton; // Botón sobre el GIF

    public EjércitoFrame() {
        // Establece el diseño del panel como BorderLayout
        setLayout(new BorderLayout());

        // Panel izquierdo para opciones
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("src\\main\\java\\com\\project\\images\\Fondocrear.jpg")); // Ruta de la imagen
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        leftPanel.setLayout(new GridLayout(6, 1, 5, 5)); // GridLayout para organizar los componentes verticalmente y con espaciado

        // Título de Entrenar tropas
        JLabel entrenarTropasLabel = new JLabel("Entrenar tropas");
        entrenarTropasLabel.setFont(new Font("Candara", Font.BOLD, 24)); // Tamaño de fuente más grande
        entrenarTropasLabel.setForeground(Color.WHITE); // Color blanco
        leftPanel.add(entrenarTropasLabel);

        // Tipo de tropa
        JLabel typeTropaLabel = new JLabel("Tipo de tropa:");
        typeTropaLabel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12)); // Tamaño de fuente más grande
        typeTropaLabel.setForeground(Color.WHITE); // Color blanco
        leftPanel.add(typeTropaLabel);

        // ComboBox con opciones
        String[] opciones = {"Swordsman", "Spearman", "Crossbow", "Cannon", "Arrow Tower", "Catapult", "Rocket Launcher Tower", "Magician", "Priest"};
        optionsComboBoxEjército = new JComboBox<>(opciones);
        optionsComboBoxEjército.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        leftPanel.add(optionsComboBoxEjército);

        // Cantidad de tropas
        JLabel cantidadTropaLabel = new JLabel("Cantidad de tropas:");
        cantidadTropaLabel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12)); // Tamaño de fuente más grande
        cantidadTropaLabel.setForeground(Color.WHITE); // Color blanco
        leftPanel.add(cantidadTropaLabel);

        // Campo de texto
        textFieldEjército = new JTextField(3);
        Font textFieldFont = new Font("Segoe UI", Font.ROMAN_BASELINE, 12);
        textFieldEjército.setFont(textFieldFont);
        textFieldEjército.setHorizontalAlignment(JTextField.CENTER);
        leftPanel.add(textFieldEjército);

        // Botones
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Ajusta el espaciado entre los botones
        cancelButtonEjército = new JButton("Cancelar");
        acceptButtonEjército = new JButton("Aceptar");
        cancelButtonEjército.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 10));
        acceptButtonEjército.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 10));
        buttonsPanel.add(cancelButtonEjército);
        buttonsPanel.add(acceptButtonEjército);
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(new EmptyBorder(0, 3, 0, 0));
        leftPanel.add(buttonsPanel);

        // Agregar panel izquierdo al oeste del panel principal
        leftPanel.setBorder(new EmptyBorder(80, 15, 80, 45)); // Ajusta el margen del panel izquierdo
        add(leftPanel, BorderLayout.WEST);

        // Panel derecho con GIF y botón
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(350, getHeight()));
        rightPanel.setOpaque(false);

        ImageIcon gifIcon = new ImageIcon("src\\main\\java\\com\\project\\images\\CrearEjercito.gif"); // Reemplaza con la ruta de tu archivo gif
        JLabel gifLabel = new JLabel(gifIcon);
        gifLabel.setLayout(new BorderLayout());

        ImageIcon buttonIcon = new ImageIcon("src\\main\\java\\com\\project\\images\\informacion.png"); // Reemplaza con la ruta de tu archivo de imagen
        gifButton = new JButton(buttonIcon);
        gifButton.setOpaque(false);
        gifButton.setContentAreaFilled(false);
        gifButton.setBorderPainted(false);
        gifButton.setFocusPainted(false);

        gifButton.setActionCommand("GIF_BUTTON_CLICKED");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(gifButton);

        buttonPanel.setBorder(new EmptyBorder(0,295,0,0));

        gifLabel.add(buttonPanel, BorderLayout.SOUTH);
        rightPanel.add(gifLabel);

        add(rightPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("EjércitoFrame Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1150, 650);
            frame.setLocationRelativeTo(null);

            EjércitoFrame ejércitoFrame = new EjércitoFrame();
            frame.add(ejércitoFrame);

            frame.setVisible(true);
        });
    }
}
