package Estados.EstadoEnemigo;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEstado;
import Grafica.Constantes.ConstantesVistas;
import Personajes.Jugador;

public class BolaQuieta extends EstadoEnemigo {
    
    protected int tiempoHastaDescongelar;
    protected final int TIEMPO_MAXIMO_DESCONGELAR = 150;
    protected int direccionOriginal;
    protected int velocidadArrastre = 3;
    protected boolean moviendo;


    public BolaQuieta(Enemigo enemigo) {
        super(enemigo);
        moviendo = false;
        direccionOriginal = enemigo.getDireccion();
        estadoActual = ConstantesEstado.BOLA_QUIETA;
        enemigo.getSprites().setEstadoActual(estadoActual);
        tiempoHastaDescongelar = TIEMPO_MAXIMO_DESCONGELAR;
        enemigo.getPuntaje().addPuntos(enemigo.getPuntajeCongelar());
    }
    
    public void actualizar() {
        if(!moviendo){
            tiempoHastaDescongelar--;
            if(tiempoHastaDescongelar == 0){
                descongelar();
                tiempoHastaDescongelar = TIEMPO_MAXIMO_DESCONGELAR;
            }
        }
        if (!enemigo.estaSobreElSuelo() && !enemigo.estaSobrePlataforma())
            aplicarCaida();
    }

    protected void descongelar() {
        enemigo.aumentarVida();
        enemigo.setDireccion(direccionOriginal);
        enemigo.setEstadoActual(new Congelado(enemigo));
    }

    public void mover() {
        moviendo = true;
        if(enemigo.puedeMoverse()) {
            enemigo.setPosicion(enemigo.getX() + (velocidadArrastre * enemigo.getDireccion()), enemigo.getY());
        }
    }
    protected boolean estaChocandoBordeIzquierdo() {
        return enemigo.getX() <= -10;
    }

    protected boolean estaChocandoBordeDerecho() {
        return  enemigo.getX() >= ConstantesVistas.PANEL_PISO_ANCHO - enemigo.getAnchoSprite() + 10;
    }

    public void empujar(int direccion) {

        if(!moviendo && enemigo.puedeMoverse()){
            moviendo = true;
            enemigo.setDireccion(direccion);
            enemigo.setPosicion(enemigo.getX() + (velocidadArrastre * enemigo.getDireccion()), enemigo.getY());
            detener();
        }
        else
            moviendo = false;
    }

    public void congelar() {
        tiempoHastaDescongelar = TIEMPO_MAXIMO_DESCONGELAR;
    }

    public void detener() {
        moviendo = false;
        enemigo.getSprites().setEstadoActual(ConstantesEstado.BOLA_QUIETA);
    }

    public void afectar(Jugador jugador) {
        if(jugador.getDireccion() == ConstantesDireccion.DERECHA || jugador.getDireccion() == ConstantesDireccion.IZQUIERDA){
            jugador.empujarBola(this.enemigo);
            enemigo.getSprites().setEstadoActual(ConstantesEstado.BOLAENMOVIMIENTO_AVANZA);
            this.enemigo.setDireccion(jugador.getDireccion());
            velocidadArrastre = jugador.getVelocidad()/2;
            moviendo = true;
        }
        else {
            detener();
        }
    }

    public void afectar(Enemigo enemigo) {
        if(enemigo != this.enemigo && moviendo){
            enemigo.empujar(this.enemigo.getDireccion());
        }
    }
    
    public void aturdir(){}
    public void hacerBolaQuieta(){}
    public void subirEscalera(int alturaVertical) {}
    public void disparar(){}
    public void saltar(){}

}