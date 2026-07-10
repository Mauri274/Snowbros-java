package Personajes.Enemigo;

import Fabricas.Sprites;
import Juego.ConstantesPuntaje;
import Juego.Puntaje;
import Juego.Controladores.ControladorEnemigo;

public abstract class Jefe extends Enemigo {

    public Jefe(int x, int y, Sprites sprites, Puntaje puntaje, ControladorEnemigo controladorEnemigo) {
        super(x, y, sprites, puntaje,  controladorEnemigo);
        cantPuntajeCongelar = ConstantesPuntaje.JEFE_CONGELADO;
        cantPuntajeEmpujar = ConstantesPuntaje.JEFE_EMPUJADO;
    }

    public void actualizar() {
        super.actualizar();
        parpadearSiEsNecesario();
    }
    
    public void recibirDanio(int danio) {
        if (vida - danio > 0){
            vida -= danio;
            parpadear(15);
        }

        else{
            vida = 0;
            puntaje.addPuntos(ConstantesPuntaje.JEFE_EMPUJADO);
            desaparecer();
        }
    }
}