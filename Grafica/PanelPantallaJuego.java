package Grafica;

import Juego.EntidadLogica;
import Juego.Puntaje;
import Juego.Controladores.ControladorJuego;
import Personajes.Jugador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import Fabricas.Sprites;
import Fuentes.GestorFuentes;
import Grafica.Constantes.ConstantesTeclado;
import Grafica.Constantes.ConstantesVistas;


public class PanelPantallaJuego extends PanelVista {

    protected JPanel[] capasParaEntidades;
    protected Puntaje puntaje;
    protected JPanel panelPiso;
    protected JPanel panelHUD;
    protected JLabel imagenFondoPanelPiso;
    protected JLabel imagenHUD;
    protected JLabel labelNivel;
    protected JLabel labelTiempo;
    protected ControladorJuego controladorJuego;
    protected ObserverPuntaje labelPuntaje;
    protected ObserverVida labelVida;
    protected int altoLabel = 20;
    protected int posicionYLabel = (ConstantesVistas.PANEL_HUD_ALTO - altoLabel) / 2;


    public PanelPantallaJuego(ControladorVistas controladorVistas, ControladorJuego controladorJuego) {
        super(controladorVistas);
        this.controladorJuego = controladorJuego;
        puntaje = controladorJuego.getPuntaje();
        setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO));
        setLayout(new BorderLayout());
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocusInWindow();
        setOpaque(false);
        configurarTeclas();
        addPanelPiso();
        addHUD();

    }

    public Observer incorporarEntidad(EntidadLogica entidadLogica, int zOrder) {
        ObserverGrafico observerGrafico = new ObserverGrafico(entidadLogica);
        capasParaEntidades[zOrder].add(observerGrafico);
        capasParaEntidades[zOrder].repaint();
        capasParaEntidades[zOrder].revalidate();

        return observerGrafico;
    }

    public ObserverGraficoNieve incorporarNieve(EntidadLogica entidadLogica, int zOrder, Sprites sprites) {
        ObserverGraficoNieve observerGraficoNieve = new ObserverGraficoNieve(entidadLogica, sprites);
            capasParaEntidades[zOrder].add(observerGraficoNieve);
            capasParaEntidades[zOrder].repaint();
            capasParaEntidades[zOrder].revalidate();

        return observerGraficoNieve;
    }

    public Observer incorporarFondo(EntidadLogica entidadLogica) {
        ObserverGrafico observerGrafico = new ObserverGrafico(entidadLogica);
        ImageIcon iconoOriginal = new ImageIcon(getClass().getClassLoader().getResource(entidadLogica.getSprites().getSpriteInfo().getRutaImagen()));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
            ConstantesVistas.PANEL_PISO_ANCHO,
            ConstantesVistas.PANEL_PISO_ALTO,
            Image.SCALE_SMOOTH
        );
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        imagenFondoPanelPiso.setIcon(iconoEscalado);
        imagenFondoPanelPiso.setBounds(0, 0, iconoEscalado.getIconWidth(), iconoEscalado.getIconHeight());
        panelPiso.setPreferredSize(new Dimension(iconoEscalado.getIconWidth(), iconoEscalado.getIconHeight()));
        return observerGrafico;
    }

    public ObserverVida incorporarObserverVida(Jugador jugador) {
        labelVida = new ObserverVida(jugador);
        
        labelVida.setForeground(Color.WHITE);
        labelVida.setFont(GestorFuentes.getFuentePixel(20f));
        labelVida.setHorizontalAlignment(SwingConstants.RIGHT);
        labelVida.setBounds(325, posicionYLabel, 20, altoLabel);

        imagenHUD.repaint();
        imagenHUD.add(labelVida);

        return labelVida;
    }

    public ObserverPuntaje incorporarPuntaje(Puntaje puntaje) {
        labelPuntaje = new ObserverPuntaje(puntaje);
        labelPuntaje.setForeground(Color.WHITE);
        labelPuntaje.setFont(GestorFuentes.getFuentePixel(20f));
        labelPuntaje.setHorizontalAlignment(SwingConstants.LEFT);
        labelPuntaje.setBounds(450, posicionYLabel, 200, altoLabel);

        imagenHUD.repaint();
        imagenHUD.add(labelPuntaje);

        return labelPuntaje;
    }

    protected void configurarTeclas() {
        InputMap inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "presionarA");
        actionMap.put("presionarA", new AccionTecla(controladorJuego, ConstantesTeclado.MOVER_IZQUIERDA, ConstantesTeclado.PRESIONAR_TECLA));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "soltarA");
        actionMap.put("soltarA", new AccionTecla(controladorJuego, ConstantesTeclado.MOVER_IZQUIERDA, ConstantesTeclado.SOLTAR_TECLA));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "presionarD");
        actionMap.put("presionarD", new AccionTecla(controladorJuego, ConstantesTeclado.MOVER_DERECHA, ConstantesTeclado.PRESIONAR_TECLA));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "soltarD");
        actionMap.put("soltarD", new AccionTecla(controladorJuego, ConstantesTeclado.MOVER_DERECHA, ConstantesTeclado.SOLTAR_TECLA));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "presionarW");
        actionMap.put("presionarW", new AccionTecla(controladorJuego, ConstantesTeclado.SALTAR, ConstantesTeclado.PRESIONAR_TECLA));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "soltarW");
        actionMap.put("soltarW", new AccionTecla(controladorJuego, ConstantesTeclado.SALTAR, ConstantesTeclado.SOLTAR_TECLA));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "presionarSpace");
        actionMap.put("presionarSpace", new AccionTecla(controladorJuego, ConstantesTeclado.DISPARAR, ConstantesTeclado.PRESIONAR_TECLA));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "soltarSpace");
        actionMap.put("soltarSpace", new AccionTecla(controladorJuego, ConstantesTeclado.DISPARAR, ConstantesTeclado.SOLTAR_TECLA));
    }

    protected void addPanelPiso() {
        imagenFondoPanelPiso = new JLabel();
        imagenFondoPanelPiso.setLayout(null);
        imagenFondoPanelPiso.setBounds(0, 0, ConstantesVistas.PANEL_PISO_ANCHO, ConstantesVistas.PANEL_PISO_ALTO);

        panelPiso = new JPanel(null);
        panelPiso.setPreferredSize(new Dimension(ConstantesVistas.PANEL_PISO_ANCHO, ConstantesVistas.PANEL_PISO_ALTO));
        crearCapasParaEntidades();
        panelPiso.add(imagenFondoPanelPiso);
        panelPiso.setOpaque(true);

        add(panelPiso, BorderLayout.CENTER);
    }

    protected void crearCapasParaEntidades() {
        capasParaEntidades = new JPanel[4];
        for (int i = 0; i < capasParaEntidades.length; i++) {
            capasParaEntidades[i] = new JPanel(null);
            capasParaEntidades[i].setOpaque(false);
            capasParaEntidades[i].setBounds(0, 0, ConstantesVistas.PANEL_PISO_ANCHO, ConstantesVistas.PANEL_PISO_ALTO);
            panelPiso.add(capasParaEntidades[i]);
        }
    }


    protected void addHUD() {
        panelHUD = new JPanel(null);
        panelHUD.setPreferredSize(new Dimension(ConstantesVistas.PANEL_HUD_ANCHO, ConstantesVistas.PANEL_HUD_ALTO));  
        panelHUD.setOpaque(true);
        agregarImagenFondoHUD();
        agregarLabelsInformacion();
        add(panelHUD, BorderLayout.NORTH);
    }

    protected void agregarImagenFondoHUD() {
        imagenHUD = new JLabel();
        imagenHUD.setLayout(null);
		ImageIcon icono_imagen = new ImageIcon(this.getClass().getResource("/Imagenes/SpritesOriginales/Pantallas/HUD.jpg"));
		Image imagen_escalada = icono_imagen.getImage().getScaledInstance(ConstantesVistas.PANEL_HUD_ANCHO, ConstantesVistas.PANEL_HUD_ALTO, Image.SCALE_SMOOTH);
		Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
		imagenHUD.setIcon(icono_imagen_escalado);
		imagenHUD.setBounds(0,0, ConstantesVistas.PANEL_HUD_ANCHO, ConstantesVistas.PANEL_HUD_ALTO);
		panelHUD.add(imagenHUD);
    }
    protected void agregarLabelsInformacion() {
        agregarLabelNivel();
        agregarLabelTiempo();   
    }

    protected void agregarLabelNivel() {
        labelNivel = new JLabel("");
        labelNivel.setForeground(Color.WHITE);
        labelNivel.setFont(GestorFuentes.getFuentePixel(20f));
        labelNivel.setHorizontalAlignment(SwingConstants.LEFT);
        labelNivel.setBounds(20, posicionYLabel, 250, altoLabel);
        imagenHUD.add(labelNivel);
    }

    protected void agregarLabelTiempo() {
            labelTiempo = new JLabel("");
        labelTiempo.setForeground(Color.WHITE);
        labelTiempo.setFont(GestorFuentes.getFuentePixel(20f));
        labelTiempo.setHorizontalAlignment(SwingConstants.RIGHT);
        labelTiempo.setBounds(ConstantesVistas.PANEL_ANCHO - 190, posicionYLabel, 170, altoLabel);
        labelTiempo.setVisible(false);
        imagenHUD.add(labelTiempo);
    }

    public void actualizarTiempo(String tiempo) {
        if (labelTiempo != null) {
            if (tiempo == null || tiempo.isEmpty()) {
                labelTiempo.setVisible(false);
            } else {
                labelTiempo.setText(tiempo);
                labelTiempo.setVisible(true);
            }
        }
    }   

    public void actualizarNombrePiso(String nombrePiso) {
        if (labelNivel != null) {
            labelNivel.setText(nombrePiso);
        }
    }
}