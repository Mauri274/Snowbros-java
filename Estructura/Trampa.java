package Estructura;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesEntidades;
import Juego.Controladores.ControladorObstaculo;
import Personajes.Enemigo.Enemigo;
import Personajes.BolaEnMovimiento;
import Personajes.Jugador;
import Proyectil.ProyectilAmigo;
import Proyectil.ProyectilEnemigo;

public class Trampa extends Obstaculo{

    public Trampa(int x, int y, Sprites sprites, ControladorObstaculo controladorObstaculos) {
        super(x, y, sprites, controladorObstaculos);
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }

    public void afectar(Jugador jugador) {
        jugador.recibirDanio();
    }

    public int getAnchoSprite() {
        return ConstantesEntidades.TRAMPA_SPRITE_ANCHO;
    }

    public int getAltoSprite() {
        return ConstantesEntidades.TRAMPA_SPRITE_ALTO;
    }

    public int getAnchoHitbox() {
        return ConstantesEntidades.TRAMPA_HITBOX_ANCHO;
    }
    
    public int getAltoHitbox() {
        return ConstantesEntidades.TRAMPA_HITBOX_ALTO;
    }

    public void afectar(BolaEnMovimiento bolaEnMovimiento) {}

    public void afectar(Enemigo enemigo) {}
    public void afectar(ProyectilAmigo proyectilAmigo) {}
    public void afectar(ProyectilEnemigo proyectilEnemigo) {}
}