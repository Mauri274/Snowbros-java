package Grafica;

import java.awt.Container;

import javax.swing.JLabel;

import Juego.Puntaje;

public class ObserverPuntaje extends JLabel implements Observer{

    protected Puntaje puntaje;

    
    public ObserverPuntaje(Puntaje puntaje) {
        super("PUNTOS: " + String.valueOf(puntaje.getPuntos()));
        this.puntaje = puntaje;
        setOpaque(false);
    }

    public void actualizar() {
        int puntos = puntaje.getPuntos();
        this.setText("PUNTOS: " + String.valueOf(puntos));
    }

    public void desaparecer() {
        Container contenedor = getParent();
        if (contenedor != null) {
            contenedor.remove(this);
            contenedor.repaint();
        }
    }

}
