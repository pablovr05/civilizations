package com.project;

import java.awt.event.ActionEvent;

public class EdificacionesControlador {

    private MainWindow mainWindow;
    private EdificacionesFrame edificacionesFrame;
    private MenúFrame menúFrame;
    private MenúControlador menúControlador;

    public EdificacionesControlador(EdificacionesFrame vBasic, MainWindow mainWindow) {
        this.edificacionesFrame = vBasic;
        this.mainWindow = mainWindow;
    }

    public void start() {
        setupActionListeners();
    }

    private void setupActionListeners() {
        edificacionesFrame.textFieldEdificios.addActionListener(this::controllerButtonAction);
        edificacionesFrame.optionsComboBoxEdificios.addActionListener(this::controllerButtonAction);
        edificacionesFrame.acceptButtonEdificios.addActionListener(this::controllerButtonAction);
        edificacionesFrame.cancelButtonEdificios.addActionListener(this::controllerButtonAction);
    }

    public void controllerButtonAction(ActionEvent e) {
        String command = e.getActionCommand();
        runCommand(command); 
    }

    public void runCommand(String command) {
        if (command.equals("Cancelar")) {
            SoundPlayer.playSound("./music/clickselect.wav", 0.5f);
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
