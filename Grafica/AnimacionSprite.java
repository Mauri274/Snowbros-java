package Grafica;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class AnimacionSprite {
    
    protected BufferedImage imagenEntera;
    protected int framesTotales;
    protected int anchoFrame;
    protected int altoFrame;
    protected int velocidadAnimacion;

    protected int frameActual;
    protected int contadorCiclos;

    protected static final int VELOCIDAD_DEFECTO_ANIMACION = 6;
    
    protected static Map<String, BufferedImage> cacheDeImagenes = new HashMap<>();


    public AnimacionSprite(String ruta, int frames, int velocidadAnimacion) {
        this.velocidadAnimacion = velocidadAnimacion;
        inicializar(ruta, frames);
    }

    public AnimacionSprite(String ruta, int frames) {
        this.velocidadAnimacion = VELOCIDAD_DEFECTO_ANIMACION;
        inicializar(ruta, frames);
    }

    protected void inicializar(String ruta, int frames) {
        this.framesTotales = frames;
        this.frameActual = 0;
        this.contadorCiclos = 0;

        try {
            if (cacheDeImagenes.containsKey(ruta)) {
                this.imagenEntera = cacheDeImagenes.get(ruta);
            }
            else {
                URL url = getClass().getClassLoader().getResource(ruta);
                if (url == null) {
                    System.err.println("No se pudo cargar la hoja de sprites: " + ruta);
                    return;
                }
                this.imagenEntera = ImageIO.read(url);
                cacheDeImagenes.put(ruta, this.imagenEntera);
            }

            if (this.imagenEntera != null) {
                this.anchoFrame = imagenEntera.getWidth() / this.framesTotales;
                this.altoFrame = imagenEntera.getHeight();
            }
        } catch (Exception e) {
            System.err.println("Error al cargar la hoja de sprites: " + ruta);
        }
    }

    public void actualizarFrame() {
        if (framesTotales > 1) {
            contadorCiclos++;
            if (contadorCiclos >= velocidadAnimacion) {
                contadorCiclos = 0;
                frameActual = (frameActual + 1) % framesTotales;
            }
        }
    }

    public BufferedImage getFrameActual() {
        if (imagenEntera == null) {
            return null;
        }
        return imagenEntera.getSubimage(
            frameActual * anchoFrame,
            0,
            anchoFrame,
            altoFrame
        );
    }
}