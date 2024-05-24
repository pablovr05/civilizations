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
    }

    public void controllerButtonAction(ActionEvent e) {
        String command = e.getActionCommand();
        runCommand(command); 
    }

    public void runCommand(String command) {
        if (command.equals("Cancelar")) {
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
