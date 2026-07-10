package Generador;

import Fabricas.FabricaEntidades;
import Juego.Fondo;
import Juego.Piso;
import Juego.PisoJefe;
import Personajes.Jugador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneradorPiso {

    protected FabricaEntidades fabrica;
    protected List<Piso> listaPisos;
    protected int indiceSiguientePiso;
    protected Jugador jugador;


    public GeneradorPiso(FabricaEntidades fabrica, Jugador jugador, String rutaACarpeta) {
        this.fabrica = fabrica;
        this.indiceSiguientePiso = 0;
        this.jugador = jugador;
        listaPisos = cargarTodosLosPisosDesdeArchivo(rutaACarpeta);
    }

    public Piso generarSiguientePiso() {
        Piso siguientePiso = null;
        if (indiceSiguientePiso < listaPisos.size()) {
            siguientePiso = listaPisos.get(indiceSiguientePiso);
            siguientePiso.setJugador(jugador);
            indiceSiguientePiso++;
        }
        return siguientePiso;
    }

    protected List<Piso> cargarTodosLosPisosDesdeArchivo(String rutaArchivo) {
        List<Piso> pisosCargados = new ArrayList<>();
        Piso pisoActual = null;

        try (InputStream is = getClass().getResourceAsStream(rutaArchivo)) {
            if (is == null) { return pisosCargados; }
            BufferedReader lector = new BufferedReader(new InputStreamReader(is));
            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                if (linea.startsWith("Piso")) {
                    if (pisoActual != null) {
                        pisosCargados.add(pisoActual);
                    }
                    if(linea.startsWith("Piso J")){
                        pisoActual = new PisoJefe(linea);
                    }
                    else{
                        pisoActual = new Piso(linea);
                    }
                } else if (pisoActual != null) {
                    char primerCaracter = linea.charAt(0);
                    String datos = linea.substring(1);
                    switch (primerCaracter) {
                        case 'F': parsearLineaFondo(datos, pisoActual);
                            break;
                        case '%': parsearLineaPersonajes(datos, pisoActual);
                            break;
                        case '$': parsearLineaEstructuraRangoHorizontal(datos, pisoActual);
                            break;
                        case '#': parsearLineaEstructuraRangoVertical(datos, pisoActual);
                            break;
                        case '&': parsearLineaObstaculos(datos, pisoActual);
                            break;
                    }
                }
            }
            if (pisoActual != null)
                pisosCargados.add(pisoActual);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pisosCargados;
    }

    protected void parsearLineaFondo(String datos, Piso pisoActual) {
        try {
            int idFondo = Integer.parseInt(datos);
            Fondo fondoCreado = fabrica.crearFondo(idFondo);

            pisoActual.setFondo(fondoCreado);
        } catch (NumberFormatException e) {
            System.err.println("Error de formato en la línea del Fondo. Se esperaba un número: ");
        }
    }

    protected void parsearLineaPersonajes(String datos, Piso pisoActual) {
      
        Pattern pattern = Pattern.compile("([A-Z])(\\d+)_(\\d+)");
        Matcher matcher = pattern.matcher(datos);
        while (matcher.find()) {
            char tipoEntidad = matcher.group(1).charAt(0);   
            int x = Integer.parseInt(matcher.group(2));             
            int y = Integer.parseInt(matcher.group(3));             

            switch (tipoEntidad) {
                case 'D': pisoActual.addEnemigo(fabrica.crearDemonioRojo(x, y, pisoActual));
                    break;
                case 'T': pisoActual.addEnemigo(fabrica.crearTrollAmarillo(x, y, pisoActual));
                    break;
                case 'R': pisoActual.addEnemigo(fabrica.crearRanaFuego(x, y, pisoActual));
                    break;
                case 'K': pisoActual.addEnemigo(fabrica.crearKamakichi(x, y, pisoActual));
                    break;
                case 'M': pisoActual.addEnemigo(fabrica.crearMoghera(x, y, pisoActual));
                    break;
            }
        }
    }

    protected void parsearLineaEstructuraRangoHorizontal(String datos, Piso pisoActual) {
    Pattern pattern = Pattern.compile("([A-Z])(\\d+)/(\\d+)_(\\d+)");
    Matcher matcher = pattern.matcher(datos);

    while (matcher.find()) {
        char tipoEntidad = matcher.group(1).charAt(0); 
        int xInicio = Integer.parseInt(matcher.group(2));
        int xFin = Integer.parseInt(matcher.group(3));
        int y = Integer.parseInt(matcher.group(4));

            switch (tipoEntidad) {
                case 'S':
                    pisoActual.addPlataforma(fabrica.crearPlataformaEstatica(xInicio, xFin, y, pisoActual));
                    break;
                case 'M':
                    pisoActual.addPlataforma(fabrica.crearPlataformaMovil(xInicio, xFin, y, pisoActual));
                    break;
                case 'Q':
                    pisoActual.addPlataforma(fabrica.crearPlataformaQuebradiza(xInicio, xFin, y, pisoActual));
                    break;
            }
        }
    }

    protected void parsearLineaEstructuraRangoVertical(String datos, Piso pisoActual) {
    Pattern pattern = Pattern.compile("([A-Z])(\\d+)_(\\d+)/(\\d+)");
    Matcher matcher = pattern.matcher(datos);

    while (matcher.find()) {
        char tipoEntidad = matcher.group(1).charAt(0); 
        int x = Integer.parseInt(matcher.group(2));
        int yInicio = Integer.parseInt(matcher.group(3));
        int yFin = Integer.parseInt(matcher.group(4));
            switch (tipoEntidad) {
                case 'E': pisoActual.addObstaculo(fabrica.crearEscaleras(x, yInicio, yFin, pisoActual));
                    break;
            }   
       }
    }

    protected void parsearLineaObstaculos(String datos, Piso pisoActual) {
        Pattern pattern = Pattern.compile("([A-Z])(\\d+)_(\\d+)");
        Matcher matcher = pattern.matcher(datos);

        while (matcher.find()) {
            char tipoEntidad = matcher.group(1).charAt(0); 
            int x = Integer.parseInt(matcher.group(2));     
            int y = Integer.parseInt(matcher.group(3));      

  
            switch (tipoEntidad) {
                case 'P': pisoActual.addObstaculo(fabrica.crearPared(x, y, pisoActual));
                    break;
                case 'D': pisoActual.addObstaculo(fabrica.crearParedDestructible(x, y, pisoActual)); 
                    break;
                case 'R': pisoActual.addObstaculo(fabrica.crearSueloResbaladizo(x, y, pisoActual)); 
                    break;
                case 'T': pisoActual.addObstaculo(fabrica.crearTrampa(x, y, pisoActual)); 
                    break;
            }
        }

    }
}