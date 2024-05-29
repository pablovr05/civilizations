package com.project;

import javax.swing.*;
import java.awt.*;

import java.util.List;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    private RoundedPanel rightPanelContent;

    private JLabel textoTítuloRight;


    public HistorialFrame() {

        System.out.println(1);

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
        leftPanel.setPreferredSize(new Dimension(385, 512));
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
        leftContentPanel.setPreferredSize(new Dimension(350, 400));
        leftPanel.add(leftContentPanel);

        leftContentPanel.add(crearScrollIzquierdo());

        JPanel buttonPanel = new JPanel();
        exitButtonHistorial = new JButton("Volver");
        exitButtonHistorial.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        exitButtonHistorial.setOpaque(false);
        exitButtonHistorial.setContentAreaFilled(false);
        exitButtonHistorial.setBorderPainted(false);
        exitButtonHistorial.setFocusPainted(false);
        exitButtonHistorial.setForeground(Color.RED);

        exitButtonHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButtonHistorial.setForeground(Color.ORANGE); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButtonHistorial.setForeground(Color.RED); // Restaura el color de fondo cuando el cursor sale
            }
        });

        buttonPanel.add(exitButtonHistorial);

        buttonPanel.setBorder(new EmptyBorder(0,285,0,0));
        buttonPanel.setBackground(new Color(0,0,0,0));

        leftPanel.add(buttonPanel);

        RoundedPanel rightPanel = new RoundedPanel();
        rightPanel.setCornerRadius(20);
        rightPanel.setOpaque(false);
        rightPanel.setBackground(new Color(0, 0, 0, 200)); //color de fondo de cada panel
        rightPanel.setPreferredSize(new Dimension(685, 555));

        RoundedPanel rightPanelTitle = new RoundedPanel();
        rightPanelTitle.setCornerRadius(20);
        rightPanelTitle.setOpaque(false);
        rightPanelTitle.setBackground(new Color(255, 255, 255, 200)); //color de fondo de cada panel
        rightPanelTitle.setPreferredSize(new Dimension(575, 65));

        textoTítuloRight = new JLabel("Desarrollo Batalla");
        textoTítuloRight.setFont(new Font("Reem Kufi", Font.BOLD, 25));
        textoTítuloRight.setForeground(Color.BLACK);
        rightPanelTitle.add(textoTítuloRight);

        rightPanel.add(rightPanelTitle);

        rightPanelContent = new RoundedPanel();
        rightPanelContent.setCornerRadius(20);
        rightPanelContent.setOpaque(false);
        rightPanelContent.setBackground(new Color(255, 255, 255, 200)); //color de fondo de cada panel
        rightPanelContent.setPreferredSize(new Dimension(673, 475));

        rightPanel.add(rightPanelContent);

        contentPanel.add(rightPanel);

    }

    private JScrollPane crearScrollIzquierdo() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(10,10,10,10));
        JPanel contenido = new JPanel(); // Creamos un panel para contener los paneles redondeados
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS)); // Layout para organizar verticalmente
        int id = CivilizaciónControlador.civilización.id;
        ArrayList<Integer> listaids = BattleDAO.listaBatallasCivilization(id);
        for (int i = 0; i < 7; i++ ) {

            boolean winner = false; // Inicializar la variable para usarla fuera del bloque try

            try {
                winner = BattleDAO.getBattleWinner(listaids.get(i), id);
            } catch (Exception e) {
                scrollPane.setBackground(new Color(0,0,0,0));
                return scrollPane;
            }

            int[] wastes = BattleDAO.getBattleWaste(listaids.get(i), id);
            
            String título = "";
            Color color = null;
            if (winner) {
                título = listaids.get(i) + ". Victoria";
                color = Color.GREEN;
            } else {
                título = listaids.get(i) + ". Derrota";
                color = Color.RED;
            }
    
            RoundedPanel roundedPanel = new RoundedPanel();
            roundedPanel.setLayout(new GridLayout(3, 1));
            roundedPanel.setPreferredSize(new Dimension(300, 50));
    
            JLabel primeraLinea = new JLabel(título);
            primeraLinea.setForeground(color);
            JLabel segundaLinea = new JLabel("Madera: " + wastes[0]);
            JLabel terceraLinea = new JLabel("Hierro: " + wastes[1]);
    
            roundedPanel.add(primeraLinea);
            roundedPanel.add(segundaLinea);
            roundedPanel.add(terceraLinea);
    
            roundedPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        String text = primeraLinea.getText() + ", " + segundaLinea.getText() + ", " + terceraLinea.getText();
                        System.out.println("Panel seleccionado: " + text);

                        String[] primeraLineaSplit = primeraLinea.getText().split("\\. ");
                        int idBatallaSeleccionada = Integer.parseInt(primeraLineaSplit[0]);
                        System.out.println("Id de la pelea seleccionada: " + idBatallaSeleccionada);

                        textoTítuloRight.setText("Desarrollo Batalla: " + idBatallaSeleccionada);

                        actualizarLista(idBatallaSeleccionada);
                    }
                }
            });
    
            contenido.add(roundedPanel); // Agregamos el panel redondeado al panel de contenido
        }
    
        // Establecemos el panel de contenido en el JScrollPane
        scrollPane.setViewportView(contenido);
        
        // Establecemos la política de visualización de las barras de desplazamiento
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        return scrollPane;
    }
    
    private void actualizarLista(int id) {
        // Eliminar todo el contenido existente del rightPanelContent
        rightPanelContent.removeAll();
    
        // Obtener el desarrollo de la batalla
        ArrayList<ArrayList<String[]>> desarrolloBatalla = BattleDAO.getDesarrolloBatalla(id, CivilizaciónControlador.civilización.id);
        
        // Crear un nuevo BatallaPanel con el nuevo desarrollo de batalla
        BatallaPanel batallaPanel = new BatallaPanel(desarrolloBatalla);
        batallaPanel.setBorder(new EmptyBorder(5,0,0,0));
        batallaPanel.setBackground(new Color(0,0,0,0));
        batallaPanel.setBackground(new Color(0,0,0,0));
        batallaPanel.setVisible(true);
    
        // Agregar el nuevo panel al rightPanelContent
        rightPanelContent.add(batallaPanel);
    
        // Validar y actualizar el layout del rightPanelContent
        rightPanelContent.revalidate();
        rightPanelContent.repaint();
    }
}
