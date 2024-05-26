package com.project;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {
    private CivilizaciónFrame civilizaciónFrame;
    private CivilizaciónControlador civilizaciónControlador;
    private Clip backgroundMusic;
    private FloatControl volumeControl;
    public ContadorRecursos contadorRecursos;
    public ContadorAtacar contadorAtacar;

    public MainWindow() {
        super("CIVILIZATIONS");
        setSize(585, 415);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src\\main\\java\\com\\project\\images\\logo.png");
        setIconImage(icon.getImage());
        setResizable(false);

        initComponents();

        System.out.println("Se creó el contadorAtacar");
        contadorAtacar = new ContadorAtacar(this);

        System.out.println("Se creó el contadorRecursos");
        contadorRecursos = new ContadorRecursos();

        civilizaciónControlador = new CivilizaciónControlador(civilizaciónFrame, this);
        civilizaciónControlador.start();

        playBackgroundMusic("src\\main\\java\\com\\project\\music\\BandaSonora.wav"); // Ruta de tu archivo de música de fondo
    }

    private void initComponents() {
        civilizaciónFrame = new CivilizaciónFrame();
        add(civilizaciónFrame);
    }

    public void cambiarPanel(JPanel newPanel) {
        getContentPane().removeAll();
        add(newPanel);
        revalidate();
        repaint();
    }

    private void playBackgroundMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusic.start();

            // Obtener el control de volumen del clip
            volumeControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
            // Establecer el volumen (en dB)
            setVolume(-10.0f); // Por ejemplo, -10.0f para bajar el volumen

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void setVolume(float volume) {
        if (volumeControl != null) {
            volumeControl.setValue(volume);
        }
    }
}
