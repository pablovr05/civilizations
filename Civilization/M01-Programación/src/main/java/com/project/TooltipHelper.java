package com.project;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class TooltipHelper {

    // Método estático para configurar un tooltip personalizado
    public static void setCustomTooltip(JComponent component, String tooltipText) {
        // Configurar el UIManager para personalizar los tooltips
        UIManager.put("ToolTip.background", new ColorUIResource(new Color(200,200,200)));
        UIManager.put("ToolTip.foreground", new ColorUIResource(Color.BLACK));
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 11));
        UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.BLACK, 1));
        
        // Establecer el tooltip en el componente
        component.setToolTipText(tooltipText);
    }
}
