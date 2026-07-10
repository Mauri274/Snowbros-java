package Estados.EstadoEnemigo;

import Grafica.ObserverGraficoNieve;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;

public class Congelado extends EstadoEnemigo{

    protected int tiempoHastaDescongelar;
    protected final int tiempoMaximoDescongelar = 25;
    protected ObserverGraficoNieve observerNieve;


    public Congelado(Enemigo enemigo) {
        super(enemigo);
        estadoActual = ConstantesEstado.ATURDIDO;
        enemigo.getSprites().setEstadoActual(estadoActual);
        tiempoHastaDescongelar = tiempoMaximoDescongelar;
        observerNieve = enemigo.getControladorEnemigo().registrarObserverNieve(enemigo);
    }

    public void actualizar() {
        if(tiempoHastaDescongelar <= 0){
            reducirCongelacion();
        }
        else
            tiempoHastaDescongelar--;
        if (!enemigo.estaSobrePlataforma() && ! enemigo.estaSobreElSuelo())
            aplicarCaida();
        actualizarSprite();
    }

    protected void reducirCongelacion() {
        enemigo.aumentarVida();
        tiempoHastaDescongelar = tiempoMaximoDescongelar;
        if (enemigo.getVidas() >= enemigo.getVidaMaxima())
            descongelar();
    }

    protected void descongelar(){
        enemigo.removeObserver(observerNieve);
        if (enemigo.getDireccion() == ConstantesDireccion.QUIETO_DERECHA || enemigo.getDireccion() == ConstantesDireccion.QUIETO_IZQUIERDA) 
            enemigo.setEstadoActual(new Quieto(enemigo));
        else
            enemigo.setEstadoActual(new Caminando(enemigo));
    }

    protected void actualizarSprite() {
        if (enemigo.getVidas() > enemigo.getVidaMaxima() - enemigo.getVidaMaxima() / 3)
            observerNieve.setEstadoActual(ConstantesEstado.CONGELADO_1);
        else
            if (enemigo.getVidas() > enemigo.getVidaMaxima() / 3)
                observerNieve.setEstadoActual(ConstantesEstado.CONGELADO_2);
            else
                observerNieve.setEstadoActual(ConstantesEstado.CONGELADO_3);
    }

    public void aturdir() {
        enemigo.removeObserver(observerNieve);
        enemigo.setEstadoActual(new Aturdido(enemigo,this));
    }

    public void hacerBolaQuieta() {
        enemigo.removeObserver(observerNieve);
        enemigo.setEstadoActual(new BolaQuieta(enemigo));
    }

    public void congelar() {
        tiempoHastaDescongelar = tiempoMaximoDescongelar;
        if(enemigo.getVidas() <= 0) 
            hacerBolaQuieta();
    }

    public void mover() {}
    public void detener() {}
    public void subirEscalera(int alturaVertical) {}
    public void afectar(Jugador jugador) {}
    public void afectar(Enemigo enemigo) {}
    public void disparar() {}
    public void saltar() {}

}