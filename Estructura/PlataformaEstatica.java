package Estructura;

import Fabricas.Sprites;
import Juego.Controladores.ControladorPlataforma;
import Personajes.Enemigo.Enemigo;
import Proyectil.ProyectilAmigo;
import Proyectil.ProyectilEnemigo;

public class PlataformaEstatica extends Plataforma{

    public PlataformaEstatica(int xInicio, int xFin, int y, Sprites sprites, ControladorPlataforma controladorPlataforma) {
        super(xInicio, xFin, y, sprites, controladorPlataforma);
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }

    public void afectar(ProyectilEnemigo proyectilEnemigo) {
        proyectilEnemigo.desaparecer();
    }

    public void afectar(ProyectilAmigo proyectilAmigo) {
        proyectilAmigo.desaparecer();
    }

    public void afectar(Enemigo enemigo) {}
}