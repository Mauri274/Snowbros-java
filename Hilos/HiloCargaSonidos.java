package Hilos;

import Juego.GestorSonido;

public class HiloCargaSonidos extends Thread {

    public HiloCargaSonidos() {
        super("HiloCargaSonidos");
    }

    public void run() {
        try {
            GestorSonido.cargarSonidosDelJuego();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}