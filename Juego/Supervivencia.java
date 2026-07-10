package Juego;

import Juego.Controladores.ControladorEnemigo;
import Personajes.Enemigo.Enemigo;
import java.util.Random;

import Estados.EstadoEnemigo.Aturdido;
import Estados.EstadoEnemigo.Quieto;
import Grafica.Constantes.ConstantesEntidades;
import Grafica.Constantes.ConstantesVistas;
import Grafica.Constantes.ConstantesZOrder;

public class Supervivencia extends ModoDeJuego {
    
    protected final int puntajeObjetivo;
    protected Random generadorRandom;
    protected final int MINIMO_ENEMIGOS = 4;
    protected int tiempoParaProximaOleada;
    protected static final int INTERVALO_OLEADA_TICKS = 10 * TICKS_POR_SEGUNDO;
    protected final int PUNTOS_PARA_GANAR = 50000;
    protected int tiempoJugadoEnTicks;


    public Supervivencia(Juego juego) {
        super(juego);
        this.puntajeObjetivo = PUNTOS_PARA_GANAR;
        this.generadorRandom = new Random();
        resetParaSiguienteNivel();
        rutaACarpeta = "/Niveles/supervivencia.txt";
        this.tiempoJugadoEnTicks = 0;
        tiempoParaProximaOleada = 0;
    }

    public String getNombreModo() {
        return "Supervivencia";
    }

    public String getInfoParaGUI() {
        return formatarTiempoTranscurrido(tiempoJugadoEnTicks);
    }

    public void actualizarLogica() {
        if (tiempoParaProximaOleada <= 0) {
            generarOleadaEnemigos();
            tiempoParaProximaOleada = INTERVALO_OLEADA_TICKS;
        }
        tiempoJugadoEnTicks++;
        tiempoParaProximaOleada--;
    }

    public boolean verificarCondicionVictoria() {
        return juego.getPuntaje().getPuntos() >= this.puntajeObjetivo;
    }

    public boolean verificarCondicionDerrota() {
        return false;
    }

    public void resetParaSiguienteNivel() {
        this.tiempoParaProximaOleada = 0;
        this.tiempoJugadoEnTicks = 0;
    }

    protected void generarOleadaEnemigos() {
        int cantEnemigos = generadorRandom.nextInt(3) + MINIMO_ENEMIGOS;
        for (int i = 0; i < cantEnemigos; i++) {
            int randomX = generadorRandom.nextInt(ConstantesVistas.PANEL_PISO_ANCHO - ConstantesEntidades.ENEMIGO_SPRITE_ANCHO/2);
            int randomY = generadorRandom.nextInt(ConstantesVistas.PANEL_PISO_ALTO - ConstantesEntidades.ENEMIGO_SPRITE_ALTO/2);
            
            int enemigoNuevo = generadorRandom.nextInt(3);
            Enemigo enemigoCreado = null;
            ControladorEnemigo controlador = juego.getPiso();
            switch (enemigoNuevo) {
                case 0:
                    enemigoCreado = juego.getFabrica().crearDemonioRojo(randomX, randomY, controlador);
                    break;
                case 1:
                    enemigoCreado = juego.getFabrica().crearTrollAmarillo(randomX, randomY, controlador);
                    break;
                case 2:
                    enemigoCreado = juego.getFabrica().crearRanaFuego(randomX, randomY, controlador);
                    break;
            }
            enemigoCreado.setEstadoActual(new Aturdido(enemigoCreado,new Quieto(enemigoCreado)));
            juego.getPiso().addEnemigo(enemigoCreado);
            juego.registrarObserverEntidad(enemigoCreado, ConstantesZOrder.ENEMIGO);
        }
    }

    public boolean verificarCondicionFinDeNivel() {
        return false;
    }

    public void actualizarLogicaCalabaza(Piso piso) {}
}