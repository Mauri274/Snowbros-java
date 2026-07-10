package Personajes.Enemigo;

import java.util.HashMap;

import Estados.EstadoEnemigo.EstadoKamakichi;
import Estados.EstadoEnemigo.Quieto;
import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEntidades;
import Juego.Puntaje;
import Juego.Controladores.ControladorEnemigo;

public class Kamakichi extends Jefe{

    protected int timerVelocidad;
    protected int contadorAceleraciones = 0;
    protected final int MAXIMO_ACELERACIONES = 3;
    protected final int TIEMPO_PARA_ACELERAR = 60 * 4;
    protected final int MIN_TIEMPO_DECISION = 15;

    public Kamakichi(int x, int y, Sprites sprites, Puntaje puntaje, ControladorEnemigo controladorEnemigo) {
        super(x, y, sprites, puntaje,  controladorEnemigo);
        this.velocidad = VELOCIDAD_BASE_ENEMIGO/2;
        this.vidaMaxima = 150;
        this.vida = this.vidaMaxima;
        this.estaPensando = true;
        this.velocidadAtaque = 20;
        this.timerVelocidad = TIEMPO_PARA_ACELERAR;
        this.estadoEnemigo = new Quieto(this);
    }

    public int getAltoHitbox(){
        return ConstantesEntidades.KAMAKICHI_HITBOX_ALTO;
    }

    public int getAnchoHitbox(){
        return ConstantesEntidades.KAMAKICHI_HITBOX_ANCHO;
    }
    
    public int getAltoSprite(){
        return ConstantesEntidades.KAMAKICHI_SPRITE_ALTO;
    }

    public int getAnchoSprite(){
        return ConstantesEntidades.KAMAKICHI_SPRITE_ANCHO;
    }

    protected void bajarPlataforma(){
        plataformaColisionando = null;
        setPosicion(x, y-15);
    }

    protected void tomarDecisionAleatoria() {
        int decision = generadorRandom.nextInt(3);
        if(decision < 2)
            estadoEnemigo.disparar();
        else
            estadoEnemigo = new EstadoKamakichi(this);
    }

    public void actualizarLogicaDecision() {
        super.actualizarLogicaDecision();
        if (!estaPensando){
            timerVelocidad--;
            if (timerVelocidad <= 0) {
                timerVelocidad = TIEMPO_PARA_ACELERAR;
                aumentarVelocidad();
                if (tiempoDecisionBase > MIN_TIEMPO_DECISION) {
                    tiempoDecisionBase -= 30;
                }
            }
        }
    }

    protected void aumentarVelocidad(){
        if(contadorAceleraciones < MAXIMO_ACELERACIONES){
            contadorAceleraciones++;
            this.velocidad += (VELOCIDAD_BASE_ENEMIGO/2);
            this.velocidadAtaque -= 5;
        }
    }

    public void crearProyectil() {
        int cantidadDisparos = generadorRandom.nextInt(1,4);
        HashMap<Integer, Boolean> disparosYaHechos = new HashMap<Integer, Boolean>();
        for(int disparo = 1; disparo <= 4; disparo++){
            disparosYaHechos.put(disparo, false);
        }
        while(cantidadDisparos > 0){
            int disparoAHacer = generadorRandom.nextInt(1, 4);
            if(!disparosYaHechos.get(disparoAHacer)){
                int posicionX = x + getAnchoSprite() - (ConstantesEntidades.KAMAKICHI_SPRITE_ANCHO/4)*disparoAHacer;
                int posicionY = y + getAltoHitbox();
                if(getAnchoSprite()/2 + x > posicionX){
                    controladorEnemigo.dispararBomba(posicionX, posicionY, ConstantesDireccion.IZQUIERDA);
                }
                else{
                    controladorEnemigo.dispararBomba(posicionX, posicionY, ConstantesDireccion.DERECHA);
                }
                cantidadDisparos--;
            }
        }
    }

}