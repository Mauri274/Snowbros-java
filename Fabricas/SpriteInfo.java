package Fabricas;

public class SpriteInfo {

    protected String rutaImagen;
    protected int framesTotales;
    protected int velocidadAnimacion;


    public SpriteInfo(String rutaImagen, int velocidadAnimacion) {
        this.rutaImagen = rutaImagen;
        framesTotales = parsearFramesDesdeRuta(rutaImagen);
        this.velocidadAnimacion = velocidadAnimacion;
    }

    public SpriteInfo(String rutaImagen) {
        this(rutaImagen, 6);
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public int getframesTotales() {
        return framesTotales;
    }

    public int getVelocidadAnimacion() {
        return velocidadAnimacion;
    }

    protected int parsearFramesDesdeRuta(String ruta) {
        int frames = 1;
        try {
            String nombreArchivo = ruta.substring(ruta.lastIndexOf('/') + 1);
            String sinExtension = nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.'));
            int indiceDelimitador = sinExtension.lastIndexOf('_');
            String ultimaParte = sinExtension.substring(indiceDelimitador + 1);
            
            if (ultimaParte.endsWith("f")) {
                String numeroString = ultimaParte.substring(0, ultimaParte.length() - 1);
                frames = Integer.parseInt(numeroString);
            }
        } catch (Exception e) {}
        return frames;
    }

}