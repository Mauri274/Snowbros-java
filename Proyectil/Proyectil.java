package Proyectil;

import Fabricas.Sprites;
import Grafica.AjustarPosicionEnPantalla;
import Grafica.Constantes.ConstantesEntidades;
import Grafica.Constantes.ConstantesVistas;
import Juego.EntidadColisionable;
import Juego.Controladores.ControladorProyectil;
import Personajes.BolaEnMovimiento;
import Visitor.Colisionable;
import Visitor.Colisionador;

public abstract class Proyectil extends EntidadColisionable implements Colisionable, Colisionador{
    
    protected int velocidadHorizontal;
    protected int direccion;
    protected ControladorProyectil controladorProyectil;
    protected boolean consumido = false;
    
    public Proyectil(int x, int y, Sprites sprites, int direccion, ControladorProyectil controladorProyectil) {
        super(x, y, sprites);
        this.direccion = direccion;
        this.controladorProyectil = controladorProyectil;
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }
    
    public int getAnchoSprite() {
        return ConstantesEntidades.PROYECTIL_SPRITE_ANCHO;
    }

    public int getAltoSprite() {
        return ConstantesEntidades.PROYECTIL_SPRITE_ALTO;
    }

    public int getAnchoHitbox(){
        return ConstantesEntidades.PROYECTIL_HITBOX_ANCHO;
    }

    public int getAltoHitbox(){
        return ConstantesEntidades.PROYECTIL_HITBOX_ALTO;
    }

    public int getDireccion() {
        return direccion;
    }

    public abstract void actualizar();
    
    public void setPosicion(int x, int y) {
        if (!estaChocandoBordePantalla()) {
            super.setPosicion(x, y);

            notificarObserver();
        }
        else
            desaparecer();
    }

    public void afectar(ProyectilAmigo proyectilAmigo) {}

    public void afectar(ProyectilEnemigo proyectilEnemigo) {}

    public void afectar(BolaEnMovimiento bolaEnMovimiento) {
        
    }

    protected int ajustarHitboxX(int x, int anchoHitbox, int anchoSprite) {
        return AjustarPosicionEnPantalla.ajustarHitboxXCentrado(x, anchoHitbox, anchoSprite);
    }

    protected int ajustarHitboxY(int y, int altoHitbox, int altoSprite) {
        return AjustarPosicionEnPantalla.ajustarHitboxYCentrado(y, altoHitbox, altoSprite);
    }

    protected boolean estaChocandoBordePantalla() {
        return x <= 0 || x >= ConstantesVistas.PANEL_PISO_ANCHO - ConstantesEntidades.PROYECTIL_SPRITE_ANCHO
        || y <= 0 || y >= ConstantesVistas.PANEL_PISO_ALTO - ConstantesEntidades.PROYECTIL_SPRITE_ALTO;
    }

    public void desaparecer() {
        controladorProyectil.removeProyectil(this);
        super.desaparecer();
    }
}
