package com.project;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class FightButtonPanel extends JDialog {

    private JButton gifButton;
    private Clip Clblucle;
    private Clip clip;

    public FightButtonPanel(JFrame parent) {
        super(parent, "Ventana emergente", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        Clblucle = playSoundBucle("src\\main\\java\\com\\project\\music\\firesound.wav");

        setLayout(new BorderLayout());
        setUndecorated(true); // Elimina la decoración de la ventana (bordes, botones de cierre, etc.)
        setBackground(new Color(0, 0, 0, 100)); // Fondo transparente
        setPreferredSize(new Dimension(585,415));

        // Configura el contenido de la ventana emergente
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setOpaque(false); // Hace que el panel sea transparente
        contentPane.setBorder(new EmptyBorder(4, 4, 4, 4)); // Añade un espacio en los bordes

        // Crear el botón con el GIF
        gifButton = createGifButton("src\\main\\java\\com\\project\\images\\fightbutton.gif");

        gifButton.setPreferredSize(new Dimension(420,115));

        // Añadir el botón al panel de contenido
        contentPane.add(gifButton);

        // Añadir el panel de contenido a la ventana emergente
        getContentPane().add(contentPane, BorderLayout.CENTER);

        // Establecer el tamaño de la ventana
        pack();

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(parent);
    }

    private JButton createGifButton(String gifPath) {
        JButton button = new JButton();

        // Cargar el GIF
        ImageIcon gifIcon = new ImageIcon(gifPath);
        button.setIcon(gifIcon);

        // Ajustar el tamaño del botón para adaptarse al contenido
        button.setPreferredSize(new Dimension(gifIcon.getIconWidth(), gifIcon.getIconHeight()));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        // Agregar el efecto hover y el sonido
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Entra");
                SoundPlayer.playSound("clickfight.wav", 0.5f);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Sale");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                stopSound(Clblucle);
                System.out.println("click");
                dispose(); // Cerrar la ventana emergente después de detener el sonido
                System.out.println("Cerrando la ventana emergente...");
                SoundPlayer.playSound("selectbattle.wav", 0.5f);
            }
        });
        return button;
    }

    private Clip playSoundBucle(String soundPath) {
        try {
            File file = new File(soundPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Reproduce el sonido en bucle
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
        return clip;
    }

    private void stopSound(Clip clip) {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }  
}