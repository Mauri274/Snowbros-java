package Estructura;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEntidades;
import Juego.ConstantesPuntaje;
import Juego.Puntaje;
import Juego.Controladores.ControladorPlataforma;
import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;
import Personajes.Personaje;
import Proyectil.ProyectilAmigo;

public class PlataformaMovil extends Plataforma{

    protected Puntaje puntaje;
    protected int velocidad;
    protected final int CANT_PUNTAJE = ConstantesPuntaje.PLATAFORMA_MOVIL;
    protected int xInicio;
    protected int xFin;
    protected int direccion;
    protected boolean usada;


    public PlataformaMovil(int xInicio, int xFin, int y, Sprites sprites, ControladorPlataforma controladorPlataforma, Puntaje puntaje) {
        super(xInicio, xFin, y, sprites, controladorPlataforma);
        anchoHitbox = ConstantesEntidades.PLATAFORMA_SPRITE_ANCHO;
        anchoSprite = anchoHitbox;
        this.xInicio = xInicio;
        this.xFin = xFin;
        this.puntaje = puntaje;
        velocidad = 2;
        direccion = ConstantesDireccion.DERECHA;
        usada = false;
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getDireccionActual() {
        return direccion;
    }
    
    public void actualizar() {
        mover();
    }

    protected void mover() {
        direccion = calcularDireccion();
        setPosicion(x + (velocidad * direccion), y);
    }

    protected int calcularDireccion() {
        if(x >= xFin){
            direccion = ConstantesDireccion.IZQUIERDA;
        }
        else if (x < xInicio){
            direccion = ConstantesDireccion.DERECHA;
        }
        return direccion;
    }

    public void afectar(Jugador jugador) {
        if(!usada){
            puntaje.addPuntos(CANT_PUNTAJE);
            usada = true;
        }
        aplicarEfectoAPersonaje(jugador);
        
    }

    public void afectar(ProyectilAmigo proyectilAmigo) {
        proyectilAmigo.desaparecer();
    }

    public void afectar(Enemigo enemigo) {
        aplicarEfectoAPersonaje(enemigo);
    }

    protected void aplicarEfectoAPersonaje(Personaje personaje) {
        personaje.setPosicion(personaje.getX() + velocidad * direccion, personaje.getY());
    }

}