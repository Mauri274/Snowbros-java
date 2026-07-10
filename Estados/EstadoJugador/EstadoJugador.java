package Estados.EstadoJugador;

import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;

public abstract class EstadoJugador{
    
    protected Jugador jugador;
    protected int estadoActual;

    public EstadoJugador(Jugador jugador){
        this.jugador = jugador;
    }

    protected void caerSiNoHaySuelo(){
        if (!jugador.estaSobrePlataforma() && !jugador.estaSobreElSuelo()) {
            jugador.setEstadoActual(new Cayendo(jugador));
        }
    }

    public abstract void mover();
    public abstract void detenerMovimiento();
    public abstract void detenerDisparo();
    public abstract void saltar();
    public abstract void disparar();
    public abstract void empujarBola(Enemigo bola);
    public abstract void actualizar();

}