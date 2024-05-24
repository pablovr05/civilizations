package com.project;

import javax.sound.sampled.*;
import java.io.File;

public class SoundPlayer {
    public static void playSound(String soundFilePath, float volume) {
        try {
            soundFilePath = "src\\main\\java\\com\\project\\music\\" + soundFilePath;
            File soundFile = new File(soundFilePath);
            if (!soundFile.exists()) {
                throw new IllegalArgumentException("Archivo de sonido no encontrado: " + soundFilePath);
            }
            
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            
            // Obtener el formato de audio del archivo
            AudioFormat format = audioInputStream.getFormat();
            
            // Crear un DataLine.Info para la reproducción del sonido
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            
            // Obtener un Clip de audio del sistema
            Clip clip = (Clip) AudioSystem.getLine(info);
            
            // Abrir el flujo de audio
            clip.open(audioInputStream);
            
            // Obtener un control de volumen del clip
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            
            // Establecer el volumen deseado
            gainControl.setValue(volume);
            
            // Reproducir el sonido una vez
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}