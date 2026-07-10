package Estados.EstadoJugador;

import Grafica.Constantes.ConstantesEstado;
import Grafica.Constantes.ConstantesSonido;
import Grafica.Constantes.ConstantesVistas;
import Juego.GestorSonido;
import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;

public class Reapareciendo extends EstadoJugador{

    protected int tiempoSprite_Reaparecer;
    protected final int TIEMPO_MAXIMO_SPRITE_REA = 42;


    public Reapareciendo(Jugador jugador) {
        super(jugador);
        estadoActual = ConstantesEstado.MUERE_CON_VIDA;
        jugador.getSprites().setEstadoActual(estadoActual);
        tiempoSprite_Reaparecer = TIEMPO_MAXIMO_SPRITE_REA;
        GestorSonido.getGestorSonido().reproducirSonido(ConstantesSonido.MUERTE);
    }

    public void actualizar() {
        tiempoSprite_Reaparecer--;
        if(tiempoSprite_Reaparecer == 0){
            jugador.setEstadoActual(new Quieto(jugador));
            jugador.setVelocidad(jugador.getVelocidadBase());
            jugador.setVelocidadPrevia(jugador.getVelocidadBase());
            jugador.setDanio(jugador.getDanioBase());
            jugador.setPosicion(ConstantesVistas.X_INICIO_JUGADOR, ConstantesVistas.Y_INICIO_JUGADOR);
        }
    }

    public void mover() {}
    public void detenerMovimiento() {}
    public void detenerDisparo() {}
    public void saltar() {}
    public void disparar() {}
    public void empujarBola(Enemigo bola) {}
}
