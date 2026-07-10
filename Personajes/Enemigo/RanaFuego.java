package Personajes.Enemigo;

import Estructura.Pared;
import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Juego.ConstantesPuntaje;
import Juego.Puntaje;
import Juego.Controladores.ControladorEnemigo;
import Juego.Controladores.ControladorProyectil;

public class RanaFuego extends EnemigoCongelable{

    protected ControladorProyectil controladorProyectil;

    public RanaFuego(int x, int y, Sprites sprites, Puntaje puntaje, ControladorEnemigo controladorEnemigo) {
        super(x, y, sprites, puntaje,  controladorEnemigo);
        cantPuntajeCongelar = ConstantesPuntaje.ENEMIGO_COMUN_CONGELADO;
        cantPuntajeEmpujar = ConstantesPuntaje.ENEMIGO_COMUN_EMPUJADO;
        velocidad = 2;
        vidaMaxima = 9;
        vida = vidaMaxima;
        estaPensando = true;
        timerQuieto = 60;
        velocidadAtaque = 10;
    }

    public void disparar(){
        this.estadoEnemigo.disparar();
    }

    protected void tomarDecisionAleatoria() {
        int decision = generadorRandom.nextInt(100);

        if (decision < 50)
            disparar();
        else{
            if (direccion == ConstantesDireccion.DERECHA)
                setDireccion(ConstantesDireccion.IZQUIERDA);
            else
                setDireccion(ConstantesDireccion.IZQUIERDA);
            
            this.estadoEnemigo.mover();
        }
    }

    public void afectar(Pared pared) {
        if (getDireccion() == ConstantesDireccion.IZQUIERDA)
            setDireccion(ConstantesDireccion.DERECHA);
        else
            setDireccion(ConstantesDireccion.IZQUIERDA);
    }

    public void crearProyectil() {
        int posicionX = x + getAltoSprite() / 2;
        int posicionY = y + getAltoSprite() / 2;
        controladorEnemigo.dispararBolaDeFuego(posicionX, posicionY, getDireccion());
    }

}