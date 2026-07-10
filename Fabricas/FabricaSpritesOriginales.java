package Fabricas;

import java.util.HashMap;
import java.util.Map;

import Grafica.Constantes.ConstantesEstado;

public class FabricaSpritesOriginales extends FabricaSprites{

    public FabricaSpritesOriginales(){
        rutaACarpeta = "Imagenes/SpritesOriginales";
    }
        
    public Sprites getEstadoCongelado() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer,SpriteInfo>();
        spritesConEstados.put(ConstantesEstado.CONGELADO_1, new SpriteInfo(rutaACarpeta + "/Proyectiles/enemigo-congelado-1.png"));
        spritesConEstados.put(ConstantesEstado.CONGELADO_2, new SpriteInfo(rutaACarpeta + "/Proyectiles/enemigo-congelado-2.png"));
        spritesConEstados.put(ConstantesEstado.CONGELADO_3, new SpriteInfo(rutaACarpeta + "/Proyectiles/enemigo-congelado-3.png"));

        return new Sprites(spritesConEstados, ConstantesEstado.CONGELADO_1);
    }

    public Sprites getSpritesJugador() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer,SpriteInfo>();
        spritesConEstados.put(ConstantesEstado.CAMINANDO, new SpriteInfo(rutaACarpeta + "/Snow/snow-camina_3f.png"));
        spritesConEstados.put(ConstantesEstado.CAYENDO, new SpriteInfo(rutaACarpeta + "/Snow/snow-cae.png"));
        spritesConEstados.put(ConstantesEstado.DISPARANDO, new SpriteInfo(rutaACarpeta + "/Snow/snow-dispara_5f.png"));
        spritesConEstados.put(ConstantesEstado.EMPUJANDO_BOLA, new SpriteInfo(rutaACarpeta + "/Snow/snow-empuja-bola_3f.png"));
        spritesConEstados.put(ConstantesEstado.SALTANDO, new SpriteInfo(rutaACarpeta + "/Snow/snow-salta_6f.png"));
        spritesConEstados.put(ConstantesEstado.QUIETO, new SpriteInfo(rutaACarpeta + "/Snow/snow-quieto.png"));
        spritesConEstados.put(ConstantesEstado.MUERE_SIN_VIDA, new SpriteInfo(rutaACarpeta + "/Snow/snow-muere-sin-vidas_5f.png"));
        spritesConEstados.put(ConstantesEstado.MUERE_CON_VIDA, new SpriteInfo(rutaACarpeta + "/Snow/snow-muere-con-vidas_7f.png"));
        spritesConEstados.put(ConstantesEstado.DENTRO_BOLA, new SpriteInfo(rutaACarpeta + "/Snow/snow-dentroBola_2f.png"));

        return new Sprites(spritesConEstados, ConstantesEstado.QUIETO);
    }

    public Sprites getSpritesDemonioRojo() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer,SpriteInfo>();
        spritesConEstados.put(ConstantesEstado.QUIETO, new SpriteInfo(rutaACarpeta + "/Enemigos/DemonioRojo/demonio-rojo-quieto.png"));
        spritesConEstados.put(ConstantesEstado.CAMINANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/DemonioRojo/demonio-rojo-camina_3f.png"));
        spritesConEstados.put(ConstantesEstado.ATURDIDO, new SpriteInfo(rutaACarpeta + "/Enemigos/DemonioRojo/demonio-rojo-aturdido_2f.png"));
        spritesConEstados.put(ConstantesEstado.CAYENDO, new SpriteInfo(rutaACarpeta + "/Enemigos/DemonioRojo/demonio-rojo-cae.png"));
        spritesConEstados.put(ConstantesEstado.BOLA_QUIETA, new SpriteInfo(rutaACarpeta + "/Proyectiles/bolaquieta.png"));
        spritesConEstados.put(ConstantesEstado.BOLAENMOVIMIENTO_AVANZA, new SpriteInfo(rutaACarpeta + "/Proyectiles/bola-movimiento_4f.png"));

        return new Sprites(spritesConEstados, ConstantesEstado.QUIETO);
    }

    public Sprites getSpritesTrollAmarillo() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer,SpriteInfo>();
        spritesConEstados.put(ConstantesEstado.QUIETO, new SpriteInfo(rutaACarpeta + "/Enemigos/TrollAmarillo/troll-amarillo-quieto.png"));
        spritesConEstados.put(ConstantesEstado.CAMINANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/TrollAmarillo/troll-amarillo-caminando_3f.png"));
        spritesConEstados.put(ConstantesEstado.SALTANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/TrollAmarillo/troll-amarillo-saltando.png"));
        spritesConEstados.put(ConstantesEstado.CAYENDO, new SpriteInfo(rutaACarpeta + "/Enemigos/TrollAmarillo/troll-amarillo-cayendo.png"));
        spritesConEstados.put(ConstantesEstado.ATURDIDO, new SpriteInfo(rutaACarpeta + "/Enemigos/TrollAmarillo/troll-amarillo-aturdido_2f.png"));
        spritesConEstados.put(ConstantesEstado.BOLA_QUIETA, new SpriteInfo(rutaACarpeta + "/Proyectiles/bolaquieta.png"));
        spritesConEstados.put(ConstantesEstado.BOLAENMOVIMIENTO_AVANZA, new SpriteInfo(rutaACarpeta + "/Proyectiles/bola-movimiento_4f.png"));

        return new Sprites(spritesConEstados, ConstantesEstado.QUIETO);
    }

    public Sprites getSpritesCalabaza() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer,SpriteInfo>();
        spritesConEstados.put(ConstantesEstado.CAMINANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/Calabaza/calabaza-flota.png"));
        spritesConEstados.put(ConstantesEstado.ATURDIDO, new SpriteInfo(rutaACarpeta + "/Enemigos/Calabaza/calabaza-flota.png"));
        spritesConEstados.put(ConstantesEstado.DISPARANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/Calabaza/calabaza-ataca.png"));

        return new Sprites(spritesConEstados, ConstantesEstado.QUIETO);
    }

    public Sprites getSpritesRanaFuego() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer,SpriteInfo>();
        spritesConEstados.put(ConstantesEstado.CAMINANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/RanaDeFuego/rana-fuego-caminando_3f.png", 12));
        spritesConEstados.put(ConstantesEstado.ATURDIDO, new SpriteInfo(rutaACarpeta + "/Enemigos/RanaDeFuego/rana-fuego-aturdida_2f.png"));
        spritesConEstados.put(ConstantesEstado.QUIETO, new SpriteInfo(rutaACarpeta + "/Enemigos/RanaDeFuego/rana-fuego-quieto.png"));
        spritesConEstados.put(ConstantesEstado.DISPARANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/RanaDeFuego/rana-fuego-disparando.png"));
        spritesConEstados.put(ConstantesEstado.SALTANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/RanaDeFuego/rana-fuego-salta.png"));
        spritesConEstados.put(ConstantesEstado.CAYENDO, new SpriteInfo(rutaACarpeta + "/Enemigos/RanaDeFuego/rana-fuego-cae.png"));
        spritesConEstados.put(ConstantesEstado.BOLA_QUIETA, new SpriteInfo(rutaACarpeta + "/Proyectiles/bolaquieta.png"));
        spritesConEstados.put(ConstantesEstado.BOLAENMOVIMIENTO_AVANZA, new SpriteInfo(rutaACarpeta + "/Proyectiles/bola-movimiento_4f.png"));
        
        return new Sprites(spritesConEstados, ConstantesEstado.QUIETO);
    }

    public Sprites getSpritesFantasma() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(ConstantesEstado.CAMINANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/Fantasma/fantasma-moviendo.png"));

        return new Sprites(spritesConEstados, ConstantesEstado.CAMINANDO);
    }

    public Sprites getSpritesKamakichi() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(ConstantesEstado.QUIETO, new SpriteInfo(rutaACarpeta + "/Enemigos/Kamakichi/kamakichi-quieto.png"));
        spritesConEstados.put(ConstantesEstado.DISPARANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/Kamakichi/kamakichi-atacando_4f.png"));

        return new Sprites(spritesConEstados, ConstantesEstado.QUIETO);
    }

    public Sprites getSpritesMoghera() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(ConstantesEstado.QUIETO, new SpriteInfo(rutaACarpeta + "/Enemigos/Moghera/moghera-quieto.png"));
        spritesConEstados.put(ConstantesEstado.DISPARANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/Moghera/moghera-atacando.png"));
        spritesConEstados.put(ConstantesEstado.SALTANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/Moghera/moghera-saltando.png"));
        spritesConEstados.put(ConstantesEstado.CAYENDO, new SpriteInfo(rutaACarpeta + "/Enemigos/Moghera/moghera-quieto.png"));
        spritesConEstados.put(ConstantesEstado.CAMINANDO, new SpriteInfo(rutaACarpeta + "/Enemigos/Moghera/moghera-camina_2f.png", 20));

        return new Sprites(spritesConEstados, ConstantesEstado.QUIETO);
    }

    public Sprites getSpritesEscaleras() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Estructuras/escalera.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesPared() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Estructuras/pared.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesSueloResbaladizo() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Estructuras/suelo-resbaladizo.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesParedDestructible() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Estructuras/pared-destructible.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesPlataformaEstatica() {
        Map<Integer,SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Estructuras/plataforma-estatica.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesPlataformaMovil() {
        Map<Integer,SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Estructuras/plataforma-movil.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesPlataformaQuebradiza() {
        Map<Integer,SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Estructuras/plataforma-quebradiza.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesTrampa() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Estructuras/trampa.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesFondo() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Fondos/fondo-piso-1.png"));
        spritesConEstados.put(2, new SpriteInfo(rutaACarpeta + "/Fondos/fondo-piso-2.png"));
        spritesConEstados.put(3, new SpriteInfo(rutaACarpeta + "/Fondos/fondo-piso-3.png"));
        spritesConEstados.put(4, new SpriteInfo(rutaACarpeta + "/Fondos/fondo-piso-4.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesDisparoNieve() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer,SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Proyectiles/disparo-nieve-normal_2f.png"));
        spritesConEstados.put(2, new SpriteInfo(rutaACarpeta + "/Proyectiles/disparo-nieve-powerup_2f.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesPocionVerde() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/PowerUps/pocion-verde_2f.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesPocionRoja() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/PowerUps/pocion-roja_2f.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesPocionAzul() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/PowerUps/pocion-azul_2f.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesFruta() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/PowerUps/fruta.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesVida() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/PowerUps/vida-extra.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesBomba() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Proyectiles/bomba.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesBolaFuego() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Proyectiles/bolaFuego_2f.png"));

        return new Sprites(spritesConEstados, 1);
    }

    public Sprites getSpritesBolaMovimiento() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(ConstantesEstado.BOLAENMOVIMIENTO_AVANZA, new SpriteInfo(rutaACarpeta + "/Proyectiles/bola-movimiento_4f.png"));
        spritesConEstados.put(ConstantesEstado.BOLAENMOVIMIENTO_CHOCA, new SpriteInfo(rutaACarpeta + "/Proyectiles/bola-choca_4f.png"));

        return new Sprites(spritesConEstados, ConstantesEstado.BOLAENMOVIMIENTO_AVANZA);
    }

    public Sprites getSpritesFuego() {
        Map<Integer, SpriteInfo> spritesConEstados = new HashMap<Integer, SpriteInfo>();
        spritesConEstados.put(1, new SpriteInfo(rutaACarpeta + "/Proyectiles/bolaFuego_2f.png"));

        return new Sprites(spritesConEstados, 1);
    }

}
