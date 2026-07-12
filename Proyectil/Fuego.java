package Proyectil;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesVistas;
import Juego.Controladores.ControladorProyectil;

public class Fuego extends ProyectilEnemigo{

    public Fuego(int x, int y, Sprites sprites, int direccion, ControladorProyectil controladorProyectil) {
        super(x, y, sprites, direccion, controladorProyectil);
        velocidadHorizontal = 7;
    }

    public void actualizar() {
        if(!chocoConPared()){
            if (getDireccion() == ConstantesDireccion.QUIETO_IZQUIERDA || getDireccion() == ConstantesDireccion.IZQUIERDA){
                setPosicion(x - velocidadHorizontal, y);
            }
            else
                if (getDireccion() == ConstantesDireccion.QUIETO_DERECHA || getDireccion() == ConstantesDireccion.DERECHA){
                    setPosicion(x + velocidadHorizontal, y);
                }
        }
        else{
            desaparecer();
        }
    }

    protected  boolean chocoConPared(){
        return x >= ConstantesVistas.PANEL_PISO_ANCHO || x <= 0;
    }
}
