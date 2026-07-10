package Estados.EstadoEnemigo;

import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;
import Personajes.Enemigo.Enemigo;

public class SubiendoEscalera extends EstadoEnemigo{

    protected int velocidadDeSubida = 3;
    protected int alturaVertical;


    public SubiendoEscalera(Enemigo enemigo, int alturaVertical) {
        super(enemigo);
        this.alturaVertical = alturaVertical;
        estadoActual = ConstantesEstado.QUIETO;
        enemigo.getSprites().setEstadoActual(estadoActual);
    }

    public void actualizar() {
        subir();
    }

    protected void subir() {
        if (enemigo.getY() < alturaVertical)
            enemigo.setPosicion(enemigo.getX(), enemigo.getY() + velocidadDeSubida);
        else
            enemigo.setEstadoActual(new Caminando(enemigo));
    }

    public void aturdir() {
        enemigo.setEstadoActual(new Aturdido(this.enemigo,this));
    }

    public void afectar(Jugador jugador) {
        jugador.recibirDanio();
    }

    public void mover() {}
    public void disparar() {}
    public void saltar() {}
    public void hacerBolaQuieta() {}
    public void detener() {}
    public void subirEscalera(int alturaVertical) {}
    public void afectar(Enemigo enemigo) {}

}