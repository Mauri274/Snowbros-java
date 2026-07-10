package Fabricas;

import Estructura.Escaleras;
import Estructura.Pared;
import Estructura.ParedDestructible;
import Estructura.PlataformaEstatica;
import Estructura.PlataformaMovil;
import Estructura.PlataformaQuebradiza;
import Estructura.SueloResbaladizo;
import Estructura.Trampa;
import Juego.Fondo;
import Juego.Juego;
import Juego.Puntaje;
import Juego.Controladores.ControladorEnemigo;
import Juego.Controladores.ControladorObstaculo;
import Juego.Controladores.ControladorPlataforma;
import Juego.Controladores.ControladorPocionVerde;
import Personajes.BolaEnMovimiento;
import Personajes.Enemigo.Calabaza;
import Personajes.Enemigo.DemonioRojo;
import Personajes.Enemigo.Fantasma;
import Personajes.Enemigo.Kamakichi;
import Personajes.Enemigo.Moghera;
import Personajes.Enemigo.RanaFuego;
import Personajes.Enemigo.TrollAmarillo;
import Personajes.Jugador;
import PowerUp.Fruta;
import PowerUp.PocionAzul;
import PowerUp.PocionRoja;
import PowerUp.PocionVerde;
import PowerUp.VidaExtra;
import Proyectil.BolaDeFuego;
import Proyectil.Bomba;
import Proyectil.DisparoNieve;
import Proyectil.Fuego;


public class FabricaEntidades {

    protected FabricaSprites fabricaSprites;
    protected Juego miJuego;


    public FabricaEntidades(FabricaSprites fabricaSprites, Juego juego) {
        this.fabricaSprites = fabricaSprites;
        this.miJuego = juego;
    }

    public Sprites getEstadoCongelado() {
        return fabricaSprites.getEstadoCongelado();
    }
    
    public Fondo crearFondo(int numeroPiso) {
        Sprites sprites = fabricaSprites.getSpritesFondo();
        Fondo fondo = new Fondo(sprites, numeroPiso);
        return fondo;
    }

    public Jugador crearJugador(int x, int y) {
        Sprites sprites = fabricaSprites.getSpritesJugador();
        Jugador jugador = new Jugador(x, y, sprites);
        return jugador;
    }

    public RanaFuego crearRanaFuego(int x, int y, ControladorEnemigo controladorEnemigo) {
        Sprites sprites = fabricaSprites.getSpritesRanaFuego();
        RanaFuego ranaFuego = new RanaFuego(x, y, sprites, miJuego.getPuntaje(), controladorEnemigo);
        return ranaFuego;
    }

    public TrollAmarillo crearTrollAmarillo(int x, int y, ControladorEnemigo controladorEnemigo) {
        Sprites sprites = fabricaSprites.getSpritesTrollAmarillo();
        TrollAmarillo trollAmarillo = new TrollAmarillo(x, y, sprites, miJuego.getPuntaje(), controladorEnemigo);
        return trollAmarillo;
    }

    public DemonioRojo crearDemonioRojo(int x, int y, ControladorEnemigo controladorEnemigo) {
        Sprites sprites = fabricaSprites.getSpritesDemonioRojo();
        DemonioRojo demonioRojo = new DemonioRojo(x, y, sprites, miJuego.getPuntaje(), controladorEnemigo);
        return demonioRojo;
    }

    public Calabaza crearCalabaza(int x, int y) {
        Sprites sprites = fabricaSprites.getSpritesCalabaza();
        Calabaza calabaza = new Calabaza(x, y, sprites, miJuego.getPuntaje(), miJuego.getPiso(), miJuego.getPiso());
        return calabaza;
    }

    public Fantasma crearFantasma(int x, int y, ControladorEnemigo controladorEnemigo) {
        Sprites sprites = fabricaSprites.getSpritesFantasma();
        Fantasma fantasma = new Fantasma(x, y, sprites, miJuego.getPuntaje(), controladorEnemigo);
        return fantasma;
    }

    public Kamakichi crearKamakichi(int x, int y, ControladorEnemigo controladorEnemigo) {
        Sprites sprites = fabricaSprites.getSpritesKamakichi();
        Kamakichi kamakichi = new Kamakichi(x, y, sprites, miJuego.getPuntaje(), controladorEnemigo);
        return kamakichi;
    }

    public Moghera crearMoghera(int x, int y, ControladorEnemigo controladorEnemigo) {
        Sprites sprites = fabricaSprites.getSpritesMoghera();
        Moghera moghera = new Moghera(x, y, sprites, miJuego.getPuntaje(), controladorEnemigo);
        return moghera;
    }

    public Escaleras crearEscaleras(int x, int yInicio, int yFin, ControladorObstaculo controladorObstaculos) {
        Sprites sprites = fabricaSprites.getSpritesEscaleras();
        Escaleras escaleras = new Escaleras(x, yInicio, yFin, sprites, controladorObstaculos);
        return escaleras;
    }

    public ParedDestructible crearParedDestructible(int x, int y, ControladorObstaculo controladorObstaculos) {
        Sprites sprites = fabricaSprites.getSpritesParedDestructible();
        ParedDestructible paredDestructible = new ParedDestructible(x, y, sprites, controladorObstaculos, miJuego.getPuntaje());
        return paredDestructible;
    }
    
