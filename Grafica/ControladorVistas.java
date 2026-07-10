package Grafica;

public interface ControladorVistas {

    public void solicitarInicioJuego();
    public void refrescar();
    public void mostrarAlerta(String mensaje);
    public void guardarPuntajeFinal(String nombre);
    
    public void setNombreJugador(String nombre);
    public void setDominio(String dominio);
    public void setModoJuego(String modoJuego);

    public void mostrarPanelPantallaRanking(boolean gano);
    public void mostrarPanelPantallaModoJuego();
    public void mostrarPanelPantallaDominio();
    public void mostrarPanelPantallaMenuPrincipal();
    public void mostrarPanelPantallaMostrarRanking();
    public void mostrarPanelPantallaVictoria();
    public void mostrarPanelPantallaGameOver();
}