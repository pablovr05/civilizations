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
                SoundPlayer.playSound("error4.wav", 0.15f);
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

    public boolean upgradeDefense() {
        int comida = tecnologíasFrame.comida2;
        int madera = tecnologíasFrame.madera2;
        int hierro = tecnologíasFrame.hierro2;
        if(CivilizaciónControlador.civilización.food >= comida && CivilizaciónControlador.civilización.wood >= madera && CivilizaciónControlador.civilización.iron >= hierro){
            CivilizaciónControlador.civilización.food -= comida;
            CivilizaciónControlador.civilización.wood -= madera;
            CivilizaciónControlador.civilización.iron -= hierro;
            CivilizaciónControlador.civilización.technologyDefense += 1;

            actualizarLabels();

            return true;
        }else{
            System.out.println("Faltan recursos para mejorar la TechnologyDefense");  
            return false;
        }
    }
    public boolean upgradeAttack() {
        int comida = tecnologíasFrame.comida1;
        int madera = tecnologíasFrame.madera1;
        int hierro = tecnologíasFrame.hierro1;
        if(CivilizaciónControlador.civilización.food >= comida && CivilizaciónControlador.civilización.wood >= madera && CivilizaciónControlador.civilización.iron >= hierro){
            CivilizaciónControlador.civilización.food -= comida;
            CivilizaciónControlador.civilización.wood -= madera;
            CivilizaciónControlador.civilización.iron -= hierro;
            CivilizaciónControlador.civilización.technologyAttack += 1;

            actualizarLabels();

            return true;
        }else{
            System.out.println("Faltan recursos para mejorar la TechnologyAttack");  
            return false;
        }
    }

    private void actualizarLabels() {
        int[] costes = CivilizaciónControlador.civilización.getUpgradeMaterials();
        tecnologíasFrame.actualizarLabels(costes);

        tecnologíasFrame.titleRight.setText("Defensa: " + CivilizaciónControlador.civilización.technologyDefense);
        tecnologíasFrame.titleLeft.setText("Ataque: " + CivilizaciónControlador.civilización.technologyAttack);
    }
}
