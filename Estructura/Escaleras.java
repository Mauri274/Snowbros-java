package Estructura;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesEntidades;
import Juego.Controladores.ControladorObstaculo;
import Personajes.Enemigo.Enemigo;
import Proyectil.ProyectilAmigo;
import Proyectil.ProyectilEnemigo;
import Personajes.Jugador;
import Personajes.Personaje;
import java.util.concurrent.ThreadLocalRandom;

public class Escaleras extends Obstaculo{

    protected int anchoHitbox;
    protected int altoHitbox;
    protected int anchoSprite;
    protected int altoSprite;


    public Escaleras(int x, int yInicio, int yFin, Sprites sprites, ControladorObstaculo controladorObstaculos){
        super(x, yInicio, sprites, controladorObstaculos);
        anchoHitbox = ConstantesEntidades.ESCALERA_HITBOX_ANCHO;
        altoHitbox = yFin - yInicio;
        anchoSprite = ConstantesEntidades.ESCALERA_SPRITE_ANCHO;
        altoSprite = yFin - yInicio;
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }

    public int getAnchoSprite() {
        return anchoSprite;
    }

    public int getAltoSprite() {
        return altoSprite;
    }

    public int getAnchoHitbox() {
        return anchoHitbox;
    }

    public int getAltoHitbox() {
        return altoHitbox;
    }

    public void afectar(Enemigo enemigo) {
        int numeroAleatorio = ThreadLocalRandom.current().nextInt(1, 3);
        if (enemigo.getY() <= y + 10 && estaEnElMedioEscalera(enemigo))
            if (numeroAleatorio == 1)
                enemigo.subirEscalera(y + altoHitbox);
    }

    protected boolean estaEnElMedioEscalera(Personaje personaje) {
        return personaje.getX() >= x - anchoSprite / 3 && personaje.getX() <= x - anchoSprite / 4;
    }

    public void afectar(Jugador jugador) {}
    public void afectar(ProyectilAmigo proyectilAmigo) {}
    public void afectar(ProyectilEnemigo proyectilEnemigo) {}

}