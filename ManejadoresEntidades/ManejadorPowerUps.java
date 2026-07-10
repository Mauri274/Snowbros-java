package ManejadoresEntidades;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Grafica.Constantes.ConstantesZOrder;
import Juego.GrillaColisiones;
import Juego.Piso;
import PowerUp.PowerUp;

public class ManejadorPowerUps {

    protected Piso piso;
    protected List<PowerUp> listaPowerUps;
    protected Stack<PowerUp> powerUpsEnEsperaAEliminar;


    public ManejadorPowerUps(Piso piso) {
        this.piso = piso;
        listaPowerUps = new LinkedList<PowerUp>();
        powerUpsEnEsperaAEliminar = new Stack<PowerUp>();
    }

    public void addPowerUp(int x, int y, int numeroPowerUp) {
        PowerUp powerUp = null;
        switch (numeroPowerUp) {
            case 1, 2, 3, 4, 5, 6: powerUp = piso.getJuego().getFabrica().crearFruta(x, y, piso.getJuego().getPuntaje());
                break;
            case 7: powerUp = piso.getJuego().getFabrica().crearPocionRoja(x, y, piso.getJuego().getPuntaje());
                break;
            case 8: powerUp = piso.getJuego().getFabrica().crearPocionVerde(x, y, piso.getJuego().getPuntaje(), piso.getJuego().getPiso());
                break;
            case 9: powerUp = piso.getJuego().getFabrica().crearPocionAzul(x, y, piso.getJuego().getPuntaje()); 
                break;
            case 10: powerUp = piso.getJuego().getFabrica().crearVidaExtra(x, y, piso.getJuego().getPuntaje());;
                break;
        }
        piso.getJuego().registrarObserverEntidad(powerUp, ConstantesZOrder.POWER_UP);
        listaPowerUps.add(powerUp);
    }
    
    public void removePowerUp(PowerUp powerUp) {
        powerUpsEnEsperaAEliminar.push(powerUp);
    } 

    public void actualizar() {
        actualizarPowerUps();
        removePowerUpsEnEspera();
    }

    protected void actualizarPowerUps() {
        for (PowerUp powerUp : listaPowerUps) {
            powerUp.actualizar();
        }
    }

    protected void removePowerUpsEnEspera() {
        while (!powerUpsEnEsperaAEliminar.isEmpty())
           listaPowerUps.remove(powerUpsEnEsperaAEliminar.pop());
    }

    public void actualizarGrillaColisiones() {
        GrillaColisiones grillaColisiones = piso.getJuego().getGrillaColisiones();
        for(PowerUp powerUp: listaPowerUps){
            grillaColisiones.addColisionable(powerUp);
        }
    }

    public void vaciar() { 
        for (PowerUp powerUp : listaPowerUps) {
                powerUp.desaparecer();
            }
    }

    public boolean estaVacio() {
        return listaPowerUps.isEmpty();
    }
}
