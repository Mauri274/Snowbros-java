package Estructura;

import Fabricas.Sprites;
import Grafica.AjustarPosicionEnPantalla;
import Grafica.Constantes.ConstantesEntidades;
import Juego.Controladores.ControladorPlataforma;
import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;
import Proyectil.ProyectilAmigo;
import Proyectil.ProyectilEnemigo;

public abstract class Plataforma extends Estructura {

    ControladorPlataforma controladorPlataforma;
    protected int anchoHitbox;
    protected int altoHitbox;
    protected int anchoSprite;
    protected int altoSprite;


    public Plataforma(int xInicio, int xFin, int y, Sprites sprites, ControladorPlataforma controladorPlataforma) {
        super(xInicio, y, sprites);
        this.controladorPlataforma = controladorPlataforma;
        anchoHitbox = xFin - xInicio;
        altoHitbox = ConstantesEntidades.PLATAFORMA_HITBOX_ALTO;
        anchoSprite = anchoHitbox;
        altoSprite = ConstantesEntidades.PLATAFORMA_SPRITE_ALTO;
        crearHitbox(getAnchoHitbox(),getAltoHitbox(),getAnchoSprite(),getAltoSprite());
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

    public void afectar(ProyectilEnemigo proyectilEnemigo) {
        proyectilEnemigo.desaparecer();
    }

    public void afectar(ProyectilAmigo proyectilAmigo) {
        proyectilAmigo.desaparecer();
    }

    public void desaparecer() {
        controladorPlataforma.removePlataforma(this);
        super.desaparecer();
    }

    protected int ajustarHitboxX(int x, int anchoHitbox, int anchoSprite) {
        return AjustarPosicionEnPantalla.ajustarHitboxXCentrado(x, anchoHitbox, anchoSprite);
    }

    protected int ajustarHitboxY(int y, int altoHitbox, int altoSprite) {
        return AjustarPosicionEnPantalla.ajustarHitboxYPlataforma(y, altoHitbox, altoSprite);
    }

    public void afectar(Jugador jugador) {}
    public void afectar(Enemigo enemigo) {}
    public void actualizar() {}
}
