import javax.swing.*;
import java.awt.*;

public class CustomOptionPane {

    public static String showCivilizationNameInputDialog(JFrame parent, String message, String title, String iconPath) {
        // Cargar la imagen del icono y escalarla si se proporciona una ruta válida
        ImageIcon icon = null;
        if (iconPath != null && !iconPath.isEmpty()) {
            ImageIcon originalIcon = new ImageIcon(iconPath);
            if (originalIcon != null) {
                Image originalImage = originalIcon.getImage();
                // Escalar la imagen al tamaño deseado
                Image scaledImage = originalImage.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                // Crear un ImageIcon con la imagen escalada
                icon = new ImageIcon(scaledImage);
            }
        }
    
        // Mostrar el diálogo de entrada del nombre de la civilización
        String result = (String) JOptionPane.showInputDialog(parent, message, title, JOptionPane.QUESTION_MESSAGE, icon, null, null);
        // Verificar si el resultado es null y retornar una cadena vacía en ese caso
        return (result != null) ? result : "";
    }
    
}
