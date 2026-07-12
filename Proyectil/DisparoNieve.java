package Proyectil;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEntidades;
import Juego.Controladores.ControladorProyectil;

public class DisparoNieve extends ProyectilAmigo{

    protected final int DISTANCIA_MAXIMA = 10;
    protected final int VELOCIDAD_HORIZONTAL_INICIAL = 25;
    protected final int FUERZA_DE_SALTO_INICIAL = 2;
    protected int distanciaRecorrida;
    protected int velocidadVertical;

    public DisparoNieve(int x, int y, Sprites sprites, int direccion, int danio, ControladorProyectil controladorProyectil) {
        super(x, y, sprites, direccion, danio, controladorProyectil);
        velocidadHorizontal = VELOCIDAD_HORIZONTAL_INICIAL;
        distanciaRecorrida = 0;
        velocidadVertical = FUERZA_DE_SALTO_INICIAL;
        setEstadoActual();
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }


    public int getAnchoHitbox(){
        return ConstantesEntidades.PROYECTIL_JUGADOR_HITBOX_ANCHO;
    }

    public int getAltoHitbox(){
        return ConstantesEntidades.PROYECTIL_JUGADOR_HITBOX_ALTO;
    }

    public int getAnchoSprite(){
        return ConstantesEntidades.PROYECTIL_JUGADOR_SPRITE_ANCHO;
    }

    public int getAltoSprite(){
        return ConstantesEntidades.PROYECTIL_SPRITE_ALTO;
    }

    public void actualizar() {
        if (getDireccion() == ConstantesDireccion.QUIETO_IZQUIERDA || getDireccion() == ConstantesDireccion.IZQUIERDA)
            setPosicion(x - velocidadHorizontal, y + velocidadVertical);
        else
            if (getDireccion() == ConstantesDireccion.QUIETO_DERECHA || getDireccion() == ConstantesDireccion.DERECHA)
                setPosicion(x + velocidadHorizontal, y + velocidadVertical);
        caer();

    }

    protected void caer() {
        if (distanciaRecorrida < DISTANCIA_MAXIMA)
            distanciaRecorrida++;

        if (distanciaRecorrida >= DISTANCIA_MAXIMA / 3 && distanciaRecorrida < DISTANCIA_MAXIMA - DISTANCIA_MAXIMA / 4) {
            if (velocidadHorizontal > 2)
                velocidadHorizontal--;
            velocidadVertical--;
        }
        else 
            if (distanciaRecorrida >= DISTANCIA_MAXIMA - DISTANCIA_MAXIMA / 4) {
                if (velocidadHorizontal > 2) 
                    velocidadHorizontal -= 2;
                if (velocidadVertical > -15) 
                    velocidadVertical -= 4;
        }
    }

    protected void setEstadoActual() {
        if (danio > 1) {
            misSprites.setEstadoActual(2);
        }
    }
}
