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
    private int velocidadCaida = 40;
    private int nivel = 1;
    private int velocidadCreacion = 1500;
    private boolean encontrada = false;

    public Controlador() {
        v = new Vista(this);
        m = new Modelo(this);
        timer = new Timer(velocidadCreacion, new ActionListener() {
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

    public void aumentarVelocidadCaida() {
        velocidadCaida -= 10;
    }

    public void aumentarVelocidadCreacion() {
        velocidadCreacion -= 200;
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
        } else if (ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SHIFT || ke.getKeyCode() == KeyEvent.VK_CONTROL) {
            System.out.println("solo activa mayus o ctrl");//Para cuando se pulsa las mayusculas o el control
        } else if (ke.getKeyCode() == KeyEvent.VK_1 || ke.getKeyCode() == KeyEvent.VK_2 || ke.getKeyCode() == KeyEvent.VK_3 || ke.getKeyCode() == KeyEvent.VK_4 || ke.getKeyCode() == KeyEvent.VK_5) {
            System.out.println("numero");//Para cuando se pulsa un numero del 1 al 5 para los atajos de teclado
        } else {
            letraEliminar = ke.getKeyChar();
            for (int i = 0; i < v.getLetras().size(); i++) {
                if (letraEliminar == v.getLetras().get(i).getText().charAt(0)) {
                    System.out.println(letraEliminar + " es igual que " + v.getLetras().get(i).getText().charAt(0));
                    m.mandarLetra(letraEliminar);
                    v.eliminarLetra(letraEliminar);
                    aciertos++;
                    if (aciertos == 10) {//para cada 10 aciertos subir el nivel
                        aciertos = 0;
                        nivel++;
                        if (nivel > 5) {
                            nivel = 5;
                        }
                        m.cambiarNivel(nivel);
                        v.cambiarNivel(nivel);
                        aumentarVelocidadCreacion();
                        aumentarVelocidadCaida();
                    }
                    encontrada = true;
                }
            }

            if (!encontrada) {
                v.pintarFondo(1);
                aciertos--;
                vidas--;
                v.restaVidas(vidas);
                if (vidas <= 0) {
                    v.gameOver();
                }
            }
            encontrada = false;
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
                velocidadCaida = 40;
                velocidadCreacion = 1500;
                aciertos = 0;
                nivel = 1;
                v.cambiarNivel(1);
                m.cambiarNivel(1);
                break;
            case "Level 2":
                velocidadCaida = 30;
                velocidadCreacion = 1300;
                nivel = 2;
                aciertos = 0;
                v.cambiarNivel(2);
                m.cambiarNivel(2);
                break;
            case "Level 3":
                velocidadCaida = 20;
                velocidadCreacion = 1100;
                aciertos = 0;
                nivel = 3;
                v.cambiarNivel(3);
                m.cambiarNivel(3);
                break;
            case "Level 4":
                velocidadCaida = 10;
                velocidadCreacion = 900;
                aciertos = 0;
                nivel = 4;
                v.cambiarNivel(4);
                m.cambiarNivel(4);
                break;
            case "Level 5":
                velocidadCaida = 1;
                velocidadCreacion = 700;
                nivel = 5;
                aciertos = 0;
                v.cambiarNivel(5);
                m.cambiarNivel(5);
                break;
        }
    }
}
