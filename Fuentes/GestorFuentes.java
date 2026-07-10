package Fuentes;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

public class GestorFuentes {

    private static Font fuentePixel;

    public static Font getFuentePixel(float tamaño) {
        if (fuentePixel == null) {
            try {
                InputStream is = GestorFuentes.class.getResourceAsStream("/Fuentes/Retro Gaming.ttf");
                if (is == null) {
                    throw new RuntimeException("No se encontró /Fuentes/Retro Gaming.ttf en el classpath");
                }
                fuentePixel = Font.createFont(Font.TRUETYPE_FONT, is);

                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(fuentePixel);

            } catch (Exception e) {
                e.printStackTrace();
                fuentePixel = new Font("Arial", Font.PLAIN, 12);
            }
        }
        return fuentePixel.deriveFont(tamaño);
    }
}
