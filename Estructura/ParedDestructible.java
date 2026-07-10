package Estructura;

import Fabricas.Sprites;
import Juego.ConstantesPuntaje;
import Juego.Puntaje;
import Juego.Controladores.ControladorObstaculo;
import Personajes.BolaEnMovimiento;

public class ParedDestructible extends Pared{

    protected Puntaje puntaje;
    protected final int CANT_PUNTAJE = ConstantesPuntaje.OSTACULO_DESTRUCTIBLE;


    public ParedDestructible(int x, int y, Sprites sprites, ControladorObstaculo controladorObstaculos, Puntaje puntaje) {
        super(x, y, sprites, controladorObstaculos);
        this.puntaje = puntaje;
    }

    public void afectar(BolaEnMovimiento bolaEnMovimiento) {
        puntaje.addPuntos(CANT_PUNTAJE);
        desaparecer();
    }

}