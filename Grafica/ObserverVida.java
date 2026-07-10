package Grafica;
import java.awt.Container;

import javax.swing.JLabel;

import Juego.Controladores.ControladorVida;


public class ObserverVida extends JLabel implements Observer {

    protected ControladorVida controladorVida;


    public ObserverVida(ControladorVida controladorVida) {
        super(String.valueOf(controladorVida.getVidas()));
        this.controladorVida = controladorVida;
        setOpaque(false);
    }

    public void actualizar() {
        int vidas = controladorVida.getVidas();
        this.setText(String.valueOf(vidas));       
    }

    public void desaparecer() {      
        Container contenedor = getParent();
        if (contenedor != null) {
            contenedor.remove(this);
            contenedor.repaint(); 
        }
    }
}