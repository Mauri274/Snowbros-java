package Personajes;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEntidades;
import Grafica.Constantes.ConstantesEstado;
import Juego.Controladores.ControladorBolaEnMovimiento;
import Personajes.Enemigo.Enemigo;
import Proyectil.ProyectilAmigo;
import Proyectil.ProyectilEnemigo;
import Visitor.Colisionable;

public class BolaEnMovimiento extends Personaje{

    protected final int VELOCIDAD = 25;
    protected ControladorBolaEnMovimiento controladorBolaEnMovimiento;
    protected int tiempoHastaDesaparecer = 23;
    protected boolean chocoElBorde = false;

    public BolaEnMovimiento(int x, int y, Sprites sprites, int direccion, ControladorBolaEnMovimiento controladorBolaEnMovimiento) {
        super(x, y, sprites);
        this.controladorBolaEnMovimiento = controladorBolaEnMovimiento;
        this.direccion = direccion;
    }

    public int getAnchoSprite() {
        return ConstantesEntidades.BOLAENMOVIMIENTO_SPRITE_ANCHO;
    }

    public int getAltoSprite() {
        return ConstantesEntidades.BOLAENMOVIMIENTO_SPRITE_ALTO;
    }

    public int getAnchoHitbox(){
        return ConstantesEntidades.BOLAENMOVIMIENTO_HITBOX_ANCHO;
    }

    public int getAltoHitbox(){
        return ConstantesEntidades.BOLAENMOVIMIENTO_HITBOX_ALTO;
    }

    public void actualizar() {
        if (!chocoElBorde)
            mover();
        else
            tiempoHastaDesaparecer--;
        if (tiempoHastaDesaparecer <= 0)
            desaparecer();
        desaparecerSiChocaElBorde();
        notificarObserver();
    }

    protected void mover() {
        moverHorizontalmente();
        moverVerticalmente();
    }

    protected void moverHorizontalmente() {
        if (estaChocandoBordeIzquierdo())
            direccion = ConstantesDireccion.DERECHA;
        else
            if (estaChocandoBordeDerecho())
                setDireccion(ConstantesDireccion.IZQUIERDA);
        setPosicion(x + VELOCIDAD * direccion, y);
    }

    protected void moverVerticalmente() {
        if (!estaSobrePlataforma() && !estaSobreElSuelo()) {
            y -= 10;
            setPosicion(x, y);
        }
    }

    protected void desaparecerSiChocaElBorde() {
        if (!chocoElBorde) {
            if (estaSobreElSuelo() && (estaChocandoBordeDerecho() || estaChocandoBordeIzquierdo())) {
                chocoElBorde = true;
                misSprites.setEstadoActual(ConstantesEstado.BOLAENMOVIMIENTO_CHOCA);
            }
        }
    }

    public boolean chocoElBorde(){
        return chocoElBorde;
    }

    public void desaparecer() {
        controladorBolaEnMovimiento.removeBolaEnMovimiento(this);
        super.desaparecer();
    }

    public void colisionar(Colisionable colisionable) {
        colisionable.afectar(this);
    }

    public void afectar(Jugador jugador) {
        if (!chocoElBorde)
            jugador.serChocadoBolaEnMovimiento(this);
        
    }

    public void afectar(Enemigo enemigo) {
        enemigo.serChocadoBolaEnMovimiento();
    }

    public void afectar(ProyectilAmigo proyectilAmigo) {}

    public void afectar(ProyectilEnemigo proyectilEnemigo) {}

    public void afectar(BolaEnMovimiento bolaEnMovimiento) {}
}