package Juego.Controladores;

import Proyectil.Proyectil;

public interface ControladorJugador {
    public abstract void terminarJuego(boolean gano);
    public abstract void morirJugador();
    public abstract void dispararNieve(int x, int y, int direccion, int danio);
    public abstract void removeProyectil(Proyectil proyectil);
}
