package PowerUp;

import Fabricas.Sprites;
import Juego.ConstantesPuntaje;
import Juego.Puntaje;
import Juego.Controladores.ControladorPocionVerde;
import Juego.Controladores.ControladorPowerUp;
import Personajes.Jugador;
import Personajes.Enemigo.Enemigo;

public class PocionVerde extends PowerUp{

    protected ControladorPocionVerde controladorPocionVerde;

    public PocionVerde(int x, int y, Sprites sprites, Puntaje puntaje, ControladorPowerUp controladorPowerUp, ControladorPocionVerde controladorPocionVerde){
        super(x, y, sprites, puntaje, controladorPowerUp);
        this.controladorPocionVerde = controladorPocionVerde;
        cantPuntaje = ConstantesPuntaje.POWER_UP;
    }

    public void afectar(Jugador jugador) {
        super.afectar(jugador);
        for(Enemigo enemigo: controladorPocionVerde.getEnemigos())
            enemigo.aturdir();
    }
    
}
