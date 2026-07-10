package ManejadoresEntidades;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Estructura.Plataforma;
import Grafica.Constantes.ConstantesZOrder;
import Juego.GrillaColisiones;
import Juego.Piso;
import Personajes.Enemigo.Calabaza;
import Personajes.Enemigo.Enemigo;
import Personajes.Enemigo.EnemigoIndestructible;
import Personajes.Enemigo.Fantasma;

public class ManejadorEnemigos {

    protected Piso piso;
    protected List<Enemigo> listaEnemigos;
    protected List<EnemigoIndestructible> enemigosIndestructibles;
    protected Stack<Enemigo> enemigosEnEsperaAEliminar;
    protected Stack<Enemigo> enemigosPorAgregar;
    protected Stack<EnemigoIndestructible> indestructiblesPorAgregar;
    protected Stack<EnemigoIndestructible> indestructiblesAEliminar;

    public ManejadorEnemigos(Piso piso) {
        this.piso = piso;
        listaEnemigos = new LinkedList<Enemigo>();
        enemigosIndestructibles = new LinkedList<EnemigoIndestructible>();
        indestructiblesPorAgregar = new Stack<EnemigoIndestructible>();
        indestructiblesAEliminar = new Stack<EnemigoIndestructible>();
        enemigosEnEsperaAEliminar = new Stack<Enemigo>();
        enemigosPorAgregar = new Stack<Enemigo>();
    }

    public List<Enemigo> getEnemigos() {
        return listaEnemigos;
    }

    public void add(Enemigo enemigo) {
        listaEnemigos.add(enemigo);
    }

    public void addFantasma(int x, int y) {
        Fantasma fantasma = piso.getJuego().getFabrica().crearFantasma(x, y, piso.getJuego().getPiso());
        fantasma.setJugador(piso.getJugador());
        indestructiblesPorAgregar.add(fantasma);
    }

    public void addCalabaza(int x, int y) {
        Calabaza calabaza = piso.getJuego().getFabrica().crearCalabaza(x, y);
        indestructiblesPorAgregar.add(calabaza);
    }
    
    public void removeEnemigo(Enemigo enemigo) {
        enemigosEnEsperaAEliminar.push(enemigo);
    } 

    public void removeEnemigosIndestructibles(){
        for(EnemigoIndestructible enemigo : enemigosIndestructibles)
            enemigo.desaparecer();
    }

    public void removeIndestructible(EnemigoIndestructible enemigo){
        indestructiblesAEliminar.push(enemigo);
    }

    public void registrarObservers() {
        for (Enemigo enemigo : listaEnemigos) 
            piso.getJuego().registrarObserverEntidad(enemigo, ConstantesZOrder.ENEMIGO);
        for(EnemigoIndestructible enemigo : enemigosIndestructibles)
            piso.getJuego().registrarObserverEntidad(enemigo, ConstantesZOrder.ENEMIGO);
    }

    public void actualizar() {
        actualizarEnemigos();
        removeEnemigosEnEspera();
        addEnemigosEnEspera();
    }

    protected void actualizarEnemigos() {
        for (Enemigo enemigo : listaEnemigos) {
            Plataforma plataforma = piso.getJuego().getGrillaColisiones().getPlataformaColisionando(enemigo);
            enemigo.actualizarPlataformaColisionando(plataforma);
            enemigo.actualizar();
        }
        for(EnemigoIndestructible enemigo : enemigosIndestructibles){
            Plataforma plataforma = piso.getJuego().getGrillaColisiones().getPlataformaColisionando(enemigo);
            enemigo.actualizarPlataformaColisionando(plataforma);
            enemigo.actualizar();
        }
    }

    protected void addEnemigosEnEspera(){
        while(!enemigosPorAgregar.isEmpty()){
            Enemigo enemigo = enemigosPorAgregar.pop();
            add(enemigo);
            piso.getJuego().registrarObserverEntidad(enemigo, ConstantesZOrder.ENEMIGO);
        }
        while(!indestructiblesPorAgregar.isEmpty()){
            EnemigoIndestructible enemigo = indestructiblesPorAgregar.pop();
            enemigosIndestructibles.add(enemigo);
            piso.getJuego().registrarObserverEntidad(enemigo, ConstantesZOrder.ENEMIGO);
        }
    }

    protected void removeEnemigosEnEspera() {
        while (!enemigosEnEsperaAEliminar.isEmpty())
           listaEnemigos.remove(enemigosEnEsperaAEliminar.pop());
        while(!indestructiblesAEliminar.isEmpty()){
            EnemigoIndestructible enemigo = indestructiblesAEliminar.pop();
            enemigosIndestructibles.remove(enemigo);
        }
    }

    public void actualizarGrillaColisiones() {
        GrillaColisiones grillaColisiones = piso.getJuego().getGrillaColisiones();
        for (Enemigo enemigo : listaEnemigos) {
            grillaColisiones.addColisionador(enemigo);
            grillaColisiones.addColisionable(enemigo);
        }
        for(EnemigoIndestructible enemigo : enemigosIndestructibles){
            grillaColisiones.addColisionador(enemigo);
            grillaColisiones.addColisionable(enemigo);
        }
    }

    public void vaciar() { 
        for (Enemigo enemigo : listaEnemigos) {
                enemigo.desaparecer();
            }
        for(EnemigoIndestructible enemigo : enemigosIndestructibles){
            enemigo.desaparecer();
        }
    }
}
