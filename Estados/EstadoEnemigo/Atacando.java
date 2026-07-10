package Estados.EstadoEnemigo;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;

public class Atacando extends EstadoEnemigo{

    protected int velocidadAtaque;
    protected int contador;


    public Atacando(Enemigo enemigo) {
        super(enemigo);
        estadoActual = ConstantesEstado.DISPARANDO;
        enemigo.getSprites().setEstadoActual(estadoActual);
        this.contador = 0;
        velocidadAtaque = enemigo.getVelocidadAtaque();
    }

    public void mover() {}

    public void disparar() {
        aplicarDisparo();
        detener();
    }

    

    public void actualizar() {
        contador++;
        if(contador > velocidadAtaque){
            disparar();
        }
    }

    protected void aplicarDisparo() {
        contador = 0;
        enemigo.crearProyectil();
        detener();
    }

    public void aturdir() {
        this.enemigo.setEstadoActual(new Aturdido(enemigo, this));
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

    public void saltar() {}
    public void afectar(Enemigo enemigo) {}
    public void hacerBolaQuieta() {}
    
}