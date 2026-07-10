package Estados.EstadoJugador;

import Estructura.Plataforma;
import Grafica.Constantes.ConstantesEstado;
import Grafica.Constantes.ConstantesSonido;
import Juego.GestorSonido;
import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;

public class Saltando extends EstadoJugador{

    protected final int VELOCIDAD_INICIAL_DE_SALTO = 15;
    protected int velocidadActualDeSalto;


    public Saltando(Jugador jugador) {
        super(jugador);
        estadoActual = ConstantesEstado.SALTANDO;
        jugador.getSprites().setEstadoActual(estadoActual);
        velocidadActualDeSalto = VELOCIDAD_INICIAL_DE_SALTO;
        GestorSonido.getGestorSonido().reproducirSonido(ConstantesSonido.SALTO);
    }

    public void actualizar() {
        jugador.aplicarMovimientoHorizontal();
        aplicarSalto();
    }

    protected void aplicarSalto() {
        if (velocidadActualDeSalto >= 0) {
            jugador.setPosicion(jugador.getX(), jugador.getY() + velocidadActualDeSalto);
            velocidadActualDeSalto--;
        }
        else
            jugador.setEstadoActual(new Cayendo(jugador));
    }

    public void caer() {
        jugador.setEstadoActual(new Cayendo(jugador));
    }

    public void mover() {}
    public void detenerMovimiento() {}
    public void saltar() {}
    public void disparar() {}
    public void empujarBola(Enemigo bola) {}
    public void detenerDisparo() {}
    public void recibirEfectoPlataforma(Plataforma plataforma) {}

}