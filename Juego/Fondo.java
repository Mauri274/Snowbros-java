package Juego;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesVistas;

public class Fondo extends Entidad {
	
	public Fondo(Sprites sprites, int numeroPiso) {
		super( 0, 0, sprites);
		sprites.setEstadoActual(numeroPiso);
	}

	public int getAnchoSprite() {
        return ConstantesVistas.PANEL_PISO_ANCHO;
    }

    public int getAltoSprite() {
        return ConstantesVistas.PANEL_PISO_ALTO;
    }

	public int getDireccion() {
		return ConstantesDireccion.QUIETO_IZQUIERDA;
	}

}