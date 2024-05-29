package com.project;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

public class AtacarFrame extends JPanel {

    private JLabel timerLabel;

    private Image backgroundImage;
    
    public ArrayList<ArrayList<MilitaryUnit>> enemyArmy;

    private int cnt_Swordsmans = CivilizaciónControlador.civilización.getNombreSwordsman();
    private int cnt_Spearmans = CivilizaciónControlador.civilización.getNombreSpearman();
    private int cnt_Crossbows = CivilizaciónControlador.civilización.getNombreCrossbow();
    private int cnt_Canons = CivilizaciónControlador.civilización.getNombreCannon();
    private int cnt_Arrow_towers = CivilizaciónControlador.civilización.getNombreArrowTower();
    private int cnt_Catapults = CivilizaciónControlador.civilización.getNombreCatapult();
    private int cnt_Rocket_launchers = CivilizaciónControlador.civilización.getNombreRocketLauncher();
    private int cnt_Magicians = CivilizaciónControlador.civilización.getNombreMagician();
    private int cnt_Priests = CivilizaciónControlador.civilización.getNombrePriest();

    private int E_cnt_Swordsmans = -1;
    private int E_cnt_Spearmans = -1;
    private int E_cnt_Crossbows = -1;
    private int E_cnt_Canons = -1;

    public JButton escaparBotón;
    public JButton empezarBotón;

    public AtacarFrame() {

        this.enemyArmy = Main.createEnemyArmy(CivilizaciónControlador.civilización.battles);

        this.E_cnt_Swordsmans = getNombreSwordsman(enemyArmy);
        this.E_cnt_Spearmans = getNombreSpearman(enemyArmy);
        this.E_cnt_Crossbows = getNombreCrossbow(enemyArmy);
        this.E_cnt_Canons = getNombreCannon(enemyArmy);

        System.out.println(enemyArmy);

        setLayout(new BorderLayout());

        // Panel izquierdo
        BackgroundPanel leftPanel = new BackgroundPanel();
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(800, getHeight()));
        leftPanel.setBorder(new EmptyBorder(15,15,15,15));
        leftPanel.setBackground(Color.green);

