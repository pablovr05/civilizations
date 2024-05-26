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
        tecnologíasFrame.upgradeAttack.addActionListener(this::controllerButtonAction);
        tecnologíasFrame.upgradeDefense.addActionListener(this::controllerButtonAction);
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
        } if (command.equals("upgradeAttack")) {
            System.out.println("Se pulsó el botón upgradear ataque");
            if (upgradeAttack()) {
                SoundPlayer.playSound("levelup.wav", 0.15f);
            } else {
                SoundPlayer.playSound("error4", 0.15f);
            }
        } if (command.equals("upgradeDefense")) {
            System.out.println("Se pulsó el botón upgradear defensa");
            if (upgradeDefense()) {
                SoundPlayer.playSound("levelup.wav", 0.15f);
            } else {
                SoundPlayer.playSound("error4.wav", 0.15f);
            }
        }
    } 

    private void llamarMenú() {
        menúFrame = new MenúFrame();
        mainWindow.cambiarPanel(menúFrame);
        menúControlador = new MenúControlador(menúFrame, mainWindow);
        menúControlador.start();
    }

    private boolean upgradeAttack() {
        return true;
        //primero comprobar que se puede upgradear y en caso de si o no devolver true o false, si es false se return false...
        //hacer las operaciones de restar los recursos
        //subir el nivel de tecnologia
        //cambiar los contadores de los materiales necesarios para el proximo nivel
    }

    private boolean upgradeDefense() {
        return false;
        //primero comprobar que se puede upgradear y en caso de si o no devolver true o false, si es false se return false...
        //hacer las operaciones de restar los recursos
        //subir el nivel de tecnologia
        //cambiar los contadores de los materiales necesarios para el proximo nivel
    }
}
