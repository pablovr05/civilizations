import java.awt.event.ActionEvent;

public class EstadísticasControlador {

    private MainWindow mainWindow;
    private EstadísticasFrame estadísticasFrame;
    private MenúFrame menúFrame;
    private MenúControlador menúControlador;

    public EstadísticasControlador(EstadísticasFrame vBasic, MainWindow mainWindow) {
        this.estadísticasFrame = vBasic;
        this.mainWindow = mainWindow;
    }

    public void start() {
        setupActionListeners();
    }

    private void setupActionListeners() {
        estadísticasFrame.backButtonEstadísticas.addActionListener(this::controllerButtonAction);
    }

    public void controllerButtonAction(ActionEvent e) {
        String command = e.getActionCommand();
        runCommand(command); 
    }

    public void runCommand(String command) {
        if (command.equals("Volver")) {
            SoundPlayer.playSound("./music/clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Salir");
            llamarMenú();
        }
    } 

    private void llamarMenú() {
        menúFrame = new MenúFrame();
        mainWindow.cambiarPanel(menúFrame);
        menúControlador = new MenúControlador(menúFrame, mainWindow);
        menúControlador.start();
    }
}
