package Personajes.Enemigo;

import Estados.EstadoEnemigo.EstadoEnemigo;
import Estados.EstadoEnemigo.Quieto;
import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEntidades;
import Juego.Puntaje;
import Juego.Controladores.ControladorEnemigo;
import Personajes.BolaEnMovimiento;
import Personajes.Jugador;
import Personajes.Personaje;
import Proyectil.ProyectilAmigo;
import Proyectil.ProyectilEnemigo;
import Visitor.Colisionable;
import java.awt.Rectangle;
import java.util.Random;


public abstract class Enemigo extends Personaje{
    
    public Random generadorRandom = new Random();
    protected int cantPuntajeCongelar;
    protected int cantPuntajeEmpujar;
    protected int timerQuieto;
    protected int vidaMaxima;
    protected int timerDecision;
    protected int tiempoDecisionBase;
    protected int tiempoDecisionRango;
    protected boolean estaPensando;
    protected Puntaje puntaje;
    protected EstadoEnemigo estadoEnemigo;
    protected ControladorEnemigo controladorEnemigo;
    protected int velocidadAtaque;

    protected final int VELOCIDAD_BASE_ENEMIGO =  2;
    protected final int TIEMPO_QUIETO_DEFINIDO = 60 * 1;
    protected final int TIEMPO_DECISION_BASE = 60 * 1;
    protected final int TIEMPO_DECISION_RANGO = 60 * 3;


    public Enemigo(int x, int y, Sprites sprites, Puntaje puntaje, ControladorEnemigo controladorEnemigo) {
        super(x, y, sprites);
        this.puntaje = puntaje;
        this.controladorEnemigo = controladorEnemigo;
        this.direccion = ConstantesDireccion.QUIETO_DERECHA;
        this.estadoEnemigo = new Quieto(this);
        this.velocidad = VELOCIDAD_BASE_ENEMIGO;
        this.tiempoDecisionBase = TIEMPO_DECISION_BASE;
        this.tiempoDecisionRango = TIEMPO_DECISION_RANGO;
        this.estaPensando = true;
        this.timerQuieto = TIEMPO_QUIETO_DEFINIDO;
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }

    public int getPuntajeCongelar() {
        return cantPuntajeCongelar;
    }

    public int getPuntajeEmpujar() {
        return cantPuntajeEmpujar;
    }

    public Puntaje getPuntaje() {
        return puntaje;
    }

    protected abstract void tomarDecisionAleatoria();

    public abstract void crearProyectil();

    protected void reiniciarTimerDecision() {
        this.timerDecision = tiempoDecisionBase + generadorRandom.nextInt(tiempoDecisionRango);
    }

    public void actualizarLogicaDecision() {
        if (estaPensando){
            timerQuieto--;
            if (timerQuieto <= 0) {
                estaPensando = false;
                tomarDecisionAleatoria();
                reiniciarTimerDecision();
            }
        }
        else{
            timerDecision--;
            if (timerDecision <= 0) {
                estaPensando = true;
                timerQuieto = TIEMPO_QUIETO_DEFINIDO;
                this.estadoEnemigo.detener();
            }
        }
        
    }

    public void actualizar() {
        estadoEnemigo.actualizar();
        estadoEnemigo.actualizarDecision();
        notificarObserver();
    }

    public void serChocadoBolaEnMovimiento() {
        desaparecer();
        generarPowerUp();
    }

    public void subirEscalera(int alturaVertical) {
        estadoEnemigo.subirEscalera(alturaVertical);
    }

    public int getAnchoSprite() {
        return ConstantesEntidades.ENEMIGO_SPRITE_ANCHO;
    }

    public int getAltoSprite() {
        return ConstantesEntidades.ENEMIGO_SPRITE_ALTO;
    }

    public int getAltoHitbox(){
        return ConstantesEntidades.ENEMIGO_HITBOX_ALTO;
    }

    public int getAnchoHitbox(){
        return ConstantesEntidades.ENEMIGO_HITBOX_ANCHO;
    }

    public ControladorEnemigo getControladorEnemigo() {
        return controladorEnemigo;
    }
    
    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setEstadoActual(EstadoEnemigo estadoEnemigo) {
        this.estadoEnemigo = estadoEnemigo;
    }

    public void recibirDanio(int danio) {
        if (vida - danio > 0)
            vida -= danio;
        else{
            vida = 0;
        }
    }

    public void aumentarVida(){
        if (vida < vidaMaxima)
            vida++;
    }

    public void afectar(Jugador jugador) {
        estadoEnemigo.afectar(jugador);
    }

    public void afectar(Enemigo enemigo) {
        if(enemigo != this){
            separarMutuo(this, enemigo);
            rebotar(enemigo);
            enemigo.setDireccion(direccion*-1);
            estadoEnemigo.afectar(enemigo);
        }
    }

    protected void rebotar(Enemigo enemigo){
        Rectangle miHitbox = this.getHitbox();
        Rectangle otraHitbox = enemigo.getHitbox();

        if(miHitbox.getCenterX() < otraHitbox.getCenterX()){
            setDireccion(ConstantesDireccion.IZQUIERDA);
            enemigo.setDireccion(ConstantesDireccion.DERECHA);
        }
        else{
            setDireccion(ConstantesDireccion.DERECHA);
            enemigo.setDireccion(ConstantesDireccion.IZQUIERDA);
        }
    }

    public void afectar(ProyectilAmigo proyectilAmigo) {
        proyectilAmigo.desaparecer();
    }

    public void afectar(ProyectilEnemigo proyectilEnemigo) {}

    public void afectar(BolaEnMovimiento bolaEnMovimiento) {}

    public void desaparecer() {
        controladorEnemigo.removeEnemigo(this);
        super.desaparecer();
    }

    public void mover(){
        estadoEnemigo.mover();
    }

    public void aturdir(){
        estadoEnemigo.aturdir();
    }

    public void morir(Enemigo enemigo) {
        controladorEnemigo.removeEnemigo(enemigo);
    }

    public void saltar(){
        estadoEnemigo.saltar();
    }

    public void colisionar(Colisionable colisionable) {
        colisionable.afectar(this);
    }

    public void empujar(int direccion){
        estadoEnemigo.empujar(direccion);
    }

    public void detener(){
        estadoEnemigo.detener();
    }

    public int getVelocidadAtaque(){
        return velocidadAtaque;
    }

    public void hacerBolaEnMovimiento() {}

    protected void generarPowerUp() {
        if(generadorRandom.nextBoolean()){
            int numeroAleatorio = generadorRandom.nextInt(1, 11);
            controladorEnemigo.addPowerUp(x, y + 10, numeroAleatorio);
        }
    }

    public void disparar(){}

    public void setControladorEnemigo(ControladorEnemigo controladorEnemigo) {
        this.controladorEnemigo = controladorEnemigo;
    };

    public void chocarPared(int direccion, int xPared){
        if(direccion == ConstantesDireccion.IZQUIERDA)
            chocarParedIzquierda(xPared);
        else
            if(direccion == ConstantesDireccion.DERECHA)
                chocarParedDerecha(xPared);
        setDireccion(this.direccion*-1);
    }

    protected void chocarParedIzquierda(int xPared) {
        setPosicion(xPared - getAnchoSprite() + ((getAnchoSprite()-getAnchoHitbox())/2) - 1, getY());
    }

    protected void chocarParedDerecha(int xPared) {
        setPosicion(xPared + getAnchoSprite() - ((getAnchoSprite() - getAnchoHitbox()) / 2) + 1, getY());
    }

}