        try {
            // Cargar la imagen de fondo desde el archivo
            backgroundImage = ImageIO.read(new File("src\\main\\java\\com\\project\\images\\patternbrown.jpg"));
            leftPanel.setBackgroundImage(backgroundImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(leftPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        RoundedPanel transparentPanel = new RoundedPanel();
        transparentPanel.setCornerRadius(20);
        transparentPanel.setLayout(new BorderLayout());
        transparentPanel.setBackground(new Color(255, 255, 255, 180)); // Color blanco con transparencia alfa
        leftPanel.add(transparentPanel, gbc);


        // Panel superior de la mitad para arriba
        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(Color.LIGHT_GRAY); // Color de ejemplo para distinguir
        upperPanel.setOpaque(false);
        upperPanel.setBorder(new EmptyBorder(14,0,0,0));
        transparentPanel.add(upperPanel, BorderLayout.NORTH);

        RoundedPanel toptextpanel = new RoundedPanel();
        toptextpanel.setCornerRadius(20);
        toptextpanel.setBackground(new Color(0,0,0,150));
        toptextpanel.setPreferredSize(new Dimension(725,250));
        toptextpanel.add(new AtacarFramePanel());
        upperPanel.add(toptextpanel);

        // Panel inferior izquierdo
        JPanel lowerLeftPanel = new JPanel(new GridLayout(10, 1));
        lowerLeftPanel.setBackground(Color.YELLOW); // Color de ejemplo para distinguir
        lowerLeftPanel.setOpaque(false);
        transparentPanel.add(lowerLeftPanel, BorderLayout.WEST);

        // Panel inferior derecho
        JPanel lowerRightPanel = new JPanel(new GridLayout(5, 1));
        lowerRightPanel.setBackground(Color.CYAN); // Color de ejemplo para distinguir
        lowerRightPanel.setOpaque(false);
        transparentPanel.add(lowerRightPanel, BorderLayout.EAST);

        JLabel títuloC = new JLabel("Tropas aliadas");
        títuloC.setFont(new Font("Bahnschrift", Font.ROMAN_BASELINE, 18));
        lowerLeftPanel.add(títuloC);

        ImageIcon iconC1 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\espada.png"); 
        Image imgC1 = iconC1.getImage();
        Image newImgC1 = imgC1.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconC1 = new ImageIcon(newImgC1);
        JLabel imageLabelC1 = new JLabel("Swordsmans: " + cnt_Swordsmans, iconC1, JLabel.LEFT);
        imageLabelC1.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelC1.setHorizontalTextPosition(JLabel.RIGHT);
        lowerLeftPanel.add(imageLabelC1);

        ImageIcon iconC2 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\lanza.png"); 
        Image imgC2 = iconC2.getImage();
        Image newImgC2 = imgC2.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconC2 = new ImageIcon(newImgC2);
        JLabel imageLabelC2 = new JLabel("Spearmans: " + cnt_Spearmans , iconC2, JLabel.LEFT);
        imageLabelC2.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelC2.setHorizontalTextPosition(JLabel.RIGHT);
        lowerLeftPanel.add(imageLabelC2);

        ImageIcon iconC3 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\ballesta.png"); 
        Image imgC3 = iconC3.getImage();
        Image newImgC3 = imgC3.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconC3 = new ImageIcon(newImgC3);
        JLabel imageLabelC3 = new JLabel("Crossbows: " + cnt_Crossbows, iconC3, JLabel.LEFT);
        imageLabelC3.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelC3.setHorizontalTextPosition(JLabel.RIGHT);
        lowerLeftPanel.add(imageLabelC3);

        ImageIcon iconC4 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\canon.png"); 
        Image imgC4 = iconC4.getImage();
        Image newImgC4 = imgC4.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconC4 = new ImageIcon(newImgC4);
        JLabel imageLabelC4 = new JLabel("Canons: " + cnt_Canons, iconC4, JLabel.LEFT);
        imageLabelC4.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelC4.setHorizontalTextPosition(JLabel.RIGHT);
        lowerLeftPanel.add(imageLabelC4);

        ImageIcon iconC5 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\tiro-al-arco.png"); 
        Image imgC5 = iconC5.getImage();
        Image newImgC5 = imgC5.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconC5 = new ImageIcon(newImgC5);
        JLabel imageLabelC5 = new JLabel("Arrow towers: " + cnt_Arrow_towers, iconC5, JLabel.LEFT);
        imageLabelC5.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelC5.setHorizontalTextPosition(JLabel.RIGHT);
        lowerLeftPanel.add(imageLabelC5);

        ImageIcon iconC6 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\catapulta.png"); 
        Image imgC6 = iconC6.getImage();
        Image newImgC6 = imgC6.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconC6 = new ImageIcon(newImgC6);
        JLabel imageLabelC6 = new JLabel("Catapults: " + cnt_Catapults, iconC6, JLabel.LEFT);
        imageLabelC6.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelC6.setHorizontalTextPosition(JLabel.RIGHT);
        lowerLeftPanel.add(imageLabelC6);

        ImageIcon iconC7 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\misil.png"); 
        Image imgC7 = iconC7.getImage();
        Image newImgC7 = imgC7.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconC6 = new ImageIcon(newImgC7);
        JLabel imageLabelC7 = new JLabel("Rocket launchers: " + cnt_Rocket_launchers, iconC7, JLabel.LEFT);
        imageLabelC7.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelC7.setHorizontalTextPosition(JLabel.RIGHT);
        lowerLeftPanel.add(imageLabelC7);

        ImageIcon iconC8 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\mago.png"); 
        Image imgC8 = iconC8.getImage();
        Image newImgC8 = imgC8.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconC8 = new ImageIcon(newImgC8);
        JLabel imageLabelC8 = new JLabel("Magicians: " + cnt_Magicians, iconC8, JLabel.LEFT);
        imageLabelC8.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelC8.setHorizontalTextPosition(JLabel.RIGHT);
        lowerLeftPanel.add(imageLabelC8);

        ImageIcon iconC9 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\sacerdote.png"); 
        Image imgC9 = iconC9.getImage();
        Image newImgC9 = imgC9.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconC9 = new ImageIcon(newImgC9);
        JLabel imageLabelC9 = new JLabel("Priests: " + cnt_Priests, iconC9, JLabel.LEFT);
        imageLabelC9.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelC9.setHorizontalTextPosition(JLabel.RIGHT);
        lowerLeftPanel.add(imageLabelC9);

        JLabel títuloE = new JLabel("Tropas enemigas");
        títuloE.setFont(new Font("Bahnschrift", Font.ROMAN_BASELINE, 18));
        lowerRightPanel.add(títuloE);

        ImageIcon iconE1 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\espada.png"); 
        Image imgE1 = iconC1.getImage();
        Image newImgE1 = imgE1.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconE1 = new ImageIcon(newImgE1);
        JLabel imageLabelE1 = new JLabel("Swordsmans: " + E_cnt_Swordsmans, iconE1, JLabel.LEFT);
        imageLabelE1.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelE1.setHorizontalTextPosition(JLabel.RIGHT);
        lowerRightPanel.add(imageLabelE1);

        ImageIcon iconE2 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\lanza.png"); 
        Image imgE2 = iconE2.getImage();
        Image newImgE2 = imgE2.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconE2 = new ImageIcon(newImgE2);
        JLabel imageLabelE2 = new JLabel("Spearmans: " + E_cnt_Spearmans, iconE2, JLabel.LEFT);
        imageLabelE2.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelE2.setHorizontalTextPosition(JLabel.RIGHT);
        lowerRightPanel.add(imageLabelE2);

        ImageIcon iconE3 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\ballesta.png"); 
        Image imgE3 = iconE3.getImage();
        Image newImgE3 = imgE3.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconC3 = new ImageIcon(newImgE3);
        JLabel imageLabelE3 = new JLabel("Crossbows: " + E_cnt_Crossbows, iconE3, JLabel.LEFT);
        imageLabelE3.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelE3.setHorizontalTextPosition(JLabel.RIGHT);
        lowerRightPanel.add(imageLabelE3);

        ImageIcon iconE4 = new ImageIcon("src\\main\\java\\com\\project\\images\\icons\\canon.png"); 
        Image imgE4 = iconE4.getImage();
        Image newImgE4 = imgE4.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        iconE4 = new ImageIcon(newImgE4);
        JLabel imageLabelE4 = new JLabel("Canons: " + E_cnt_Canons, iconE4, JLabel.LEFT);
        imageLabelE4.setFont(new Font("Microsoft Yi Baiti", Font.ROMAN_BASELINE, 18));
        imageLabelE4.setHorizontalTextPosition(JLabel.RIGHT);
        lowerRightPanel.add(imageLabelE4);

        lowerLeftPanel.setBorder(new EmptyBorder(0,30,5,0));
        lowerRightPanel.setBorder(new EmptyBorder(3,0,160,190));

        // Panel derecho con GIF de fondo y contador
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(350, getHeight()));
        rightPanel.setOpaque(false); // Hacer el fondo transparente
        add(rightPanel, BorderLayout.EAST);

        JLabel gifLabel = new JLabel(new ImageIcon("src\\main\\java\\com\\project\\images\\peleapuentegif.gif"));
        gifLabel.setLayout(new BorderLayout());

        // Panel para contener los botones azules y el contador
        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setOpaque(false); // Hacer el fondo transparente

        // Panel para los botones azules
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false); // Hacer el fondo transparente

        RoundedPanel botón1 = new RoundedPanel();
        botón1.setCornerRadius(15);
        botón1.setBackground(Color.LIGHT_GRAY);
        botón1.setPreferredSize(new Dimension(100, 42));

        escaparBotón = new JButton("Escapar");

        escaparBotón.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                escaparBotón.setForeground(Color.RED); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                escaparBotón.setForeground(Color.BLACK); // Restaura el color de fondo cuando el cursor sale
            }
        });

        escaparBotón.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        escaparBotón.setOpaque(false);
        escaparBotón.setContentAreaFilled(false);
        escaparBotón.setBorderPainted(false);
        escaparBotón.setFocusPainted(false);
        escaparBotón.setHorizontalAlignment(SwingConstants.CENTER); // Alinea el texto a la izquierda

        botón1.add(escaparBotón);

        bottomPanel.add(botón1);

        RoundedPanel botón2 = new RoundedPanel();
        botón2.setCornerRadius(15);
        botón2.setBackground(Color.LIGHT_GRAY);
        botón2.setPreferredSize(new Dimension(100, 42));

        empezarBotón = new JButton("Empezar");

        empezarBotón.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                empezarBotón.setForeground(Color.RED); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                empezarBotón.setForeground(Color.BLACK); // Restaura el color de fondo cuando el cursor sale
            }
        });

        empezarBotón.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        empezarBotón.setOpaque(false);
        empezarBotón.setContentAreaFilled(false);
        empezarBotón.setBorderPainted(false);
        empezarBotón.setFocusPainted(false);
        empezarBotón.setHorizontalAlignment(SwingConstants.CENTER); // Alinea el texto a la izquierda

        botón2.add(empezarBotón);

        bottomPanel.add(botón2);

        bottomPanel.setBorder(new EmptyBorder(505,0,0,0));

        timerPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Contador
        timerLabel = new JLabel("00:30", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("Comforta", Font.BOLD, 44));
        timerLabel.setForeground(Color.WHITE);

        // Agregar el timerLabel al timerPanel después de establecer el borde
        timerPanel.add(timerLabel, BorderLayout.NORTH);

        gifLabel.add(timerPanel, BorderLayout.NORTH);
        rightPanel.add(gifLabel);

        
    }

    // Método para actualizar el contador con el tiempo restante
    public void actualizarContador(String tiempo) {
        timerLabel.setText(tiempo);
    }

    public int getNombreSwordsman(ArrayList<ArrayList<MilitaryUnit>> ejercitoEnemigo) {
        return getElementCount(0);
    }
    
    public int getNombreSpearman(ArrayList<ArrayList<MilitaryUnit>> ejercitoEnemigo) {
        return getElementCount(1);
    }
    
    public int getNombreCrossbow(ArrayList<ArrayList<MilitaryUnit>> ejercitoEnemigo) {
        return getElementCount(2);
    }
    
    public int getNombreCannon(ArrayList<ArrayList<MilitaryUnit>> ejercitoEnemigo) {
        return getElementCount(3);
    }

    public int getElementCount(int index) {
        if (index >= 0 && index < enemyArmy.size()) {
            List<MilitaryUnit> sublista = enemyArmy.get(index); 
            return sublista.size(); 
        } else {
            return -1; 
        }
    }
}
