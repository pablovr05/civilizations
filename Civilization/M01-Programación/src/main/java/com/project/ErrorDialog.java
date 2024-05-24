import javax.swing.*;
import java.awt.*;

public class ErrorDialog extends JDialog {

    public ErrorDialog(JFrame parent, String errorMessage) {
        super(parent, "Error", true);

        JLabel label = new JLabel(errorMessage);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    public static void showErrorDialog(JFrame parent, String errorMessage) {
        ErrorDialog dialog = new ErrorDialog(parent, errorMessage);
        dialog.setVisible(true);
    }
}

/*
ErrorDialog.showErrorDialog(mainWindow, "¡Hubo un error al cargar el archivo!");
*/