package ManejadoresEntidades;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Estructura.Obstaculo;
import Grafica.Constantes.ConstantesZOrder;
import Juego.GrillaColisiones;
import Juego.Piso;

public class ManejadorObstaculos {

    protected Piso piso; 
    protected List<Obstaculo> listaObstaculos;
    protected Stack<Obstaculo> obstaculosEnEsperaAEliminar;


    public ManejadorObstaculos(Piso piso) {
        this.piso = piso;
        listaObstaculos = new LinkedList<Obstaculo>();
        obstaculosEnEsperaAEliminar = new Stack<Obstaculo>();
    }

    public void add(Obstaculo obstaculo) {
        listaObstaculos.add(obstaculo);
    }

    public void remove(Obstaculo obstaculo) {
        obstaculosEnEsperaAEliminar.push(obstaculo);
    }

    public void actualizar() {
        removeObstaculosEnEspera();
    }

    protected void removeObstaculosEnEspera() {
        while (!obstaculosEnEsperaAEliminar.isEmpty()) 
            listaObstaculos.remove(obstaculosEnEsperaAEliminar.pop());
    }

    public void registrarObservers() {
        for (Obstaculo obstaculo : listaObstaculos) {
            piso.getJuego().registrarObserverEntidad(obstaculo, ConstantesZOrder.OBSTACULO);
        }
    }

    public void actualizarGrillaColisiones() {
        GrillaColisiones grillaColisiones = piso.getJuego().getGrillaColisiones();
        for(Obstaculo obstaculo : listaObstaculos){
            grillaColisiones.addColisionable(obstaculo);
        }
    }
    
    public void vaciar() {
        for (Obstaculo obstaculo : listaObstaculos) {
            obstaculo.desaparecer();
        }
    }    
}
