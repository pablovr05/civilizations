import javax.sound.sampled.*;

public class SoundPlayer {
    public static void playSound(String soundFilePath, float volume) {
        try {
            // Cargar el archivo de sonido desde la ruta proporcionada
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(SoundPlayer.class.getResource(soundFilePath));
            
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
/*
SoundPlayer.playSound("./music/botón.wav", 0.5f);
*/