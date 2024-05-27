package com.project;

import java.util.Timer;
import java.util.TimerTask;

public class ContadorRecursos {

    private Timer timer;
    public int segundos;
    private FixedPanel fixedPanel;

    public ContadorRecursos(FixedPanel fixedPanel) {
        this.segundos = 0;
        this.fixedPanel = fixedPanel;
    }

    public void iniciarContador() {
        fixedPanel.updateQuantities();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                segundos++;
                if (segundos % 1 == 0) {
                    accion();
                }
            }
        }, 1000, 1000);
    }

    // Método para detener el contador
    public void detenerContador() {
        fixedPanel.updateQuantities();
        timer.cancel();
    }

    // Método para realizar la acción deseada cada segundo
    private void accion() {
        // Aquí puedes colocar la acción que deseas realizar cada segundo

        CivilizaciónControlador.civilización.updateResourceGeneration();

        System.out.println("Se generan recursos");
        
        CivilizaciónControlador.civilización.generateResourcesPerSecond();
        
        fixedPanel.updateQuantities();
    }
}
