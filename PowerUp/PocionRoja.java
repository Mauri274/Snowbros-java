package PowerUp;

import Fabricas.Sprites;
import Juego.ConstantesPuntaje;
import Juego.Puntaje;
import Juego.Controladores.ControladorPowerUp;
import Personajes.Jugador;

public class PocionRoja extends PowerUp {

    public PocionRoja(int x, int y, Sprites sprites, Puntaje puntaje, ControladorPowerUp controladorPowerUp){
        super(x, y, sprites, puntaje, controladorPowerUp);
        cantPuntaje = ConstantesPuntaje.POWER_UP;
    }
    
    public void afectar(Jugador jugador) {
        super.afectar(jugador);
        jugador.setDanio(jugador.getDanio() * 2);
    }

}