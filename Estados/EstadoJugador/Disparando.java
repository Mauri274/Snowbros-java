package Estados.EstadoJugador;

import Grafica.Constantes.ConstantesEstado;
import Grafica.Constantes.ConstantesSonido;
import Juego.GestorSonido;
import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;

public class Disparando extends EstadoJugador {

    protected int retrasoDisparo;
    protected int contador;

    public Disparando (Jugador jugador) {
        super(jugador);
        estadoActual = ConstantesEstado.DISPARANDO;
        jugador.getSprites().setEstadoActual(estadoActual);
        retrasoDisparo = 6;
        contador = retrasoDisparo;
    }

    public void actualizar() {
        jugador.aplicarMovimientoHorizontal();
        aplicarDisparo();
        caerSiNoHaySuelo();
    }
    
    protected void aplicarDisparo() {
        if (contador < retrasoDisparo)
            contador++;
        else {
            contador = 0;
            int x = jugador.getX() + jugador.getAnchoSprite() / 3;
            int y = jugador.getY() + jugador.getAltoHitbox() / 2;
            int direccion = jugador.getDireccion();
            jugador.getControladorJugador().dispararNieve(x, y, direccion, jugador.getDanio());
            GestorSonido.getGestorSonido().reproducirSonido(ConstantesSonido.DISPARO);
        }
    }

    public void saltar() {
        jugador.setEstadoActual(new Saltando(jugador));
    }

    public void caer() {
        jugador.setEstadoActual(new Cayendo(jugador));
    }

    public void empujarBola(Enemigo bola) {
        jugador.setEstadoActual(new EmpujandoBola(jugador, bola));
    }

    public void detenerDisparo() {
        jugador.setEstadoActual(new Quieto(jugador));
    }

    public void mover() {}
    public void detenerMovimiento() {}
    public void disparar() {}
}
