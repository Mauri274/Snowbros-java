package Estados.EstadoJugador;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;

public class EmpujandoBola extends EstadoJugador {

    protected int direccionOriginal;
    protected Enemigo bolaQuieta;


    public EmpujandoBola (Jugador jugador, Enemigo bola) {
        super(jugador);
        estadoActual = ConstantesEstado.EMPUJANDO_BOLA;
        jugador.getSprites().setEstadoActual(estadoActual);
        direccionOriginal = jugador.getDireccion();
        bolaQuieta = bola;
    }

    public void actualizar() {
        caerSiNoHaySuelo();
    }
    
    public void mover() {
        if(jugador.getDireccion() != direccionOriginal){
            jugador.setEstadoActual(new Caminando(jugador));
            bolaQuieta.detener();
        }
        else{
            bolaQuieta.mover();
            aplicarMovimientoHorizontal();
        }
    }

    protected void aplicarMovimientoHorizontal(){
        if (jugador.puedeMoverse()) {
            int direccion = jugador.getDireccion();
            int velocidad = 3;
            jugador.setPosicion(jugador.getX() + ((velocidad) * direccion), jugador.getY());
        }
    }

    public void detenerMovimiento() {
        jugador.setEstadoActual(new Quieto(jugador));
        bolaQuieta.detener();
    }

    public void disparar() {
        jugador.setEstadoActual(new Quieto(jugador));
        bolaQuieta.getPuntaje().addPuntos(bolaQuieta.getPuntajeEmpujar());
        bolaQuieta.hacerBolaEnMovimiento();
    }

    public void saltar() {}
    public void empujarBola(Enemigo bola) {}
    public void detenerDisparo() {}
}