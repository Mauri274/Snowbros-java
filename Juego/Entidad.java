package Juego;

import Fabricas.Sprites;
import Grafica.Observer;
import java.util.LinkedList;
import java.util.List;


public abstract class Entidad implements EntidadLogica {
    
    protected int x,y;
    protected Sprites misSprites;
    protected List<Observer> listaObservers;
    protected boolean esVisible;
    protected int tiempoParpadeoRestante = 0;
    protected int fotogramasParaSiguienteCambio = 0;
    protected final int VELOCIDAD_DE_PARPADEO = 5;
    
    public Entidad(int x, int y, Sprites sprites) {
        misSprites = sprites;
        this.x = x;
        this.y = y;
        listaObservers = new LinkedList<Observer>();
        esVisible = true;
    }
    public abstract int getAnchoSprite();
    
    public abstract int getAltoSprite();


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Sprites getSprites() {
        return misSprites;
    }

    public boolean esVisible(){
        return esVisible;
    }

    public void setSprites(Sprites sprites) {
        misSprites = sprites;
    }

    public void addObserver(Observer observer) {
        listaObservers.add(observer);
    }

    public void removeObserver(Observer observer) {
        listaObservers.remove(observer);
        observer.desaparecer();
    }

    public void notificarObserver(){
        for(Observer observer: listaObservers){
            observer.actualizar();
        }
    }

    public void desaparecer() {
        for (Observer observers : listaObservers)
            observers.desaparecer();
    }

    public void parpadear(int tiempoDeParpadeo) {
        tiempoParpadeoRestante = tiempoDeParpadeo;
        esVisible = true;
        fotogramasParaSiguienteCambio = VELOCIDAD_DE_PARPADEO;
    }

    protected void parpadearSiEsNecesario() {
        if (tiempoParpadeoRestante > 0) {
            tiempoParpadeoRestante--;
            fotogramasParaSiguienteCambio--;
                if (fotogramasParaSiguienteCambio <= 0) {
                    esVisible = !esVisible;
                    fotogramasParaSiguienteCambio = VELOCIDAD_DE_PARPADEO;
                }
            if (tiempoParpadeoRestante <= 0)
                esVisible = true;
        }
    }
}