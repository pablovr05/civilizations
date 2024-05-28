package com.project;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class ShowEdificios extends JDialog {

    private JButton gifButton;
    private Clip Clblucle;
    private Clip clip;

    private String type;
    private int food;
    private int wood;
    private int iron;
    private int mana;
    private int foodGen;
    private int woodGen;
    private int ironGen;
    private int manaGen;

    private Image backgroundImage;

    public ShowEdificios(JFrame parent, String type) {
        super(parent, "Ventana emergente", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        int[] materiales = CivilizaciónControlador.civilización.getEdificaciónInfo(type);
        System.out.println("Food: " + materiales[0] + " Wood: " + materiales[1] + " Iron: " + materiales[2] + " Mana: " + materiales[3] + " FoodGen: " + materiales[4] + " WoodGen: " + materiales[5] + " IronGen: " + materiales[6] + " ManaGen: " + materiales[7]);

        this.type = type;
        this.food = materiales[0];
        this.wood = materiales[1];
        this.iron = materiales[2];
        this.mana = materiales[3];
        this.foodGen = materiales[4];
        this.woodGen = materiales[5];
        this.ironGen = materiales[6];
        this.manaGen = materiales[7];

        setLayout(new BorderLayout());
        setUndecorated(true); // Elimina la decoración de la ventana (bordes, botones de cierre, etc.)
        setBackground(new Color(0, 0, 0, 100)); // Fondo transparente
        setPreferredSize(new Dimension(585,415));

        // Configura el contenido de la ventana emergente
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setOpaque(false); // Hace que el panel sea transparente
        contentPane.setBorder(new EmptyBorder(4, 4, 4, 4)); // Añade un espacio en los bordes

        // Crear el botón con el GIF
        RoundedPanel roundedPanel = new RoundedPanel();
        roundedPanel.setCornerRadius(20);

        try {
            // Cargar la imagen de fondo desde el archivo
            backgroundImage = ImageIO.read(new File("src\\main\\java\\com\\project\\images\\fondo11.jpg"));
            roundedPanel.setBackgroundImage(backgroundImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Entra");
                SoundPlayer.playSound("clickfight.wav", 0.5f);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Sale");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("click");
                dispose(); // Cerrar la ventana emergente después de detener el sonido
                System.out.println("Cerrando la ventana emergente...");
                SoundPlayer.playSound("selectbattle.wav", 0.5f);
            }
        });

        roundedPanel.setPreferredSize(new Dimension(250,315));

        // Añadir el botón al panel de contenido
        contentPane.add(roundedPanel);

        JLabel titleLabel = new JLabel(type, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        roundedPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel imageLabel = new JLabel(new ImageIcon("src\\main\\java\\com\\project\\images\\center_image.jpg"));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roundedPanel.add(imageLabel, BorderLayout.CENTER);

        // Añadir el panel de contenido a la ventana emergente
        getContentPane().add(contentPane, BorderLayout.CENTER);

        // Establecer el tamaño de la ventana
        pack();

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(parent);
    }

    public String getEdificioURL(String tipo) {
        switch (tipo) {
            case "Farm":
                return "";  
            case "Smithy":
                return "";  
            case "Church":
                return "";            
            case "Magic Tower":
                return "";  
            case "Carpentry":
                return "";
            default:
                return null;
        }
    }
}