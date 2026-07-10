package Juego;

import Estructura.Plataforma;
import Visitor.Colisionable;
import Visitor.Colisionador;
import java.util.ArrayList;
import java.util.List;

public class Celda {
    protected List<Colisionador> listaColisionadores;
    protected List<Colisionable> listaColisionables;
    protected List<Plataforma> listaPlataformas;
    

    public Celda() {
        listaColisionadores = new ArrayList<Colisionador>();
        listaColisionables = new ArrayList<Colisionable>();
        listaPlataformas = new ArrayList<Plataforma>();
    }

    public List<Colisionador> getListaColisionadores() {
        return listaColisionadores;
    }

    public List<Colisionable> getListaColisionables() {
        return listaColisionables;
    }

    public List<Plataforma> getListaPlataformas() {
        return listaPlataformas;
    }

    public void addColisionables(Colisionable colisionable) {
        listaColisionables.add(colisionable);
    }
    public void addColisionador(Colisionador colisionador) {
        listaColisionadores.add(colisionador);
    }

    public void addPlataforma(Plataforma plataforma) {
        listaPlataformas.add(plataforma);
    }

    public void limpiarCelda() {
        listaColisionadores.clear();
        listaColisionables.clear();
        listaPlataformas.clear();
    }
}