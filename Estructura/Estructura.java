package Estructura;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Juego.EntidadColisionable;
import Personajes.BolaEnMovimiento;
import Visitor.Colisionable;

public abstract class Estructura extends EntidadColisionable implements Colisionable{

    public Estructura(int x, int y, Sprites sprites) {
        super(x, y, sprites);
    }

    public int getDireccion() {
        return ConstantesDireccion.IZQUIERDA;
    }

    public void afectar(BolaEnMovimiento bolaEnMovimiento) {}

}
