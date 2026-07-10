package Estados.EstadoEnemigo;

import Grafica.Constantes.ConstantesEstado;
import Personajes.Enemigo.Enemigo;

public class AtacandoKamakichi extends Atacando{

    public AtacandoKamakichi(Enemigo enemigo) {
        super(enemigo);
        estadoActual = ConstantesEstado.DISPARANDO;
        enemigo.getSprites().setEstadoActual(estadoActual);
        this.contador = 0;
        velocidadAtaque = enemigo.getVelocidadAtaque();
    }

    public void actualizar() {
        contador++;
        if(contador > velocidadAtaque){
            disparar();
        }
    }

    public void disparar() {
        aplicarDisparo();
        detener();
    }

    protected void aplicarDisparo() {
        contador = 0;
        enemigo.crearProyectil();
    }

    public void detener() {
        enemigo.setEstadoActual(new EstadoKamakichi(enemigo));
    }

}
