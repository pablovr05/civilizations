package com.project;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {
    private CivilizaciónFrame civilizaciónFrame;
    private CivilizaciónControlador civilizaciónControlador;
    private Clip backgroundMusic;
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

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
