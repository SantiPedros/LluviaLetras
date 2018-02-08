package lluviadeletras;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JPanel;

/**
 *
 * @author rodry
 */
public class Vista extends JFrame {

    private Controlador c;
    private ArrayList<Label> letras;
    private Label lb;
    private JButton si, no;
    private JLabel fraseNivel, vidas, contadorVidas;
    private JPanel barra, bloque, salida;
    private JMenuBar barraMenu;
    private JMenu archivo, level;
    private JMenuItem salir, guardar, cargar;
    private JMenuItem level1, level2, level3, level4, level5;
    private Timer pintar;

    private int contadorV = 0;

    private int y = 0;
    private int x = 0;

    public Vista(Controlador c) {
        this.setTitle("LLuvia de Letras");
        this.c = c;
        this.setBackground(Color.white);
        this.setLayout(null);
        this.crearMenu();
        this.menuAddition();
        this.setResizable(false);
        letras = new ArrayList();
        crearBloque();
        crearBarra();

//    letras = new ArrayList();
//    JLabel a = new JLabel("a");
//    a.setBounds(60,60,200,200);
//    letras.add(a);
//    this.add(a);
        this.addKeyListener(c);
        this.setResizable(false);
        this.setBounds(380, 80, 600, 600);
        this.getContentPane().setBackground(Color.blue);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    //inicializar elementos de la Barra de Menu superior.
    public void crearMenu() {
        barraMenu = new JMenuBar();
        archivo = new JMenu("Archivo");
        level = new JMenu("Level");
        guardar = new JMenuItem("Guardar");
        cargar = new JMenuItem("Cargar");
        salir = new JMenuItem("Salir");
        salir.addActionListener(c);
        level1 = new JMenuItem("Level 1");
        level2 = new JMenuItem("Level 2");
        level3 = new JMenuItem("Level 3");
        level4 = new JMenuItem("Level 4");
        level5 = new JMenuItem("Level 5");
        fraseNivel = new JLabel("NIVEL 1");
        fraseNivel.setBounds(400, 100, 100, 100);
        vidas = new JLabel("VIDAS: ");
        vidas.setBounds(100, 490, 100, 100);
        this.add(vidas);
        contadorVidas = new JLabel("10");
        contadorVidas.setBounds(200, 490, 100, 100);
        this.add(contadorVidas);
        this.add(fraseNivel);
        contadorV = 10;
    }

    // Añadimos los componentes de menu a la Barra de menu y a la vista.
    public void menuAddition() {
        archivo.add(guardar);
        archivo.add(cargar);
        archivo.add(salir);

        level1.addActionListener(c);
        level2.addActionListener(c);
        level3.addActionListener(c);
        level4.addActionListener(c);
        level5.addActionListener(c);

        level.add(level1);
        level1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
        level.add(level2);
        level2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
        level.add(level3);
        level3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
        level.add(level4);
        level4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK));
        level.add(level5);
        level5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.CTRL_DOWN_MASK));

        barraMenu.add(archivo);
        barraMenu.add(level);
        this.setJMenuBar(barraMenu);
    }

    //método de creación de letras
    public void crearLetras(String letra) {
        System.out.println(letra);
        x = (int) (Math.random() * 575);
        lb = new Label();
        lb.setText(letra);
        lb.setBounds(x, 10, 30, 30);
        lb.setBackground(Color.blue);
        this.add(lb);
        lb.setFont(lb.getFont().deriveFont(30.0f));
        lb.setForeground(Color.white);
        letras.add(lb);
    }

    //caida de letras 
    public void cambiarY() {
        for (int i = 0; i < letras.size(); i++) {
            letras.get(i).setBounds(letras.get(i).getX(), letras.get(i).getY() + 10, 30, 30);
            if (letras.get(i).getY() >= 500 && (letras.get(i).getX() < bloque.getX() || letras.get(i).getX() > bloque.getX() + 85)) {
                gameOver();
            } else if ((letras.get(i).getX() >= bloque.getX() || letras.get(i).getX() <= bloque.getX() + 85) && letras.get(i).getY() >= 500) {
                ascensoLetras(i);
            }
        }
        this.repaint();
    }

    //ascenso de letras
    public void ascensoLetras(int i) {
        Timer timer;
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                letras.get(i).setBounds(letras.get(i).getX(), letras.get(i).getY() - 10, 25, 25);
                if (letras.get(i).getY() <= 0) {
                    System.out.println("asñdlkfjasdkñfñasdsñldkfjasdf");
                    gameOver();
                }
            }
        });
        timer.start();
    }

    //creación y adición de barra inferior de la ventana
    public void crearBarra() {
        barra = new JPanel();
        barra.setBounds(0, 500, 600, 30);
        barra.setBackground(Color.lightGray);
        this.add(barra);
    }

    //creación y adición de bloque
    public void crearBloque() {
        bloque = new JPanel();
        bloque.setBounds(275, 500, 90, 30);
        bloque.setBackground(Color.gray);
        this.add(bloque);
    }

    //método para mover bloque
    public void moverBloqueDerecha() {
        if (bloque.getX() <= 550) {
            bloque.setBounds(bloque.getX() + 10, bloque.getY(), 90, 30);
        }

    }

    //método para mover bloque
    public void moverBloqueIzquierda() {
        if (bloque.getX() >= 0) {
            bloque.setBounds(bloque.getX() - 10, bloque.getY(), 90, 30);
        }
    }

    //método de rebote de letras cuando tocan el bloque inferior
    public void rebotarLetras() {
        for (int i = 0; i < letras.size(); i++) {
            if (letras.get(i).getX() == bloque.getX() && letras.get(i).getY() == bloque.getY()) {
                System.out.println("SUBEN");
                letras.get(i).setBounds(letras.get(i).getX(), letras.get(i).getY() - 10, 25, 25);
                if (letras.get(i).getY() <= 0) {
                    System.out.println("asñdlkfjasdkñfñasdsñldkfjasdf");
                    gameOver();
                } else {
                    System.out.println("asdfasdf");
                }
            }

        }
    }

    //método para eliminar las letras de pantalla
    public void eliminarLetra(char letra) {
        for (int i = 0; i < letras.size(); i++) {

            if (letra == letras.get(i).getText().charAt(0)) {
                letras.get(i).setVisible(false);
                letras.get(i).setBackground(Color.blue);

                letras.remove(i);
            }
        }
    }

    //Método para cambiar el color de fondo según se pulse o no la tecla correcta.
    public void pintarFondo(int x) {
        if (x == 1) {//fallo
            this.getContentPane().setBackground(Color.orange);
            this.repaint();

        } else {//volver a pintar blanco
            this.getContentPane().setBackground(Color.blue);
            this.repaint();
        }
    }

    //PANEL DE SALIDA. Se generará al pulsar "Salir" en el menú
    public void CrearPanelSalida() {
        this.repaint();
        salida = new JPanel();
        JLabel mensaje = new JLabel("SEGURO QUE DESEA SALIR?");
        mensaje.setFont(mensaje.getFont().deriveFont(30.0f));
        mensaje.setForeground(Color.white);
        mensaje.setBounds(75, 100, 500, 45);
        si = new JButton("SI");
        no = new JButton("NO");
        si.setFont(si.getFont().deriveFont(25.0f));
        no.setFont(si.getFont().deriveFont(25.0f));
        si.setBounds(170, 210, 90, 35);
        no.setBounds(330, 210, 90, 35);
        no.setForeground(Color.white);
        si.setBackground(Color.green);
        no.setBackground(Color.red);
        si.addActionListener(c);
        no.addActionListener(c);
        salida.add(mensaje);
        salida.add(si);
        salida.add(no);
        salida.setLayout(null);
        salida.setBounds(0, 0, 600, 600);
        salida.setBackground(Color.darkGray);
        //salida.setFocusable(false);
        salida.setVisible(true);
        this.add(salida);
    }

//FIN DEL JUEGO
    public void gameOver() {
        //this.getContentPane().removeAll();
        this.getContentPane().setEnabled(false);
        c.pararTimers();
        JLabel b = new JLabel("GAME OVER!");
        b.setFont(b.getFont().deriveFont(40.0f));
        b.setForeground(Color.red);
        b.setBounds(170, 260, 280, 50);
        add(b);
        this.getContentPane().setBackground(Color.black);
        this.repaint();
    }

    public JPanel getSalida() {
        return salida;
    }

    public ArrayList<Label> getLetras() {
        return letras;
    }

    public Label getLb() {
        return lb;
    }

    public void cambiarNivel(String frase) {
        fraseNivel.setText(frase);
    }
//        public void sumarAciertos(){
//        contadorAciertos++;
//        contadorPuntos.setText(String.valueOf(contadorAciertos));
//        
//        
//    }

    public void restaVidas() {
        contadorV--;
        if (contadorV > 0) {
            contadorVidas.setText(String.valueOf(contadorV));
        } else {
            contadorVidas.setText(String.valueOf("0"));
        }

    }

}
