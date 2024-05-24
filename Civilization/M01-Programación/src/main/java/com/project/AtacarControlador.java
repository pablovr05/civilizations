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

    public AtacarControlador(AtacarFrame atacarFrame, MainWindow mainWindow) {
        this.atacarFrame = atacarFrame;
        this.mainWindow = mainWindow;
        mainWindow.setSize(1150, 650);
    }

    public void start() {
        startTimer();
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
        resultadoBattallaFrame = new ResultadoBattallaFrame(true);
        mainWindow.cambiarPanel(resultadoBattallaFrame);
        resultadoBatallaControlador = new ResultadoBatallaControlador(resultadoBattallaFrame, mainWindow);
        resultadoBatallaControlador.start();
    }
}
