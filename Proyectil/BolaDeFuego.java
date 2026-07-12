package Proyectil;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Juego.Controladores.ControladorProyectil;

public class BolaDeFuego extends ProyectilEnemigo{

    protected final int DISTANCIA_MAXIMA = 300;
    protected final int VELOCIDAD_HORIZONTAL_INICIAL = 8;
    protected int distanciaRecorrida;
    protected int distanciaProyectil;

    public BolaDeFuego(int x, int y, Sprites sprites, int direccion, ControladorProyectil controladorProyectil) {
        super(x, y, sprites, direccion, controladorProyectil);
        distanciaRecorrida = 0;
        velocidadHorizontal = VELOCIDAD_HORIZONTAL_INICIAL;
        distanciaProyectil = DISTANCIA_MAXIMA;
    }

    public void actualizar() {
        if(distanciaRecorrida < distanciaProyectil){
            if (getDireccion() == ConstantesDireccion.QUIETO_IZQUIERDA || getDireccion() == ConstantesDireccion.IZQUIERDA){
                setPosicion(x - velocidadHorizontal, y);
            }
            else
                if (getDireccion() == ConstantesDireccion.QUIETO_DERECHA || getDireccion() == ConstantesDireccion.DERECHA){
                    setPosicion(x + velocidadHorizontal, y);
                }
            distanciaRecorrida = distanciaRecorrida+velocidadHorizontal;
        }
        else{
            desaparecer();
        }
        
    }
    
}