package PowerUp;

import Fabricas.Sprites;
import Juego.ConstantesPuntaje;
import Juego.Puntaje;
import Juego.Controladores.ControladorPowerUp;
import Personajes.Jugador;

public class Fruta extends PowerUp{

    public Fruta(int x, int y, Sprites sprites, Puntaje puntaje, ControladorPowerUp controladorPowerUp){
        super(x, y, sprites, puntaje, controladorPowerUp);
        this.cantPuntaje = ConstantesPuntaje.FRUTA;

    }

    public void afectar(Jugador jugador) {
        super.afectar(jugador);
    }
}