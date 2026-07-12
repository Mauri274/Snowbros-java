package PowerUp;

import Fabricas.Sprites;
import Grafica.AjustarPosicionEnPantalla;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEntidades;
import Grafica.Constantes.ConstantesSonido;
import Juego.EntidadColisionable;
import Juego.GestorSonido;
import Juego.Puntaje;
import Juego.Controladores.ControladorPowerUp;
import Personajes.BolaEnMovimiento;
import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;
import Proyectil.ProyectilAmigo;
import Proyectil.ProyectilEnemigo;
import Visitor.Colisionable;

public abstract class PowerUp extends EntidadColisionable implements Colisionable{

    protected static final int TIEMPO_VIDA_DEFAULT = 30 * 10;
    protected int tiempoSpawn;
    protected int cantPuntaje;
    protected Puntaje puntaje;
    protected ControladorPowerUp controladorPowerUp;

    public PowerUp(int x, int y, Sprites sprites, Puntaje puntaje, ControladorPowerUp controladorPowerUp) {
        super(x, y, sprites);
        this.puntaje = puntaje;
        this.tiempoSpawn = TIEMPO_VIDA_DEFAULT;
        this.controladorPowerUp = controladorPowerUp;
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }

    public int getAnchoSprite() {
        return ConstantesEntidades.POWERUP_SPRITE_ANCHO;
    }

    public int getAltoSprite() {
        return ConstantesEntidades.POWERUP_SPRITE_ALTO;
    }

    public int getAnchoHitbox(){
        return ConstantesEntidades.POWERUP_HITBOX_ANCHO;
    }

    public int getAltoHitbox(){
        return ConstantesEntidades.POWERUP_HITBOX_ALTO;
    }

    public int getDireccion () {
        return ConstantesDireccion.IZQUIERDA;
    }

    public void actualizar(){
        parpadearSiEsNecesario();
        actualizarLogicaDespawn();
        notificarObserver();
    }
    
    public void afectar(Jugador jugador){
        puntaje.addPuntos(this.cantPuntaje);
        this.desaparecer();
        GestorSonido.getGestorSonido().reproducirSonido(ConstantesSonido.POWER_UP);
    }

    public void afectar(Enemigo enemigo) {}
    public void afectar(ProyectilAmigo proyectilAmigo) {}
    public void afectar(ProyectilEnemigo proyectilEnemigo) {}
    public void afectar(BolaEnMovimiento bolaEnMovimiento) {}
    
    protected int ajustarHitboxX(int x, int anchoHitbox, int anchoSprite) {
        return AjustarPosicionEnPantalla.ajustarHitboxXCentrado(x, anchoHitbox, anchoSprite);
    }

    protected int ajustarHitboxY(int y, int altoHitbox, int altoSprite) {
        return AjustarPosicionEnPantalla.ajustarHitboxYCentrado(y, altoHitbox, altoSprite);
    }

    public void desaparecer() {
        super.desaparecer();
        controladorPowerUp.removePowerUp(this);
    }

    protected void actualizarLogicaDespawn() {
        tiempoSpawn--;
        if (tiempoSpawn == TIEMPO_VIDA_DEFAULT / 3)
            parpadear(tiempoSpawn);
        if (tiempoSpawn <= 0)
            this.desaparecer();
    }
}