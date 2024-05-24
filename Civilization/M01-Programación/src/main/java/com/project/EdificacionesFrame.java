import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class EdificacionesFrame extends JPanel {

    public JTextField textFieldEdificios;
    public JButton cancelButtonEdificios;
    public JButton acceptButtonEdificios;
    public JComboBox<String> optionsComboBoxEdificios;

    public EdificacionesFrame() {
        // Establece el diseño del panel como BorderLayout
        setLayout(new BorderLayout());

        // Panel izquierdo para opciones
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("images/Fondocrear.jpg")); // Ruta de la imagen
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        leftPanel.setLayout(new GridLayout(6, 1, 5, 5)); // GridLayout para organizar los componentes verticalmente y con espaciado

        // Título de Entrenar tropas
        JLabel entrenarTropasLabel = new JLabel("Construir edificios");
        entrenarTropasLabel.setFont(new Font("Candara", Font.BOLD, 24)); // Tamaño de fuente más grande
        entrenarTropasLabel.setForeground(Color.WHITE); // Color verde oscuro
        leftPanel.add(entrenarTropasLabel);

        // Tipo de tropa
        JLabel typeTropaLabel = new JLabel("Tipo de construcción :");
        typeTropaLabel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12)); // Tamaño de fuente más grande
        typeTropaLabel.setForeground(Color.WHITE); // Color verde oscuro
        leftPanel.add(typeTropaLabel);

        // ComboBox con opciones
        String[] opciones = {"Farm","Smithy","Carpentry","Magic Tower","Church"};
        optionsComboBoxEdificios = new JComboBox<>(opciones);
        optionsComboBoxEdificios.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        leftPanel.add(optionsComboBoxEdificios);

        // Cantidad de tropas
        JLabel cantidadTropaLabel = new JLabel("Cantidad de construcciones :");
        cantidadTropaLabel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12)); // Tamaño de fuente más grande
        cantidadTropaLabel.setForeground(Color.WHITE); // Color verde oscuro
        leftPanel.add(cantidadTropaLabel);

        // Campo de texto
        textFieldEdificios = new JTextField(3);
        Font textFieldFont = new Font("Segoe UI", Font.ROMAN_BASELINE, 12);
        textFieldEdificios.setFont(textFieldFont);
        textFieldEdificios.setHorizontalAlignment(JTextField.CENTER);
        leftPanel.add(textFieldEdificios);

        // Botones
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Ajusta el espaciado entre los botones
        cancelButtonEdificios = new JButton("Cancelar");
        acceptButtonEdificios = new JButton("Aceptar");
        cancelButtonEdificios.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 10));
        acceptButtonEdificios.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 10));
        buttonsPanel.add(cancelButtonEdificios);
        buttonsPanel.add(acceptButtonEdificios);
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
        leftPanel.add(buttonsPanel);

        // Agregar panel izquierdo al oeste del panel principal
        leftPanel.setBorder(new EmptyBorder(80, 15, 80, 15)); // Ajusta el margen del panel izquierdo
        add(leftPanel, BorderLayout.WEST);

        ImageIcon gifIcon = new ImageIcon("images/Edificios.gif"); // Reemplaza con la ruta de tu archivo gif
        JLabel gifLabel = new JLabel(gifIcon);
        add(gifLabel, 0);
    }
}
