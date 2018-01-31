/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author rodry
 */
public class Vista extends JFrame {

    Controlador c;
    private ArrayList<Label> letras;
    private Label lb;

    private int y = 0;
    private int x=0;


    public Vista(Controlador c) {
        this.c = c;
        this.setLayout(null);
        y = -10;
        this.setResizable(false);
        y = -20;
        letras = new ArrayList();
                crearBloque();

        crearBarra();

//    letras = new ArrayList();
//    JLabel a = new JLabel("a");
//    a.setBounds(60,60,200,200);
//    letras.add(a);
//    this.add(a);
    this.setResizable(false);
    this.setBounds(50,50,600,600);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    
    
}

    public void crearLetras(String letra) {
        System.out.println(letra);
        x = (int) (Math.random() * 600);
        lb = new Label();
        lb.setText(letra);
        lb.setBounds(x, 10, 25, 25);
        this.add(lb);
        letras.add(lb);
        lb.addKeyListener(c);
        
                
    }
    public void cambiarY(){
       
        for (int i = 0; i < letras.size(); i++) {
            letras.get(i).setBounds(letras.get(i).getX(),letras.get(i).getY()+10, 25,25);
        }
       this.repaint();
    }
    public void crearEscuchador(){
        for (int i = 0; i < letras.size(); i++) {
            letras.get(i).addKeyListener(c);
        }


    }


    public void crearBarra() {
        JPanel barra=new JPanel();
        barra.setBounds(0, 500, 600, 20);
        barra.setBackground(Color.red);
        this.add(barra);
    }
    
    public void crearBloque(){
        JPanel bloque=new JPanel();
        bloque.setBounds(275, 500, 50, 20);
        bloque.setBackground(Color.black);
        
        this.add(bloque);
    }

}
