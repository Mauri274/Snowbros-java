package Estados.EstadoJugador;

import Grafica.Constantes.ConstantesEstado;
import Grafica.Constantes.ConstantesSonido;
import Juego.GestorSonido;
import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;

public class Muriendo extends EstadoJugador{

    protected int tiempoSprite_mDefinitivo;
    protected final int TIEMPO_MAXIMO_SPRITE_MDEF = 30;

    
    public Muriendo(Jugador jugador) {
        super(jugador);
        estadoActual = ConstantesEstado.MUERE_SIN_VIDA;
        jugador.getSprites().setEstadoActual(estadoActual);
        tiempoSprite_mDefinitivo = TIEMPO_MAXIMO_SPRITE_MDEF;
        GestorSonido.getGestorSonido().reproducirSonido(ConstantesSonido.MUERTE);
    }

    public void actualizar() {
        tiempoSprite_mDefinitivo--;
            if(tiempoSprite_mDefinitivo == 0){
                jugador.getControladorJugador().terminarJuego(false);
            }
    }

    public void mover() {}
    public void detenerMovimiento() {}
    public void detenerDisparo() {}
    public void saltar() {}
    public void disparar() {}
    public void empujarBola(Enemigo bola) {}
}
