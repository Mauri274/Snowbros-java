package Grafica;

import java.awt.*;
import java.util.List;
import javax.swing.*;

import Fuentes.GestorFuentes;
import Grafica.Constantes.ConstantesVistas;


public class PanelPantallaMostrarRanking extends PanelVista {

    protected JLabel lblTituloModo;
    protected JPanel panelListaPuntajes;
    protected JButton botonVolver;

    protected final JPanel panelContenido = new JPanel(new GridBagLayout()) {
        public boolean isOpaque() { return false; }
    };

    public PanelPantallaMostrarRanking(ControladorVistas controladorVistas) {
        super(controladorVistas);

        setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO));
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        inicializarComponentes();
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    protected void paintChildren(Graphics g) {
        Rectangle r = panelContenido.getBounds();
        if (r.width > 0 && r.height > 0) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0, 0, 0, 180));
            int arc = 30;
            g2.fillRoundRect(r.x, r.y, r.width, r.height, arc, arc);
            g2.dispose();
        }
        super.paintChildren(g);
    }

    protected void inicializarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(15, 25, 15, 25);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblTituloModo = new JLabel("RANKING", SwingConstants.CENTER);
        lblTituloModo.setFont(GestorFuentes.getFuentePixel(36f));
        lblTituloModo.setForeground(Color.YELLOW);
        panelContenido.add(lblTituloModo, gbc);

        panelListaPuntajes = new JPanel();
        panelListaPuntajes.setLayout(new BoxLayout(panelListaPuntajes, BoxLayout.Y_AXIS));
        panelListaPuntajes.setOpaque(false);
        panelContenido.add(panelListaPuntajes, gbc);

        gbc.insets = new Insets(20, 25, 20, 25);
        botonVolver = new JButton("Volver al Menú");
        botonVolver.setFont(GestorFuentes.getFuentePixel(18f));
        botonVolver.setForeground(Color.WHITE);
        botonVolver.setFocusPainted(false);
        botonVolver.setOpaque(false);
        botonVolver.setContentAreaFilled(false);
        botonVolver.setBorderPainted(false);

        botonVolver.addActionListener(e -> controladorVistas.mostrarPanelPantallaMenuPrincipal());
        
        panelContenido.add(botonVolver, gbc);
        add(panelContenido);
    }

    public void mostrarPuntajes(String titulo, List<String> puntajes) {
        lblTituloModo.setText(titulo);
        panelListaPuntajes.removeAll(); 

        if (puntajes == null || puntajes.isEmpty()) {
            JLabel lbl = new JLabel("No hay puntajes guardados");
            lbl.setFont(GestorFuentes.getFuentePixel(22f));
            lbl.setForeground(Color.WHITE);
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelListaPuntajes.add(lbl);
        } else {
            for (String linea : puntajes) {
                JLabel lbl = new JLabel(linea);
                lbl.setFont(GestorFuentes.getFuentePixel(22f));
                lbl.setForeground(Color.WHITE);
                lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
                panelListaPuntajes.add(lbl);
            }
        }
        
        panelListaPuntajes.revalidate();
        panelListaPuntajes.repaint();
    }
}