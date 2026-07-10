package Hilos;

import Juego.Juego;

public class HiloPrincipal extends Thread {

    protected Juego juego;
    protected volatile boolean enEjecucion;
    protected int millis = 20;


    public HiloPrincipal(Juego juego) {
        this.juego = juego;
        this.enEjecucion = true;
    }

    public void run() {
        long tiempoObjetivoPorFrame = millis * 1_000_000L;

        while (enEjecucion) {
            long tiempoInicioFrame = System.nanoTime();

            try {
                juego.actualizarJuego();
                long tiempoDeActualizacion = System.nanoTime() - tiempoInicioFrame;
                long tiempoDeEspera = (tiempoObjetivoPorFrame - tiempoDeActualizacion) / 1_000_000L;
                if (tiempoDeEspera > 0) {
                    sleep(tiempoDeEspera);
                }
            } catch (InterruptedException e) {
                enEjecucion = false;
            }
        }
    }

    public void detener() {
        enEjecucion = false;
        this.interrupt();
    }

}