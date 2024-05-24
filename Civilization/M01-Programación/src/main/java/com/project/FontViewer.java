import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FontViewer extends JFrame {

    public FontViewer() {
        setTitle("Font Viewer");
        setSize(800, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Lista de fuentes especificadas
        List<String> fonts = List.of(
            "Alef", "Amiri", "Amiri Quran", "Arial", "Arial Black", "Bahnschrift", "Caladea",
            "Calibri", "Calibri Light", "Cambria", "Cambria Math", "Candara", "Candara Light",
            "Carlito", "Comic Sans MS", "Consolas", "Constantia", "Corbel", "Corbel Light",
            "Courier New", "David CLM", "David Libre", "DejaVu Math TeX Gyre", "DejaVu Sans",
            "DejaVu Sans Condensed", "DejaVu Sans Light", "DejaVu Sans Mono", "DejaVu Serif",
            "DejaVu Serif Condensed", "Dialog", "DialogInput", "Ebrima", "Frank Ruehl CLM",
            "Frank Ruhl Hofshi", "Franklin Gothic Medium", "Gabriola", "Gadugi", "Gentium Basic",
            "Gentium Book Basic", "Georgia", "HoloLens MDL2 Assets", "HP Simplified",
            "HP Simplified Light", "Impact", "Ink Free", "Javanese Text", "Leelawadee UI",
            "Leelawadee UI Semilight", "Liberation Mono", "Liberation Sans",
            "Liberation Sans Narrow", "Liberation Serif", "Linux Biolinum G",
            "Linux Libertine Display G", "Linux Libertine G", "Lucida Console",
            "Lucida Sans Unicode", "Malgun Gothic", "Malgun Gothic Semilight", "Marlett",
            "Microsoft Himalaya", "Microsoft JhengHei", "Microsoft JhengHei Light",
            "Microsoft JhengHei UI", "Microsoft JhengHei UI Light", "Microsoft New Tai Lue",
            "Microsoft PhagsPa", "Microsoft Sans Serif", "Microsoft Tai Le", "Microsoft YaHei",
            "Microsoft YaHei Light", "Microsoft YaHei UI", "Microsoft YaHei UI Light",
            "Microsoft Yi Baiti", "MingLiU-ExtB", "MingLiU_HKSCS-ExtB", "Miriam CLM",
            "Miriam Libre", "Miriam Mono CLM", "Mongolian Baiti", "Monospaced", "MS Gothic",
            "MS PGothic", "MS UI Gothic", "MV Boli", "Myanmar Text", "Nachlieli CLM",
            "Nirmala UI", "Nirmala UI Semilight", "Noto Kufi Arabic", "Noto Naskh Arabic",
            "Noto Sans", "Noto Sans Arabic", "Noto Sans Armenian", "Noto Sans Georgian",
            "Noto Sans Hebrew", "Noto Sans Lao", "Noto Sans Lisu", "Noto Serif",
            "Noto Serif Armenian", "Noto Serif Georgian", "Noto Serif Hebrew", "Noto Serif Lao",
            "NSimSun", "OpenSymbol", "Palatino Linotype", "PMingLiU-ExtB", "Reem Kufi", "Rubik",
            "Sans Serif Collection", "SansSerif", "Scheherazade", "Segoe Fluent Icons",
            "Segoe MDL2 Assets", "Segoe Print", "Segoe Script", "Segoe UI", "Segoe UI Black",
            "Segoe UI Emoji", "Segoe UI Historic", "Segoe UI Light", "Segoe UI Semibold",
            "Segoe UI Semilight", "Segoe UI Symbol", "Segoe UI Variable", "Serif", "SimSun",
            "SimSun-ExtB", "Sitka Text", "Sylfaen", "Symbol", "Tahoma", "Times New Roman",
            "Trebuchet MS", "Verdana", "Webdings", "Wingdings", "Yu Gothic", "Yu Gothic Light",
            "Yu Gothic Medium", "Yu Gothic UI", "Yu Gothic UI Light", "Yu Gothic UI Semibold",
            "Yu Gothic UI Semilight"
        );

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        for (String fontName : fonts) {
            JLabel label = new JLabel(fontName);
            label.setFont(new Font(fontName, Font.PLAIN, 18));
            panel.add(label);
        }
        
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FontViewer frame = new FontViewer();
            frame.setVisible(true);
        });
    }
}
