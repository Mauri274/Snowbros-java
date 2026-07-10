package Estados.EstadoJugador;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;

public class Quieto extends EstadoJugador {

    public Quieto(Jugador jugador) {
        super(jugador);
        estadoActual = ConstantesEstado.QUIETO;
        jugador.getSprites().setEstadoActual(estadoActual);
    }

    public void actualizar() {
        caerSiNoHaySuelo();
    }

    public void mover() {
        jugador.setEstadoActual(new Caminando(jugador));
    }

    public void saltar() {
        jugador.setEstadoActual(new Saltando(jugador));
    }

    public void disparar() {
        jugador.setEstadoActual(new Disparando(jugador));
    }

    public void empujarBola(Enemigo bola) {
        jugador.setEstadoActual(new EmpujandoBola(jugador, bola));
    }

    public void detenerMovimiento() {}
    public void detenerDisparo() {}
}