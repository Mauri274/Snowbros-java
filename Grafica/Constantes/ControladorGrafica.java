package Grafica.Constantes;

import Fabricas.Sprites;
import Grafica.Observer;
import Grafica.ObserverGraficoNieve;
import Grafica.ObserverPuntaje;
import Grafica.ObserverVida;
import Juego.EntidadLogica;
import Juego.Puntaje;
import Personajes.Jugador;

public interface ControladorGrafica {

    public void mostrarPanelPantallaModoJuego();
    public void mostrarPanelPantallaMenuPrincipal();
    public void mostrarPanelPantallaJuego();
    public void mostrarPanelPantallaMostrarRanking();
    public void mostrarPanelPantallaRanking(boolean gano);
    public void mostrarPanelPantallaDominio();
    public void mostrarPanelPantallaGameOver();
    public void mostrarPanelPantallaVictoria();
    public void mostrarPanelPantallaInicio();


    public Observer registrarEntidad(EntidadLogica entidadLogica, int zOrder);
    public Observer registrarFondo(EntidadLogica entidadLogica);
    public ObserverGraficoNieve registrarNieve(EntidadLogica enemigo, Sprites sprites);
    public ObserverVida registrarObserverVida(Jugador jugador);
    public ObserverPuntaje registrarPuntaje(Puntaje puntaje);

    public void actualizarNombrePiso(String nombre);
    public void actualizarTiempo(String tiempo);

}