package Estados.EstadoEnemigo;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;

public class Aturdido extends EstadoEnemigo {

    protected int spritePrevio;
    protected EstadoEnemigo estadoPrevio;
    protected int tiempoRestante;


    public Aturdido(Enemigo enemigo, EstadoEnemigo estadoPrevio) {
        super(enemigo);
        spritePrevio = enemigo.getSprites().getEstadoActual();
        estadoActual = ConstantesEstado.ATURDIDO;
        enemigo.getSprites().setEstadoActual(estadoActual);
        this.estadoPrevio = estadoPrevio;
        tiempoRestante = DURACION_ATURDIMIENTO;
    }

    public void actualizar() {
        tiempoRestante--;
        if (tiempoRestante <= 0) {
            enemigo.setEstadoActual(estadoPrevio);
            enemigo.getSprites().setEstadoActual(spritePrevio);
        }
        if (!enemigo.estaSobrePlataforma() && ! enemigo.estaSobreElSuelo())
            aplicarCaida();
    }

    public void mover(){}
    public void aturdir(){}
    public void hacerBolaQuieta(){}
    public void detener(){}
    public void subirEscalera(int alturaVertical){}
    public void afectar(Jugador jugador){}
    public void afectar(Enemigo enemigo){}
    public void disparar(){}
    public void saltar(){}
}