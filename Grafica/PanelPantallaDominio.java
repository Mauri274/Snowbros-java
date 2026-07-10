package Grafica;

import java.awt.*;
import javax.swing.*;

import Grafica.Constantes.ConstantesVistas;

public class PanelPantallaDominio extends PanelVista {

    protected Image imagenFondo;
    protected JButton botonDominio1;
    protected JButton botonDominio2;


    public PanelPantallaDominio(ControladorVistas controladorVistas) {
        super(controladorVistas);
        java.net.URL url = getClass().getResource("/Imagenes/SpritesOriginales/Pantallas/dominio.png");
        if (url == null) {
            throw new IllegalStateException("No se encontró /Imagenes/SpritesOriginales/Pantallas/dominio.png");
        }
        imagenFondo = new ImageIcon(url).getImage();

        setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO));
        setLayout(new GridBagLayout());
        iniciarCLASICO();
        iniciarALTERNATIVO();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }

    protected void iniciarCLASICO() {

        botonDominio1 = crearBoton("");
        botonDominio1.setPreferredSize(new Dimension(365, 60));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx   = 0;
        c.gridy   = 0;
        c.weightx = 1.0;                 
        c.weighty = 1.0;                  
        c.anchor  = GridBagConstraints.SOUTH; 
        c.insets  = new Insets(0, 0, 265, 0); 
        c.fill    = GridBagConstraints.NONE;

        add(botonDominio1, c);

        botonDominio1.addActionListener(e -> seleccionarDominio("Clasico"));
    }

    protected void iniciarALTERNATIVO() {

        botonDominio2 = crearBoton("");
        botonDominio2.setPreferredSize(new Dimension(565, 60));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx   = 0;
        c.gridy   = 0;
        c.weightx = 1.0;              
        c.weighty = 1.0;                
        c.anchor  = GridBagConstraints.SOUTH; 
        c.insets  = new Insets(0, 0, 150, 0);
        c.fill    = GridBagConstraints.NONE;

        add(botonDominio2, c);

        botonDominio2.addActionListener(e -> seleccionarDominio("Alternativo"));
    }


    protected void seleccionarDominio(String dominio) {
        controladorVistas.setDominio(dominio);
        controladorVistas.mostrarPanelPantallaModoJuego();
    }

}