import javax.swing.*;

public class Notificación {
    public Notificación(JFrame parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Notification", JOptionPane.INFORMATION_MESSAGE);
    }
}

/*
new Notificación(mainWindow, "¡Hola! Esta es una notificación de prueba.");
*/
