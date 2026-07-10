package Estados.EstadoJugador;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;

public class Caminando extends EstadoJugador{
    
    public Caminando(Jugador jugador){
        super(jugador);
        estadoActual = ConstantesEstado.CAMINANDO;
        jugador.getSprites().setEstadoActual(estadoActual);
    }

    public void actualizar() {
        jugador.aplicarMovimientoHorizontal();
        caerSiNoHaySuelo();
    }

    public void detenerMovimiento() {
        jugador.setEstadoActual(new Quieto(jugador));
    }

    public void saltar() {
        jugador.setEstadoActual(new Saltando(jugador));
    }

    public void caer() {
        jugador.setEstadoActual(new Cayendo(jugador));
    }

    public void disparar() {
        jugador.setEstadoActual(new Disparando(jugador));
    }

    public void empujarBola(Enemigo bola) {
        jugador.setEstadoActual(new EmpujandoBola(jugador, bola));
    }

    public void mover() {}
    public void detenerDisparo() {}

}