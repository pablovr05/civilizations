package com.project;

import java.awt.event.ActionEvent;

public class HistorialControlador {

    private MainWindow mainWindow;
    private HistorialFrame historialFrame;
    private MenúFrame menúFrame;
    private MenúControlador menúControlador;

    public HistorialControlador(HistorialFrame vBasic, MainWindow mainWindow) {
        this.historialFrame = vBasic;
        this.mainWindow = mainWindow;
    }

    public void start() {
        setupActionListeners();
    }

    private void setupActionListeners() {
        historialFrame.exitButtonHistorial.addActionListener(this::controllerButtonAction);
    }

    public void controllerButtonAction(ActionEvent e) {
        String command = e.getActionCommand();
        runCommand(command); 
    }

    public void runCommand(String command) {
        if (command.equals("Salir")) {
            SoundPlayer.playSound("clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Salir");
            llamarMenú();
        }
    } 

    private void llamarMenú() {
        menúFrame = new MenúFrame();
        mainWindow.cambiarPanel(menúFrame);
        menúControlador = new MenúControlador(menúFrame, mainWindow);
        menúControlador.start();
    }
}
