package ManejadoresEntidades;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Estructura.Plataforma;
import Grafica.Constantes.ConstantesZOrder;
import Juego.GrillaColisiones;
import Juego.Piso;

public class ManejadorPlataformas {

    protected Piso piso;
    protected List<Plataforma> listaPlataformas;
    protected Stack<Plataforma> plataformasEnEsperaAEliminar;


    public ManejadorPlataformas(Piso piso) {
        this.piso = piso;
        listaPlataformas = new LinkedList<Plataforma>();
        plataformasEnEsperaAEliminar = new Stack<Plataforma>();
    }

    public void add(Plataforma plataforma) {
        listaPlataformas.add(plataforma);
    }

    public void remove(Plataforma plataforma) {
        plataformasEnEsperaAEliminar.push(plataforma);
    }

    public void actualizar() {
        actualizarPlataformas();
        removeplataformasEnEspera();
    }

    protected void actualizarPlataformas(){
        for(Plataforma plataforma: listaPlataformas){
            plataforma.actualizar();
        }
    }

    protected void removeplataformasEnEspera() {
        while (!plataformasEnEsperaAEliminar.isEmpty()) 
            listaPlataformas.remove(plataformasEnEsperaAEliminar.pop());
    }

    public void registrarObservers() {
        for (Plataforma plataforma : listaPlataformas) {
            piso.getJuego().registrarObserverEntidad(plataforma, ConstantesZOrder.PLATAFORMA);
        }
    }

    public void actualizarGrillaColisiones() {
        GrillaColisiones grillaColisiones = piso.getJuego().getGrillaColisiones();
        for(Plataforma plataforma: listaPlataformas){
            grillaColisiones.addPlataforma(plataforma);
            grillaColisiones.addColisionable(plataforma);
        }
    }

    public void vaciar() {
        for (Plataforma plataforma : listaPlataformas) {
            plataforma.desaparecer();
        }
    }    
}
