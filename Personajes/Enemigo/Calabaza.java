package Personajes.Enemigo;

import Estados.EstadoEnemigo.VolandoCalabaza;
import Fabricas.Sprites;
import Grafica.Constantes.ConstantesEstado;
import Juego.ConstantesPuntaje;
import Juego.Puntaje;
import Juego.Controladores.ControladorCalabaza;
import Juego.Controladores.ControladorEnemigo;

public class Calabaza extends EnemigoIndestructible{

    protected ControladorCalabaza controladorCalabaza;
    protected int framesDisparando;
    protected boolean disparando = false;
    protected final int MAXIMO_FANTASMAS = 3;
    protected final int MAXIMO_TIEPO_FANTASMA = 150;
    protected int contadorFantasmas = 0;

    public Calabaza(int x, int y, Sprites sprites, Puntaje puntaje, ControladorEnemigo controladorEnemigo, ControladorCalabaza controladorCalabaza) {
        super(x, y, sprites, puntaje,  controladorEnemigo);
        this.controladorCalabaza = controladorCalabaza;
        this.estadoEnemigo = new VolandoCalabaza(this);
        cantPuntajeCongelar = ConstantesPuntaje.ENEMIGO_VOLADOR_CONGELADO;
        cantPuntajeEmpujar = ConstantesPuntaje.ENEMIGO_VOLADOR_EMPUJADO;
    }

    public void actualizar(){
        super.actualizar();
        if(disparando){
            framesDisparando--;
            if(framesDisparando == 20){
                controladorCalabaza.addFantasma(x, y);
            }
            else if(framesDisparando == 0){
                misSprites.setEstadoActual(ConstantesEstado.CAMINANDO);
                disparando = false;
            }
        }
    }

    protected void tomarDecisionAleatoria() {
        int decision = generadorRandom.nextInt(100);
        if (decision > 50)
            estadoEnemigo.disparar();
    }

    public void disparar(){
        if(!disparando && contadorFantasmas < MAXIMO_FANTASMAS){
            disparando = true;
            framesDisparando = 40;
            contadorFantasmas++;
            misSprites.setEstadoActual(ConstantesEstado.DISPARANDO);
        }
    }

    public void crearProyectil() {}
    public void chocarPared(int direccion, int xPared){}

    public void serChocadoBolaEnMovimiento(){
        estadoEnemigo.aturdir();
    }
}