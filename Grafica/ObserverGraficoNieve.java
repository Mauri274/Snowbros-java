package Grafica;


import Fabricas.SpriteInfo;
import Fabricas.Sprites;
import Juego.EntidadLogica;

public class ObserverGraficoNieve extends ObserverGrafico {
    protected int estadoActual;
    protected Sprites sprites;
    

    public ObserverGraficoNieve(EntidadLogica entidadObservada, Sprites sprites) {
        super(entidadObservada);
        this.sprites = sprites;
        SpriteInfo spriteInfo = sprites.getSpriteInfo();
        animacionSpriteActual = new AnimacionSprite(spriteInfo.getRutaImagen(), spriteInfo.getframesTotales());
    }

    public void setEstadoActual(int estado) {
        estadoActual = estado;
        sprites.setEstadoActual(estado);
    }

    protected void actualizarAnimacion() {
        if (sprites != null) {
            if (estadoActual != estadoCache) {
                estadoCache = estadoActual;
                SpriteInfo spriteInfo = sprites.getSpriteInfo();
                animacionSpriteActual = new AnimacionSprite(spriteInfo.getRutaImagen(), spriteInfo.getframesTotales());
            }
            animacionSpriteActual.actualizarFrame();
        }
    }
}
