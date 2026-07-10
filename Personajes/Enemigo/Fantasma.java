package Personajes.Enemigo;

import Estados.EstadoEnemigo.VolandoFantasma;
import Fabricas.Sprites;
import Juego.ConstantesPuntaje;
import Juego.EntidadLogica;
import Juego.Puntaje;
import Juego.Controladores.ControladorEnemigo;

public class Fantasma extends EnemigoIndestructible{

    protected EntidadLogica jugador;

    public Fantasma(int x, int y, Sprites sprites, Puntaje puntaje, ControladorEnemigo controladorEnemigo) {
        super(x, y, sprites, puntaje,  controladorEnemigo);
        this.estadoEnemigo = new VolandoFantasma(this);
        cantPuntajeCongelar = ConstantesPuntaje.ENEMIGO_VOLADOR_CONGELADO;
        cantPuntajeEmpujar = ConstantesPuntaje.ENEMIGO_VOLADOR_EMPUJADO;
        velocidad = 2;
    }

    public void setJugador(EntidadLogica jugador) {
        this.jugador = jugador;
    }

    public EntidadLogica getJugador(){
        return jugador;
    }

    public void actualizar(){
        estadoEnemigo.actualizar();
    }

    protected void tomarDecisionAleatoria() {}
    public void crearProyectil() {}
    public void serChocadoBolaEnMovimiento() {}
    public void chocarPared(int direccion, int xPared){}

}