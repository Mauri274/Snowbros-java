package Grafica;

import Fabricas.SpriteInfo;
import Grafica.Constantes.ConstantesDireccion;
import Grafica.Constantes.ConstantesEstado;
import Juego.EntidadLogica;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

public class ObserverGrafico extends JLabel implements Observer {

    protected EntidadLogica entidadObservada;
    protected int estadoCache;
    protected AnimacionSprite animacionSpriteActual;

    public ObserverGrafico(EntidadLogica entidadObservada) {
        super();
        this.entidadObservada = entidadObservada;
        estadoCache = ConstantesEstado.INICIAR_CACHE;
        setOpaque(false);
        actualizar();
    }

    protected void actualizarAnimacion() {
        int estadoActual = entidadObservada.getSprites().getEstadoActual();
        if (estadoActual != estadoCache) {
            estadoCache = estadoActual;
            SpriteInfo spriteInfo = entidadObservada.getSprites().getSpriteInfo();
            animacionSpriteActual = new AnimacionSprite(spriteInfo.getRutaImagen(), spriteInfo.getframesTotales(), spriteInfo.getVelocidadAnimacion());
        }
        animacionSpriteActual.actualizarFrame();
    }

    protected void actualizarPosicion() {
        int x = entidadObservada.getX();
        int y = entidadObservada.getY();

        int ancho = entidadObservada.getAnchoSprite();
        int alto = entidadObservada.getAltoSprite();

        int xAjustada = AjustarPosicionEnPantalla.ajustarSpriteX(x);
        int yAjustada = AjustarPosicionEnPantalla.ajustarSpriteY(y, alto);

        setBounds(xAjustada, yAjustada, ancho, alto);
    }

    public void actualizar() { 
        setVisible(entidadObservada.esVisible());    
        actualizarPosicion();
        actualizarAnimacion();
        repaint();
    }

    public void desaparecer() {
        
        Container contenedor = getParent();
    
        if (contenedor != null) {
            contenedor.remove(this);
            contenedor.repaint(); 
        }
    }

    protected void paintComponent(Graphics g) {
        BufferedImage frame = animacionSpriteActual.getFrameActual();
        Graphics2D g2d = (Graphics2D) g.create();

        AffineTransform oldTransform = g2d.getTransform();
        
        if (entidadObservada.getDireccion() == ConstantesDireccion.DERECHA || entidadObservada.getDireccion() == ConstantesDireccion.QUIETO_DERECHA) {
            g2d.scale(-1, 1);
            g2d.translate(-entidadObservada.getAnchoSprite(), 0);
        }
        
        g2d.drawImage(frame, 0, 0, entidadObservada.getAnchoSprite(), entidadObservada.getAltoSprite(), this);
        g2d.setTransform(oldTransform);
        g2d.dispose();
    }
}



