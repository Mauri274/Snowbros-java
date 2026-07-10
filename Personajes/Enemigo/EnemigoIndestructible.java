package Personajes.Enemigo;

import Fabricas.Sprites;
import Grafica.Observer;
import Juego.Puntaje;
import Juego.Controladores.ControladorEnemigo;

public abstract class EnemigoIndestructible extends Enemigo{

    public EnemigoIndestructible(int x, int y, Sprites sprites, Puntaje puntaje, ControladorEnemigo controladorEnemigo) {
        super(x, y, sprites, puntaje,  controladorEnemigo);
    }

    public void recibirDanio(int danio){}
    
    public void desaparecer(){
        for (Observer observers : listaObservers)
            observers.desaparecer();
        controladorEnemigo.removeIndestructible(this);
    }
}
