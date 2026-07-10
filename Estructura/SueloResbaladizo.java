package Estructura;

import Fabricas.Sprites;
import Grafica.Constantes.ConstantesEntidades;
import Juego.Controladores.ControladorObstaculo;
import Personajes.Enemigo.Enemigo;
import Personajes.Jugador;
import Proyectil.ProyectilAmigo;
import Proyectil.ProyectilEnemigo;

public class SueloResbaladizo extends Obstaculo{

    public SueloResbaladizo(int x, int y, Sprites sprites, ControladorObstaculo controladorObstaculos) {
        super(x, y, sprites, controladorObstaculos);
        crearHitbox(getAnchoHitbox(), getAltoHitbox(), getAnchoSprite(), getAltoSprite());
    }

    public void afectar(Jugador jugador) {
        jugador.setTiempoRalentizado(1);
        jugador.ralentizar();
    }

    public int getAnchoSprite() {
        return ConstantesEntidades.SUELO_RESBALADIZO_SPRITE_ANCHO;
    }

    public int getAltoSprite() {
        return ConstantesEntidades.SUELO_RESBALADIZO_SPRITE_ALTO;
    }

    public int getAnchoHitbox() {
        return ConstantesEntidades.SUELO_RESBALADIZO_HITBOX_ANCHO;
    }

    public int getAltoHitbox() {
        return ConstantesEntidades.SUELO_RESBALADIZO_HITBOX_ALTO;
    }

    public void afectar(Enemigo enemigo) {}
    public void afectar(ProyectilAmigo proyectilAmigo) {}
    public void afectar(ProyectilEnemigo proyectilEnemigo) {}

}