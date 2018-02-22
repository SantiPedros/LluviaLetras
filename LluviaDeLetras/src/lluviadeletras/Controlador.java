package lluviadeletras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class Controlador implements KeyListener, ActionListener {

    private Vista v;
    private VistaInicio vista;
    private Modelo m;
    private Timer timer, timer2;
    private String letra;
    private char letraEliminar;
    private int aciertos = 0;
    private int vidas = 10;
    private int velocidadCaida = 40;
    private int nivel = 1;
    private int velocidadCreacion = 1500;
    private boolean encontrada = false;//variable de control para saber si la letra está en pantalla o no.

    /**
     * Constructor Controlador
     */
    public Controlador() {
        v = new Vista(this);
        m = new Modelo(this);
        crearTimers();
    }

    /**
     * Creamos los dos timers, uno para controlar la velocidad de aparecer
     * letras, el otro controla la velocidad de caida
     */
    public void crearTimers() {
        //Creacion de letras
        timer = new Timer(velocidadCreacion, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                letra = m.recogerLetra();
                v.crearLetras(letra);
            }
        });
        timer.start();

       
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    /**
     *
     * METODOS DE CONTROL DE TECLAS
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {//derecha -->
            v.moverBloqueDerecha();
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {//izquierda <--
            v.moverBloqueIzquierda();
        } else if (ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SHIFT || ke.getKeyCode() == KeyEvent.VK_CONTROL) {
            //Esto es para las teclase especiales, Mayusculas, y CTLR para los atajos 
        } else if (ke.getKeyCode() == KeyEvent.VK_1 || ke.getKeyCode() == KeyEvent.VK_2 || ke.getKeyCode() == KeyEvent.VK_3 || ke.getKeyCode() == KeyEvent.VK_4 || ke.getKeyCode() == KeyEvent.VK_5) {
            //Aquí nos encargamos del numero de CTRL que es pulsado.
        }else if(ke.getKeyCode()==KeyEvent.VK_SPACE){
            v.pause();
        } else {

            /**
             * Control de eliminación de letras. Aquí se controla que letras hay
             * en pantalla
             */
            letraEliminar = ke.getKeyChar();
            for (int i = 0; i < v.getLetras().size(); i++) {

                if (letraEliminar == v.getLetras().get(i).getText().charAt(0)) {
                    v.colorChange(i);
                    if(v.getColores().get(i)==0){
                        m.mandarLetra(letraEliminar);
                        v.eliminarLetra(letraEliminar);
                        aciertos++;
                        if (aciertos == 10) {//para cada 10 aciertos subir el nivel
                            aciertos = 0;
                            nivel++;

                            if (nivel > 5) {
                                nivel = 5;
                            } else {
                                m.cambiarNivel(nivel);
                                v.cambiarNivel(nivel);
                                aumentarVelocidadCaida();
                            }
                        }                    
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

    /**
     * Metodo de parada de timers, lo usaremos cuando acabe el juego
     */
    public void pararTimers() {
        timer.stop();
        
    }

    /**
     *
     * Switch/Case asociado a la barra de menu y sus opciones
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Salir":

                v.CrearPanelSalida();

                break;

            case "SI":
                System.exit(0);
                break;

            case "NO":
                v.getSalida().setVisible(false);
                break;
            case "Level 1":
                pararTimers();
                velocidadCaida = 40;
                velocidadCreacion = 1500;
                aciertos = 0;
                nivel = 1;
                v.cambiarNivel(nivel);
                m.cambiarNivel(nivel);
                crearTimers();
                break;
            case "Level 2":
                pararTimers();
                velocidadCaida = 30;
                velocidadCreacion = 1300;
                nivel = 2;
                aciertos = 0;
                v.cambiarNivel(nivel);
                m.cambiarNivel(nivel);
                crearTimers();

                break;
            case "Level 3":
                pararTimers();
                velocidadCaida = 20;
                velocidadCreacion = 1100;
                aciertos = 0;
                nivel = 3;
                v.cambiarNivel(nivel);
                m.cambiarNivel(nivel);
                crearTimers();

                break;
            case "Level 4":
                pararTimers();
                velocidadCaida = 10;
                velocidadCreacion = 900;
                aciertos = 0;
                nivel = 4;
                v.cambiarNivel(nivel);
                m.cambiarNivel(nivel);
                crearTimers();

                break;
            case "Level 5":
                pararTimers();
                velocidadCaida = 1;
                velocidadCreacion = 700;
                nivel = 5;
                aciertos = 0;
                v.cambiarNivel(nivel);
                m.cambiarNivel(nivel);
                crearTimers();

                break;
        }
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
        pararTimers();
        velocidadCaida -= 10;
        velocidadCreacion -= 200;
        crearTimers();
    }

    public int getNivel() {
        return nivel;
    }

    
}
