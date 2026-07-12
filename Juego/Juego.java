package Juego;

import Fabricas.FabricaEntidades;
import Fabricas.FabricaSpritesAlternativos;
import Fabricas.FabricaSpritesOriginales;
import Generador.GeneradorPiso;
import Grafica.Observer;
import Grafica.ObserverGraficoNieve;
import Grafica.ObserverPuntaje;
import Grafica.ObserverVida;
import Grafica.Constantes.ConstantesSonido;
import Grafica.Constantes.ConstantesVistas;
import Grafica.Constantes.ControladorGrafica;
import Hilos.HiloPrincipal;
import Juego.Controladores.ControladorJuego;
import Juego.Controladores.ControladorPuntaje;
import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;
import java.io.IOException;


public class Juego implements ControladorJuego, ControladorPuntaje {

    protected ControladorGrafica controladorGrafica;
    protected GeneradorPiso generadorPiso;
    protected FabricaEntidades fabricaEntidades;
    protected Puntaje puntaje;
    protected Piso pisoActual;
    protected ModoDeJuego modoActual;
    protected HiloPrincipal hiloPrincipal;
    protected String nombreJugador;
    protected GrillaColisiones grillaColisiones;
    protected int puntajeFinalTemporal = 0;
    protected boolean partidaEnCurso;
    

    public Juego(){
        fabricaEntidades = new FabricaEntidades(new FabricaSpritesOriginales(),this);
        grillaColisiones = new GrillaColisiones();
        this.partidaEnCurso = false;
    }

    public Puntaje getPuntaje(){
        return puntaje;
    }

    public Piso getPiso() {
        return pisoActual;
    }

    public FabricaEntidades getFabrica() {
        return fabricaEntidades;
    }

    public GrillaColisiones getGrillaColisiones() {
        return grillaColisiones;
    }

    public ModoDeJuego getModoActual() {
        return modoActual;
    }

    public void setControladorGrafica(ControladorGrafica controladorGrafica) {
        this.controladorGrafica = controladorGrafica;
    }

    public void setNombreJugador(String nombre){
        this.nombreJugador = nombre;
    }

    public void setDominio(String dominio) {
        if("Clasico".equals(dominio))
            fabricaEntidades = new FabricaEntidades(new FabricaSpritesOriginales(),this);
        else
            if("Alternativo".equals(dominio))
                fabricaEntidades = new FabricaEntidades(new FabricaSpritesAlternativos(),this);
    }

    public void setModoJuego(String modoJuego) {
        if(modoJuego.equals("Clasico"))
            this.modoActual = new Clasico(this);
        else
            if(modoJuego.equals("Contrarreloj"))
                this.modoActual = new Contrarreloj(this);
            else
                if(modoJuego.equals("Supervivencia"))
                    this.modoActual = new Supervivencia(this);
    }

    public void setPuntajeTemporal(int puntaje) {
        this.puntajeFinalTemporal = puntaje;
    }

    public void iniciarPartida() {
        partidaEnCurso = true;

        if (modoActual != null)
            modoActual.resetParaSiguienteNivel();

            crearPuntaje();
        crearGeneradorPiso();

        pisoActual = generadorPiso.generarSiguientePiso();
        pisoActual.setJuego(this);

        pisoActual.getJugador().setNombre(nombreJugador);
        pisoActual.iniciarPiso();

        controladorGrafica.actualizarNombrePiso(pisoActual.getNombre());
        controladorGrafica.actualizarTiempo(modoActual.getInfoParaGUI());

        hiloPrincipal = new HiloPrincipal(this);
        hiloPrincipal.start();
		controladorGrafica.mostrarPanelPantallaJuego();
        GestorSonido.getGestorSonido().reproducirMusicaDeFondo(ConstantesSonido.MUSICA_FONDO);
    }
    
    protected void crearPuntaje() {
        puntaje = new Puntaje();
        ObserverPuntaje observerPuntaje = controladorGrafica.registrarPuntaje(puntaje);
        puntaje.registrarObserverPuntaje(observerPuntaje);
    }

    public void generarSiguientePiso() {
        if (pisoActual != null) {
            pisoActual.vaciarPiso();
        }
        pisoActual = generadorPiso.generarSiguientePiso();
        
        if (pisoActual != null) {
            controladorGrafica.actualizarNombrePiso(pisoActual.getNombre());
            pisoActual.setJuego(this);
            pisoActual.iniciarPiso();
        }
    }

    protected void registrarObserverFondo(Fondo fondo) {
        Observer observer = controladorGrafica.registrarFondo(fondo);
        fondo.addObserver(observer);
	}

    protected ObserverGraficoNieve registrarObserverNieve(Enemigo enemigo) {
        ObserverGraficoNieve observerNieve = controladorGrafica.registrarNieve(enemigo, fabricaEntidades.getEstadoCongelado());
        enemigo.addObserver(observerNieve);
        return observerNieve;
    }

    public void registrarObserverEntidad(Entidad entidad, int zOrder) {
        Observer observerEntidad = controladorGrafica.registrarEntidad(entidad, zOrder);
        entidad.addObserver(observerEntidad);
    }

    public void registrarObserverVida(Jugador jugador) {
        ObserverVida observerVida = controladorGrafica.registrarObserverVida(jugador);
        jugador.addObserverVida(observerVida);
    }


    public void moverAJugador(int direccion){
        if(partidaEnCurso)
            pisoActual.getJugador().moverADireccion(direccion);
    }

    public void detenerMovimientoJugador(int tecla){
        if(partidaEnCurso)
            pisoActual.getJugador().detenerMovimiento(tecla);
    }

    public void saltarAJugador(){
        if(partidaEnCurso)
            pisoActual.getJugador().saltar();
    }

    public void dispararAJugador() {
        if (partidaEnCurso)
            pisoActual.getJugador().disparar();
    }

    public void detenerDisparoJugador() {
        pisoActual.getJugador().detenerDisparo();
    }
    
    public void actualizarJuego() {
        modoActual.actualizarLogica();
        controladorGrafica.actualizarTiempo(modoActual.getInfoParaGUI());
        if (modoActual.verificarCondicionDerrota()) {
            terminarJuego(false);
            return;
        }
        if (modoActual.verificarCondicionVictoria()) {
            terminarJuego(true);
            return;
        }
        if (modoActual.verificarCondicionFinDeNivel()) {
            generarSiguientePiso();
            if (pisoActual == null)
                terminarJuego(true);
        }
        else
            pisoActual.actualizar();
    }

    protected void crearGeneradorPiso(){
        Jugador jugador = fabricaEntidades.crearJugador(ConstantesVistas.X_INICIO_JUGADOR, ConstantesVistas.Y_INICIO_JUGADOR);
        generadorPiso = new GeneradorPiso(fabricaEntidades, jugador, modoActual.getRutaACarpeta());
    }

    public void terminarJuego(boolean gano) {
        if (partidaEnCurso) {

            if (pisoActual != null) {
                pisoActual.vaciarPiso();
            }
            
            partidaEnCurso = false;
            hiloPrincipal.detener();
            modoActual.gestionarFinDePartida(gano);
            puntaje.desaparecer();
            GestorSonido.detenerMusica(ConstantesSonido.MUSICA_FONDO);
        }
    }

    public void guardarPuntajeFinal(String nombre) {
        String nombreModo = modoActual.getNombreModo();
        Ranking ranking = modoActual.getRanking();
        try {
            ranking.ingresarJugador(nombre, puntajeFinalTemporal);
            ranking.guardar(nombreModo);
        } catch (IOException e) {
            System.err.println("Error al guardar el ranking: " + e.getMessage());
        }
    }
}