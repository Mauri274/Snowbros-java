package Juego;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Grafica.Constantes.ConstantesSonido;


public class GestorSonido {

    protected static GestorSonido instancia;
    protected Map<Integer, ArrayList<Clip>> sfxClips;
    protected static Map<Integer, Clip> musicClips;
    protected Map<Integer, Integer> sfxIndices;


    protected GestorSonido() {
        sfxClips = new HashMap<>();
        musicClips = new HashMap<>();
        sfxIndices = new HashMap<>();
    }

    public static GestorSonido getGestorSonido() {
        if (instancia == null) {
            instancia = new GestorSonido();
        }
        return instancia;
    }

    public void cargarEfecto(int idSonido, String rutaRecurso, int poolSize) {
        try {
            ArrayList<Clip> pool = new ArrayList<>();
            sfxIndices.put(idSonido, 0);

            URL url = getClass().getResource(rutaRecurso);
            if (url == null) {
                System.err.println("No se pudo encontrar el recurso de sonido: " + rutaRecurso);
                return;
            }

            for (int i = 0; i < poolSize; i++) {

                InputStream audioSrc = url.openStream();
                BufferedInputStream bufferedIn = new BufferedInputStream(audioSrc);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn);
                
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                pool.add(clip);
                
                audioIn.close();
            }

            sfxClips.put(idSonido, pool);

        } catch (Exception e) {
            System.err.println("Error al cargar el efecto: " + rutaRecurso);
            e.printStackTrace();
        }
    }

    public void cargarMusica(int idMusica, String rutaRecurso) {
        try {
            URL url = getClass().getResource(rutaRecurso);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            musicClips.put(idMusica, clip);
        } catch (Exception e) {
            System.err.println("Error al cargar la musica: " + rutaRecurso);
            e.printStackTrace();
        }
    }

    public void reproducirSonido(int idSonido) {
        ArrayList<Clip> pool = sfxClips.get(idSonido);
        if (pool == null) {
            System.err.println("El efecto '" + idSonido + "' no fue cargado.");
            return;
        }
    
        int indiceActual = sfxIndices.get(idSonido);
        Clip clip = pool.get(indiceActual);

        clip.stop();
        clip.setFramePosition(0);
        clip.start();

        indiceActual = (indiceActual + 1) % pool.size();
        sfxIndices.put(idSonido, indiceActual);
    }

    public void reproducirMusicaDeFondo(int idMusica) {
        Clip clip = musicClips.get(idMusica);
        if (clip != null) {
            if (!clip.isRunning()) {
                clip.setFramePosition(0);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
    }

    public static void detenerMusica(int idMusica) {
        Clip clip = musicClips.get(idMusica);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public static void cargarSonidosDelJuego() {
        GestorSonido gestor = GestorSonido.getGestorSonido();
        gestor.cargarEfecto(ConstantesSonido.DISPARO, "/Sonidos/snowshot.wav", 5);
        gestor.cargarEfecto(ConstantesSonido.SALTO, "/Sonidos/salto.wav", 2);
        gestor.cargarEfecto(ConstantesSonido.POWER_UP, "/Sonidos/power-up.wav", 2);
        gestor.cargarEfecto(ConstantesSonido.MUERTE, "/Sonidos/snowdead.wav", 2);

        gestor.cargarMusica(ConstantesSonido.MUSICA_FONDO, "/Sonidos/musica-fondo.wav");
    }
}