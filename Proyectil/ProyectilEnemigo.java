package Proyectil;

import Fabricas.Sprites;
import Juego.Controladores.ControladorProyectil;
import Personajes.Jugador;
import Personajes.Enemigo.Enemigo;
import Visitor.Colisionable;

public abstract class ProyectilEnemigo extends Proyectil{

    public ProyectilEnemigo(int x, int y, Sprites sprites, int direccion, ControladorProyectil controladorProyectil) {
        super(x, y, sprites, direccion, controladorProyectil);
    }

    public void afectar(Enemigo enemigo) {}

    public void colisionar(Colisionable colisionable) {
        colisionable.afectar(this);
    }
    
    public void afectar(Jugador jugador) {
        if(!consumido){
            consumido = true;
            jugador.recibirDanio();
        }
    }
}
