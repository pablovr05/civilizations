package com.project;

import java.awt.event.ActionEvent;

public class TecnologíasControlador {

    private MainWindow mainWindow;
    private TecnologíasFrame tecnologíasFrame;
    private MenúFrame menúFrame;
    private MenúControlador menúControlador;

    public TecnologíasControlador(TecnologíasFrame vBasic, MainWindow mainWindow) {
        this.tecnologíasFrame = vBasic;
        this.mainWindow = mainWindow;
    }

    public void start() {
        setupActionListeners();
    }

    private void setupActionListeners() {
        tecnologíasFrame.exitButtonTecnologías.addActionListener(this::controllerButtonAction);
    }

    public void controllerButtonAction(ActionEvent e) {
        String command = e.getActionCommand();
        runCommand(command); 
    }

    public void runCommand(String command) {
        if (command.equals("Salir")) {
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
