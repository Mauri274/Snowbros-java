package Estados.EstadoEnemigo;

import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;
import Personajes.Enemigo.Enemigo;
import Personajes.Enemigo.Fantasma;

public class VolandoFantasma extends EstadoEnemigo{

    protected Fantasma fantasma;
    protected final int RETRASO_MOVIMIENTO = 60;
    protected int velocidadActual;
    protected int tickActual;

    public VolandoFantasma(Enemigo enemigo) {
        super(enemigo);
        fantasma = (Fantasma)this.enemigo;
        estadoActual = ConstantesEstado.CAMINANDO;
        enemigo.getSprites().setEstadoActual(estadoActual);
        velocidadActual = enemigo.getVelocidad();
        tickActual = RETRASO_MOVIMIENTO;
    }

    public void actualizar() {
        tickActual--;
        if (tickActual < 40) {
            seguirAJugador();
            velocidadActual--;
            if (tickActual <= 0) {
                velocidadActual = enemigo.getVelocidad();
                tickActual = RETRASO_MOVIMIENTO;
            }
        }
    }

    protected void seguirAJugador() {
        int jugadorX = fantasma.getJugador().getX();
        int jugadorY = fantasma.getJugador().getY();
        
        int newX = this.enemigo.getX();
        int newY = this.enemigo.getY();

        if (jugadorX > this.enemigo.getX()) {
            newX += this.enemigo.getVelocidad();
            this.enemigo.setDireccion(ConstantesDireccion.DERECHA);
        }
        else
            if (jugadorX < this.enemigo.getX()) {
                newX -= this.enemigo.getVelocidad();
                this.enemigo.setDireccion(ConstantesDireccion.IZQUIERDA);
            }
        if (jugadorY > this.enemigo.getY())
            newY += this.enemigo.getVelocidad();
        else
            if (jugadorY < this.enemigo.getY())
                newY -= this.enemigo.getVelocidad();
        this.enemigo.setPosicion(newX, newY);
    }

    public void aturdir() {
        this.enemigo.setEstadoActual(new Aturdido(this.enemigo,this));
    }

    public void afectar(Jugador jugador) {
        jugador.recibirDanio();
    }

    public void mover(){}
    public void disparar(){}
    public void saltar(){}
    public void hacerBolaQuieta() {}
    public void detener() {}
    public void subirEscalera(int alturaVertical) {}
    public void afectar(Enemigo enemigo) {}

}