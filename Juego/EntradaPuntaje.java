package Juego;

public class EntradaPuntaje implements Comparable<EntradaPuntaje> {

    protected String nombre;
    protected int puntaje;

    
    public EntradaPuntaje(String nombre, int puntaje) {
        this.nombre = nombre.trim().toUpperCase();
        this.puntaje = puntaje;
    }

    public int getPuntaje(){
        return puntaje;
    }

    public int compareTo(EntradaPuntaje otro) {
        return Integer.compare(otro.puntaje, this.puntaje);
    }

}