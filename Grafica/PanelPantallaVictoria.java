package Grafica;

import java.awt.*;
import javax.swing.*;

import Grafica.Constantes.ConstantesVistas;

public class PanelPantallaVictoria extends PanelVista {

    protected Image imagenFondo;
    protected JButton botonJugar;


    protected PanelPantallaVictoria(ControladorVistas controladorVistas) {
        super(controladorVistas);

 
        java.net.URL url = getClass().getResource("/Imagenes/SpritesOriginales/Pantallas/victoria.png");
        if (url == null) {
            throw new IllegalStateException("No se encontró /Imagenes/SpritesOriginales/Pantallas/victoria.png en el classpath");
        }
        imagenFondo = new ImageIcon(url).getImage();
        setBackground(Color.BLACK);

        setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO));
        setLayout(new GridBagLayout());

        inicializarComponentes();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }

    protected void inicializarComponentes() {

        botonJugar   = crearBoton("");
        botonJugar.setPreferredSize(new Dimension(200, 50));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.SOUTH;
        c.insets = new Insets(0, 0, 150, 0);
        c.fill = GridBagConstraints.NONE;

        add(botonJugar, c);

        botonJugar.addActionListener(e -> controladorVistas.mostrarPanelPantallaMenuPrincipal());
    }
}