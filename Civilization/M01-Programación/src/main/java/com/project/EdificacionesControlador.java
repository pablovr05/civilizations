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
            SoundPlayer.playSound("clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Salir");
            llamarMenú();
        } else if (command.equals("Aceptar")) {
            SoundPlayer.playSound("clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Aceptar");
            String tipo = edificacionesFrame.optionsComboBoxEdificios.getSelectedItem().toString();
            String cantidadStr = edificacionesFrame.textFieldEdificios.getText();
            try {
                int cantidad = Integer.parseInt(cantidadStr);
                SoundPlayer.playSound("botónASSERT.wav", 0.5f);
                llamarConstruir(tipo, cantidad);
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

    public void llamarConstruir(String tipo, int Cantidad) {
        System.out.println(tipo + ", " + Cantidad);
        try {
            if (tipo.equals("Farm")) {
                String[] strs = CivilizaciónControlador.civilización.newFarmN(Cantidad);
                InfoWindow infoWindow = new InfoWindow(mainWindow, strs[0], strs[1]);
                infoWindow.setVisible(true);
            } else if (tipo.equals("Smithy")) {
                String[] strs = CivilizaciónControlador.civilización.newSmithyN(Cantidad);
                InfoWindow infoWindow = new InfoWindow(mainWindow, strs[0], strs[1]);
                infoWindow.setVisible(true);
            } else if (tipo.equals("Church")) {
                String[] strs = CivilizaciónControlador.civilización.newChurchN(Cantidad);
                InfoWindow infoWindow = new InfoWindow(mainWindow, strs[0], strs[1]);
                infoWindow.setVisible(true);
            } else if (tipo.equals("Magic Tower")) {
                String[] strs = CivilizaciónControlador.civilización.newMagicTowerN(Cantidad);
                InfoWindow infoWindow = new InfoWindow(mainWindow, strs[0], strs[1]);
                infoWindow.setVisible(true);
            } else if (tipo.equals("Carpentry")) {
                String[] strs = CivilizaciónControlador.civilización.newCarpentryN(Cantidad);
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
