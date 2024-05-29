package com.project;

import java.util.Timer;
import java.util.TimerTask;

public class ContadorAtacar {

    private Timer timer;
    public int segundos;
    public MainWindow mainWindow;

    private AtacarFrame atacarFrame;
    private AtacarControlador atacarControlador;

    public ContadorAtacar(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        segundos = 0;
    }

    // Método para iniciar el contador cada 30 segundos
    public void iniciarContador() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Incrementar los segundos
                segundos++;
                // Si han pasado 30 segundos, realizar la acción y reiniciar el contador de segundos
                if (segundos % 150 == 0) {
                    aviso();
                }
                if (segundos % 5 == 0) {
                    if(CivilizaciónControlador.civilización.getArmyQuantity() == 0){
                        segundos = 120;
                    } else{
                        accion();
                        segundos = 0;
                    }
                }
            }
        }, 1000, 1000); // Iniciar el contador después de 1 segundo y repetir cada segundo
    }

    // Método para detener el contador
    public void detenerContador() {
        timer.cancel();
    }

    // Método para realizar la acción deseada cada 30 segundos
    private void accion() {
        mainWindow.contadorAtacar.detenerContador();
        mainWindow.contadorRecursos.detenerContador();
        FightButtonPanel dialog = new FightButtonPanel(mainWindow);
        dialog.setVisible(true);
        llamarAtacar();
    }

    private void aviso() {
        System.out.println("aviso");
        FadeOutGifDialog dialog = new FadeOutGifDialog(mainWindow, "src\\main\\java\\com\\project\\images\\avecina.gif");
        dialog.setVisible(true);
    }

    private void llamarAtacar() {
        atacarFrame = new AtacarFrame();
        mainWindow.cambiarPanel(atacarFrame);
        atacarControlador = new AtacarControlador(atacarFrame, mainWindow);
        atacarControlador.start();
    }
}
