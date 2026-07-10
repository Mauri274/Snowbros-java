package Estados.EstadoEnemigo;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEntidades;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;

public class Quieto extends EstadoEnemigo{

    public Quieto(Enemigo enemigo) {
        super(enemigo);
        estadoActual = ConstantesEstado.QUIETO;
        actualizarDireccion();
        enemigo.getSprites().setEstadoActual(estadoActual);
    }

    protected void actualizarDireccion() {
        if (enemigo.getDireccion() == ConstantesDireccion.DERECHA) 
            enemigo.setDireccion(ConstantesDireccion.QUIETO_DERECHA);
        else 
            if (enemigo.getDireccion() == ConstantesDireccion.IZQUIERDA)
                enemigo.setDireccion(ConstantesDireccion.QUIETO_IZQUIERDA);
    }

    public void actualizar() {
        ponerEnPlataforma();
        caerSiNoHaySuelo();
        if (enemigo.getDireccion() == ConstantesDireccion.DERECHA || enemigo.getDireccion() == ConstantesDireccion.IZQUIERDA)
            mover();
    }

    protected void ponerEnPlataforma() {
        if(enemigo.estaSobrePlataforma()){
            int posicionY = enemigo.getPlataformaColisionando().getY() + ConstantesEntidades.PLATAFORMA_SPRITE_ALTO - 10;
            enemigo.setPosicion(enemigo.getX(), posicionY);
        }
    }

    public void actualizarDecision(){
        enemigo.actualizarLogicaDecision();
    }

    public void mover() {
        if (enemigo.getDireccion() == ConstantesDireccion.QUIETO_DERECHA) {
            enemigo.setDireccion(ConstantesDireccion.DERECHA);
        }
        else
            if (enemigo.getDireccion() == ConstantesDireccion.QUIETO_IZQUIERDA) {
                enemigo.setDireccion(ConstantesDireccion.IZQUIERDA);
            }
        enemigo.setEstadoActual(new Caminando(enemigo));
    }

    public void aturdir() {
        enemigo.setEstadoActual(new Aturdido(this.enemigo,this));
    }

    public void subirEscalera(int alturaVertical) {
        enemigo.setEstadoActual(new SubiendoEscalera(enemigo, alturaVertical));
    }

    public void afectar(Jugador jugador) {
        jugador.recibirDanio();
    }

    public void disparar() {
        this.enemigo.setEstadoActual(new Atacando(this.enemigo));
    }

    public void saltar() {
        enemigo.setEstadoActual(new Saltando(this.enemigo));
    }

    public void hacerBolaQuieta() {}
    public void detener() {}
    public void afectar(Enemigo Enemigo) {}

}