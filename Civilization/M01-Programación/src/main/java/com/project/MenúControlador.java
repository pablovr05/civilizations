import java.awt.event.ActionEvent;

public class MenúControlador {

    private MenúFrame menúFrame;
    private MainWindow mainWindow;
    private CivilizaciónFrame civilizaciónFrame;
    private CivilizaciónControlador civilizaciónControlador;
    private EstadísticasFrame estadísticasFrame;
    private EstadísticasControlador estadísticasControlador;
    private EjércitoFrame ejércitoFrame;
    private EjércitoControlador ejércitoControlador;
    private EdificacionesFrame edificacionesFrame;
    private EdificacionesControlador edificacionesControlador;
    private TecnologíasFrame tecnologíasFrame;
    private TecnologíasControlador tecnologíasControlador;
    private HistorialFrame historialFrame;
    private HistorialControlador historialControlador;

    public MenúControlador(MenúFrame vBasic, MainWindow mainWindow) {
        this.menúFrame = vBasic;
        this.mainWindow = mainWindow;
    }

    public void start() {
        setupActionListeners();
    }

    private void setupActionListeners() {
        menúFrame.btnEstadísticas.addActionListener(this::controllerButtonAction);
        menúFrame.btnCrearEdificios.addActionListener(this::controllerButtonAction);
        menúFrame.btnCrearEjército.addActionListener(this::controllerButtonAction);
        menúFrame.btnTecnologías.addActionListener(this::controllerButtonAction);
        menúFrame.btnHistorial.addActionListener(this::controllerButtonAction);
        menúFrame.btnSalir.addActionListener(this::controllerButtonAction);
    }

    public void controllerButtonAction(ActionEvent e) {
        String command = e.getActionCommand();
        runCommand(command); 
    }

    public void runCommand(String command) {
        if (command.equals("Estadísticas")) {
            SoundPlayer.playSound("./music/clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Estadísticas");
            llamarEstadísticas();
        } if (command.equals("Crear edificios")) {
            SoundPlayer.playSound("./music/clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Crear edificios");
            llamarEdificaciones();
        } if (command.equals("Crear ejército")) {
            SoundPlayer.playSound("./music/clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Crear ejército");
            llamarEjército();
        } if (command.equals("Tecnologías")) {
            SoundPlayer.playSound("./music/clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Tecnologías");
            llamarTecnologías();
        } if (command.equals("Ver historial de batallas")) {
            SoundPlayer.playSound("./music/clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Ver historial de batallas");
            llamarHistorial();
        } if (command.equals("Salir")) {
            SoundPlayer.playSound("./music/clickselect.wav", 0.5f);
            System.out.println("Se pulsó el botón Salir");
            mainWindow.contadorAtacar.segundos = 0;
            mainWindow.contadorAtacar.detenerContador();
            mainWindow.contadorRecursos.segundos = 0;
            mainWindow.contadorRecursos.detenerContador();
            llamarCivilización();
        }
    } 

    private void llamarCivilización() {
        civilizaciónFrame = new CivilizaciónFrame();
        mainWindow.cambiarPanel(civilizaciónFrame);
        civilizaciónControlador = new CivilizaciónControlador(civilizaciónFrame, mainWindow);
        civilizaciónControlador.start();
    }

    private void llamarEstadísticas() {
        estadísticasFrame = new EstadísticasFrame();
        mainWindow.cambiarPanel(estadísticasFrame);
        estadísticasControlador = new EstadísticasControlador(estadísticasFrame, mainWindow);
        estadísticasControlador.start();
    }

    private void llamarEdificaciones() {
        edificacionesFrame = new EdificacionesFrame();
        mainWindow.cambiarPanel(edificacionesFrame);
        edificacionesControlador = new EdificacionesControlador(edificacionesFrame, mainWindow);
        edificacionesControlador.start();
    }

    private void llamarEjército() {
        ejércitoFrame = new EjércitoFrame();
        mainWindow.cambiarPanel(ejércitoFrame);
        ejércitoControlador = new EjércitoControlador(ejércitoFrame, mainWindow);
        ejércitoControlador.start();
    }

    private void llamarTecnologías() {
        tecnologíasFrame = new TecnologíasFrame();
        mainWindow.cambiarPanel(tecnologíasFrame);
        tecnologíasControlador = new TecnologíasControlador(tecnologíasFrame, mainWindow);
        tecnologíasControlador.start();
    }

    private void llamarHistorial() {
        historialFrame = new HistorialFrame();
        mainWindow.cambiarPanel(historialFrame);
        historialControlador = new HistorialControlador(historialFrame, mainWindow);
        historialControlador.start();
    }
}