package PowerUp;

import Fabricas.Sprites;
import Juego.ConstantesPuntaje;
import Juego.Puntaje;
import Juego.Controladores.ControladorPowerUp;
import Personajes.Jugador;

public class PocionAzul extends PowerUp{

    public PocionAzul(int x, int y, Sprites sprites, Puntaje puntaje, ControladorPowerUp controladorPowerUp){
        super(x, y, sprites, puntaje, controladorPowerUp);
        cantPuntaje = ConstantesPuntaje.POWER_UP;
    }
    
    public void afectar(Jugador jugador) {
        super.afectar(jugador);
        jugador.setVelocidad(jugador.getVelocidad() + 2);
    }
    
}
