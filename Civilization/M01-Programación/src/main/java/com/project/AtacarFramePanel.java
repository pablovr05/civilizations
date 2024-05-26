
package com.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class AtacarFramePanel extends JPanel {
    private RoundedPanel centerPanel;
    private JLabel[] labels;
    private int currentPhraseIndex = 0;
    private String[] phrases = {
        "1. En la batalla, nuestros ejércitos, con diversas unidades, enfrentan a un enemigo sin defensas.",
        "2. Cada tipo de unidad forma un grupo, determinando quién ataca en cada turno.",
        "3. La selección del grupo atacante se basa en probabilidades establecidas previamente.",
        "4. Una vez elegido el atacante, se selecciona una unidad de ese grupo para el ataque.",
        "5. La elección del grupo defensor depende de la cantidad de unidades en ese grupo.",
        "6. La unidad defensora se elige aleatoriamente dentro del grupo seleccionado.",
        "7. Durante el ataque, se inflige daño y se reduce la armadura del defensor.",
        "8. Si la armadura se reduce a cero, la unidad es eliminada.",
        "9. Se pueden generar residuos al ser eliminada una unidad, afectando los recursos disponibles.",
        "10. Algunas unidades pueden atacar nuevamente con cierta probabilidad.",
        "11. Si no atacan nuevamente, se cambia el turno entre atacante y defensor.",
        "12. La batalla continúa hasta que uno de los ejércitos pierda al menos el 20% de sus unidades.",
        "13. Al final, el ejército con menos pérdidas de recursos será el ganador.",
        "14. Si vencemos, podemos recolectar los residuos de la batalla."
    };

    public AtacarFramePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(710, 250)); // Establecer tamaño preferido
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Agregar un borde de 10 píxeles
        setOpaque(false); // Establecer el panel como transparente

        // Crear el botón y configurar su fondo como transparente y el borde como null
        JButton button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorder(null); // Establecer el borde como null para quitar el borde gris
        button.setIcon(new ImageIcon("src\\main\\java\\com\\project\\images\\next.png")); // Ruta de la imagen
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changePhrase();
            }
        });

        // Crear el panel central con GridLayout de 5 filas
        centerPanel = new RoundedPanel();
        centerPanel.setBackground(new Color(0, 0, 0, 150)); // Fondo semi-transparente
        centerPanel.setLayout(new GridLayout(5, 1)); // 5 filas para las frases

        // Crear las etiquetas para las primeras 5 frases y establecer el color azul
        labels = new JLabel[5];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(phrases[i]);
            labels[i].setForeground(Color.WHITE);
            labels[i].setFont(new Font("Scheherazade", Font.ROMAN_BASELINE, 15));
            labels[i].setBorder(new EmptyBorder(0,10,0,0));
            centerPanel.add(labels[i]);
        }

        // Agregar el panel central al BorderLayout.CENTER
        add(centerPanel, BorderLayout.CENTER);

        // Crear un panel para el botón con FlowLayout alineado a la derecha
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false); // Configurar el fondo del panel como transparente
        buttonPanel.add(button);

        // Agregar el panel del botón al BorderLayout.SOUTH
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Método para cambiar las frases
    private void changePhrase() {
        currentPhraseIndex = (currentPhraseIndex + 1) % phrases.length;
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(phrases[(currentPhraseIndex + i) % phrases.length]);
            SoundPlayer.playSound("page.wav", 1);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Tamaño de la ventana
        frame.add(new AtacarFramePanel());
        frame.setVisible(true);
    }
}
