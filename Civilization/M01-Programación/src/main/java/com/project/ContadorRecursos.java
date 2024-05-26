package com.project;

import java.util.Timer;
import java.util.TimerTask;

public class ContadorRecursos {

    private Timer timer;
    public int segundos;

    public ContadorRecursos() {
        segundos = 0;
    }

    public void iniciarContador() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                segundos++;
                if (segundos % 60 == 0) {
                    accion();
                }
            }
        }, 1000, 1000);
    }

    // Método para detener el contador
    public void detenerContador() {
        timer.cancel();
    }

    // Método para realizar la acción deseada cada segundo
    private void accion() {
        // Aquí puedes colocar la acción que deseas realizar cada segundo
        System.out.println("Se generan recursos");
        //SoundPlayer.playSound("money.wav", 0.2f);
        segundos = 0;
    }
}