/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author rodry
 */
public class Vista extends JFrame {
    Controlador c;
    private ArrayList <Label> letras;
    private Label lb;
    private int y = 0;
    private int x=0;
    private Timer timer;
    
    public Vista(Controlador c){
    this.c =c;
    this.setLayout(null);
    y = -20;
    letras = new ArrayList();
    
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
    public void crearLetras(String letra){
        //System.out.println(letra);
        
        x = (int) (Math.random() * 600);
        lb = new Label();
        lb.setText(letra);
        lb.setBounds(x, -20,25,25);
        //crearEscuchador();
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
    
    
}
