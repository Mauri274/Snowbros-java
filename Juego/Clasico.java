package Juego;

public class Clasico extends ModoDeJuego{

    protected int tiempoJugadoEnTicks;


    public Clasico(Juego juego){
        super(juego);
        rutaACarpeta = "/Niveles/clasico.txt";
        this.tiempoJugadoEnTicks = 0;
    }

    public void actualizarLogica() {
        tiempoJugadoEnTicks++;
    }

    public boolean verificarCondicionVictoria() {
        return false;
    }

    public boolean verificarCondicionDerrota() {
        return false;
    }

    public String getNombreModo() {
        return "Clasico";
    }
    
    public boolean verificarCondicionFinDeNivel() {
        return juego.getPiso().terminoElPiso();
    }

    public String getInfoParaGUI() {
        return formatarTiempoTranscurrido(tiempoJugadoEnTicks);
    }

    public void actualizarLogicaCalabaza(Piso piso) {
        int contadorCalabaza = piso.getContadorCalabaza();
        if(contadorCalabaza >= 0){
            piso.decrementarContadorCalabaza();
        }
        if(contadorCalabaza == 0){
            piso.addCalabaza(100, 200);
        }
    }

    public void resetParaSiguienteNivel(){
        this.tiempoJugadoEnTicks = 0;
    }
}