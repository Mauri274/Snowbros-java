package Grafica;

import java.awt.*;

import javax.swing.*;

import Grafica.Constantes.ConstantesVistas;

public class PanelPantallaMenuPrincipal extends PanelVista {

    protected Image imagenFondo;
    protected JButton botonJugar;
    protected JButton botonRanking;
    protected JButton botonVolver;


    public PanelPantallaMenuPrincipal(ControladorVistas controladorVistas) {
        super(controladorVistas);
 
        java.net.URL url = getClass().getResource("/Imagenes/SpritesOriginales/Pantallas/pantalla-principal.png");
        if (url == null) {
            throw new IllegalStateException("No se encontró /Imagenes/SpritesOriginales/Pantallas/pantalla-principal.png en el classpath");
        }
        imagenFondo = new ImageIcon(url).getImage();

        setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO));
        setLayout(new GridBagLayout()); 

        crearBotonJugar();
        crearBotonRanking();
        crearBotonVolver();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }

    protected void crearBotonJugar() {

        botonJugar = crearBoton("");
        botonJugar.setPreferredSize(new Dimension(170, 60));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx   = 0;
        c.gridy   = 0;
        c.weightx = 1.0;    
        c.weighty = 1.0;                
        c.anchor  = GridBagConstraints.SOUTH; 
        c.insets  = new Insets(0, 0, 400, 0); 
        c.fill    = GridBagConstraints.NONE;

        add(botonJugar, c);

        botonJugar.addActionListener(e -> controladorVistas.solicitarInicioJuego());
    }

    protected void crearBotonRanking(){
        botonRanking = crearBoton("");
        botonRanking.setPreferredSize(new Dimension(250, 60));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx   = 0;
        c.gridy   = 0;
        c.weightx = 1.0;              
        c.weighty = 1.0;                 
        c.anchor  = GridBagConstraints.SOUTH; 
        c.insets  = new Insets(0, 0, 315, 0);
        c.fill    = GridBagConstraints.NONE;

        add(botonRanking, c);

        botonRanking.addActionListener(e -> {
            controladorVistas.mostrarPanelPantallaMostrarRanking();
        });
    }

    protected void crearBotonVolver(){
        botonVolver = crearBoton("");
        botonVolver.setPreferredSize(new Dimension(200, 60));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx   = 0;
        c.gridy   = 0;
        c.weightx = 1.0;                
        c.weighty = 1.0;                 
        c.anchor  = GridBagConstraints.SOUTH; 
        c.insets  = new Insets(0, 0, 225, 0); 
        c.fill    = GridBagConstraints.NONE;

        add(botonVolver, c);

        botonVolver.addActionListener(e -> controladorVistas.mostrarPanelPantallaModoJuego());
    }
}
