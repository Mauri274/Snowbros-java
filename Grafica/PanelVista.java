package Grafica;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import Fuentes.GestorFuentes;

public abstract class PanelVista extends JPanel {
    
    protected ControladorVistas controladorVistas;

    protected PanelVista (ControladorVistas controladorVistas){
        this.controladorVistas = controladorVistas;
    }

    protected JButton crearBoton(String texto) {
        JButton b = new JButton(texto);
        b.setFont(GestorFuentes.getFuentePixel(18f));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        transparentarBoton(b);
        return b;
    }

    protected void transparentarBoton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }
}