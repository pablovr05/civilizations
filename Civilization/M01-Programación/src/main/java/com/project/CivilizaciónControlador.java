import java.awt.event.ActionEvent;

public class CivilizaciónControlador {

    private CivilizaciónFrame civilizaciónFrame;
    private MainWindow mainWindow;
    private MenúControlador menúControlador;
    private MenúFrame menúFrame;

    public CivilizaciónControlador(CivilizaciónFrame vBasic, MainWindow mainWindow) {
        this.civilizaciónFrame = vBasic;
        this.mainWindow = mainWindow;
    }

    public void start() {
        setupActionListeners();
    }

    private void setupActionListeners() {
        civilizaciónFrame.btnCargarPartida.addActionListener(this::controllerButtonAction);
        civilizaciónFrame.btnNuevaPartida.addActionListener(this::controllerButtonAction);
    }

    public void controllerButtonAction(ActionEvent e) {
        String command = e.getActionCommand();
        runCommand(command); 
    }

    public void runCommand(String command) {
        if (command.equals("Cargar Partida")) {
            SoundPlayer.playSound("./music/clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Cargar Partida");
            // Mostrar el diálogo de la lista de opciones utilizando la clase CustomOptionListPane
            String selectedOption = CustomOptionListPane.showOptionListDialog(mainWindow, "Selecciona una opción:", "Cargar Partida", "images/logo.png");
            if (selectedOption != null) {
                // Aquí puedes hacer algo con la opción seleccionada
                SoundPlayer.playSound("./music/botónASSERT.wav", 0.5f);
                System.out.println("Opción seleccionada: " + selectedOption);
                System.out.println("Se inició el contadorRecursos");
                mainWindow.contadorRecursos.iniciarContador();
                System.out.println("Se inició el contadorAtacar");
                mainWindow.contadorAtacar.iniciarContador();
                llamarMenú();
            } else {
                ErrorDialog.showErrorDialog(mainWindow, "¡No has seleccionado ninguna civilización!");
            }
        } else if (command.equals("Nueva Partida")) {
            SoundPlayer.playSound("./music/clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Nueva Partida");
            // Mostrar el diálogo de entrada del nombre de la civilización utilizando la clase CustomOptionPane
            String nombreCivilizacion = CustomOptionPane.showCivilizationNameInputDialog(mainWindow, "Introduce el nombre de tu nueva civilización:", "Nombre de la Civilización", "images/logo.png");
            if (nombreCivilizacion != null && !nombreCivilizacion.isEmpty()) {
                // Aquí puedes hacer algo con el nombre de la civilización, como mostrarlo en la interfaz
                SoundPlayer.playSound("./music/botónASSERT.wav", 0.5f);
                System.out.println("Nombre de la nueva civilización: " + nombreCivilizacion);
                System.out.println("Se inició el contadorRecursos");
                mainWindow.contadorRecursos.iniciarContador();
                System.out.println("Se inició el contadorAtacar");
                mainWindow.contadorAtacar.iniciarContador();
                llamarMenú();
            } else {
                System.out.println("No se ingresó ningún nombre de civilización.");
                ErrorDialog.showErrorDialog(mainWindow, "¡No has añadido ningún nombre!");
            }
        }
    }

    private void llamarMenú() {
        menúFrame = new MenúFrame();
        mainWindow.cambiarPanel(menúFrame);
        menúControlador = new MenúControlador(menúFrame, mainWindow);
        menúControlador.start();
    }
}
