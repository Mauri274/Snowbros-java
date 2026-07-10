package Estados.EstadoEnemigo;

import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;

public abstract class EstadoEnemigo{

    protected Enemigo enemigo;
    protected int estadoActual;
    protected final int VELOCIDAD_MAXIMA_DE_CAIDA = 6;
    protected int velocidadActualDeCaida;
    protected final int DURACION_ATURDIMIENTO = 60 * 3;


    public EstadoEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
        velocidadActualDeCaida = 0;
    }

    public void congelar() {
        enemigo.setEstadoActual(new Congelado(enemigo));
    }
    
    protected void caerSiNoHaySuelo() {
        if (!enemigo.estaSobrePlataforma() && !enemigo.estaSobreElSuelo())
            enemigo.setEstadoActual(new Cayendo(enemigo));
    }

    protected void aplicarCaida() {
        int posicionY = enemigo.getY() - velocidadActualDeCaida;
        if (posicionY > 0) {
            enemigo.setPosicion(enemigo.getX(), posicionY);
            if (velocidadActualDeCaida < VELOCIDAD_MAXIMA_DE_CAIDA)
                velocidadActualDeCaida++;
        }
        else
            enemigo.setPosicion(enemigo.getX(), 0);
    }

    public abstract void mover();
    public abstract void disparar();
    public abstract void saltar();
    public abstract void actualizar();
    public abstract void aturdir();
    public abstract void hacerBolaQuieta();
    public abstract void detener();
    public abstract void subirEscalera(int alturaVertical);
    public void empujar(int direccion) {};
    public abstract void afectar(Jugador jugador);
    public abstract void afectar(Enemigo enemigo);
    public void actualizarDecision(){}
}
