package com.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class EstadísticasFrame extends JPanel {

    private Color colorCambio = new Color(91, 213, 91 );
    private Color colorDefault = new Color(255, 255, 255);
    public JButton backButtonEstadísticas;

    public EstadísticasFrame() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("src\\main\\java\\com\\project\\images\\fondomenu8.jpg"));
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Estadísticas de " + CivilizaciónControlador.civilización.name);
        titleLabel.setFont(new Font("Candara", Font.BOLD, 24));
        titleLabel.setForeground(new Color(230, 230, 230));
        titlePanel.add(titleLabel);

        panel.add(titleLabel, BorderLayout.NORTH);

        List<String> descriptions = new ArrayList<>();
        List<Integer> results = new ArrayList<>();

        descriptions.add("Recursos: Cantidad de comida");
        results.add(CivilizaciónControlador.civilización.food);
        descriptions.add("Recursos: Cantidad de madera");
        results.add(CivilizaciónControlador.civilización.wood);
        descriptions.add("Recursos: Cantidad de hierro");
        results.add(CivilizaciónControlador.civilización.iron);
        descriptions.add("Recursos: Cantidad de maná");
        results.add(CivilizaciónControlador.civilización.mana);

        descriptions.add("Generación: Comida");
        results.add(CivilizaciónControlador.civilización.foodGeneration);
        descriptions.add("Generación: Madera");
        results.add(CivilizaciónControlador.civilización.woodGeneration);
        descriptions.add("Generación: Hierro");
        results.add(CivilizaciónControlador.civilización.ironGeneration);
        descriptions.add("Generación: Maná");
        results.add(CivilizaciónControlador.civilización.manaGeneration);

        descriptions.add("Nivel de tecnología: Defensa");
        results.add(CivilizaciónControlador.civilización.technologyDefense);
        descriptions.add("Nivel de tecnología: Ataque");
        results.add(CivilizaciónControlador.civilización.technologyAttack);

        descriptions.add("Construcciones: Farm");
        results.add(CivilizaciónControlador.civilización.farm);
        descriptions.add("Construcciones: Smithy");
        results.add(CivilizaciónControlador.civilización.smithy);
        descriptions.add("Construcciones: Carpentry");
        results.add(CivilizaciónControlador.civilización.carpentry);
        descriptions.add("Construcciones: Magic Tower");
        results.add(CivilizaciónControlador.civilización.magicTower);
        descriptions.add("Construcciones: Church");
        results.add(CivilizaciónControlador.civilización.church);

        descriptions.add("Defensas: Arrow Tower");
        results.add(CivilizaciónControlador.civilización.getNombreArrowTower());
        descriptions.add("Defensas: Catapult");
        results.add(CivilizaciónControlador.civilización.getNombreCatapult());
        descriptions.add("Defensas: Rocket Launcher");
        results.add(CivilizaciónControlador.civilización.getNombreRocketLauncher());

        descriptions.add("Unidades de ataque: Swordsman");
        results.add(CivilizaciónControlador.civilización.getNombreSwordsman());
        descriptions.add("Unidades de ataque: Spearman");
        results.add(CivilizaciónControlador.civilización.getNombreSpearman());
        descriptions.add("Unidades de ataque: Crossbow");
        results.add(CivilizaciónControlador.civilización.getNombreCrossbow());
        descriptions.add("Unidades de ataque: Cannon");
        results.add(CivilizaciónControlador.civilización.getNombreCannon());

        descriptions.add("Unidades especiales: Magician");
        results.add(CivilizaciónControlador.civilización.getNombreMagician());
        descriptions.add("Unidades especiales: Priest");
        results.add(CivilizaciónControlador.civilización.getNombrePriest());

        Object[][] data = new Object[24][2];
        for (int i = 0; i < 24; i++) {
            data[i][0] = descriptions.get(i);
            data[i][1] = results.get(i);
        }

        DefaultTableModel model = new DefaultTableModel(data, new String[]{"Descripción", "Resultado"});
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desactiva la edición de celdas
            }

            @Override
            public boolean isCellSelected(int row, int column) {
                return false; // Desactiva la selección de celdas
            }
        };

        Dimension tableSize = new Dimension(460, 225);
        table.setPreferredScrollableViewportSize(tableSize);

        // Desactiva la selección de filas
        table.setRowSelectionAllowed(false);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            Font font = new Font("Segoe UI", Font.ROMAN_BASELINE, 10);
        
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setFont(font);
                if (column == 1) {
                    setHorizontalAlignment(SwingConstants.CENTER); // Alinea el texto al centro solo para la columna "Resultado"
                } else {
                    setHorizontalAlignment(SwingConstants.LEFT); // Alinea el texto al centro para la primera columna
                }
                setBackground(new Color(230, 230, 230));
                return this;
            }
        };
        
        // Aplica el renderizador personalizado a todas las columnas
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);

        // Renderizador personalizado para el encabezado de la tabla
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14)); // Cambia el tipo de fuente y el tamaño
        header.setReorderingAllowed(false); // Desactiva el reordenamiento de columnas

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.LEFT); // Centra el título de la primera columna

        // Ajusta el ancho preferido de la columna "Resultado"
        table.getColumnModel().getColumn(0).setPreferredWidth(425);

        // Ajusta la altura de las filas
        table.setRowHeight(30);

        // Envuelve la tabla en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Botón a la derecha
        backButtonEstadísticas = new JButton("Volver");
        backButtonEstadísticas.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        backButtonEstadísticas.setOpaque(false);
        backButtonEstadísticas.setContentAreaFilled(false);
        backButtonEstadísticas.setBorderPainted(false);
        backButtonEstadísticas.setFocusPainted(false);
        backButtonEstadísticas.setForeground(colorDefault);
        backButtonEstadísticas.setHorizontalAlignment(SwingConstants.LEFT);

        backButtonEstadísticas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButtonEstadísticas.setForeground(colorCambio); // Cambia el color de fondo cuando el cursor entra
                SoundPlayer.playSound("botónopciones.wav", 0.5f);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButtonEstadísticas.setForeground(colorDefault); // Restaura el color de fondo cuando el cursor sale
            }
        });
        
        buttonPanel.add(backButtonEstadísticas);

        buttonPanel.setBorder(new EmptyBorder(10,390,0,0));

        buttonPanel.setOpaque(false);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        panel.setBorder(new EmptyBorder(10,0,0,0));

        add(panel);

    }
}
