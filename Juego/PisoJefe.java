package Juego;

public class PisoJefe extends Piso {

    public PisoJefe(String nombre) {
        super(nombre);
    }

    public void actualizar() {
        manejadorBolasEnMovimiento.actualizar();
        manejadorEnemigos.actualizar();
        manejadorObstaculos.actualizar();
        manejadorPlataformas.actualizar();
        manejadorPowerUps.actualizar();
        manejadorProyectiles.actualizar();
        actualizarGrillaColisiones();
        actualizarJugador();
    }

}