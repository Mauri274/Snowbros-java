package Fabricas;



public abstract class FabricaSprites {
    
    protected String rutaACarpeta;

    public abstract Sprites getEstadoCongelado();

    public abstract Sprites getSpritesJugador();
    public abstract Sprites getSpritesDemonioRojo();
    public abstract Sprites getSpritesTrollAmarillo();
    public abstract Sprites getSpritesCalabaza();
    public abstract Sprites getSpritesRanaFuego();
    public abstract Sprites getSpritesFantasma();
    public abstract Sprites getSpritesKamakichi();
    public abstract Sprites getSpritesMoghera();

    public abstract Sprites getSpritesEscaleras();
    public abstract Sprites getSpritesPared();
    public abstract Sprites getSpritesSueloResbaladizo();
    public abstract Sprites getSpritesParedDestructible();
    public abstract Sprites getSpritesPlataformaEstatica();
    public abstract Sprites getSpritesPlataformaMovil();
    public abstract Sprites getSpritesPlataformaQuebradiza();
    public abstract Sprites getSpritesTrampa();

    public abstract Sprites getSpritesBolaMovimiento();
    
    public abstract Sprites getSpritesFondo();

    public abstract Sprites getSpritesDisparoNieve();
    public abstract Sprites getSpritesBomba();
    public abstract Sprites getSpritesBolaFuego();
    public abstract Sprites getSpritesFuego();
    
    public abstract Sprites getSpritesPocionVerde();
    public abstract Sprites getSpritesPocionRoja();
    public abstract Sprites getSpritesPocionAzul();
    public abstract Sprites getSpritesFruta();
    public abstract Sprites getSpritesVida();

}
