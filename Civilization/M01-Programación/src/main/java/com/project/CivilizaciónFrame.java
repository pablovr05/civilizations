import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CivilizaciónFrame extends JPanel {

    public JPanel cardPanel;
    public CardLayout cardLayout;
    public JButton btnCargarPartida;
    public JButton btnNuevaPartida;

    public CivilizaciónFrame() {
        // Creamos el CardLayout y lo asignamos al panel principal
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Panel izquierdo con el texto y los botones
        JPanel leftPanel = createLeftPanel();

        // Panel derecho para la imagen
        JPanel rightPanel = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel();
        try {
            // Carga la imagen desde el archivo "imagen.jpg" en la carpeta "images" (ajusta la ruta según tu estructura de directorios)
            Image img = ImageIO.read(new File("images/imagenfondoportada.png"));
            // Escalamos la imagen para que ocupe todo el espacio disponible
            Image scaledImg = img.getScaledInstance(300, 400, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rightPanel.add(imageLabel);

        // Dividimos el panel en dos partes con BorderLayout
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.CENTER); // Ahora el panel izquierdo va en el centro
        add(rightPanel, BorderLayout.EAST); // El panel derecho va a la derecha

        // Por defecto mostramos el panel principal
        cardLayout.show(cardPanel, "splitPane");
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("CIVILIZATIONS", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Comforta", Font.PLAIN, 18));
        leftPanel.add(welcomeLabel, gbc);

        btnCargarPartida = new JButton("Cargar Partida");
        btnCargarPartida.setFont(new Font("Comforta", Font.PLAIN, 12));
        btnCargarPartida.setOpaque(false);
        btnCargarPartida.setContentAreaFilled(false);
        btnCargarPartida.setBorderPainted(false);
        btnCargarPartida.setFocusPainted(false);
        gbc.gridy = 1;
        leftPanel.add(btnCargarPartida, gbc);

        btnNuevaPartida = new JButton("Nueva Partida");
        btnNuevaPartida.setFont(new Font("Comforta", Font.PLAIN, 12));
        btnNuevaPartida.setOpaque(false);
        btnNuevaPartida.setContentAreaFilled(false);
        btnNuevaPartida.setBorderPainted(false);
        btnNuevaPartida.setFocusPainted(false);
        gbc.gridy = 2;
        leftPanel.add(btnNuevaPartida, gbc);



        // Agregar MouseListeners para cambiar el color de fondo cuando el cursor entra y sale del área del botón
        btnCargarPartida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCargarPartida.setForeground(Color.RED); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("./music/botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCargarPartida.setForeground(Color.BLACK); // Restaura el color de fondo cuando el cursor sale
            }
        });

        btnNuevaPartida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevaPartida.setForeground(Color.RED); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("./music/botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevaPartida.setForeground(Color.BLACK); // Restaura el color de fondo cuando el cursor sale
            }
        });

        JLabel Créditos = new JLabel("[ Hecho por Joel Martínez, Adrian Martínez, Pablo Vicente]");
        Créditos.setFont(new Font("Ubuntu", Font.PLAIN, 8));

        Border border = new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                // No necesitamos pintar nada aquí
            }

            @Override
            public Insets getBorderInsets(Component c) {
                // Definimos los márgenes
                return new Insets(20, 0, 0, 0); // 10 píxeles de margen en cada lado
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        };

        Créditos.setBorder(border);
        gbc.gridy = 3;

        leftPanel.add(Créditos, gbc);

        return leftPanel;
    }
}
