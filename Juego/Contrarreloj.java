package Juego;

public class Contrarreloj extends Clasico {

    protected int tiempoRestanteEnTicks;
    protected final int tiempoLimiteInicialEnTicks;
    protected final int tiempoLimiteEnSegundos = 300;

    public Contrarreloj(Juego juego) {
        super(juego);
        this.tiempoLimiteInicialEnTicks = tiempoLimiteEnSegundos * TICKS_POR_SEGUNDO;
        this.tiempoRestanteEnTicks = this.tiempoLimiteInicialEnTicks;
    }

    public void actualizarLogica() {
        if (tiempoRestanteEnTicks > 0)
            tiempoRestanteEnTicks--;
    }

    public boolean verificarCondicionVictoria() {
        return super.verificarCondicionVictoria() && tiempoRestanteEnTicks > 0;
    }

    public boolean verificarCondicionDerrota() {
        return tiempoRestanteEnTicks <= 0;
    }

    public String getNombreModo() {
        return "Contrarreloj";
    }

    public void resetParaSiguienteNivel() {
        super.resetParaSiguienteNivel();
        this.tiempoRestanteEnTicks = this.tiempoLimiteInicialEnTicks;
    }

    public String getInfoParaGUI() {
        int ticks = Math.max(0, tiempoRestanteEnTicks);
        return formatarTiempoTranscurrido(ticks);
    }

    public boolean verificarCondicionFinDeNivel() {
        return super.verificarCondicionFinDeNivel() && tiempoRestanteEnTicks > 0;
    }
}