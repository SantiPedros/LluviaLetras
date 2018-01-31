/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

/**
 *
 * @author rodry
 */
public class Controlador implements KeyListener {
    private Vista v;
    private Modelo m;
    private Timer timer, timer2;
    private String letra;
    public Controlador(){
        v = new Vista(this);
        m = new Modelo(this);
        timer = new Timer(2000,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                letra=m.recogerLetra();
                v.crearLetras(letra);
            }
            
        });
        timer.start();
        
        timer2 = new Timer(1000,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                v.cambiarY();
            }
            
        });
        timer2.start();
        
      }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    
}
