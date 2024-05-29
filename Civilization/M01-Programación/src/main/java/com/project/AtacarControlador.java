package com.project;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class AtacarControlador {

    private AtacarFrame atacarFrame;
    private Timer timer;
    private int timeRemaining = 30; 

    private ResultadoBattallaFrame resultadoBattallaFrame;
    private ResultadoBatallaControlador resultadoBatallaControlador;
    private MainWindow mainWindow;

    private MenúFrame menúFrame;
    private MenúControlador menúControlador;

    public AtacarControlador(AtacarFrame atacarFrame, MainWindow mainWindow) {
        this.atacarFrame = atacarFrame;
        this.mainWindow = mainWindow;
        mainWindow.setSize(1150, 650);
    }

    public void start() { 
        setupActionListeners();
        startTimer();
    }

    private void setupActionListeners() {
        // Agregar ActionListener para el botón "Empezar"
        atacarFrame.empezarBotón.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica cuando se hace clic en el botón "Empezar"
                timer.stop();
                System.out.println("Botón Empezar clicado");
                llamarResultadoBatalla();
            }
        });

        // Agregar ActionListener para el botón "Escapar"
        atacarFrame.escaparBotón.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica cuando se hace clic en el botón "Escapar"
                timer.stop();
                System.out.println("Botón Escapar clicado");
                llamarMenú();
                mainWindow.setSize(585,415);
                mainWindow.contadorAtacar.iniciarContador();
                mainWindow.contadorRecursos.iniciarContador();
            }
        });
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });
        timer.start();
    }

    private void updateTimer() {
        if (timeRemaining > 0) {
            timeRemaining--;
            String timeText = String.format("%02d:%02d",
                    TimeUnit.SECONDS.toMinutes(timeRemaining),
                    timeRemaining % 60);
            atacarFrame.actualizarContador(timeText);
        } else {
            timer.stop();
            System.out.println("Se acabó el tiempo");
            llamarResultadoBatalla();
        }
    }

    private void llamarResultadoBatalla() {
        Battle battle = new Battle(CivilizaciónControlador.civilización.army, atacarFrame.enemyArmy);
        boolean winner = battle.startBattle(CivilizaciónControlador.civilización);
        BattleDAO.save(CivilizaciónControlador.civilization_id, battle);
        saveGame(CivilizaciónControlador.civilización);

        //Unidades iniciales, unidades que se mueren de cada ejercito, coste incial de los ejercitos, perdidas de cada ejercito, madera, iron, food generados.
        //Más tarde el desarrollo resultadoBattallaFrame, battle.initialArmies, battle.civililizationDrops, battle.enemyDrops, battle.initialCostFleet, battle.resourcesLooses, battle.wasteWoodIron, 

        resultadoBattallaFrame = new ResultadoBattallaFrame(winner, battle);
        mainWindow.cambiarPanel(resultadoBattallaFrame);
        resultadoBatallaControlador = new ResultadoBatallaControlador(resultadoBattallaFrame, mainWindow);
        resultadoBatallaControlador.start();
    }

    private void llamarMenú() {
        menúFrame = new MenúFrame();
        mainWindow.cambiarPanel(menúFrame);
        menúControlador = new MenúControlador(menúFrame, mainWindow);
        menúControlador.start();
    }

    private void saveGame(Civilization civilización) {
        civilización.printearBonito();
        CivilizationDAO.save(civilización);
    }
}
