
package lluviadeletras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;


public class Controlador implements KeyListener {

    private Vista v;
    private Modelo m;
    private Timer timer, timer2;
    private String letra;
    private char letraEliminar;

    public Controlador() {
        v = new Vista(this);
        m = new Modelo(this);
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                letra = m.recogerLetra();
                v.crearLetras(letra);
            }

        });
        timer.start();

        timer2 = new Timer(100, new ActionListener() {
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
        //System.out.println("ionciorwniocwnoino"); 
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("derechaaaa");
            v.moverBloqueDerecha();
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("izquierdaaaaa");
            v.moverBloqueIzquierda();
        }
        
        letraEliminar = ke.getKeyChar();
        for (int i = 0; i < v.getLetras().size(); i++) {
            if (letraEliminar == v.getLetras().get(i).getText().charAt(0)) {
                m.mandarLetra(letraEliminar);
                v.eliminarLetra(letraEliminar);
            }else{
                System.out.println("ERRONEA");
                v.pintarFondo();
            }
        
            else  if (letraEliminar != v.getLetras().get(i).getText().charAt(0)){
                v.pintarFondo();
                v.repaint();
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
