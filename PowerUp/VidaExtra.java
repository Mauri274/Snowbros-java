package PowerUp;

import Fabricas.Sprites;
import Juego.Puntaje;
import Juego.Controladores.ControladorPowerUp;
import Personajes.Jugador;

public class VidaExtra extends PowerUp{

    public VidaExtra(int x, int y, Sprites sprites, Puntaje puntaje, ControladorPowerUp controladorPowerUp){
        super(x, y, sprites, puntaje, controladorPowerUp);
        this.cantPuntaje = 0;
    }

    public void afectar(Jugador jugador) {
        super.afectar(jugador);
        jugador.aumentarVida();
    }

}