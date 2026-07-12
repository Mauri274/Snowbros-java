package Proyectil;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEntidades;
import Juego.Controladores.ControladorProyectil;

import java.util.Random;

public class Bomba extends ProyectilEnemigo{

    protected final int DISTANCIA_MAXIMA = 25;
    protected final int DISTANCIA_MINIMA = 10;
    protected int distanciaARecorrer;
    protected final int VELOCIDAD_HORIZONTAL_INICIAL = 15;
    protected final int FUERZA_DE_SALTO_INICIAL = 10;
    protected int distanciaRecorrida;
    protected int velocidadVertical;
    protected Random generadorDistancia;

    public Bomba(int x, int y, Sprites sprites, int direccion, ControladorProyectil controladorProyectil) {
        super(x, y, sprites, direccion, controladorProyectil);
        velocidadHorizontal = VELOCIDAD_HORIZONTAL_INICIAL;
        distanciaRecorrida = 0;
        velocidadVertical = FUERZA_DE_SALTO_INICIAL;
        generadorDistancia = new Random();
        distanciaARecorrer = generadorDistancia.nextInt(DISTANCIA_MINIMA, DISTANCIA_MAXIMA);
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }

    public int getAnchoHitbox(){
        return ConstantesEntidades.BOMBA_HITBOX_ANCHO;
    }

    public int getAltoHitbox(){
        return ConstantesEntidades.BOMBA_HITBOX_ALTO;
    }

    public int getAnchoSprite(){
        return ConstantesEntidades.BOMBA_SPRITE_ANCHO;
    }

    public int getAltoSprite(){
        return ConstantesEntidades.BOMBA_SPRITE_ALTO;
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
        if (distanciaRecorrida < distanciaARecorrer)
            distanciaRecorrida++;

        if (distanciaRecorrida >= distanciaARecorrer / 3 && distanciaRecorrida < distanciaARecorrer - distanciaARecorrer / 4) {
            if (velocidadHorizontal > 2)
                velocidadHorizontal--;
            velocidadVertical--;
        }
        else 
            if (distanciaRecorrida >= distanciaARecorrer - distanciaARecorrer / 4) {
                if (velocidadHorizontal > 2) 
                    velocidadHorizontal -= 2;
                if (velocidadVertical > -15) 
                    velocidadVertical -= 4;
        }
    }

    public void desaparecer(){
        
        super.desaparecer();
    }

}