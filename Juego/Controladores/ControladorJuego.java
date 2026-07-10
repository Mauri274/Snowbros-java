package Juego.Controladores;

import Juego.Puntaje;

public interface ControladorJuego {
    
    public void moverAJugador(int direccion);
    public void saltarAJugador();
    public void detenerMovimientoJugador(int tecla);
    public void dispararAJugador();
    public void detenerDisparoJugador();
    public void setNombreJugador(String nombreJugador);
    public void setModoJuego(String modoJuego);
    public void setDominio(String dominio);
    public void iniciarPartida();
    public void guardarPuntajeFinal(String nombre);
    public Puntaje getPuntaje();
}