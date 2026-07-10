package Juego;

import Estructura.Plataforma;
import Grafica.Constantes.ConstantesVistas;
import Personajes.Personaje;
import Visitor.Colisionable;
import Visitor.Colisionador;
import Visitor.EntidadConHitbox;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GrillaColisiones {

    protected Celda [][] grillaColisiones;
    protected int filas, columnas;
    protected int anchoCelda, altoCelda;

    public GrillaColisiones() {
        filas = 10;
        columnas = 10;
        anchoCelda = ConstantesVistas.PANEL_PISO_ANCHO / columnas;
        altoCelda = ConstantesVistas.PANEL_PISO_ALTO / filas;
        grillaColisiones = new Celda [filas][columnas];
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                grillaColisiones[i][j] = new Celda();
    }

    public void chequearColisiones() {
        Set<Long> paresProcesados = new HashSet<>();

        for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    Celda celda = grillaColisiones[i][j];
                    var listaColisionadores = celda.getListaColisionadores();
                    var listaColisionables  = celda.getListaColisionables();
                if (listaColisionadores.isEmpty() || listaColisionables.isEmpty()) continue;

                for (var colisionador : listaColisionadores) {
                    for (var colisionable : listaColisionables) {
                        long key = (((long)System.identityHashCode(colisionador)) << 32)
                                ^ (System.identityHashCode(colisionable) & 0xffffffffL);
                        if (!paresProcesados.add(key)) continue;

                        if (colisionador.getHitbox().intersects(colisionable.getHitbox())) {
                            colisionador.colisionar(colisionable);
                        }
                    }
                }
            }
        }
    }

    public Plataforma getPlataformaColisionando(Personaje personaje) {
        Iterable <Celda> iterableCeldas = asiganarCeldas(personaje);
            for (Celda celda : iterableCeldas)
                for (Plataforma plataforma : celda.getListaPlataformas())
                    if (personaje.getHitbox().intersects(plataforma.getHitbox()) && personaje.getY() > plataforma.getY()){
                        return plataforma;
                    }
        return null;
    }

    public void addColisionador(Colisionador colisionador) {
        Iterable<Celda> iterableCeldas = asiganarCeldas(colisionador);
        for (Celda celda : iterableCeldas)
            celda.addColisionador(colisionador);
    }

    public void addColisionable(Colisionable colisionable) {
        Iterable<Celda> iterableCeldas = asiganarCeldas(colisionable);
        for (Celda celda : iterableCeldas)
            celda.addColisionables(colisionable);
    }

    public void addPlataforma(Plataforma plataforma) {
        Iterable<Celda> iterableCeldas = asiganarCeldas(plataforma);
        for (Celda celda : iterableCeldas) {
            celda.addPlataforma(plataforma);
        }
    }

    public void limpiarGrilla() {
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                grillaColisiones[i][j].limpiarCelda();
    }

    protected Iterable<Celda> asiganarCeldas(EntidadConHitbox entidad) {
        List<Celda> celdasRetorno = new LinkedList<>();
        Rectangle hitbox = entidad.getHitbox();

        int celdaMinimaX = hitbox.x / anchoCelda;
        int celdaMinimaY = hitbox.y / altoCelda;

        int celdaMaximaX = (hitbox.x + hitbox.width) / anchoCelda;
        int celdaMaximaY = (hitbox.y + hitbox.height) / altoCelda ;

        for (int y = celdaMinimaY; y <= celdaMaximaY; y++)
            for (int x = celdaMinimaX; x <= celdaMaximaX; x++)
                if (x >= 0 && x < columnas && y >= 0 && y < filas)
                    celdasRetorno.add(grillaColisiones[y][x]);
        return celdasRetorno;
    }
}
