package com.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import java.util.List;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;

import java.awt.event.*;

public class ResultadoBattallaFrame extends JPanel {

    private String título;
    private String fondo;
    private Color color;

    public JButton ButtonVolver;

    private Battle battle;
    private ArrayList<int[]> initialArmies;
    private int[] civililizationDrops;
    private int[] enemyDrops;
    private ArrayList<int[]> initialCostFleet;
    private ArrayList<int[]> resourcesLooses;
    private int[] wasteWoodIron;
    public ArrayList<ArrayList<String[]>> desarrolloBatalla;
    
    public ResultadoBattallaFrame(boolean winner, Battle battle) {

        this.battle = battle;
        this.initialArmies = battle.initialArmies;
        this.civililizationDrops = battle.civilizationDrops;
        this.enemyDrops = battle.enemyDrops;
        this.initialCostFleet = battle.initialCostFleet;
        this.resourcesLooses = battle.resourcesLooses;
        this.wasteWoodIron = battle.wasteWoodIron;
        this.desarrolloBatalla = battle.desarrolloBatalla;

        if ( winner ) {
            título = "VICTORIA";
            fondo = "src\\main\\java\\com\\project\\images\\victoria.gif";
            color = new Color(0,255,0);
        } else {
            título = "DERROTA";
            fondo = "src\\main\\java\\com\\project\\images\\derrota.gif";
            color = new Color(255,0,0);
        }

        setLayout(new BorderLayout());

        // Crea un JLabel para mostrar el GIF de fondo
        JLabel background = new JLabel(new ImageIcon(fondo));
        background.setLayout(new BorderLayout()); // Establece el layout para el JLabel del fondo
        add(background);

        // Crea un panel para colocar los componentes sobre el fondo
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false); // Hace que el panel no sea opaco para que el fondo sea visible
        contentPanel.setBorder(new EmptyBorder(0,0,0,0));
        background.add(contentPanel, BorderLayout.CENTER);

        background.setBorder(new EmptyBorder(25,25,25,25));

        // Crea el panel izquierdo con color rojo transparente
        RoundedPanel leftPanel = new RoundedPanel();
        leftPanel.setCornerRadius(20);
        leftPanel.setOpaque(false);
        leftPanel.setBackground(new Color(0, 0, 0, 0));
        leftPanel.setPreferredSize(new Dimension(335, 500));
        contentPanel.add(leftPanel);

        RoundedPanel titlePanel = new RoundedPanel();
        titlePanel.setCornerRadius(20);
        titlePanel.setOpaque(false);
        titlePanel.setBackground(new Color(255, 255, 255, 100));
        titlePanel.setPreferredSize(new Dimension(320, 100));
        leftPanel.add(titlePanel);

        JLabel textoTítulo = new JLabel(título);
        textoTítulo.setFont(new Font("Reem Kufi", Font.BOLD, 59));
        textoTítulo.setForeground(color);
        titlePanel.add(textoTítulo);

        RoundedPanel leftContentPanel = new RoundedPanel();
        leftContentPanel.setCornerRadius(20);
        leftContentPanel.setOpaque(false);
        leftContentPanel.setBackground(new Color(255, 255, 255));
        leftContentPanel.setPreferredSize(new Dimension(320, 380));
        leftPanel.add(leftContentPanel);

        JPanel leftEstadísticasPanel = new JPanel();
        leftEstadísticasPanel.setBackground(new Color(255,255,255,255));
        leftEstadísticasPanel.setPreferredSize(new Dimension(300, 300));
        leftContentPanel.add(leftEstadísticasPanel);

        JPanel infoArmies = new JPanel(new GridLayout(1, 2));

