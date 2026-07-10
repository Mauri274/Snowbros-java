package ManejadoresEntidades;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Estructura.Plataforma;
import Grafica.Constantes.ConstantesZOrder;
import Juego.GrillaColisiones;
import Juego.Piso;
import Personajes.BolaEnMovimiento;

public class ManejadorBolasEnMovimiento {

    protected Piso piso;
    protected List<BolaEnMovimiento> listaBolasEnMovimiento;
    protected Stack<BolaEnMovimiento> bolaEnMovimientosEnEsperaAEliminar;


    public ManejadorBolasEnMovimiento(Piso piso) {
        this.piso = piso;
        listaBolasEnMovimiento = new LinkedList<BolaEnMovimiento>();
        bolaEnMovimientosEnEsperaAEliminar = new Stack<BolaEnMovimiento>();
    }

    public void add(int x, int y, int direccion){
        BolaEnMovimiento bolaEnMovimiento = piso.getJuego().getFabrica().crearBolaEnMovimiento(x, y, direccion);
        listaBolasEnMovimiento.add(bolaEnMovimiento);
        piso.getJuego().registrarObserverEntidad(bolaEnMovimiento, ConstantesZOrder.BOLA_EN_MOVIMIENTO);
    }
    
    public void remove(BolaEnMovimiento bolaEnMovimiento) {
        bolaEnMovimientosEnEsperaAEliminar.push(bolaEnMovimiento);
    } 

    public void actualizar() {
        actualizarBolasEnMovimientos();
        removeBolaEnMovimientosEnEspera();
    }

    protected void actualizarBolasEnMovimientos() {
        for (BolaEnMovimiento bolaEnMovimiento : listaBolasEnMovimiento) {
            Plataforma plataforma = piso.getJuego().getGrillaColisiones().getPlataformaColisionando(bolaEnMovimiento);
            bolaEnMovimiento.actualizarPlataformaColisionando(plataforma);
            bolaEnMovimiento.actualizar();
        }
    }

    protected void removeBolaEnMovimientosEnEspera() {
        while (!bolaEnMovimientosEnEsperaAEliminar.isEmpty())
           listaBolasEnMovimiento.remove(bolaEnMovimientosEnEsperaAEliminar.pop());
    }

    public void actualizarGrillaColisiones() {
        GrillaColisiones grillaColisiones = piso.getJuego().getGrillaColisiones();
        for (BolaEnMovimiento bolaEnMovimiento : listaBolasEnMovimiento) {
            grillaColisiones.addColisionador(bolaEnMovimiento);
            grillaColisiones.addColisionable(bolaEnMovimiento);
        }
    }

    public void vaciar() { 
        for (BolaEnMovimiento bolaEnMovimiento : listaBolasEnMovimiento) {
                bolaEnMovimiento.desaparecer();
            }
    }

    public boolean estaVacio() {
        return listaBolasEnMovimiento.isEmpty();
    }
}
