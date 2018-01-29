/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author rodry
 */
public class Vista extends JFrame {
    Controlador c;
    private ArrayList <JLabel> letras;
    
    public Vista(Controlador c){
    this.c =c;
    this.setLayout(null);
    
    letras = new ArrayList();
    JLabel a = new JLabel("a");
    a.setBounds(60,60,200,200);
    letras.add(a);
    this.add(a);
    
    this.setBounds(50,50,600,600);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    
    
    }
}