    public Pared crearPared(int x, int y, ControladorObstaculo controladorObstaculos) {
        Sprites sprites = fabricaSprites.getSpritesPared();
        Pared pared = new Pared(x, y, sprites, controladorObstaculos);
        return pared;
    }

    public SueloResbaladizo crearSueloResbaladizo(int x, int y, ControladorObstaculo controladorObstaculos) {
        Sprites sprites = fabricaSprites.getSpritesSueloResbaladizo();
        SueloResbaladizo sueloResbaladizo = new SueloResbaladizo(x, y, sprites, controladorObstaculos);
        return sueloResbaladizo;
    }

    public Trampa crearTrampa(int x, int y, ControladorObstaculo controladorObstaculos){
        Sprites sprites = fabricaSprites.getSpritesTrampa();
        Trampa trampa = new Trampa(x, y, sprites, controladorObstaculos);
        return trampa;
    }

    public PlataformaQuebradiza crearPlataformaQuebradiza(int xInicio, int xFin, int y, ControladorPlataforma controladorPlataforma){
        Sprites sprites = fabricaSprites.getSpritesPlataformaQuebradiza();
        PlataformaQuebradiza plataformaQuebradiza = new PlataformaQuebradiza(xInicio, xFin, y, sprites, controladorPlataforma, miJuego.getPuntaje());
        return plataformaQuebradiza;
    }

    public PlataformaMovil crearPlataformaMovil(int xInicio, int xFin, int y, ControladorPlataforma controladorPlataforma){
        Sprites sprites = fabricaSprites.getSpritesPlataformaMovil();
        PlataformaMovil plataformaMovil = new PlataformaMovil(xInicio, xFin, y, sprites, controladorPlataforma, miJuego.getPuntaje());
        return plataformaMovil;
    }

    public PlataformaEstatica crearPlataformaEstatica(int xInicio, int xFin, int y, ControladorPlataforma controladorPlataforma){
        Sprites sprites = fabricaSprites.getSpritesPlataformaEstatica();
        PlataformaEstatica plataformaEstatica = new PlataformaEstatica(xInicio, xFin, y, sprites, controladorPlataforma);
        return plataformaEstatica;
    }

    public DisparoNieve crearDisparoNieve(int x, int y, int direccion, int danio) {
        Sprites sprites = fabricaSprites.getSpritesDisparoNieve();
        DisparoNieve disparoNieve = new DisparoNieve(x, y, sprites, direccion, danio, miJuego.getPiso());
        return disparoNieve;
    }

    public Bomba crearBomba(int x, int y, int direccion) {
        Sprites sprites = fabricaSprites.getSpritesBomba();
        Bomba bomba = new Bomba(x, y, sprites, direccion, miJuego.getPiso());
        return bomba;
    }

    public BolaDeFuego crearBolaDeFuego(int x, int y, int direccion) {
        Sprites sprites = fabricaSprites.getSpritesBolaFuego();
        BolaDeFuego bolaFuego = new BolaDeFuego(x, y, sprites, direccion, miJuego.getPiso());
        return bolaFuego;
    }

    public Fuego crearFuego(int x, int y, int direccion) {
        Sprites sprites = fabricaSprites.getSpritesBolaFuego();
        Fuego fuego = new Fuego(x, y, sprites, direccion, miJuego.getPiso());
        return fuego;
    }

    public BolaEnMovimiento crearBolaEnMovimiento(int x, int y, int direccion) {
        Sprites sprites = fabricaSprites.getSpritesBolaMovimiento();
        BolaEnMovimiento bolaMovimiento = new BolaEnMovimiento(x, y, sprites, direccion, miJuego.getPiso());
        return bolaMovimiento;
    }

    public PocionAzul crearPocionAzul(int x, int y, Puntaje puntaje) {
        Sprites sprites = fabricaSprites.getSpritesPocionAzul();
        PocionAzul pocionAzul = new PocionAzul(x, y, sprites, puntaje, miJuego.getPiso());
        return pocionAzul;
    }

    public PocionRoja crearPocionRoja(int x, int y, Puntaje puntaje) {
        Sprites sprites = fabricaSprites.getSpritesPocionRoja();
        PocionRoja pocionRoja = new PocionRoja(x, y, sprites, puntaje, miJuego.getPiso());
        return pocionRoja;
    }

    public PocionVerde crearPocionVerde(int x, int y, Puntaje puntaje, ControladorPocionVerde controladorPocionVerde) {
        Sprites sprites = fabricaSprites.getSpritesPocionVerde();
        PocionVerde pocionVerde = new PocionVerde(x, y, sprites, puntaje,miJuego.getPiso(), controladorPocionVerde);
        return pocionVerde;
    }

    public Fruta crearFruta(int x, int y, Puntaje puntaje) {
        Sprites sprites = fabricaSprites.getSpritesFruta();
        Fruta fruta = new Fruta(x, y, sprites, puntaje, miJuego.getPiso());
        return fruta;
    }

    public VidaExtra crearVidaExtra(int x, int y, Puntaje puntaje) {
        Sprites sprites = fabricaSprites.getSpritesVida();
        VidaExtra vidaExtra = new VidaExtra(x, y, sprites, puntaje, miJuego.getPiso());
        return vidaExtra;
    }
}
