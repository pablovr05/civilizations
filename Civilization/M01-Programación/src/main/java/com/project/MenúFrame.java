package com.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.List;
import java.util.Random;

public class MenúFrame extends JPanel {

    private Image backgroundImage;
    private JPanel buttonPanel; // Panel para contener los botones
    public JButton btnEstadísticas;
    public JButton btnCrearEdificios;
    public JButton btnCrearEjército;
    public JButton btnTecnologías;
    public JButton btnHistorial;
    public JButton btnSalir;
    private Color colorCambio = new Color(0, 133, 0);
    private Color colorDefault = new Color(0, 0, 0);

    private List<String> strings = List.of("images/fondomenu9.jpg", "images/fondomenu10.jpg");
    private String randomString = getRandomString(strings);
   
    public MenúFrame() {
        try {
            // Cargar la imagen de fondo desde el archivo
            backgroundImage = ImageIO.read(new File(randomString));
            setPreferredSize(new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setOpaque(false); // Hace que el panel sea transparente
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Alinea los botones uno debajo del otro

        // Crea y añade el espacio en blanco
        add(Box.createVerticalGlue());

        setLayout(new BorderLayout()); // Establecer un BorderLayout en el MenúFrame

        // Crear el panel de botones y establecer su diseño como GridLayout
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1)); // Un botón por fila

        JLabel welcomeLabel = new JLabel("CIVILIZATIONS", SwingConstants.CENTER);


        welcomeLabel.setFont(new Font("Candara", Font.BOLD, 24)); // Tamaño de fuente más grande
        welcomeLabel.setForeground(Color.BLACK); // Color verde oscuro
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 17, 0, 0));
        
        
        welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        buttonPanel.add(welcomeLabel);

        // Agregar los botones al panel de botones
        btnEstadísticas = addButton("Estadísticas");
        btnCrearEdificios = addButton("Crear edificios");
        btnCrearEjército = addButton("Crear ejército");
        btnTecnologías = addButton("Tecnologías");
        btnHistorial = addButton("Ver historial de batallas");
        btnSalir = addButton("Salir");

        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(30,15,150,0));
        // Añadir el panel de botones al lado izquierdo del MenúFrame
        add(buttonPanel, BorderLayout.WEST);

        btnEstadísticas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEstadísticas.setForeground(colorCambio); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("./music/botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEstadísticas.setForeground(colorDefault); // Restaura el color de fondo cuando el cursor sale
            }
        });

        btnCrearEdificios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCrearEdificios.setForeground(colorCambio); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("./music/botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCrearEdificios.setForeground(colorDefault); // Restaura el color de fondo cuando el cursor sale
            }
        });

        btnCrearEjército.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCrearEjército.setForeground(colorCambio); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("./music/botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCrearEjército.setForeground(colorDefault); // Restaura el color de fondo cuando el cursor sale
            }
        });

        btnTecnologías.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTecnologías.setForeground(colorCambio); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("./music/botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTecnologías.setForeground(colorDefault); // Restaura el color de fondo cuando el cursor sale
            }
        });

        btnHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHistorial.setForeground(colorCambio); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("./music/botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHistorial.setForeground(colorDefault); // Restaura el color de fondo cuando el cursor sale
            }
        });

        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalir.setForeground(colorCambio); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("./music/botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalir.setForeground(colorDefault); // Restaura el color de fondo cuando el cursor sale
            }
        });

        FixedPanel fixedPanel = new FixedPanel();
        fixedPanel.setBounds(0, 0, 200, 200);
        fixedPanel.setBorder(new EmptyBorder(25,225,0,0));
        add(fixedPanel);
    }

    private JButton addButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(colorDefault);
        button.setHorizontalAlignment(SwingConstants.LEFT); // Alinea el texto a la izquierda
        buttonPanel.add(button); // Agregar el botón al panel de botones
        return button; // Devolver el botón creado
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static String getRandomString(List<String> strings) {
        // Verificar si la lista está vacía o nula
        if (strings == null || strings.isEmpty()) {
            return null;
        }

        // Crear un objeto Random
        Random random = new Random();
        // Obtener un índice aleatorio dentro del rango de la lista
        int randomIndex = random.nextInt(strings.size());
        // Devolver la cadena en el índice aleatorio
        return strings.get(randomIndex);
    }
}
