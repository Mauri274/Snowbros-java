package Personajes;

import Estructura.Plataforma;
import Fabricas.Sprites;
import Grafica.AjustarPosicionEnPantalla;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesVistas;
import Juego.EntidadColisionable;
import Visitor.*;
import java.awt.Rectangle;

public abstract class Personaje extends EntidadColisionable implements Colisionador, Colisionable {
    
    protected int vida;
    protected int velocidad;
    protected int direccion;
    protected Plataforma plataformaColisionando;
    

    public Personaje(int x, int y, Sprites sprites) {
        super(x, y, sprites);
        direccion = ConstantesDireccion.QUIETO_DERECHA;
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getDireccion() {
        return direccion;
    }

    public int getVidas(){
        return vida;
    }

    public Plataforma getPlataformaColisionando() {
        return plataformaColisionando;
    }


    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public void actualizarPlataformaColisionando(Plataforma plataforma) {
        plataformaColisionando = plataforma;
    }

    public boolean estaSobrePlataforma() {
        return plataformaColisionando != null;
    }

    public void aplicarMovimientoHorizontal(){
        if (puedeMoverse()) {
            int direccion = getDireccion();
            int velocidad = getVelocidad();
            setPosicion(getX() + (velocidad * direccion), getY());
        }
    }

    public boolean estaQuieto(){
        int direccionPersonaje = getDireccion();
        return (direccionPersonaje == ConstantesDireccion.QUIETO_IZQUIERDA || direccionPersonaje == ConstantesDireccion.QUIETO_DERECHA);
    }
    public abstract void actualizar();
    
    public boolean estaChocandoBordeIzquierdo() {
        return hitbox.x <= 0;
    }

    public boolean estaChocandoBordeDerecho() {
        return hitbox.x >= ConstantesVistas.PANEL_PISO_ANCHO - hitbox.getWidth();
    }

    public boolean puedeMoverse() {
        return (!estaChocandoBordeIzquierdo() && getDireccion() == ConstantesDireccion.IZQUIERDA
            || !estaChocandoBordeDerecho() && getDireccion() == ConstantesDireccion.DERECHA);
    }

    public boolean estaSobreElSuelo() {
        return getY() <= 0;
    }
    
    protected static void separarMutuo(Personaje a, Personaje b) {
        Rectangle hitboxPersonajeA = a.getHitbox();
        Rectangle hitboxPersonajeB = b.getHitbox();
        Rectangle interseccion = hitboxPersonajeA.intersection(hitboxPersonajeB);
        if (interseccion.isEmpty()) return;

        if (interseccion.width <= interseccion.height) {
            int separacionx = (interseccion.width / 2) + 1;
            if (hitboxPersonajeA.getCenterX() < hitboxPersonajeB.getCenterX()) {
                a.setPosicion(a.getX() - separacionx, a.getY());
                b.setPosicion(b.getX() + separacionx, b.getY());
            } else {
                a.setPosicion(a.getX() + separacionx, a.getY());
                b.setPosicion(b.getX() - separacionx, b.getY());
            }
        } else {
            int separaciony = (interseccion.height / 2) + 1;
            if (hitboxPersonajeA.getCenterY() < hitboxPersonajeB.getCenterY()) {
                a.setPosicion(a.getX(), a.getY()-separaciony);
                b.setPosicion(b.getX(), b.getY()+separaciony);
            } else {
                a.setPosicion(a.getX(), a.getY()+separaciony);
                b.setPosicion(b.getX(), b.getY()-separaciony);
            }
        }
    }

    protected static void separarBloqueando(Personaje agente, Personaje solido) {
        Rectangle hitboxAgente = agente.getHitbox();
        Rectangle hitboxSolido = solido.getHitbox();
        var interseccion = hitboxAgente.intersection(hitboxSolido);
        if (interseccion.isEmpty()) return;

        if (interseccion.width <= interseccion.height) {
            int separacionx = interseccion.width + 1;
            if (hitboxAgente.getCenterX() < hitboxSolido.getCenterX()) {
                agente.setPosicion(agente.getX() - separacionx, agente.getY());
            }
            else{ 
                agente.setPosicion(agente.getX() + separacionx, agente.getY());
            }
        } else {
            if(!agente.estaSobreElSuelo()){
                int separaciony = interseccion.height + 1;
                if (hitboxAgente.getCenterY() < hitboxSolido.getCenterY()) {
                    agente.setPosicion(agente.getX(), agente.getY()-separaciony);
                }
                else { 
                    agente.setPosicion(agente.getX(), agente.getY()-separaciony);
                }
            }
        }
    }
    protected int ajustarHitboxX(int x, int anchoHitbox, int anchoSprite) {
        return AjustarPosicionEnPantalla.ajustarHitboxXCentrado(x, anchoHitbox, anchoSprite);
    }

    protected int ajustarHitboxY(int y, int altoHitbox, int altoSprite) {
        return AjustarPosicionEnPantalla.ajustarHitboxYPersonaje(y, altoHitbox, altoSprite);
    }
}
    