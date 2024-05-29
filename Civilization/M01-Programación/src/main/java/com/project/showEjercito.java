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

public class ShowEjercito extends JDialog {

    private JButton gifButton;
    private Clip Clblucle;
    private Clip clip;

    private String type;
    private int food;
    private int wood;
    private int iron;
    private int mana;
    private int armor;
    private int dmg;
    private int chanceAtt;
    private int chanceWst;

    private String URL;

    private Image backgroundImage;

    public ShowEjercito(JFrame parent, String type) {
        super(parent, "Ventana emergente", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        int[] materiales = CivilizaciónControlador.civilización.getEjércitoInfo(type);
        System.out.println("Food: " + materiales[0] + " Wood: " + materiales[1] + " Iron: " + materiales[2] + " Mana: " + materiales[3] + " Armor: " + materiales[4] + " Base Damage: " + materiales[5] + " Chance of Attack Again: " + materiales[6] + "%" + " Chance of Generating Wastes: " + materiales[7] + "%");

        this.type = type;
        this.food = materiales[0];
        this.wood = materiales[1];
        this.iron = materiales[2];
        this.mana = materiales[3];
        this.armor = materiales[4];
        this.dmg = materiales[5];
        this.chanceAtt = materiales[6];
        this.chanceWst = materiales[7];

        setLayout(new BorderLayout());
        setUndecorated(true); // Elimina la decoración de la ventana (bordes, botones de cierre, etc.)
        setBackground(new Color(0, 0, 0, 100)); // Fondo transparente
        setPreferredSize(new Dimension(585, 415));

        // Configura el contenido de la ventana emergente
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setOpaque(false); // Hace que el panel sea transparente
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0)); // Elimina el espacio en los bordes

        // Crear el botón con el GIF
        RoundedPanel roundedPanel = new RoundedPanel();
        roundedPanel.setLayout(new BoxLayout(roundedPanel, BoxLayout.Y_AXIS));
        roundedPanel.setCornerRadius(20);

        try {
            // Cargar la imagen de fondo desde el archivo
            backgroundImage = ImageIO.read(new File("src/main/java/com/project/images/fondo12.jpg"));
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

        roundedPanel.setPreferredSize(new Dimension(250, 315));

        // Añadir el botón al panel de contenido
        contentPane.add(roundedPanel);

        JLabel titleLabel = new JLabel(type, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setPreferredSize(new Dimension(200, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        roundedPanel.add(titleLabel);

        URL = getEjercitoURL(type);

        JLabel imageLabel = new JLabel(new ImageIcon(URL));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar la imagen
        roundedPanel.add(imageLabel);

        String[][] data = new String[8][2]; // Inicializar la tabla vacía
        String[] columnNames = {"Attribute", "Value"};
        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setName("Estadísticas");
        table.setEnabled(false);

        // Añadir datos a la tabla
        data[0][0] = "Food";
        data[0][1] = Integer.toString(food);
        data[1][0] = "Wood";
        data[1][1] = Integer.toString(wood);
        data[2][0] = "Iron";
        data[2][1] = Integer.toString(iron);
        data[3][0] = "Mana";
        data[3][1] = Integer.toString(mana);
        data[4][0] = "Armor";
        data[4][1] = Integer.toString(armor);
        data[5][0] = "Base Damage";
        data[5][1] = Integer.toString(dmg);
        data[6][0] = "Chance of Attack Again";
        data[6][1] = Integer.toString(chanceAtt) + "%";
        data[7][0] = "Chance of Generating Wastes";
        data[7][1] = Integer.toString(chanceWst) + "%";

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar la tabla
        roundedPanel.add(scrollPane);

        getContentPane().add(contentPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(parent);
    }

    public String getEjercitoURL(String tipo) {
        switch (tipo) {
            case "Swordsman":
                return "src\\main\\java\\com\\project\\images\\sprites\\guerrero.png";
            case "Spearman":
                return "src\\main\\java\\com\\project\\images\\sprites\\lancero.png";
            case "Crossbow":
                return "src\\main\\java\\com\\project\\images\\sprites\\ballesta.png";
            case "Cannon":
                return "src\\main\\java\\com\\project\\images\\sprites\\cañón.png";
            case "Arrow Tower":
                return "src\\main\\java\\com\\project\\images\\sprites\\arquero.png";
            case "Catapult":
                return "src\\main\\java\\com\\project\\images\\sprites\\catapulta.png";
            case "Rocket Launcher Tower":
                return "src\\main\\java\\com\\project\\images\\sprites\\misil.png";
            case "Magician":
                return "src\\main\\java\\com\\project\\images\\sprites\\mago.png";
            case "Priest":
                return "src\\main\\java\\com\\project\\images\\sprites\\sacerdote.png";
            default:
                return null;
        }
    }
}