package Personajes.Enemigo;

import Fabricas.Sprites;
import Juego.Puntaje;
import Juego.Controladores.ControladorEnemigo;

public abstract class EnemigoCongelable extends Enemigo{


    public EnemigoCongelable(int x, int y, Sprites sprites, Puntaje puntaje, ControladorEnemigo controladorEnemigo) {
        super(x, y, sprites, puntaje,  controladorEnemigo);
    }

    public void recibirDanio(int danio){
        super.recibirDanio(danio);
        congelar();
    }

    public void congelar(){
        estadoEnemigo.congelar();
    }

    public void hacerBolaEnMovimiento() {
        desaparecer();
        controladorEnemigo.addBolaEnMovimiento(x, y, direccion);
    }

}