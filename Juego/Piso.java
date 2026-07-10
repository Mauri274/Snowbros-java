package Juego;

import Estructura.Obstaculo;
import Estructura.Plataforma;
import Grafica.ObserverGraficoNieve;
import Grafica.Constantes.ConstantesZOrder;
import Juego.Controladores.ControladorBolaEnMovimiento;
import Juego.Controladores.ControladorCalabaza;
import Juego.Controladores.ControladorEnemigo;
import Juego.Controladores.ControladorJugador;
import Juego.Controladores.ControladorObstaculo;
import Juego.Controladores.ControladorPlataforma;
import Juego.Controladores.ControladorPocionVerde;
import Juego.Controladores.ControladorPowerUp;
import Juego.Controladores.ControladorProyectil;
import ManejadoresEntidades.ManejadorBolasEnMovimiento;
import ManejadoresEntidades.ManejadorEnemigos;
import ManejadoresEntidades.ManejadorObstaculos;
import ManejadoresEntidades.ManejadorPlataformas;
import ManejadoresEntidades.ManejadorPowerUps;
import ManejadoresEntidades.ManejadorProyectiles;
import Personajes.BolaEnMovimiento;
import Personajes.Enemigo.Enemigo;
import Personajes.Enemigo.EnemigoIndestructible;
import Personajes.Jugador;
import PowerUp.PowerUp;
import Proyectil.Proyectil;
import java.util.List;

public class Piso implements ControladorProyectil, ControladorCalabaza, ControladorPowerUp, ControladorPocionVerde, ControladorEnemigo, ControladorBolaEnMovimiento, ControladorJugador, ControladorObstaculo, ControladorPlataforma {
    
    protected int cantEnemigos;
    protected Jugador jugador;
    protected ManejadorBolasEnMovimiento manejadorBolasEnMovimiento;
    protected ManejadorEnemigos manejadorEnemigos;
    protected ManejadorObstaculos manejadorObstaculos;
    protected ManejadorPlataformas manejadorPlataformas;
    protected ManejadorPowerUps manejadorPowerUps;
    protected ManejadorProyectiles manejadorProyectiles;
    protected Juego miJuego;
    protected Fondo fondo;
    protected int contadorCalabaza;
    protected final int TIEMPO_MAXIMO_CALABAZA = 1200;
    protected String nombre;

