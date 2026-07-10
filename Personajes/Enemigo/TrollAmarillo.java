package Personajes.Enemigo;

import Estados.EstadoEnemigo.Cayendo;
import Estructura.Pared;
import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Juego.ConstantesPuntaje;
import Juego.Puntaje;
import Juego.Controladores.ControladorEnemigo;

public class TrollAmarillo extends EnemigoCongelable{

    protected boolean estaPensando = false;

    public TrollAmarillo(int x, int y, Sprites sprites, Puntaje puntaje, ControladorEnemigo controladorEnemigo) {
        super(x, y, sprites, puntaje,  controladorEnemigo);
        velocidad += 2;
        vidaMaxima = 6;
        vida = vidaMaxima;
        cantPuntajeCongelar = ConstantesPuntaje.ENEMIGO_VELOZ_CONGELADO;
        cantPuntajeEmpujar = ConstantesPuntaje.ENEMIGO_COMUN_EMPUJADO;
    }

    public void saltar(){
        this.estadoEnemigo.saltar();
    }

    protected void bajarPlataforma(){
        plataformaColisionando = null;
        setPosicion(x, y-15);
        this.estadoEnemigo = new Cayendo(this);
    }

    protected void tomarDecisionAleatoria() {
        int decision = generadorRandom.nextInt(100);
        if (decision < 50){
            if (direccion == ConstantesDireccion.DERECHA)
                setDireccion(ConstantesDireccion.IZQUIERDA);
            else
                setDireccion(ConstantesDireccion.DERECHA);
            this.estadoEnemigo.mover();
        }
        else{
            if(this.estaSobrePlataforma()){
                if(decision > 70){
                    bajarPlataforma();
                }
            }
            else
                saltar();
        }
    }

    public void afectar(Pared pared) {
        if (getDireccion() == ConstantesDireccion.IZQUIERDA)
            setDireccion(ConstantesDireccion.DERECHA);
        else
            setDireccion(ConstantesDireccion.IZQUIERDA);
    }

    public void crearProyectil() {}  
}