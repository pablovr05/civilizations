package com.project;

import javax.swing.*;
import java.awt.*;

import java.util.List;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class HistorialFrame extends JPanel {

    private String fondo;

    private ArrayList<int[]> initialArmies;
    private int[] civililizationDrops;
    private int[] enemyDrops;
    private ArrayList<int[]> initialCostFleet;
    private ArrayList<int[]> resourcesLooses;
    private int[] wasteWoodIron;
    public ArrayList<ArrayList<String[]>> desarrolloBatalla;

    public JButton exitButtonHistorial;

    public HistorialFrame() {

        this.fondo = "src\\main\\java\\com\\project\\images\\historialgif.gif";
        
        setLayout(new BorderLayout());

        // Crea un JLabel para mostrar el GIF de fondo
        JLabel background = new JLabel(new ImageIcon(fondo));
        background.setLayout(new BorderLayout()); // Establece el layout para el JLabel del fondo
        add(background);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false); // Hace que el panel no sea opaco para que el fondo sea visible
        contentPanel.setBorder(new EmptyBorder(0,0,0,0));
        background.add(contentPanel, BorderLayout.CENTER);

        background.setBorder(new EmptyBorder(25,25,25,25));

        RoundedPanel leftPanel = new RoundedPanel();
        leftPanel.setCornerRadius(20);
        leftPanel.setOpaque(false);
        leftPanel.setBackground(new Color(0, 0, 0, 150)); //color de fondo para cada panel
        leftPanel.setPreferredSize(new Dimension(385, 500));
        contentPanel.add(leftPanel);

        RoundedPanel titlePanel = new RoundedPanel();
        titlePanel.setCornerRadius(20);
        titlePanel.setOpaque(false);
        titlePanel.setBackground(new Color(255, 255, 255, 200));
        titlePanel.setPreferredSize(new Dimension(310, 50));
        leftPanel.add(titlePanel);

        JLabel textoTítulo = new JLabel("Historial de batalla");
        textoTítulo.setFont(new Font("Reem Kufi", Font.BOLD, 25));
        textoTítulo.setForeground(Color.BLACK);
        titlePanel.add(textoTítulo);

        RoundedPanel leftContentPanel = new RoundedPanel();
        leftContentPanel.setCornerRadius(20);
        leftContentPanel.setOpaque(false);
        leftContentPanel.setBackground(new Color(255, 255, 255, 100));
        leftContentPanel.setPreferredSize(new Dimension(350, 415));
        leftPanel.add(leftContentPanel);

        crearScrollIzquierdo();

        RoundedPanel rightPanel = new RoundedPanel();
        rightPanel.setCornerRadius(20);
        rightPanel.setOpaque(false);
        rightPanel.setBackground(new Color(255, 0, 0, 200)); //color de fondo de cada panel
        rightPanel.setPreferredSize(new Dimension(685, 555));
        contentPanel.add(rightPanel);

    
    }

    private JScrollPane crearScrollIzquierdo() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(10,10,10,10));
        int id = CivilizaciónControlador.civilización.id;
        ArrayList<Integer> listaids = BattleDAO.listaBatallasCivilization(id);
        for (int i = 0; i < listaids.size(); i++ ) {
            
        }


        return scrollPane;
    }
}
