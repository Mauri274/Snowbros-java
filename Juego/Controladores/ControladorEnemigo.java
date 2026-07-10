package Juego.Controladores;

import Grafica.ObserverGraficoNieve;
import Personajes.Enemigo.Enemigo;
import Personajes.Enemigo.EnemigoIndestructible;
import Proyectil.Proyectil;

public interface ControladorEnemigo {
    public void removeEnemigo(Enemigo enemigo);
    public void removeIndestructible(EnemigoIndestructible enemigo);
    public ObserverGraficoNieve registrarObserverNieve(Enemigo enemigo);
    public void addBolaEnMovimiento(int x, int y, int direccion);
    public void addPowerUp(int x, int y, int numeroPowerUp);
    public abstract void dispararBolaDeFuego(int x, int y, int direccion);
    public abstract void dispararBomba(int x, int y, int direccion);
    public abstract void dispararFuego(int x, int y, int direccion);
    public abstract void removeProyectil(Proyectil proyectil);
}
