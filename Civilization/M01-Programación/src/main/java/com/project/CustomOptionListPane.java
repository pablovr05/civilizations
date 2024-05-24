import javax.swing.*;
import java.awt.*;

public class CustomOptionListPane extends JPanel {

    private JComboBox<String> comboBox;
    private String selectedOption;

    public CustomOptionListPane() {
        setLayout(new BorderLayout());

        String[] options = {"Patata", "Fresa", "Ensalada"};
        comboBox = new JComboBox<>(options);

        add(comboBox, BorderLayout.CENTER);
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public static String showOptionListDialog(Component parentComponent, String message, String title, String iconPath) {
        CustomOptionListPane pane = new CustomOptionListPane();

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

        int result = JOptionPane.showConfirmDialog(parentComponent, pane, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
        if (result == JOptionPane.OK_OPTION) {
            pane.selectedOption = (String) pane.comboBox.getSelectedItem();
            return pane.getSelectedOption();
        } else {
            return null;
        }
    }
}
