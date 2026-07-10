package Estados.EstadoJugador;


import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesEntidades;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;

public class Cayendo extends EstadoJugador{

    protected final int VELOCIDAD_MAXIMA_DE_CAIDA = 10;
    protected int velocidadActualDeCaida;


    public Cayendo(Jugador jugador){
        super(jugador);
        estadoActual = ConstantesEstado.CAYENDO;
        jugador.getSprites().setEstadoActual(estadoActual);
    }

    public void actualizar() {
        jugador.aplicarMovimientoHorizontal();
        aplicarCaida();
        aterrizarSiHaySuelo();
    }

    protected void aplicarCaida() {
        int posicionY = jugador.getY() - velocidadActualDeCaida;
        jugador.setPosicion(jugador.getX(), posicionY);
        if (velocidadActualDeCaida < VELOCIDAD_MAXIMA_DE_CAIDA) 
            velocidadActualDeCaida++;
    }

    protected void aterrizarSiHaySuelo() {
        if (jugador.estaSobrePlataforma()) {
            int posicionY = jugador.getPlataformaColisionando().getY() + ConstantesEntidades.PLATAFORMA_SPRITE_ALTO - 10;
            jugador.setPosicion(jugador.getX(), posicionY);
            aterrizar();
        } 
        else
            if (jugador.estaSobreElSuelo()) {
                jugador.setPosicion(jugador.getX(),0);
                aterrizar();
            }
    }

    public void aterrizar() {
        if (jugador.estaQuieto())
            jugador.setEstadoActual(new Quieto(jugador));
        else
            jugador.setEstadoActual(new Caminando(jugador));
    }

    public void disparar() {}
    public void mover() {}
    public void detenerMovimiento() {}
    public void saltar() {}
    public void caer() {}
    public void empujarBola(Enemigo bola) {}
    public void detenerDisparo() {}
}