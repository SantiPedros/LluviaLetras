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
    private int y;
    private Timer timer;
    
    public Vista(Controlador c){
    this.c =c;
    this.setLayout(null);
    y = -20;
    
//    letras = new ArrayList();
//    JLabel a = new JLabel("a");
//    a.setBounds(60,60,200,200);
//    letras.add(a);
//    this.add(a);
    
    this.setBounds(50,50,600,600);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    
        timer = new Timer(300,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                cambiarY();
            }
            
        });
        timer.start();
    
    }
    public void crearLetras(String letra){
        System.out.println(letra);
        
        letras = new ArrayList();
        lb = new Label();
        lb.setText(letra);
        lb.setBounds((int) (Math.random() * 600), y,25,25);
        this.add(lb);
        lb.addKeyListener(c);
        letras.add(lb);
                
    }
    public void cambiarY(){
        y=y+20;
    }
    
    
}
