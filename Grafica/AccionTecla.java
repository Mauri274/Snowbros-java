package Grafica;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesTeclado;
import Juego.Controladores.ControladorJuego;

public class AccionTecla extends AbstractAction {
    
    protected  ControladorJuego controladorJuego;
    protected final int ACCION;
    protected final int TIPO_EVENTO;
    

    public AccionTecla(ControladorJuego controladorJuego, int accion, int tipoEvento) {
        this.controladorJuego = controladorJuego;
        ACCION = accion;
        TIPO_EVENTO = tipoEvento;
    }

    public void actionPerformed(ActionEvent e) {
        if (TIPO_EVENTO == ConstantesTeclado.PRESIONAR_TECLA) {
            switch (ACCION) {
                case ConstantesTeclado.MOVER_IZQUIERDA:
                    controladorJuego.moverAJugador(ConstantesDireccion.IZQUIERDA);
                    break;
                case ConstantesTeclado.MOVER_DERECHA:
                    controladorJuego.moverAJugador(ConstantesDireccion.DERECHA);
                    break;
                case ConstantesTeclado.SALTAR:
                    controladorJuego.saltarAJugador();
                    break;
                case ConstantesTeclado.DISPARAR:
                    controladorJuego.dispararAJugador();
                    break;
            }
        }
        else
            if (TIPO_EVENTO == ConstantesTeclado.SOLTAR_TECLA) {
                switch (ACCION) {
                    case ConstantesTeclado.MOVER_IZQUIERDA, ConstantesTeclado.MOVER_DERECHA:
                        controladorJuego.detenerMovimientoJugador(ACCION);
                        break;
                    case ConstantesTeclado.DISPARAR :
                        controladorJuego.detenerDisparoJugador();
                        break;
                    case ConstantesTeclado.SALTAR:
                        break;
                }
            }
    }
}