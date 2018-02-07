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
   
    private JPanel barra, bloque,salida;
    private JMenuBar barraMenu;
    private JMenu archivo, level;
    private JMenuItem salir, guardar, cargar;
    private JMenuItem level1, level2, level3, level4, level5;
    private Timer pintar;

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
        this.setBounds(250, 80, 600, 600);
        this.setBackground(Color.white);
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
        level1 = new JMenuItem("Level 1");
        level2 = new JMenuItem("Level 2");
        level3 = new JMenuItem("Level 3");
        level4 = new JMenuItem("Level 4");
        level5 = new JMenuItem("Level 5");
    }

    // Añadimos los componentes de menu a la Bara de menu y a la vista.
    public void menuAddition() {
        archivo.add(guardar);
        archivo.add(cargar);
        archivo.add(salir);

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
        lb.setBounds(x, 10, 25, 25);
        this.add(lb);
        letras.add(lb);
    }
//caida de letras 

    public void cambiarY() {
        for (int i = 0; i < letras.size(); i++) {
            letras.get(i).setBounds(letras.get(i).getX(), letras.get(i).getY() + 10, 25, 25);
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
            }
        });
        timer.start();
    }

//creación y adición de barra inferior de la ventana
    public void crearBarra() {
        barra = new JPanel();
        barra.setBounds(0, 500, 600, 20);
        barra.setBackground(Color.red);
        this.add(barra);
    }
//creación y adición de bloque

    public void crearBloque() {
        bloque = new JPanel();
        bloque.setBounds(275, 500, 85, 20);
        bloque.setBackground(Color.DARK_GRAY);
        this.add(bloque);
    }
//método para mover bloque

    public void moverBloqueDerecha() {
        if (bloque.getX() <= 550) {
            bloque.setBounds(bloque.getX() + 10, bloque.getY(), 85, 20);
        }
    }
    //método para mover bloque

    public void moverBloqueIzquierda() {
        if (bloque.getX() >= 0) {
            bloque.setBounds(bloque.getX() - 10, bloque.getY(), 85, 20);
        }
    }

    //método de rebote de letras cuando tocan el bloque inferior
    public void rebotarLetras() {
        for (int i = 0; i < letras.size(); i++) {
            if (letras.get(i).getX() == bloque.getX() && letras.get(i).getY() == bloque.getY()) {
                // ascensoLetras();
                letras.get(i).setBounds(letras.get(i).getX(), letras.get(i).getY() - 10, 25, 25);
            }
        }
    }

    //método para eliminar las letras de pantalla
    public void eliminarLetra(char letra) {
        for (int i = 0; i < letras.size(); i++) {

            if (letras.get(i).getText().equals("" + letra)) {
                System.out.println(letras.get(i).getText().toString());

                
            if (letra == letras.get(i).getText().charAt(0)) {

                this.remove(letras.get(i));
                letras.remove(letras.get(i));
                remove(lb);
            }
            this.repaint();
        }
       }
    }

    public ArrayList<Label> getLetras() {
        return letras;
    }

    public Label getLb() {
        return lb;
    }

    public void pintarFondo(int x) {
        if (x == 1) {//fallo
            this.getContentPane().setBackground(Color.yellow);
            this.repaint();

        } else {//volver a pintar blanco
            this.getContentPane().setBackground(Color.white);
            this.repaint();

        }


    }

//FIN DEL JUEGO
    public void gameOver() {
        //this.getContentPane().removeAll();
        this.getContentPane().setEnabled(false);
        c.pararTimers();
        JLabel b = new JLabel("GAMEOVER");
        b.setBounds(300, 300, 100, 20);
        add(b);
        this.repaint();
    }
}
