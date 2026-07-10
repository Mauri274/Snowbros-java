package Estados.EstadoJugador;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesEntidades;
import Grafica.Constantes.ConstantesEstado;
import Personajes.BolaEnMovimiento;
import Personajes.Jugador;

public class DentroBola extends EstadoJugador{

    protected int direccionOriginal;
    protected BolaEnMovimiento bolaEnMovimiento;


    public DentroBola(Jugador jugador, BolaEnMovimiento bolaEnMovimiento){
        super(jugador);
        estadoActual = ConstantesEstado.DENTRO_BOLA;
        jugador.getSprites().setEstadoActual(estadoActual);
        this.bolaEnMovimiento = bolaEnMovimiento;
        direccionOriginal = bolaEnMovimiento.getDireccion();
    }

    public void actualizar() {
        if (bolaEnMovimiento.chocoElBorde())
            jugador.setEstadoActual(new Saltando(jugador));
        else
            actualizarPosicion();
    }

    public void saltar() {
        int posicionY = bolaEnMovimiento.getY() + ConstantesEntidades.BOLAENMOVIMIENTO_HITBOX_ALTO +
        (ConstantesEntidades.BOLAENMOVIMIENTO_SPRITE_ALTO - ConstantesEntidades.BOLAENMOVIMIENTO_HITBOX_ALTO) / 2;
        this.jugador.setPosicion(this.jugador.getX(), posicionY );
        this.jugador.setEstadoActual(new Saltando(this.jugador));
    }

    protected void actualizarPosicion() {
        jugador.setPosicion(bolaEnMovimiento.getX(), bolaEnMovimiento.getY());
    }

    public void mover() {}
    public void detenerMovimiento() {}
    public void disparar() {}
    public void empujarBola(Enemigo bola) {}
    public void detenerDisparo() {}

}
