package com.project;

import java.awt.event.ActionEvent;

public class EjércitoControlador {

    private MainWindow mainWindow;
    private EjércitoFrame ejércitoFrame;
    private MenúFrame menúFrame;
    private MenúControlador menúControlador;

    public EjércitoControlador(EjércitoFrame vBasic, MainWindow mainWindow) {
        this.ejércitoFrame = vBasic;
        this.mainWindow = mainWindow;
    }

    public void start() {
        setupActionListeners();
    }

    private void setupActionListeners() {
        ejércitoFrame.textFieldEjército.addActionListener(this::controllerButtonAction);
        ejércitoFrame.optionsComboBoxEjército.addActionListener(this::controllerButtonAction);
        ejércitoFrame.acceptButtonEjército.addActionListener(this::controllerButtonAction);
        ejércitoFrame.cancelButtonEjército.addActionListener(this::controllerButtonAction);
        ejércitoFrame.gifButton.addActionListener(this::controllerButtonAction);
    }

    public void controllerButtonAction(ActionEvent e) {
        String command = e.getActionCommand();
        runCommand(command); 
    }

    public void runCommand(String command) {
        if (command.equals("GIF_BUTTON_CLICKED")) {
            String tipo = ejércitoFrame.optionsComboBoxEjército.getSelectedItem().toString();
            showEjercito dialog = new showEjercito(mainWindow, tipo);
            dialog.setVisible(true);
        } if (command.equals("Cancelar")) {
            SoundPlayer.playSound("clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Salir");
            llamarMenú();
        } else if (command.equals("Aceptar")) {
            SoundPlayer.playSound("clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Aceptar");
            String tipo = ejércitoFrame.optionsComboBoxEjército.getSelectedItem().toString();
            String cantidadStr = ejércitoFrame.textFieldEjército.getText();
            try {
                int cantidad = Integer.parseInt(cantidadStr);
                SoundPlayer.playSound("botónASSERT.wav", 0.5f);
                llamarEntrenar(tipo, cantidad);
            } catch (NumberFormatException e) {
                System.out.println("La cantidad ingresada no es un número entero válido");
                SoundPlayer.playSound("rename.wav", 0.5f);
                InfoWindow infoWindow = new InfoWindow(mainWindow, "Error de tipo", "La cantidad ingresada no es correcta");
                infoWindow.setVisible(true);
            }
        }
    }

    private void llamarMenú() {
        menúFrame = new MenúFrame();
        mainWindow.cambiarPanel(menúFrame);
        menúControlador = new MenúControlador(menúFrame, mainWindow);
        menúControlador.start();
    }
    
    private void llamarEntrenar(String tipo, int cantidad) {
        System.out.println(tipo + ", " + cantidad);
        try {
            String[] strs = null;
            if (tipo.equals("Swordsman")) {
                strs = CivilizaciónControlador.civilización.newSwordsmanN(cantidad);
            } else if (tipo.equals("Spearman")) {
                strs = CivilizaciónControlador.civilización.newSpearmanN(cantidad);
            } else if (tipo.equals("Crossbow")) {
                strs = CivilizaciónControlador.civilización.newCrossbowN(cantidad);
            } else if (tipo.equals("Cannon")) {
                strs = CivilizaciónControlador.civilización.newCannonN(cantidad);
            } else if (tipo.equals("Arrow Tower")) {
                strs = CivilizaciónControlador.civilización.newArrowTowerN(cantidad);
            } else if (tipo.equals("Catapult")) {
                strs = CivilizaciónControlador.civilización.newCatapultN(cantidad);
            } else if (tipo.equals("Rocket Launcher Tower")) {
                strs = CivilizaciónControlador.civilización.newRocketLauncherTowerN(cantidad);
            } else if (tipo.equals("Magician")) {
                strs = CivilizaciónControlador.civilización.newMagicianN(cantidad);
            } else if (tipo.equals("Priest")) {
                strs = CivilizaciónControlador.civilización.newPriestN(cantidad);
            }
            if (strs != null && strs.length == 2) {
                InfoWindow infoWindow = new InfoWindow(mainWindow, strs[0], strs[1]);
                infoWindow.setVisible(true);
            }
        } catch (NumberFormatException e) {
            System.out.println("No has introducido una cantidad válida");
            InfoWindow infoWindow = new InfoWindow(mainWindow, "Error", "Has introducido una cantidad inválida");
            infoWindow.setVisible(true);
        }
    }    
}
