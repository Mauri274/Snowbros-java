package Juego;

import java.io.IOException;

public abstract class ModoDeJuego {
    
    protected Ranking ranking;
    protected Juego juego;
    protected static final int TICKS_POR_SEGUNDO = 50;
    protected String rutaACarpeta;

    public ModoDeJuego(Juego juego){
        this.ranking = new Ranking();
        this.juego = juego;
    }
    
    public abstract void actualizarLogica();
    public abstract boolean verificarCondicionVictoria();
    public abstract boolean verificarCondicionFinDeNivel();
    public abstract boolean verificarCondicionDerrota();
    public abstract String getNombreModo();
    public abstract void actualizarLogicaCalabaza(Piso piso);


    public String getRutaACarpeta(){
        return rutaACarpeta;
    }

    public String getInfoParaGUI() {
        return "";
    }

    public void gestionarFinDePartida(boolean gano) {
        int puntaje = juego.getPuntaje().getPuntos();
        String nombreModo = this.getNombreModo();

        try {
            ranking.cargar(nombreModo);
        } catch (IOException e) {
            System.err.println("Aviso al cargar el ranking (el archivo puede no existir): " + e.getMessage());
        }

        if (ranking.esPuntajeAlto(puntaje)) {
            juego.puntajeFinalTemporal = puntaje;
            juego.controladorGrafica.mostrarPanelPantallaRanking(gano);
        }
        else {
            if (gano) {
                juego.controladorGrafica.mostrarPanelPantallaVictoria();
            }
            else {
                juego.controladorGrafica.mostrarPanelPantallaGameOver();
            }
        }
    }

    public void resetParaSiguienteNivel() {}

    public Ranking getRanking() {
        return ranking;
    }

    protected String formatarTiempoTranscurrido(int ticks) {
        int totalSegundos = (ticks / TICKS_POR_SEGUNDO);
        int minutos = totalSegundos / 60;
        int segundos = totalSegundos % 60;
        return String.format("Tiempo: %02d:%02d", minutos, segundos);
    }

}