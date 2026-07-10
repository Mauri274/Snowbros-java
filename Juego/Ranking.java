package Juego;

import java.io.*;
import java.util.*;

public class Ranking {

    protected List<EntradaPuntaje> topPuntajes;
    protected static final int MAX_RANKING_SIZE = 5;

    public Ranking(){
        topPuntajes = new ArrayList<>();
    }

    public void ingresarJugador(String nombre, int puntos){
        topPuntajes.add(new EntradaPuntaje(nombre, puntos));
    }

    protected void ordenarYRecortar() {
        Collections.sort(topPuntajes);
        if (topPuntajes.size() > MAX_RANKING_SIZE) {
            topPuntajes = new ArrayList<>(topPuntajes.subList(0, MAX_RANKING_SIZE));
        }
    }
    
    public List<String> getTop5ComoStrings() {
        ordenarYRecortar();
        List<String> lista = new ArrayList<>();
        int pos = 1;
        for (EntradaPuntaje entrada : topPuntajes) {
            lista.add(String.format("%d. %s - %d", pos++, entrada.nombre, entrada.puntaje));
        }
        return lista;
    }

    public boolean esPuntajeAlto(int puntaje) {
        if (topPuntajes.size() < MAX_RANKING_SIZE) {
            return true;
        }
        ordenarYRecortar();
        return puntaje > topPuntajes.get(topPuntajes.size() - 1).getPuntaje();
    }

    public void guardar(String nombreModo) throws IOException {
        ordenarYRecortar();
        
        Properties propiedades = new Properties();
        for (int i = 0; i < topPuntajes.size(); i++) {
            EntradaPuntaje entry = topPuntajes.get(i);
            propiedades.setProperty("top" + i + ".nombre", entry.nombre);
            propiedades.setProperty("top" + i + ".puntaje", String.valueOf(entry.puntaje));
        }

        String nombreArchivo = "ranking_" + nombreModo + ".properties";
        try (OutputStream output = new FileOutputStream(nombreArchivo)) {
            propiedades.store(output, "Ranking Top 5 - " + nombreModo);
        }
    }

    public void cargar(String nombreModo) throws IOException {
        topPuntajes.clear();
        Properties propiedades = new Properties();
        String nombreArchivo = "ranking_" + nombreModo + ".properties";
        
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            return;
        }

        try (InputStream input = new FileInputStream(archivo)) {
            propiedades.load(input);
            for (int i = 0; i < MAX_RANKING_SIZE; i++) {
                String nombre = propiedades.getProperty("top" + i + ".nombre");
                String puntajeStr = propiedades.getProperty("top" + i + ".puntaje");
                
                if (nombre != null && puntajeStr != null) {
                    topPuntajes.add(new EntradaPuntaje(nombre, Integer.parseInt(puntajeStr)));
                }
            }
        }
    }

}