package com.project;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FadeOutGifDialog extends JDialog {

    private Timer timer;
    private JLabel gifLabel;
    private ImageIcon gifIcon;

    public FadeOutGifDialog(JFrame parent, String gifPath) {
        super(parent, true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setBackground(new Color(0,0,0,0));

        SoundPlayer.playSound("./music/alerta.wav", 0.5f);

        // Cargar la imagen GIF
        gifIcon = new ImageIcon(gifPath);
        gifLabel = new JLabel(gifIcon);

        setContentPane(gifLabel);

        // Configurar temporizador para el desvanecimiento
        timer = new Timer(35, new ActionListener() {
            float alpha = 1.0f;
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha -= 0.01f;
                if (alpha <= 0.0f) {
                    ((Timer)e.getSource()).stop();
                    dispose();
                } else {
                    setOpacity(alpha);
                }
            }
        });

        // Configurar temporizador para cerrar el diálogo después de 3 segundos
        Timer closeTimer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });
        closeTimer.setRepeats(false);
        closeTimer.start();

        // Centrar el diálogo en la pantalla
        pack();
        setLocationRelativeTo(parent);
    }
}
