package Personajes;

import Estados.EstadoJugador.DentroBola;
import Estados.EstadoJugador.EstadoJugador;
import Estados.EstadoJugador.Muriendo;
import Estados.EstadoJugador.Quieto;
import Estados.EstadoJugador.Reapareciendo;
import Fabricas.Sprites;
import Grafica.ObserverVida;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEntidades;
import Grafica.Constantes.ConstantesVistas;
import Juego.Controladores.ControladorJugador;
import Juego.Controladores.ControladorVida;
import Personajes.Enemigo.Enemigo;
import Proyectil.ProyectilAmigo;
import Proyectil.ProyectilEnemigo;
import Visitor.Colisionable;


public class Jugador extends Personaje implements ControladorVida {

    protected String nombre;
    protected static final int DANIO_BASE = 1;
    protected static final int VELOCIDAD_BASE_JUGADOR = 5;
    protected static final int VELOCIDAD_MAXIMA_JUGADOR = 10;
    protected static final int TIEMPO_INVENCIBILIDAD = 150;
    protected int danioActual;
    protected EstadoJugador estadoJugador;
    protected ControladorJugador controladorJugador;
    protected int tiempoRalentizado;
    protected int velocidadPrevia;
    protected boolean ralentizado;

    protected ObserverVida observerVida;

    protected boolean invencible = false;
    protected int tiempoRestanteInvencibilidad;
    
    public Jugador(int x, int y, Sprites sprites) {
        super(x, y, sprites);
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
        velocidad = VELOCIDAD_BASE_JUGADOR;
        danioActual = DANIO_BASE;
        vida = 3;
        estadoJugador = new Quieto(this);
        tiempoRalentizado = 0;
        ralentizado = false;
    }

    public int getVelocidadBase(){
        return VELOCIDAD_BASE_JUGADOR;
    }

    public int getAnchoSprite() {
        return ConstantesEntidades.JUGADOR_SPRITE_ANCHO;
    }

    public int getAltoSprite() {
        return ConstantesEntidades.JUGADOR_SPRITE_ALTO;
    }

    public int getAnchoHitbox(){
        return ConstantesEntidades.JUGADOR_HITBOX_ANCHO;
    }

    public int getAltoHitbox(){
        return ConstantesEntidades.JUGADOR_HITBOX_ALTO;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVidas() {
        return vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getVelocidadPrevia(){
        return velocidadPrevia;
    }

    public int getDanio(){
        return danioActual;
    }

    public int getDanioBase(){
        return DANIO_BASE;
    }

    public ControladorJugador getControladorJugador() {
        return controladorJugador;
    }
    
    public int getTiempoRalentizacion(){
        return tiempoRalentizado;
    }

    public void setVelocidadPrevia(int velocidadPrevia){
        this.velocidadPrevia = velocidadPrevia;
    }

    public void setTiempoRalentizado(int tiempo){
        tiempoRalentizado = tiempo;
    }

    public void setControladorJugador(ControladorJugador controladorJugador) {
        this.controladorJugador = controladorJugador;
    }

    public void setVelocidad(int velocidad){
        if(velocidad > VELOCIDAD_MAXIMA_JUGADOR)
            this.velocidad = VELOCIDAD_MAXIMA_JUGADOR;
        else
            this.velocidad = velocidad;
    }

    public void setDanio(int danioNuevo){
        this.danioActual = danioNuevo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstadoActual(EstadoJugador estadoJugador) {
        this.estadoJugador = estadoJugador;
    }

    public void moverADireccion(int direccion){
        this.direccion = direccion;
        estadoJugador.mover();
    }

    public void detenerMovimiento(int tecla){
        if (direccion == tecla) {
            if (direccion == ConstantesDireccion.DERECHA)
                direccion = ConstantesDireccion.QUIETO_DERECHA;
            else
                if (direccion == ConstantesDireccion.IZQUIERDA)
                    direccion = ConstantesDireccion.QUIETO_IZQUIERDA;
            estadoJugador.detenerMovimiento();
        }
    }

    public void detenerDisparo() {
        estadoJugador.detenerDisparo();
    }

    public void actualizar() {
        estadoJugador.actualizar();
        actualizarInvencibilidad();
        controlarRalentizacion();
        parpadearSiEsNecesario();
        notificarObserver();
    }

    public void ralentizar(){
        if(!ralentizado){
            velocidadPrevia = velocidad;
            velocidad /= 2;
            ralentizado = true;
        }
    }

    protected void controlarRalentizacion(){
        if(tiempoRalentizado > 0){
            ralentizar();
            tiempoRalentizado --;
        }
        else if(ralentizado){
            ralentizado = false;
            velocidad = velocidadPrevia;
        }
    }

    public void disparar(){
        estadoJugador.disparar();
    }

    public void empujarBola(Enemigo Bola){
        estadoJugador.empujarBola(Bola);
    }

    public void aumentarVida(){
        vida += 1;
        notificarObserverVida();
    }

    public void saltar(){
        estadoJugador.saltar();
    }

    public void actualizarInvencibilidad() {
        if (invencible) {
            tiempoRestanteInvencibilidad--;
            if (tiempoRestanteInvencibilidad <= 0) {
                invencible = false;}
        }
    }

    public void recibirDanio(){
        if (!invencible) {
            vida--;
            notificarObserverVida();
            hacerInvencibleTemporalmente();
            if (vida > 0) {
                reaparecer();
                controladorJugador.morirJugador();
            }
            else
                morirDefinitivamente();
        }
    }
    
    public void desaparecer() {
        observerVida.desaparecer();
        super.desaparecer();
    }

    public void colisionar(Colisionable colisionable) {
        colisionable.afectar(this);
    }

    public void afectar(Jugador jugador) {}

    public void afectar(Enemigo enemigo){
        separarBloqueando(this, enemigo);
    }

    public void afectar(ProyectilAmigo proyectilAmigo) {}

    public void afectar(ProyectilEnemigo proyectilEnemigo) {
        proyectilEnemigo.desaparecer();
    }

    public void afectar(BolaEnMovimiento bolaEnMovimiento) {
    }

    public void aparecerEnPiso() {
        setPosicion(ConstantesVistas.X_INICIO_JUGADOR, ConstantesVistas.Y_INICIO_JUGADOR);
        hacerInvencibleTemporalmente();
    }

    protected void reaparecer(){
        this.setEstadoActual(new Reapareciendo(this));
    }

    protected void morirDefinitivamente(){
        this.setEstadoActual(new Muriendo(this));
    }

    public void addObserverVida(ObserverVida observerVida) {
        this.observerVida = observerVida;
    }

    public void notificarObserverVida() {
        observerVida.actualizar();
    }

    protected void hacerInvencibleTemporalmente() {
            invencible = true;
            tiempoRestanteInvencibilidad = TIEMPO_INVENCIBILIDAD;
            parpadear(TIEMPO_INVENCIBILIDAD);
    }

    public void serChocadoBolaEnMovimiento(BolaEnMovimiento bolaEnMovimiento) {
        this.setEstadoActual(new DentroBola(this, bolaEnMovimiento));
        hacerInvencibleTemporalmente();
    }
}