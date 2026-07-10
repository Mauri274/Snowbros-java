package Grafica;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ConversorAGraphics {

    public static BufferedImage pasarABufferedImage(Image imagen) {

        BufferedImage bufferedimage = new BufferedImage(
            imagen.getWidth(null),
            imagen.getHeight(null),
            BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2d = bufferedimage.createGraphics();
        g2d.drawImage(imagen, 0, 0, null);
        g2d.dispose();

        return bufferedimage;
    }
}