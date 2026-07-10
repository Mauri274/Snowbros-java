package Launcher;

import Grafica.GUI;
import Hilos.HiloCargaSonidos;
import Juego.Juego;
import java.awt.EventQueue;

public class Launcher{
    public static void main(String[] args) {

        new HiloCargaSonidos().start();

        EventQueue.invokeLater(new Runnable() {
            public void run(){
                try {
                    Juego juego = new Juego();
                    GUI gui = new GUI(juego);
                    juego.setControladorGrafica(gui);
                    gui.mostrarPanelPantallaInicio();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}