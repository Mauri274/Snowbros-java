package Estructura;

import Fabricas.Sprites;
import Grafica.AjustarPosicionEnPantalla;
import Juego.Controladores.ControladorObstaculo;

public abstract class Obstaculo extends Estructura{

    protected ControladorObstaculo controladorObstaculos;


    public Obstaculo(int x, int y, Sprites sprites, ControladorObstaculo controladorObstaculos) {
        super(x, y, sprites);
        this.controladorObstaculos = controladorObstaculos;
    }

    public void desaparecer() {
        controladorObstaculos.removeObstaculo(this);
        super.desaparecer();
    }

    protected int ajustarHitboxX(int x, int anchoHitbox, int anchoSprite){
        return AjustarPosicionEnPantalla.ajustarHitboxXCentrado(x, anchoHitbox, anchoSprite);
    }

    protected int ajustarHitboxY(int y, int altoHitbox, int altoSprite){
        return AjustarPosicionEnPantalla.ajustarHitboxYCentrado(y,altoHitbox,altoSprite);
    }

}