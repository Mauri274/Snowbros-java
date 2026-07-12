package Visitor;

import Personajes.Enemigo.Enemigo;
import Proyectil.ProyectilAmigo;
import Proyectil.ProyectilEnemigo;


import Personajes.BolaEnMovimiento;
import Personajes.Jugador;

public interface Colisionable extends EntidadConHitbox{

    public void afectar(Jugador jugador);
    public void afectar(Enemigo enemigo);
    public void afectar(ProyectilAmigo proyectilAmigo);
    public void afectar(ProyectilEnemigo proyectilEnemigo);
    public void afectar(BolaEnMovimiento bolaEnMovimiento);
}