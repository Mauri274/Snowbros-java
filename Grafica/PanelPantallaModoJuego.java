package Grafica;

import java.awt.*;
import javax.swing.*;

import Grafica.Constantes.ConstantesVistas;

public class PanelPantallaModoJuego extends PanelVista {

    protected Image imagenFondo;
    protected JButton botonClasico;
    protected JButton botonContrarreloj;
    protected JButton botonSupervivencia;
    protected JButton botonVolver;


    public PanelPantallaModoJuego(ControladorVistas controladorVistas) {
        super(controladorVistas);

        java.net.URL url = getClass().getResource("/Imagenes/SpritesOriginales/Pantallas/modo-juego.png");
        if (url == null) {
            throw new IllegalStateException("No se encontró /Imagenes/SpritesOriginales/Pantallas/modo-juego.png");
        }
        imagenFondo = new ImageIcon(url).getImage();

        setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO));
        setLayout(new GridBagLayout()); 
        crearBotonClasico();
        crearBotonContrarreloj();
        crearBotonSupervivencia();
        crearBotonVolver();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }

    protected void crearBotonClasico() {

        botonClasico = crearBoton("");
        botonClasico.setPreferredSize(new Dimension(360, 60));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx   = 0;
        c.gridy   = 0;
        c.weightx = 1.0;           
        c.weighty = 1.0;               
        c.anchor  = GridBagConstraints.SOUTH; 
        c.insets  = new Insets(0, 0, 270, 0);
        c.fill    = GridBagConstraints.NONE;

        add(botonClasico, c);

        botonClasico.addActionListener(e -> seleccionarModoJuego("Clasico"));
    }

    protected void crearBotonContrarreloj() {

        botonContrarreloj = crearBoton("");
        botonContrarreloj.setPreferredSize(new Dimension(620,60));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx   = 0;
        c.gridy   = 0;
        c.weightx = 1.0;              
        c.weighty = 1.0;          
        c.anchor  = GridBagConstraints.SOUTH; 
        c.insets  = new Insets(0, 0, 100, 0); 
        c.fill    = GridBagConstraints.NONE;

        add(botonContrarreloj, c);

        botonContrarreloj.addActionListener(e -> seleccionarModoJuego("Contrarreloj"));
    }

    protected void crearBotonSupervivencia() {

        botonSupervivencia = crearBoton("");
        botonSupervivencia.setPreferredSize(new Dimension(650, 60));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx   = 0;
        c.gridy   = 0;
        c.weightx = 1.0;              
        c.weighty = 1.0;                  
        c.anchor  = GridBagConstraints.SOUTH; 
        c.insets  = new Insets(0, 0, 180, 0);
        c.fill    = GridBagConstraints.NONE;

        add(botonSupervivencia, c);

        botonSupervivencia.addActionListener(e -> seleccionarModoJuego("Supervivencia"));

    }

    protected void crearBotonVolver() {
        botonVolver = crearBoton("");
        botonVolver.setPreferredSize(new Dimension(190,60));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx   = 0;
        c.gridy   = 0;
        c.weightx = 1.0;                
        c.weighty = 1.0;                  
        c.anchor  = GridBagConstraints.NORTHWEST; 
        c.insets  = new Insets(10, 20, 0, 0); 
        c.fill    = GridBagConstraints.NONE;

        add(botonVolver, c);

        botonVolver.addActionListener(e -> controladorVistas.mostrarPanelPantallaDominio());

    }

    protected void seleccionarModoJuego(String modo) {
        controladorVistas.setModoJuego(modo);
        controladorVistas.mostrarPanelPantallaMenuPrincipal();
    }
}
