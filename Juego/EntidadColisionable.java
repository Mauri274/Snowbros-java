package Juego;

import Fabricas.Sprites;
import java.awt.Rectangle;

public abstract class EntidadColisionable extends Entidad{

    protected Rectangle hitbox;


    public EntidadColisionable(int x, int y, Sprites sprites){
        super(x, y, sprites);
    }
    
    public abstract int getAnchoHitbox();
    public abstract int getAltoHitbox();


    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
        setPosicionHitbox(x,y);
        notificarObserver();
    }

    protected void crearHitbox(int anchoHitbox, int altoHitbox, int anchoSprite, int altoSprite){
        int posicionX = ajustarHitboxX(x, anchoHitbox, anchoSprite);
        int posicionY = ajustarHitboxY(y, altoHitbox, altoSprite);
        hitbox = new Rectangle(posicionX, posicionY, anchoHitbox, altoHitbox);
    }

    protected abstract int ajustarHitboxX(int x, int anchoHitbox, int anchoSprite);
    protected abstract int ajustarHitboxY(int y, int altoHitbox, int altoSprite);

    protected void setPosicionHitbox(int x, int y) {
        int posicionX = ajustarHitboxX(x, getAnchoHitbox(), getAnchoSprite());
        int posicionY = ajustarHitboxY(y, getAltoHitbox(), getAltoSprite());
        hitbox.setLocation(posicionX, posicionY);
    }
}