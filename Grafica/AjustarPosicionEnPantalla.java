package Grafica;

import Grafica.Constantes.ConstantesVistas;

public class AjustarPosicionEnPantalla {
    
    public static int ajustarSpriteX(int x) {
        return x;
    }

    public static int ajustarSpriteY(int y, int altoSprite) {
        return ConstantesVistas.PANEL_PISO_ALTO - y - altoSprite - ConstantesVistas.PANEL_PISO_SUELO_LOGICO;
    }

    public static int ajustarHitboxXCentrado(int x, int anchoHitbox, int anchoSpriteEntidad) {
        int posicionAjustada = x + (anchoSpriteEntidad - anchoHitbox) / 2;
        return posicionAjustada;
    }

    public static int ajustarHitboxYPlataforma(int y, int altoHitbox, int altoSpriteEntidad) {
        int posicionAjustada = ConstantesVistas.PANEL_PISO_ALTO - y - altoSpriteEntidad - altoHitbox - ConstantesVistas.PANEL_PISO_SUELO_LOGICO;
        return posicionAjustada;
    }

    public static int ajustarHitboxYPersonaje(int y, int altoHitbox, int altoSpriteEntidad) {
        int posicionAjustada = ConstantesVistas.PANEL_PISO_ALTO - y - altoHitbox - 10 - ConstantesVistas.PANEL_PISO_SUELO_LOGICO;
        return posicionAjustada;
    }

    public static int ajustarHitboxYCentrado(int y, int altoHitbox, int altoSpriteEntidad) {
        int posicionAjustada = ConstantesVistas.PANEL_PISO_ALTO - y - altoSpriteEntidad + (altoSpriteEntidad - altoHitbox) / 2 -  ConstantesVistas.PANEL_PISO_SUELO_LOGICO;
        return posicionAjustada;
    }
}