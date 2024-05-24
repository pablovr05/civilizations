package com.project;

import java.awt.event.ActionEvent;

public class ResultadoBatallaControlador {

    private MainWindow mainWindow;
    private ResultadoBattallaFrame resultadoBattallaFrame;
    private MenúFrame menúFrame;
    private MenúControlador menúControlador;

    public ResultadoBatallaControlador(ResultadoBattallaFrame vBasic, MainWindow mainWindow) {
        this.resultadoBattallaFrame = vBasic;
        this.mainWindow = mainWindow;
    }

    public void start() {
        setupActionListeners();

    }

    private void setupActionListeners() {
        resultadoBattallaFrame.ButtonVolver.addActionListener(this::controllerButtonAction);
    }

    public void controllerButtonAction(ActionEvent e) {
        String command = e.getActionCommand();
        runCommand(command); 
    }

    public void runCommand(String command) {
        if (command.equals("Volver")) {
            SoundPlayer.playSound("./music/clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Volver");
            llamarMenú();
            mainWindow.setSize(585,415);
            mainWindow.contadorAtacar.iniciarContador();
            mainWindow.contadorRecursos.iniciarContador();
        }
    } 

    private void llamarMenú() {
        menúFrame = new MenúFrame();
        mainWindow.cambiarPanel(menúFrame);
        menúControlador = new MenúControlador(menúFrame, mainWindow);
        menúControlador.start();
    }
}
