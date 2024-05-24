package com.project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyListenerExample implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Tecla presionada: " + KeyEvent.getKeyText(keyCode));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No necesitamos este método en este ejemplo, pero debe ser implementado
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No necesitamos este método en este ejemplo, pero debe ser implementado
    }

    public static void main(String[] args) {
        keyListenerExample listener = new keyListenerExample();
        // Agregar el listener al componente que quieres escuchar, por ejemplo un JFrame
        // frame.addKeyListener(listener);
    }
}