    public Piso(String nombre){
        manejadorBolasEnMovimiento = new ManejadorBolasEnMovimiento(this);
        manejadorEnemigos = new ManejadorEnemigos(this);
        manejadorObstaculos = new ManejadorObstaculos(this);
        manejadorPlataformas = new ManejadorPlataformas(this);
        manejadorPowerUps = new ManejadorPowerUps(this);
        manejadorProyectiles = new ManejadorProyectiles(this);
        contadorCalabaza = TIEMPO_MAXIMO_CALABAZA;
        this.nombre = nombre;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public List<Enemigo> getEnemigos() {
        return manejadorEnemigos.getEnemigos();
    }

    public Juego getJuego() {
        return miJuego;
    }

    public String getNombre(){
        return nombre;
    }

    public int getContadorCalabaza(){
        return contadorCalabaza;
    }

    public void setJuego(Juego juego) {
        miJuego = juego;
    }
 
    public void setFondo(Fondo fondo) {
        this.fondo = fondo;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        jugador.setControladorJugador(this);
    }

    public void addPlataforma(Plataforma plataforma){
        manejadorPlataformas.add(plataforma);
    }

    public void addObstaculo(Obstaculo obstaculo) {
        manejadorObstaculos.add(obstaculo);
    }

    public void addPowerUp(int x, int y, int numeroPowerUp){
        manejadorPowerUps.addPowerUp(x, y, numeroPowerUp);
    }

    public void addEnemigo(Enemigo enemigo) {
        manejadorEnemigos.add(enemigo);
    }

    public void addFantasma(int x, int y) {
        manejadorEnemigos.addFantasma(x, y);
    }

    public void addCalabaza(int x, int y){
        manejadorEnemigos.addCalabaza(x, y);
    }

    public void addBolaEnMovimiento(int x, int y, int direccion) {
        manejadorBolasEnMovimiento.add(x, y, direccion);
    }

    public void removePowerUp(PowerUp powerUp) {
        manejadorPowerUps.removePowerUp(powerUp);
    }

    public void removeEnemigo(Enemigo enemigo) {
        manejadorEnemigos.removeEnemigo(enemigo);
        cantEnemigos--;
    }

    public void removeIndestructible(EnemigoIndestructible enemigo){
        manejadorEnemigos.removeIndestructible(enemigo);
    }

    public void removeProyectil(Proyectil proyectil) {
        manejadorProyectiles.remove(proyectil);
    }

    public void removeBolaEnMovimiento(BolaEnMovimiento bolaEnMovimiento) {
        manejadorBolasEnMovimiento.remove(bolaEnMovimiento);
    }
    
    public void removePlataforma(Plataforma plataforma) {
        manejadorPlataformas.remove(plataforma);
    }

    public void removeObstaculo(Obstaculo obstaculo) {
        manejadorObstaculos.remove(obstaculo);
    }

    public void iniciarPiso() {
        cantEnemigos = getEnemigos().size();
        registrarObservers();
        jugador.aparecerEnPiso();
    }

    public void actualizar() {
        manejadorBolasEnMovimiento.actualizar();
        manejadorEnemigos.actualizar();
        manejadorObstaculos.actualizar();
        manejadorPlataformas.actualizar();
        manejadorPowerUps.actualizar();
        manejadorProyectiles.actualizar();
        miJuego.getModoActual().actualizarLogicaCalabaza(this); 
        actualizarGrillaColisiones();
        actualizarJugador();
    }

    public void actualizarGrillaColisiones() {
        GrillaColisiones grillaColisiones = miJuego.getGrillaColisiones();
        grillaColisiones.limpiarGrilla();

        grillaColisiones.addColisionable(jugador);
        grillaColisiones.addColisionador(jugador);

        manejadorBolasEnMovimiento.actualizarGrillaColisiones();
        manejadorEnemigos.actualizarGrillaColisiones();
        manejadorObstaculos.actualizarGrillaColisiones();
        manejadorPlataformas.actualizarGrillaColisiones();
        manejadorPowerUps.actualizarGrillaColisiones();
        manejadorProyectiles.actualizarGrillaColisiones();
        
        grillaColisiones.chequearColisiones();
    }

    protected void actualizarJugador() {
            Plataforma plataforma = miJuego.getGrillaColisiones().getPlataformaColisionando(jugador);
            jugador.actualizarPlataformaColisionando(plataforma);
            jugador.actualizar();
    }

    public void vaciarPiso(){
        manejadorBolasEnMovimiento.vaciar();
        manejadorEnemigos.vaciar();
        manejadorObstaculos.vaciar();
        manejadorPlataformas.vaciar();
        manejadorPowerUps.vaciar();
        manejadorProyectiles.vaciar();
        jugador.desaparecer();
    }

    protected void registrarObservers() {
        registrarObserverJugador();
        manejadorEnemigos.registrarObservers();
        manejadorObstaculos.registrarObservers();
        manejadorPlataformas.registrarObservers();
		registrarObserverFondo();
	}

    protected void registrarObserverJugador() {
        miJuego.registrarObserverEntidad(jugador, ConstantesZOrder.JUGADOR);
        miJuego.registrarObserverVida(jugador);
    }

    protected void registrarObserverFondo() {
        miJuego.registrarObserverFondo(fondo);
    }

    public ObserverGraficoNieve registrarObserverNieve(Enemigo enemigo) {
        return miJuego.registrarObserverNieve(enemigo);
    }

    public void terminarJuego(boolean gano) {
        vaciarPiso();
        miJuego.terminarJuego(gano);
    }

    public boolean terminoElPiso() {
        return cantEnemigos <= 0 && manejadorBolasEnMovimiento.estaVacio() && manejadorPowerUps.estaVacio();
    }

    public void dispararNieve(int x, int y, int direccion, int danio) {
        manejadorProyectiles.dispararNieve(x, y, direccion, danio);
    }

    public void dispararBolaDeFuego(int x, int y, int direccion) {
        manejadorProyectiles.dispararBolaDeFuego(x, y, direccion);
    }

    public void dispararBomba(int x, int y, int direccion) {
        manejadorProyectiles.dispararBomba(x, y, direccion);
    }

    public void dispararFuego(int x, int y, int direccion) {
        manejadorProyectiles.dispararFuego(x, y, direccion);
    }

    public void decrementarContadorCalabaza(){
        contadorCalabaza--;
    }

    public void morirJugador(){
        manejadorEnemigos.removeEnemigosIndestructibles();
        contadorCalabaza = TIEMPO_MAXIMO_CALABAZA;
    }

}
