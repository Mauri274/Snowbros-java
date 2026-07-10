package Estados.EstadoEnemigo;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEntidades;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;

public class Cayendo extends EstadoEnemigo{

    public Cayendo(Enemigo enemigo) {
        super(enemigo);
        estadoActual = ConstantesEstado.CAYENDO;
        enemigo.getSprites().setEstadoActual(estadoActual);
    }

    public void actualizar() {
        aplicarMovimientoHorizontal();
        aplicarCaida();
        aterrizarSiHaySuelo();
    }

    protected void aplicarMovimientoHorizontal() {
        if (!enemigo.estaQuieto()) {
            int direccion = enemigo.getDireccion();
            int velocidad = enemigo.getVelocidad();
            enemigo.setPosicion(enemigo.getX() + (velocidad * direccion), enemigo.getY());
            rebotarPared();
        }
    }

    protected void rebotarPared() {
        if(enemigo.getX() <= 0){
            enemigo.setDireccion(ConstantesDireccion.DERECHA);
            enemigo.setPosicion(0, enemigo.getY());
        }
        else
            if(enemigo.getX() >= Grafica.Constantes.ConstantesVistas.PANEL_PISO_ANCHO - enemigo.getAnchoSprite()){
                enemigo.setDireccion(ConstantesDireccion.IZQUIERDA);
                enemigo.setPosicion(Grafica.Constantes.ConstantesVistas.PANEL_PISO_ANCHO - enemigo.getAnchoSprite(), enemigo.getY());
        }
    }

    protected void aterrizarSiHaySuelo() {
        if (enemigo.estaSobrePlataforma()) {
            int posicionY = enemigo.getPlataformaColisionando().getY() + ConstantesEntidades.PLATAFORMA_SPRITE_ALTO - 10;
            enemigo.setPosicion(enemigo.getX(), posicionY);
            aterrizar();
        } 
        else
            if (enemigo.estaSobreElSuelo()) {
                enemigo.setPosicion(enemigo.getX(),0);
                aterrizar();
            }
    }

    protected void aterrizar() {
        if (enemigo.estaQuieto())
            enemigo.setEstadoActual(new Quieto(enemigo));
        else
            enemigo.setEstadoActual(new Caminando(enemigo));
    }

    public void aturdir() {
        this.enemigo.setEstadoActual(new Aturdido(this.enemigo, this));
    }

    public void afectar(Jugador jugador) {
        jugador.recibirDanio();
    }
    
    public void mover() {}
    public void hacerBolaQuieta() {}
    public void detener() {}
    public void subirEscalera(int alturaVertical) {}
    public void afectar(Enemigo enemigo) {}
    public void disparar() {}
    public void saltar() {}
}
