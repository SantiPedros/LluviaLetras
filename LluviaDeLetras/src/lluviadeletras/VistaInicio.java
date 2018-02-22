
package lluviadeletras;

import java.awt.Color;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VistaInicio extends JFrame {
    private JPanel panel;
    private JLabel nombre;
    private TextField texto;
    private JButton boton;
    private Controlador control;
    
    VistaInicio(Controlador control){
        
        this.control=control;
        this.setLayout(null);
        
        this.inicializado();
        this.addElements();
        this.addControladores();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(380, 80, 600, 600);
        this.getContentPane().setBackground(Color.blue);
        this.setVisible(true);
    }
    
    /**
     * Inicializacion de elementos de Vista Inicio
     */
    public void inicializado(){
        panel=new JPanel();
        nombre=new JLabel("Jugador");
        texto=new TextField();
        boton=new JButton();
       
    }
    
    /**
     * Añadido de controladores
     */
    public void addControladores(){
        boton.addActionListener(control);        
    }
    
    /**
     * Añadido de elementos a VistaInicio
     */
    public void addElements(){
        nombre.setBounds(50, 50, 300, 150);
        nombre.setFont(nombre.getFont().deriveFont(35.0f));
        panel.add(nombre);
        
        texto.setBounds(50, 200, 280, 120);
        panel.add(texto);
        
        boton.setBounds(220, 400, 200, 80);
        boton.setFont(nombre.getFont().deriveFont(25.0f));
        panel.add(boton);
        this.add(panel);
    }
    
    
}
