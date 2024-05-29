package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class OptionPanelListSanctify extends JPanel {

    private JComboBox<String> comboBox;
    private String selectedOption;

    public OptionPanelListSanctify() {
        setLayout(new BorderLayout());

        // Label para el mensaje
        JLabel messageLabel = new JLabel("A qué tropa quieres santificar:");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageLabel, BorderLayout.NORTH);

        // Combo Box con las opciones
        String[] options = {"Swordsman", "Spearman", "Crossbow", "Cannon", "Arrow Tower", "Catapult", "Rocket Launcher Tower"};
        comboBox = new JComboBox<>(options);
        add(comboBox, BorderLayout.CENTER);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton acceptButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOption = (String) comboBox.getSelectedItem();
                // Cerrar el panel cuando se presiona Aceptar
                Window window = SwingUtilities.windowForComponent((Component) e.getSource());
                if (window instanceof JDialog) {
                    ((JDialog) window).dispose();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Establecer la opción seleccionada como nula y cerrar el panel cuando se presiona Cancelar
                selectedOption = null;
                Window window = SwingUtilities.windowForComponent((Component) e.getSource());
                if (window instanceof JDialog) {
                    ((JDialog) window).dispose();
                }
            }
        });

        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public static String showOptionPanelListSanctify(Component parentComponent, String title) {
        OptionPanelListSanctify panel = new OptionPanelListSanctify();

        System.out.println(1);

        // Cargar la imagen de icono y redimensionarla a 16x16 píxeles
        ImageIcon icon = new ImageIcon("src\\main\\java\\com\\project\\images\\logo.png"); // Reemplaza con la ruta de tu propio icono
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizedImg);

        // Mostrar el diálogo con el icono redimensionado
        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, icon, new Object[]{}, null);
        JDialog dialog = optionPane.createDialog(parentComponent, title);
        dialog.setVisible(true);

        if (optionPane.getValue() instanceof Integer) {
            int result = (Integer) optionPane.getValue();
            if (result == JOptionPane.OK_OPTION) {
                return panel.getSelectedOption();
            }
        }
        return null;
    }
}
