package com.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class ResultadoBattallaFrame extends JPanel {

    private String título;
    private String fondo;
    private Color color;

    public JButton ButtonVolver;
    
    public ResultadoBattallaFrame(boolean winner) {
        // Establece el diseño del panel como BorderLayout

        if ( winner ) {
            título = "VICTORIA";
            fondo = "images/victoria.gif";
            color = new Color(0,255,0);
        } else {
            título = "DERROTA";
            fondo = "images/derrota.gif";
            color = new Color(255,0,0);
        }

        setLayout(new BorderLayout());

        // Crea un JLabel para mostrar el GIF de fondo
        JLabel background = new JLabel(new ImageIcon(fondo));
        background.setLayout(new BorderLayout()); // Establece el layout para el JLabel del fondo
        add(background);

        // Crea un panel para colocar los componentes sobre el fondo
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false); // Hace que el panel no sea opaco para que el fondo sea visible
        contentPanel.setBorder(new EmptyBorder(0,0,0,0));
        background.add(contentPanel, BorderLayout.CENTER);

        background.setBorder(new EmptyBorder(25,25,25,25));

        // Crea el panel izquierdo con color rojo transparente
        RoundedPanel leftPanel = new RoundedPanel();
        leftPanel.setCornerRadius(20);
        leftPanel.setOpaque(false);
        leftPanel.setBackground(new Color(0, 0, 0, 100));
        leftPanel.setPreferredSize(new Dimension(335, 500));
        contentPanel.add(leftPanel);

        RoundedPanel titlePanel = new RoundedPanel();
        titlePanel.setCornerRadius(20);
        titlePanel.setOpaque(false);
        titlePanel.setBackground(new Color(255, 255, 255, 100));
        titlePanel.setPreferredSize(new Dimension(320, 100));
        leftPanel.add(titlePanel);

        JLabel textoTítulo = new JLabel(título);
        textoTítulo.setFont(new Font("Reem Kufi", Font.BOLD, 59));
        textoTítulo.setForeground(color);
        titlePanel.add(textoTítulo);

        RoundedPanel leftContentPanel = new RoundedPanel();
        leftContentPanel.setCornerRadius(20);
        leftContentPanel.setOpaque(false);
        leftContentPanel.setBackground(new Color(255, 255, 255));
        leftContentPanel.setPreferredSize(new Dimension(320, 380));
        leftPanel.add(leftContentPanel);

        JPanel leftEstadísticasPanel = new JPanel();
        leftEstadísticasPanel.setBackground(new Color(255,0,0,205));
        leftEstadísticasPanel.setPreferredSize(new Dimension(300, 300));
        leftContentPanel.add(leftEstadísticasPanel);

        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setBackground(new Color(255,255,255,255));
        ButtonPanel.setPreferredSize(new Dimension(300, 50));
        ButtonVolver = new JButton("Volver");
        ButtonVolver.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        ButtonVolver.setContentAreaFilled(false);
        ButtonVolver.setBorderPainted(false);
        ButtonVolver.setFocusPainted(false);
        ButtonVolver.setHorizontalAlignment(SwingConstants.RIGHT); // Alinea el texto a la izquierda
        ButtonPanel.setBorder(new EmptyBorder(10,225,0,0));
        ButtonPanel.add(ButtonVolver); // Agregar el botón al panel de botones
        leftContentPanel.add(ButtonPanel);

        ButtonVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonVolver.setForeground(color); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("./music/botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonVolver.setForeground(Color.BLACK); // Restaura el color de fondo cuando el cursor sale
            }
        });

        // Crea el panel derecho con color azul transparente
        RoundedPanel rightPanel = new RoundedPanel();
        rightPanel.setCornerRadius(20);
        rightPanel.setOpaque(false);
        rightPanel.setBackground(new Color(0, 0, 0, 100));
        rightPanel.setPreferredSize(new Dimension(735, 555));
        contentPanel.add(rightPanel);

        RoundedPanel rightTitlePanel = new RoundedPanel();
        rightTitlePanel.setCornerRadius(20);
        rightTitlePanel.setOpaque(false);
        rightTitlePanel.setBackground(new Color(255, 255, 255, 100));
        rightTitlePanel.setPreferredSize(new Dimension(500, 100));
        rightPanel.add(rightTitlePanel);

        JLabel RightTextoTítulo = new JLabel("Desarrolo del combate");
        RightTextoTítulo.setFont(new Font("Reem Kufi", Font.BOLD, 30));
        RightTextoTítulo.setForeground(Color.WHITE);
        RightTextoTítulo.setBorder(new EmptyBorder(20,0,0,0));
        rightTitlePanel.add(RightTextoTítulo);

        RoundedPanel rightContentPanel = new RoundedPanel();
        rightContentPanel.setCornerRadius(20);
        rightContentPanel.setOpaque(false);
        rightContentPanel.setBackground(new Color(255, 255, 255));
        rightContentPanel.setPreferredSize(new Dimension(710, 435));
        rightPanel.add(rightContentPanel);
    }
    
    public static void main(String[] args) {
        // Crea el marco de la aplicación
        JFrame frame = new JFrame("Interfaz de Fondo con GIF");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1150, 650); // Establece el tamaño del marco

        // Crea una instancia del panel personalizado y lo agrega al marco
        ResultadoBattallaFrame customPanel = new ResultadoBattallaFrame(false);
        frame.add(customPanel);
        
        // Hace visible el marco
        frame.setVisible(true);
    }
}
