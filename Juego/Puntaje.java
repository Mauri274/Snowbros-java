package Juego;
import Grafica.ObserverPuntaje;

public class Puntaje{
    
    protected int cant;
    protected ObserverPuntaje observerPuntaje;

    public Puntaje(){
        cant = 0;
    }

    public int getPuntos(){
        return cant;
    }

    public void addPuntos(int puntos){
        cant += puntos;
        notificarObserverPuntaje();
    }

    public void desaparecer() {
        observerPuntaje.desaparecer();
    }

    public void registrarObserverPuntaje(ObserverPuntaje observerPuntaje) {
        this.observerPuntaje = observerPuntaje;
    }

    public void notificarObserverPuntaje() {
        observerPuntaje.actualizar();
    }
}