package Personajes.Enemigo;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEntidades;
import Juego.Puntaje;
import Juego.Controladores.ControladorEnemigo;

public class Moghera extends Jefe{

    public Moghera(int x, int y, Sprites sprites, Puntaje puntaje, ControladorEnemigo controladorEnemigo) {
        super(x, y, sprites, puntaje,  controladorEnemigo);
        vida = 80;
        velocidad = 3;
        velocidadAtaque = 15;
    }

    public int getAltoSprite(){
        return ConstantesEntidades.MOGHERA_SPRITE_ALTO;
    }

    public int getAnchoSprite(){
        return ConstantesEntidades.MOGHERA_SPRITE_ANCHO;
    }

    public int getAnchoHitbox(){
        return ConstantesEntidades.MOGHERA_HITBOX_ANCHO;
    }

    public int getAltoHitbox(){
        return ConstantesEntidades.MOGHERA_HITBOX_ALTO;
    }

    public void lanzarFuego(){
        this.controladorEnemigo.dispararBolaDeFuego(x, y, direccion);
    }

    protected void tomarDecisionAleatoria() {
        int decision = generadorRandom.nextInt(100);
    
        if (decision < 50)
            this.estadoEnemigo.disparar();
        else{
            if (decision < 100)
                this.estadoEnemigo.saltar();
            }
        
        if (generadorRandom.nextBoolean())
            setDireccion(ConstantesDireccion.DERECHA);
        else
            setDireccion(ConstantesDireccion.IZQUIERDA);
        
        this.estadoEnemigo.mover();
    }

    public void crearProyectil() {
        int posicionX = x + ConstantesEntidades.MOGHERA_SPRITE_ANCHO / 3;
        int rangoAltura = ConstantesEntidades.MOGHERA_HITBOX_ALTO / 2;
        int posicionY1 = generadorRandom.nextInt(y, y + rangoAltura);
        int inicio2 = y + rangoAltura;
        int posicionY2 = generadorRandom.nextInt(inicio2, inicio2 + rangoAltura);
        
        controladorEnemigo.dispararFuego(posicionX, posicionY1, getDireccion());
        controladorEnemigo.dispararFuego(posicionX, posicionY2, getDireccion());
    }

}

