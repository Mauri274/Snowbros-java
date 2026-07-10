package Estados.EstadoEnemigo;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;

public class EstadoKamakichi extends EstadoEnemigo {

    protected final boolean subir;
    protected int contador;
    protected boolean estaAturdido = false;
    protected int velocidadActualVertical;
    protected final int VELOCIDAD_VERTICAL_MAXIMA = 3;
    protected int distanciaVerticalARecorrer = 30;


    public EstadoKamakichi(Enemigo enemigo) {
        super(enemigo);
        estadoActual = ConstantesEstado.QUIETO;
        enemigo.getSprites().setEstadoActual(estadoActual);
        velocidadActualVertical = VELOCIDAD_VERTICAL_MAXIMA;
        subir = enemigo.estaSobreElSuelo();
        velocidadActualDeCaida = enemigo.getVelocidad();
    }

    public void disparar() {
        enemigo.setEstadoActual(new AtacandoKamakichi(enemigo));
    }

    protected void aplicarSalto() {
        if(distanciaVerticalARecorrer > 0){
            enemigo.setPosicion(enemigo.getX(), enemigo.getY() + velocidadActualVertical);
            distanciaVerticalARecorrer--;
        }
    }

    public void actualizar() {
        if(subir)
            aplicarSalto();
        else
            aplicarCaida();
    }

    protected void aplicarCaida(){
        int posicionY = enemigo.getY() - velocidadActualDeCaida;
        if (!enemigo.estaSobreElSuelo())
            enemigo.setPosicion(enemigo.getX(), posicionY);
        else
            enemigo.setPosicion(enemigo.getX(), 0);
    }

    public void actualizarDecision() {
        enemigo.actualizarLogicaDecision();
    }

    public void aturdir() {}

    public void afectar(Jugador jugador) {
        jugador.recibirDanio();
    }
    
    public void mover() {}
    public void saltar() {}
    public void hacerBolaQuieta(){}
    public void detener(){}
    public void subirEscalera(int alturaVertical){}
    public void afectar(Enemigo enemigo){}

}
