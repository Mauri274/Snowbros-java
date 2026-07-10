package Estados.EstadoEnemigo;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEstado;
import Grafica.Constantes.ConstantesVistas;
import Personajes.Jugador;

public class VolandoCalabaza extends EstadoEnemigo{

    protected boolean estaMoviendose;
    protected int timerQuieto;
    protected int timerMovimiento;
    protected final int TIEMPO_QUIETO = 60 * 1;
    protected final int TIEMPO_MOVIMIENTO_BASE = 60 * 1;
    protected final int TIEMPO_MOVIMIENTO_RANGO = 60 * 2;
    protected int distanciaX, distanciaY;


    public VolandoCalabaza(Enemigo enemigo) {
        super(enemigo);
        estadoActual = ConstantesEstado.CAMINANDO;
        enemigo.getSprites().setEstadoActual(estadoActual);
        this.estaMoviendose = false;
        this.timerQuieto = TIEMPO_QUIETO;
        this.distanciaX = 0;
        this.distanciaY = 0;
    }

    public void mover() {
        estaMoviendose = true;
        reiniciarTimerMovimiento();
        cambiarDireccionAleatoria();
    }

    public void disparar(){
        enemigo.disparar();
    }

    public void actualizar() {
        if (!estaMoviendose) {
            timerQuieto--;
            if (timerQuieto <= 0) {
                estaMoviendose = true;
                reiniciarTimerMovimiento();
                cambiarDireccionAleatoria();
            }
        }
        else {
            timerMovimiento--;
            if (timerMovimiento <= 0) {
                estaMoviendose = false;
                timerQuieto = TIEMPO_QUIETO;
                distanciaX = 0;
                distanciaY = 0;
            }
            int newX = enemigo.getX() + distanciaX;
            int newY = enemigo.getY() + distanciaY;
            if (newX <= 0) {
                newX = 0;
                cambiarDireccionAleatoria();
                reiniciarTimerMovimiento();
            }
            else
                if (newX >= ConstantesVistas.PANEL_PISO_ANCHO - enemigo.getAnchoSprite()) {
                    newX = ConstantesVistas.PANEL_PISO_ANCHO - enemigo.getAnchoSprite();
                    cambiarDireccionAleatoria();
                    reiniciarTimerMovimiento();
                }
            if (newY <= 0) {
                newY = 0;
                cambiarDireccionAleatoria();
                reiniciarTimerMovimiento();
            }
            else
                if (newY >= ConstantesVistas.PANEL_PISO_ALTO - enemigo.getAltoSprite()) {
                    newY = ConstantesVistas.PANEL_PISO_ALTO - enemigo.getAltoSprite();
                    cambiarDireccionAleatoria();
                    reiniciarTimerMovimiento();
                }
            enemigo.setPosicion(newX, newY);
        }
    }

    protected void cambiarDireccionAleatoria() {
        int velocidad = enemigo.getVelocidad();
        this.distanciaX = (enemigo.generadorRandom.nextInt(3) - 1) * velocidad;
        this.distanciaY = (enemigo.generadorRandom.nextInt(3) - 1) * velocidad;
        if (distanciaX > 0)
            enemigo.setDireccion(ConstantesDireccion.DERECHA);
        else if (distanciaX < 0)
            enemigo.setDireccion(ConstantesDireccion.IZQUIERDA);
    }

    protected void reiniciarTimerMovimiento() {
        this.timerMovimiento = TIEMPO_MOVIMIENTO_BASE + enemigo.generadorRandom.nextInt(TIEMPO_MOVIMIENTO_RANGO);
    }

    public void actualizarDecision() {
        enemigo.actualizarLogicaDecision();
    }

    public void aturdir() {
        enemigo.setEstadoActual(new Aturdido(this.enemigo, this));
    }

    public void detener() {
        estaMoviendose = false;
        timerQuieto = TIEMPO_QUIETO;
        this.distanciaX = 0;
        this.distanciaY = 0;
    }

    public void afectar(Jugador jugador) {
        jugador.recibirDanio();
    }

    public void saltar() {}
    public void hacerBolaQuieta() {}
    public void subirEscalera(int alturaVertical) {}
    public void afectar(Enemigo enemigo) {}

}