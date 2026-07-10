package Grafica;

import Juego.EntidadLogica;
import Juego.Juego;
import Juego.ModoDeJuego;
import Juego.Puntaje;
import Juego.Ranking;
import Juego.Controladores.ControladorJuego;
import Personajes.Jugador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Fabricas.Sprites;
import Grafica.Constantes.ConstantesZOrder;
import Grafica.Constantes.ControladorGrafica;

public class GUI implements ControladorVistas, ControladorGrafica{

    protected JFrame ventana;
    protected ControladorJuego controladorJuego;
    
    protected PanelPantallaJuego panelPantallaJuego;
    protected PanelPantallaModoJuego panelPantallaModoJuego;
    protected PanelPantallaMenuPrincipal panelPantallaMenuPrincipal;
    protected PanelPantallaRanking panelPantallaRanking;
    protected PanelPantallaDominio panelPantallaDominio;
    protected PanelPantallaGameOver panelPantallaGameOver;
    protected PanelPantallaVictoria panelPantallaVictoria;
    protected PanelPantallaInicio panelPantallaInicio;
    protected PanelPantallaMostrarRanking panelPantallaMostrarRanking;


    public GUI(ControladorJuego controladorJuego) {
        this.controladorJuego = controladorJuego;
        panelPantallaModoJuego = new PanelPantallaModoJuego(this);
        panelPantallaMenuPrincipal = new PanelPantallaMenuPrincipal(this);
        panelPantallaRanking = new PanelPantallaRanking(this);
        panelPantallaDominio = new PanelPantallaDominio(this);
        panelPantallaInicio = new PanelPantallaInicio(this);
        panelPantallaMostrarRanking = new PanelPantallaMostrarRanking(this);
        panelPantallaVictoria = new PanelPantallaVictoria(this);
        panelPantallaGameOver = new PanelPantallaGameOver(this);
        panelPantallaJuego = new PanelPantallaJuego(this, controladorJuego);
        configurarVentana();
    }

    protected void configurarVentana() {
		ventana = new JFrame("SnowBros");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
	}

    public void setNombreJugador(String nombreJugador) {
        controladorJuego.setNombreJugador(nombreJugador);
    }

    public void setDominio(String dominio){
        controladorJuego.setDominio(dominio);
    }

    public void setModoJuego(String modoJuego){
        controladorJuego.setModoJuego(modoJuego);
    }

    public Observer registrarFondo(EntidadLogica fondo) {
		Observer observerFondo = panelPantallaJuego.incorporarFondo(fondo);
		return observerFondo;
	}

    public Observer registrarEntidad(EntidadLogica entidadLogica, int zOrder) {
        Observer observerEntidad = panelPantallaJuego.incorporarEntidad(entidadLogica, zOrder);
        return observerEntidad;
    }

    public ObserverGraficoNieve registrarNieve(EntidadLogica enemigo, Sprites sprites) {
        ObserverGraficoNieve observerNieve = panelPantallaJuego.incorporarNieve(enemigo, ConstantesZOrder.NIEVE_ENEMIGO, sprites);
        return observerNieve;
    }

    public ObserverVida registrarObserverVida(Jugador jugador) {
        ObserverVida observerVida = panelPantallaJuego.incorporarObserverVida(jugador);
        return observerVida;
    }

    public ObserverPuntaje registrarPuntaje(Puntaje puntaje) {
        ObserverPuntaje observerPuntaje = panelPantallaJuego.incorporarPuntaje(puntaje);
        return observerPuntaje;
    }

    public void mostrarPanelPantallaModoJuego() {
        ventana.setContentPane(panelPantallaModoJuego);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        refrescar();
    }

    public void mostrarPanelPantallaJuego() {
        ventana.setContentPane(panelPantallaJuego);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        refrescar();
    }

    public void mostrarPanelPantallaVictoria() {
        ventana.setContentPane(panelPantallaVictoria);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        refrescar();
    }

    public void mostrarPanelPantallaMenuPrincipal() {
        ventana.setContentPane(panelPantallaMenuPrincipal);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        refrescar();
    }

    public void mostrarPanelPantallaInicio(){
        ventana.setContentPane(panelPantallaInicio);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        refrescar();
    }

    public void mostrarPanelPantallaMostrarRanking() {
        ModoDeJuego modoActual = ((Juego)controladorJuego).getModoActual();
        
        String nombreModo = modoActual.getNombreModo();
        Ranking ranking = modoActual.getRanking();
        
        String titulo = "RANKING - " + nombreModo.toUpperCase();
        List<String> puntajes;
        
        try {
            ranking.cargar(nombreModo);
            puntajes = ranking.getTop5ComoStrings();
        } catch (IOException e) {
            System.err.println("Aviso al cargar el ranking (el archivo puede no existir): " + e.getMessage());
            puntajes = new ArrayList<>();
        }
        
        panelPantallaMostrarRanking.mostrarPuntajes(titulo, puntajes);
        ventana.setContentPane(panelPantallaMostrarRanking);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        refrescar();
    }

    public void mostrarPanelPantallaRanking(boolean gano) {
        panelPantallaRanking.gano(gano);
        ventana.setContentPane(panelPantallaRanking);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        refrescar();
    }

    public void mostrarPanelPantallaDominio() {
        ventana.setContentPane(panelPantallaDominio);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        refrescar();
    }

    public void mostrarPanelPantallaGameOver() {
        ventana.setContentPane(panelPantallaGameOver);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        refrescar();
    }

    public void refrescar() {
		ventana.revalidate();
		ventana.repaint();
	}

    public void mostrarAlerta(String mensaje) {
        JOptionPane.showMessageDialog(ventana, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public void solicitarInicioJuego() {
        controladorJuego.iniciarPartida();
    }

    public void actualizarTiempo(String tiempo) {
        panelPantallaJuego.actualizarTiempo(tiempo);
    }

    public void actualizarNombrePiso(String nombre) {
        panelPantallaJuego.actualizarNombrePiso(nombre);
    }

    public void guardarPuntajeFinal(String nombre) {
        controladorJuego.guardarPuntajeFinal(nombre);
    }
}