package ManejadoresEntidades;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Grafica.Constantes.ConstantesZOrder;
import Juego.GrillaColisiones;
import Juego.Piso;
import Proyectil.BolaDeFuego;
import Proyectil.Bomba;
import Proyectil.DisparoNieve;
import Proyectil.Fuego;
import Proyectil.Proyectil;

public class ManejadorProyectiles {

    protected Piso piso;
    protected List<Proyectil> listaProyectiles;
    protected Stack<Proyectil> proyectilesEnEsperaAEliminar;


    public ManejadorProyectiles(Piso piso) {
        this.piso = piso;
        listaProyectiles = new LinkedList<Proyectil>();
        proyectilesEnEsperaAEliminar = new Stack<Proyectil>();
    }

    
    public void remove(Proyectil proyectil) {
        proyectilesEnEsperaAEliminar.push(proyectil);
    } 

    public void actualizar() {
        actualizarProyectiles();
        removeProyectilesEnEspera();
    }

    protected void actualizarProyectiles() {
        for (Proyectil proyectil : listaProyectiles) {
            proyectil.actualizar();
        }
    }

    protected void removeProyectilesEnEspera() {
        while (!proyectilesEnEsperaAEliminar.isEmpty())
           listaProyectiles.remove(proyectilesEnEsperaAEliminar.pop());
    }

    public void actualizarGrillaColisiones() {
        GrillaColisiones grillaColisiones = piso.getJuego().getGrillaColisiones();
        for (Proyectil proyectil : listaProyectiles) {
            grillaColisiones.addColisionador(proyectil);
            grillaColisiones.addColisionable(proyectil);
        }
    }

    public void vaciar() { 
        for (Proyectil proyectil : listaProyectiles) {
                proyectil.desaparecer();
            }
    }

    public void dispararBolaDeFuego(int x, int y, int direccion) {
        BolaDeFuego bolaDeFuego = piso.getJuego().getFabrica().crearBolaDeFuego(x, y, direccion);
        listaProyectiles.add(bolaDeFuego);
        piso.getJuego().registrarObserverEntidad(bolaDeFuego, ConstantesZOrder.PROYECTIL);
    }

    public void dispararBomba(int x, int y, int direccion) {
        Bomba bomba = piso.getJuego().getFabrica().crearBomba(x, y, direccion);
        listaProyectiles.add(bomba);
        piso.getJuego().registrarObserverEntidad(bomba, ConstantesZOrder.PROYECTIL);
    }

    public void dispararFuego(int x, int y, int direccion) {
        Fuego fuego = piso.getJuego().getFabrica().crearFuego(x, y, direccion);
        listaProyectiles.add(fuego);
        piso.getJuego().registrarObserverEntidad(fuego, ConstantesZOrder.PROYECTIL);
    }

    public void dispararNieve(int x, int y, int direccion, int danio) {
        DisparoNieve disparoNieve = piso.getJuego().getFabrica().crearDisparoNieve(x, y, direccion, danio);
        listaProyectiles.add(disparoNieve);
        piso.getJuego().registrarObserverEntidad(disparoNieve, ConstantesZOrder.PROYECTIL);
    }
}

