package Estructura;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEntidades;
import Juego.Controladores.ControladorObstaculo;
import Personajes.BolaEnMovimiento;
import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;
import Personajes.Personaje;
import Proyectil.ProyectilAmigo;
import Proyectil.ProyectilEnemigo;

public class Pared extends Obstaculo{

    public Pared(int x, int y, Sprites sprites, ControladorObstaculo controladorObstaculos) {
        super(x, y, sprites, controladorObstaculos);
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }

    public void afectar(Jugador jugador) {
        if (chocoDesdeIzquierda(jugador))
            ajustarPosicionDesdeIzquierda(jugador, ConstantesEntidades.JUGADOR_HITBOX_ANCHO);
        else
            if (chocoDesdeDerecha(jugador))
                ajustarPosicionDesdeDerecha(jugador, ConstantesEntidades.JUGADOR_HITBOX_ANCHO);
    }

    public void afectar(Enemigo enemigo) {
        if (chocoDesdeIzquierda(enemigo))
            enemigo.chocarPared(ConstantesDireccion.IZQUIERDA,x);
        else
            if (chocoDesdeDerecha(enemigo))
                enemigo.chocarPared(ConstantesDireccion.DERECHA,x);
    }

    public void afectar(BolaEnMovimiento bolaEnMovimiento) {
        if (chocoDesdeIzquierda(bolaEnMovimiento))
            ajustarPosicionDesdeIzquierda(bolaEnMovimiento, ConstantesEntidades.BOLAENMOVIMIENTO_HITBOX_ANCHO);
        else
            if (chocoDesdeDerecha(bolaEnMovimiento))
                ajustarPosicionDesdeDerecha(bolaEnMovimiento, ConstantesEntidades.BOLAENMOVIMIENTO_HITBOX_ANCHO);
        bolaEnMovimiento.setDireccion(bolaEnMovimiento.getDireccion() * -1);
    }

    protected boolean chocoDesdeIzquierda(Personaje personaje) {
        return (personaje.getDireccion() == ConstantesDireccion.DERECHA || personaje.getDireccion() == ConstantesDireccion.QUIETO_DERECHA) &&
        (personaje.getHitbox().x < this.getHitbox().x);
    }

    protected boolean chocoDesdeDerecha(Personaje personaje) {
        return (personaje.getDireccion() == ConstantesDireccion.IZQUIERDA || personaje.getDireccion() == ConstantesDireccion.QUIETO_IZQUIERDA) &&
        (this.getHitbox().x < personaje.getHitbox().x);
    }

    protected void ajustarPosicionDesdeIzquierda(Personaje personaje, int anchoHitboxPersonaje) {
        personaje.setPosicion(x - personaje.getAnchoSprite() + (personaje.getAnchoSprite() - anchoHitboxPersonaje) / 2 - 1, personaje.getY());
    }

    protected void ajustarPosicionDesdeDerecha(Personaje personaje, int anchoHitboxPersonaje) {
        personaje.setPosicion(x + getAnchoSprite() - (personaje.getAnchoSprite() - anchoHitboxPersonaje) / 2 + 1, personaje.getY());
    }

    public void afectar(ProyectilEnemigo proyectilEnemigo) {
        proyectilEnemigo.desaparecer();
    }

    public void afectar(ProyectilAmigo proyectilAmigo) {
        proyectilAmigo.desaparecer();
    }

    public int getAnchoSprite() {
        return ConstantesEntidades.PARED_SPRITE_ANCHO;
    }

    public int getAltoSprite() {
        return ConstantesEntidades.PARED_SPRITE_ALTO;
    }
    
    public int getAnchoHitbox() {
        return ConstantesEntidades.PARED_HITBOX_ANCHO;
    }

    public int getAltoHitbox() {
        return ConstantesEntidades.PARED_HITBOX_ALTO;
    }

}