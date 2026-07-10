package Estructura;

import Fabricas.Sprites;
import Juego.ConstantesPuntaje;
import Juego.Puntaje;
import Juego.Controladores.ControladorPlataforma;
import Personajes.Jugador;
import Personajes.Enemigo.Enemigo;

public class PlataformaQuebradiza extends Plataforma{

    protected Puntaje puntaje;
    protected final int CANT_PUNTAJE = ConstantesPuntaje.PLATAFORMA_QUEBRADIZA;
    protected boolean estaQuebrandose = false;
    protected int tiempoParaRomperse;
    protected final int TIEMPO_QUEBRAR_TICKS = 50;


    public PlataformaQuebradiza(int xInicio, int xFin, int y, Sprites sprites, ControladorPlataforma controladorPlataforma, Puntaje puntaje) {
        super(xInicio, xFin, y, sprites, controladorPlataforma);
        this.puntaje = puntaje;
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }

    public void romperse() {
        puntaje.addPuntos(CANT_PUNTAJE);
        desaparecer();
    }

    protected void iniciarQuebrar() {
        if (!estaQuebrandose) {
            estaQuebrandose = true;
            parpadear(TIEMPO_QUEBRAR_TICKS);
            tiempoParaRomperse = TIEMPO_QUEBRAR_TICKS;
        }
    }

    public void actualizar() {
        parpadearSiEsNecesario();
        if (estaQuebrandose) {
            tiempoParaRomperse--;
            if (tiempoParaRomperse <= 0)
                romperse();
        }
        notificarObserver();
    }

    public void afectar(Jugador jugador) {
        iniciarQuebrar();
    }

    public void afectar(Enemigo enemigo) {}

}