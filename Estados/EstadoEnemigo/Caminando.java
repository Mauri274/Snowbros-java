package Estados.EstadoEnemigo;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;

public class Caminando extends EstadoEnemigo {

    public Caminando(Enemigo enemigo) {
        super(enemigo);
        estadoActual = ConstantesEstado.CAMINANDO;
        enemigo.getSprites().setEstadoActual(estadoActual);
    }

    public void actualizar() {
        if (!enemigo.estaQuieto()) {
            this.mover();
            rebotarParedHardcodeado();
            caerSiNoHaySuelo();
        }
    }

    public void actualizarDecision() {
        enemigo.actualizarLogicaDecision();
    }

    public void mover() {
        int direccion = enemigo.getDireccion();
        int velocidad = enemigo.getVelocidad();
        enemigo.setPosicion(enemigo.getX() + (velocidad * direccion), enemigo.getY());
    }
    
    public void aturdir() {
        enemigo.setEstadoActual(new Aturdido(enemigo, this));
    }

    public void detener() {
        enemigo.setEstadoActual(new Quieto(enemigo));
    }
    
    public void subirEscalera(int alturaVertical) {
        enemigo.setEstadoActual(new SubiendoEscalera(enemigo, alturaVertical));
    }

    public void afectar(Jugador jugador) {
        jugador.recibirDanio();
    }

    protected void rebotarParedHardcodeado() {
        if(enemigo.estaChocandoBordeIzquierdo())
            enemigo.setDireccion(ConstantesDireccion.DERECHA);
        else
            if(enemigo.estaChocandoBordeDerecho())
                enemigo.setDireccion(ConstantesDireccion.IZQUIERDA);
    }

    public void disparar() {
        enemigo.setEstadoActual(new Atacando(this.enemigo));
    }

    public void saltar() {
        enemigo.setEstadoActual(new Saltando(enemigo));
    }

    public void hacerBolaQuieta(){}
    public void afectar(Enemigo enemigo){}

}