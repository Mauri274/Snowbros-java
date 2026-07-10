package Personajes.Enemigo;

import Estructura.Pared;
import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Juego.ConstantesPuntaje;
import Juego.Puntaje;
import Juego.Controladores.ControladorEnemigo;


public class DemonioRojo extends EnemigoCongelable{

    public DemonioRojo(int x, int y, Sprites sprites, Puntaje puntaje, ControladorEnemigo controladorEnemigo) {
        super(x, y, sprites, puntaje,  controladorEnemigo);
        velocidad = 3;
        vidaMaxima = 6;
        vida = vidaMaxima;
        estaPensando = true;
        timerQuieto = 60 * 2;
        cantPuntajeCongelar = ConstantesPuntaje.ENEMIGO_COMUN_CONGELADO;
        cantPuntajeEmpujar = ConstantesPuntaje.ENEMIGO_COMUN_EMPUJADO;
    }

    protected void tomarDecisionAleatoria() {
        if (generadorRandom.nextBoolean()) {
            if (getDireccion() == ConstantesDireccion.IZQUIERDA)
                setDireccion(ConstantesDireccion.DERECHA);
            else
                setDireccion(ConstantesDireccion.IZQUIERDA);
        }
        this.estadoEnemigo.mover();
    }

    public void afectar(Pared pared) {
        this.chocarContraPared();
    }

    public void chocarContraPared() {
        if (!estaPensando) {
            if (getDireccion() == ConstantesDireccion.DERECHA) {
                direccion = ConstantesDireccion.QUIETO_DERECHA;
            }
            else
                if (getDireccion() == ConstantesDireccion.IZQUIERDA)
                    direccion = ConstantesDireccion.QUIETO_IZQUIERDA;
            this.estadoEnemigo.detener();
            estaPensando = true;
            timerQuieto = TIEMPO_QUIETO_DEFINIDO;

            reiniciarTimerDecision();

            if (getDireccion() == ConstantesDireccion.IZQUIERDA)
                setDireccion(ConstantesDireccion.DERECHA);
            else
                setDireccion(ConstantesDireccion.IZQUIERDA);
        }
    }

    public void crearProyectil() {}

}