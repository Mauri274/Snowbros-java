package Grafica;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent; 
import javax.swing.*;
import Fuentes.GestorFuentes;
import Grafica.Constantes.ConstantesVistas;


public class PanelPantallaRanking extends PanelVista {

    protected Image fondo;
    protected JTextField campoTexto;
    protected boolean jugadorGano;
    
    protected static final int MAX_LONGITUD = 20;

    protected PanelPantallaRanking(ControladorVistas controladorVistas) {
        super(controladorVistas);
        
        java.net.URL url = getClass().getResource("/Imagenes/SpritesOriginales/Pantallas/ranking.png");
        fondo = new ImageIcon(url).getImage();

        setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO));
        setLayout(new GridBagLayout());
        inicializarComponentes();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    protected void inicializarComponentes() {
        campoTexto = crearCampoTexto();
        campoTexto.setPreferredSize(new Dimension(450, 50)); 

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.anchor  = GridBagConstraints.SOUTH;
        c.insets  = new Insets(0, 0, 205, 0);

        add(campoTexto, c);

        SwingUtilities.invokeLater(() -> campoTexto.requestFocusInWindow());
        
        campoTexto.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (campoTexto.getText().length() >= MAX_LONGITUD) {
                    e.consume();
                    JOptionPane.showMessageDialog(
                        PanelPantallaRanking.this,
                        "El nombre no puede tener más de " + MAX_LONGITUD + " caracteres.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    validarNombre();
                }
            }
        });
    }

    protected JTextField crearCampoTexto() {
        JTextField textField = new JTextField();
        textField.setColumns(21);
        textField.setFont(GestorFuentes.getFuentePixel(30f));
        textField.setForeground(Color.BLACK);
        textField.setCaretColor(Color.BLACK);

        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setBackground(new Color(0, 0, 0, 0));
        textField.setOpaque(false);
        
        textField.setMargin(new Insets(10, 12, 10, 12));
        return textField;
    }

    public void gano(boolean gano) {
        jugadorGano = gano;
    }

    public String getTextoIngresado() {
        return campoTexto.getText();
    }
    
    protected void validarNombre() {
        String nombre = campoTexto.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "El nombre no puede estar vacío.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        controladorVistas.guardarPuntajeFinal(nombre);
        if (jugadorGano)
            controladorVistas.mostrarPanelPantallaVictoria();
        else
            controladorVistas.mostrarPanelPantallaGameOver();
        limpiarCampo();
    }
    
    public void limpiarCampo() {
        campoTexto.setText("");
    }

}