package Estados.EstadoEnemigo;

import Personajes.Enemigo.Enemigo;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEstado;
import Personajes.Jugador;

public class Saltando extends EstadoEnemigo{

    protected final int VELOCIDAD_INICIAL_DE_SALTO = 20;
    protected int velocidadActualDeSalto;


    public Saltando(Enemigo enemigo) {
        super(enemigo);
        estadoActual = ConstantesEstado.SALTANDO;
        enemigo.getSprites().setEstadoActual(estadoActual);
        this.velocidadActualDeSalto = VELOCIDAD_INICIAL_DE_SALTO;
    }

    public void actualizar() {
        aplicarMovimientoHorizontal();
        aplicarSalto();
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

    protected void aplicarSalto() {
        if (velocidadActualDeSalto >= 0) {
            enemigo.setPosicion(enemigo.getX(), enemigo.getY() + velocidadActualDeSalto);
            velocidadActualDeSalto--;
        }
        else
            enemigo.setEstadoActual(new Cayendo(enemigo));
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
