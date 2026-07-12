package Proyectil;

import Fabricas.Sprites;
import Juego.Controladores.ControladorProyectil;
import Personajes.Jugador;
import Personajes.Enemigo.Enemigo;
import Visitor.Colisionable;

public abstract class ProyectilAmigo extends Proyectil{

    protected int danio;

    public ProyectilAmigo(int x, int y, Sprites sprites, int direccion, int danio, ControladorProyectil controladorProyectil) {
        super(x, y, sprites, direccion, controladorProyectil);
        this.danio = danio;
    }

    public void afectar(Enemigo enemigo) {
        if(!consumido){
            consumido = true;
            enemigo.recibirDanio(danio);
        }
    }
    
    public void colisionar(Colisionable colisionable) {
        colisionable.afectar(this);
    }
    public void afectar(Jugador jugador) {}


}