        // Crear la tabla de Civilization Info con columnas estrechas
        String[] columnNamesCivilization = {"Unidad", "Bajas"};
        Object[][] dataCivilization = {
            {"Swordsman", initialArmies.get(0)[0] + " -> " + civililizationDrops[0]},
            {"Spearman", initialArmies.get(0)[1] + " -> " + civililizationDrops[1]},
            {"Crossbow", initialArmies.get(0)[2] + " -> " + civililizationDrops[2]},
            {"Cannon", initialArmies.get(0)[3] + " -> " + civililizationDrops[3]},
            {"Arrow Tower", initialArmies.get(0)[4] + " -> " + civililizationDrops[4]},
            {"Catapult", initialArmies.get(0)[5] + " -> " + civililizationDrops[5]},
            {"Rocket Launcher Tower", initialArmies.get(0)[6] + " -> " + civililizationDrops[6]},
            {"Magician", initialArmies.get(0)[7] + " -> " + civililizationDrops[7]},
            {"Priest", initialArmies.get(0)[8] + " -> " + civililizationDrops[8]}
        };

        JTable tableCivilization = new JTable(dataCivilization, columnNamesCivilization);
        tableCivilization.setRowHeight(17); // Ajustar la altura de las filas
        tableCivilization.setFont(new Font("Segoe UI", Font.PLAIN, 8)); // Establecer la fuente del texto
        tableCivilization.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 8)); // Establecer la fuente del encabezado de la tabla
        tableCivilization.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableCivilization.getColumnModel().getColumn(0).setPreferredWidth(90);
        tableCivilization.getColumnModel().getColumn(1).setPreferredWidth(55);
        tableCivilization.setEnabled(false); // Hacer que la tabla no sea editable
        JPanel inforArmiesCivilization = new JPanel(new BorderLayout());
        TitledBorder civilizationBorder = BorderFactory.createTitledBorder("Civilization Info");
        civilizationBorder.setTitleJustification(TitledBorder.CENTER);
        inforArmiesCivilization.setBorder(civilizationBorder);
        inforArmiesCivilization.add(tableCivilization.getTableHeader(), BorderLayout.NORTH); // Agregar solo el encabezado
        inforArmiesCivilization.add(tableCivilization, BorderLayout.CENTER);

        infoArmies.add(inforArmiesCivilization);

        // Crear la tabla de Enemy Info con columnas estrechas
        String[] columnNamesEnemy = {"Unidad", "Bajas"};
        Object[][] dataEnemy = {
            {"Swordsman", initialArmies.get(1)[0] + " -> " + enemyDrops[0]},
            {"Spearman", initialArmies.get(1)[1] + " -> " + enemyDrops[1]},
            {"Crossbow", initialArmies.get(1)[2] + " -> " + enemyDrops[2]},
            {"Cannon", initialArmies.get(1)[3] + " -> " + enemyDrops[3]}
        };

        JTable tableEnemy = new JTable(dataEnemy, columnNamesEnemy);
        tableEnemy.setRowHeight(17); // Ajustar la altura de las filas
        tableEnemy.setFont(new Font("Segoe UI", Font.PLAIN, 8)); // Establecer la fuente del texto
        tableEnemy.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 8)); // Establecer la fuente del encabezado de la tabla
        tableEnemy.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableEnemy.getColumnModel().getColumn(0).setPreferredWidth(90);
        tableEnemy.getColumnModel().getColumn(1).setPreferredWidth(55);
        tableEnemy.setEnabled(false); // Hacer que la tabla no sea editable
        JPanel inforArmiesEnemy = new JPanel(new BorderLayout());
        TitledBorder enemyBorder = BorderFactory.createTitledBorder("Enemy Info");
        enemyBorder.setTitleJustification(TitledBorder.CENTER);
        inforArmiesEnemy.setBorder(enemyBorder);
        inforArmiesEnemy.add(tableEnemy.getTableHeader(), BorderLayout.NORTH); // Agregar solo el encabezado
        inforArmiesEnemy.add(tableEnemy, BorderLayout.CENTER);

        infoArmies.add(inforArmiesEnemy);

        leftEstadísticasPanel.add(infoArmies);

        JPanel fleetInfo = new JPanel(new GridLayout(1, 2));

        // Crear la tabla de Initial Cost Fleet con columnas estrechas
        String[] columnNamesInitialCost = {"Material", "Resource Losses"};
        Object[][] dataInitialCost = {
            {"Food", initialCostFleet.get(0)[0] + " -> " + resourcesLooses.get(0)[0]},
            {"Wood", initialCostFleet.get(0)[1] + " -> " + resourcesLooses.get(0)[1]},
            {"Iron", initialCostFleet.get(0)[2] + " -> " + resourcesLooses.get(0)[2]},
            //{"Mana", initialCostFleet.get(0)[3] + " -> " + resourcesLooses.get(0)[3]},
        };

        JTable tableInitialCost = new JTable(dataInitialCost, columnNamesInitialCost);
        tableInitialCost.setRowHeight(17); // Ajustar la altura de las filas
        tableInitialCost.setFont(new Font("Segoe UI", Font.PLAIN, 8)); // Establecer la fuente del texto
        tableInitialCost.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 8)); // Establecer la fuente del encabezado de la tabla
        tableInitialCost.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableInitialCost.getColumnModel().getColumn(0).setPreferredWidth(70);
        tableInitialCost.getColumnModel().getColumn(1).setPreferredWidth(75);
        tableInitialCost.setEnabled(false); // Hacer que la tabla no sea editable
        JPanel inforInitialCostFleet = new JPanel(new BorderLayout());
        TitledBorder initialCostBorder = BorderFactory.createTitledBorder("Civilization initial Cost Fleet");
        initialCostBorder.setTitleJustification(TitledBorder.CENTER);
        inforInitialCostFleet.setBorder(initialCostBorder);
        inforInitialCostFleet.add(tableInitialCost.getTableHeader(), BorderLayout.NORTH); // Agregar solo el encabezado
        inforInitialCostFleet.add(tableInitialCost, BorderLayout.CENTER);

        fleetInfo.add(inforInitialCostFleet);

        // Crear la tabla de Resources Looses con columnas estrechas
        String[] columnNamesResourcesLooses = {"Material", "Resource Losses"};
        Object[][] dataResourcesLooses = {
            {"Food", initialCostFleet.get(1)[0] + " -> " + resourcesLooses.get(1)[0]},
            {"Wood", initialCostFleet.get(1)[1] + " -> " + resourcesLooses.get(1)[1]},
            {"Iron", initialCostFleet.get(1)[2] + " -> " + resourcesLooses.get(1)[2]},
            //{"Mana", initialCostFleet.get(1)[3] + " -> " + resourcesLooses.get(1)[3]},
        };

        JTable tableResourcesLooses = new JTable(dataResourcesLooses, columnNamesResourcesLooses);
        tableResourcesLooses.setRowHeight(17); // Ajustar la altura de las filas
        tableResourcesLooses.setFont(new Font("Segoe UI", Font.PLAIN, 8)); // Establecer la fuente del texto
        tableResourcesLooses.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 8)); // Establecer la fuente del encabezado de la tabla
        tableResourcesLooses.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableResourcesLooses.getColumnModel().getColumn(0).setPreferredWidth(70);
        tableResourcesLooses.getColumnModel().getColumn(1).setPreferredWidth(75);
        tableResourcesLooses.setEnabled(false); // Hacer que la tabla no sea editable
        JPanel inforResourcesLooses = new JPanel(new BorderLayout());
        TitledBorder resourcesLoosesBorder = BorderFactory.createTitledBorder("Enemy initial Cost Fleet");
        resourcesLoosesBorder.setTitleJustification(TitledBorder.CENTER);
        inforResourcesLooses.setBorder(resourcesLoosesBorder);
        inforResourcesLooses.add(tableResourcesLooses.getTableHeader(), BorderLayout.NORTH); // Agregar solo el encabezado
        inforResourcesLooses.add(tableResourcesLooses, BorderLayout.CENTER);

        fleetInfo.add(inforResourcesLooses);

        leftEstadísticasPanel.add(fleetInfo);

        JLabel maderaConseguida = new JLabel("Madera conseguida: " + wasteWoodIron[0]);
        maderaConseguida.setFont(new Font("Segoe UI", Font.BOLD, 8));
        JLabel hierroConseguido = new JLabel("Hierro conseguido: " + wasteWoodIron[1]);
        hierroConseguido.setFont(new Font("Segoe UI", Font.BOLD, 8));

        leftEstadísticasPanel.add(maderaConseguida);
        leftEstadísticasPanel.add(hierroConseguido);

        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setBackground(new Color(255,255,255,255));
        ButtonPanel.setPreferredSize(new Dimension(300, 50));
        ButtonVolver = new JButton("Volver");
        ButtonVolver.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        ButtonVolver.setContentAreaFilled(false);
        ButtonVolver.setBorderPainted(false);
        ButtonVolver.setFocusPainted(false);
        ButtonVolver.setHorizontalAlignment(SwingConstants.RIGHT); // Alinea el texto a la izquierda
        ButtonPanel.setBorder(new EmptyBorder(10,225,0,0));
        ButtonPanel.add(ButtonVolver); // Agregar el botón al panel de botones
        leftContentPanel.add(ButtonPanel);

        ButtonVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonVolver.setForeground(color); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonVolver.setForeground(Color.BLACK); // Restaura el color de fondo cuando el cursor sale
            }
        });
        // Crea el panel derecho con color azul transparente
        RoundedPanel rightPanel = new RoundedPanel();
        rightPanel.setLayout(new BorderLayout()); // Usamos BorderLayout para llenar todo el espacio
        rightPanel.setCornerRadius(20);
        rightPanel.setOpaque(false);
        rightPanel.setBackground(new Color(0, 0, 0, 0));
        rightPanel.setPreferredSize(new Dimension(735, 555));
        contentPanel.add(rightPanel, BorderLayout.EAST); // Agrega el rightPanel al contentPanel

        RoundedPanel rightContentPanel = new RoundedPanel();
        rightContentPanel.setLayout(new BoxLayout(rightContentPanel, BoxLayout.Y_AXIS)); // BoxLayout con eje Y para apilar los paneles verticalmente
        rightContentPanel.setCornerRadius(20);
        rightContentPanel.setOpaque(false);
        rightContentPanel.setBackground(new Color(0,0,0,0)); // Cambiado el color de fondo a blanco
        rightContentPanel.setPreferredSize(new Dimension(735, 535)); // Ajustado el tamaño del rightContentPanel
        rightPanel.add(rightContentPanel, BorderLayout.CENTER); // Agrega el rightContentPanel al rightPanel

        JPanel firstPanel = new JPanel();
        firstPanel.setBackground(new Color(0,0,0,0)); // Color de fondo del primer panel
        firstPanel.setOpaque(false); // Hacer el fondo transparente
        firstPanel.setPreferredSize(new Dimension(500, 50)); // Ajustado el tamaño del primer panel

        RoundedPanel subtitlePanel = new RoundedPanel();
        subtitlePanel.setCornerRadius(20);
        subtitlePanel.setBackground(new Color(255,255,255,200));
        JLabel titleLabel = new JLabel("Desarrollo de la batalla");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        subtitlePanel.add(titleLabel);
        firstPanel.add(subtitlePanel); // Agregado el primer panel al rightContentPanel

        rightContentPanel.add(firstPanel);

        JPanel secondPanel = new JPanel();
        BatallaPanel batallaPanel = new BatallaPanel(desarrolloBatalla);
        batallaPanel.setBorder(new EmptyBorder(5,0,0,0));
        batallaPanel.setBackground(new Color(0,0,0,0));
        secondPanel.setBackground(new Color(0,0,0,0));
        batallaPanel.setVisible(true);
        secondPanel.add(batallaPanel);
        rightContentPanel.add(secondPanel);
    }
}