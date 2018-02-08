package lluviadeletras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class Controlador implements KeyListener, ActionListener {

    private Vista v;
    private Modelo m;
    private Timer timer, timer2;
    private String letra;
    private char letraEliminar;
    private int aciertos = 0;
    private int vidas = 10;
    private boolean encontrada = false;
    private int velocidadCaida = 100;
    private int nivel = 1;

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

        timer2 = new Timer(velocidadCaida, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                v.cambiarY();
            }
        });
        timer2.start();
    }

    public int getVelocidadCaida() {
        return velocidadCaida;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public void aumentarVelocidad() {
        velocidadCaida += 500;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            v.moverBloqueDerecha();
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            v.moverBloqueIzquierda();
        } else if (ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SHIFT) {
            System.out.println("solo activa mayus");
        } else {
            letraEliminar = ke.getKeyChar();
            for (int i = 0; i < v.getLetras().size(); i++) {
                if (letraEliminar == v.getLetras().get(i).getText().charAt(0)) {
                    m.mandarLetra(letraEliminar);
                    v.eliminarLetra(letraEliminar);
                    aciertos++;
                    if (aciertos == 10) {//para cada 10 aciertos subir el nivel
                        aciertos = 0;
                        m.cambiarNivel(nivel);
                        nivel++;
                       aumentarVelocidad();
                    }
                    encontrada = true;
                }
            }
            if (!encontrada) {
                v.pintarFondo(1);
                aciertos--;
                vidas--;
                if (vidas <= 0) {

                    v.gameOver();
                }
                v.restaVidas();
                encontrada = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        v.pintarFondo(0);
    }

    public void pararTimers() {
        timer.stop();
        timer2.stop();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Salir":
                System.out.println("PanelSalida");
                v.CrearPanelSalida();
                //v.getSalida().setFocusable(true);
                break;

            case "SI":
                System.exit(0);
                break;

            case "NO":
                //v.getSalida().setFocusable(false);
                v.getSalida().setVisible(false);
                break;
            case "Level 1":
                velocidadCaida = 100;
                v.cambiarNivel("NIVEL 1");
                m.cambiarNivel(1);
                break;
            case "Level 2":
                velocidadCaida = 600;
                v.cambiarNivel("NIVEL 2");
                m.cambiarNivel(2);
                break;
            case "Level 3":
                velocidadCaida = 1100;
                v.cambiarNivel("NIVEL 3");
                m.cambiarNivel(3);
                break;
            case "Level 4":
                velocidadCaida = 1600; 
                v.cambiarNivel("NIVEL 4");
                m.cambiarNivel(4);
                break;
            case "Level 5":
                velocidadCaida = 2100;
                v.cambiarNivel("NIVEL 5");
                m.cambiarNivel(5);
                break;
        }
    }
}
