package lluviadeletras;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;


public class Vista extends JFrame {
    private Controlador c;
    private ArrayList <Label> letras;
    private ArrayList<Integer> estados=new ArrayList<>();//para saber si las letras suben o bajan
    private ArrayList<Integer> colores=new ArrayList<>();//Array de control de colores de las letras.
    private ArrayList<Integer> velocidades=new ArrayList<>();
    private Label lb;
    private JButton si, no;
    private JLabel fraseNivel, vidas, contadorVidas;
    private JPanel barra, bloque,bloque2 ,salida;
    private JMenuBar barraMenu;
    private JMenu archivo, level;
    private JMenuItem salir, guardar, cargar;
    private JMenuItem level1, level2, level3, level4, level5;
    private int contadorV = 0;
    private int x = 0;
    private Timer tempo;
    private boolean pause=false;
    

    /**
     * Constructor de la vista
     * @param c 
     */
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
        crearBloque2();
        crearBarra();
        moverLetra();
        this.addKeyListener(c);
        this.setResizable(false);
        this.setBounds(380, 80, 600, 600);
        this.getContentPane().setBackground(Color.blue);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
      
       
    }

    /**
     * inicializar elementos de la Barra de Menu superior.
     */
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
        fraseNivel.setBounds(510, 15, 100, 20);
        fraseNivel.setForeground(Color.orange);
        fraseNivel.setFont(fraseNivel.getFont().deriveFont(15.0f));
        vidas = new JLabel("VIDAS: ");
        vidas.setFont(vidas.getFont().deriveFont(15.0f));
        vidas.setForeground(Color.white);
        vidas.setBounds(15, 15, 55, 20);
        this.add(vidas);
        contadorVidas = new JLabel("10");
        contadorVidas.setBounds(70, 15, 60, 20);
        contadorVidas.setFont(contadorVidas.getFont().deriveFont(15.0f));
        contadorVidas.setForeground(Color.white);

        this.add(contadorVidas);
        this.add(fraseNivel);
        contadorV = 10;
    }

    /**
     * Añadimos los componentes de menu a la Barra de menu y a la vista.
     */
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

    /**
     * método de creación de letras
     *
     * @param letra
     */
    public void crearLetras(String letra) {
        boolean comprobar=true;
        for (int i = 0; i < letras.size(); i++) {//comprobamos que las letras creadas no estén repetidas.
            if(letras.get(i).getText().equals(letra)){
                comprobar=false;
            }
        }
        if(comprobar){
            x = (int) (Math.random() * 575);
            lb = new Label(letra);
            lb.setBounds(x, 60, 30, 30);
            lb.setBackground(Color.blue);
            this.add(lb);
            lb.setFont(lb.getFont().deriveFont(30.0f));
            letras.add(lb);
            estados.add(1);
            colores.add((int)Math.floor(Math.random()*(5-2)+2));
            speedingSwap();
            colorChange(colores.size()-1);
        }
    }

    /**
     * caida de letras
     */
    public void cambiarY() {
        for (int i = 0; i < letras.size(); i++) {
          
            if (letras.get(i).getY() >= 499 && (letras.get(i).getX() < bloque.getX() || letras.get(i).getX() > bloque.getX() + 85)) {
                gameOver();
            }
            if (letras.get(i).getY() <=15 && (letras.get(i).getX() < bloque2.getX() || letras.get(i).getX() > bloque2.getX() + 85)) {
                gameOver();            
            } 
           
            if((letras.get(i).getX() >= bloque.getX() && letras.get(i).getX() <= bloque.getX() + 85) && letras.get(i).getY() >= 480) {
                if(estados.get(i)==1){
                    System.out.println("sube");
                    
                    estados.set(i, 2);
                }
            }if((letras.get(i).getX() >= bloque2.getX() && letras.get(i).getX() <= bloque2.getX() + 85) && letras.get(i).getY() <= 62) {
                if(estados.get(i)==2){
                    System.out.println("baja");
                   
                    estados.set(i, 1);
                }
            }
        }
        this.repaint();
    }
    
    //METODO PARA MOVER LAS LETRAS
     public void moverLetra(){
        tempo=new Timer(40,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (int i = 0; i < letras.size(); i++) {
                    if(estados.get(i)==1){
                        System.out.println("baja");
                         letras.get(i).setBounds(letras.get(i).getX(), letras.get(i).getY() +(velocidades.get(i)+1 ), 25, 25);
                       
                    }else if(estados.get(i)==2){System.out.println("sube");
                      letras.get(i).setBounds(letras.get(i).getX(), letras.get(i).getY() -(velocidades.get(i)+1), 25, 25); 
                     
                    }
                }
                cambiarY();
            }
            
        });
        tempo.start();
    }
    

    /**
     * creación y adición de barra inferior de la ventana
     */
    public void crearBarra() {
        barra = new JPanel();
        barra.setBounds(0, 500, 600, 50);
        barra.setBackground(Color.lightGray);
        this.add(barra);
    }

    /**
     * creación y adición de bloque
     */
    public void crearBloque() {
        bloque = new JPanel();
        bloque.setBounds(275, 500, 90, 50);
        bloque.setBackground(Color.gray);
        this.add(bloque);
    }
    
    /**
     * Creación del bloque en la zona superior de la ventana.L
     */
    public void crearBloque2(){
        bloque2=new JPanel();
        bloque2.setBounds(275, 35 , 90, 30);
        bloque2.setBackground(Color.gray);
        this.add(bloque2);        
    }

    /**
     * método para mover bloque a la derecha
     */
    public void moverBloqueDerecha() {
        if (bloque.getX()<= 550) {
            bloque.setBounds(bloque.getX() + 10, bloque.getY(), 90, 50);
            bloque2.setBounds(bloque.getX()+10,bloque2.getY(),90,30);
        }
    }

    /**
     * método para mover bloque a la izquierda
     */
    public void moverBloqueIzquierda() {
        if (bloque.getX() >= 0) {
            bloque.setBounds(bloque.getX() - 10, bloque.getY(), 90, 50);
             bloque2.setBounds(bloque.getX()-10,bloque2.getY(),90,30);
        }
    }
    
    
    /**
     * método para eliminar las letras de pantalla
     *
     * @param letra
     */
    public void eliminarLetra(char letra) {
        for (int i = 0; i < letras.size(); i++) {
            if (letra == letras.get(i).getText().charAt(0)) {
                letras.get(i).setVisible(false);
                letras.get(i).setBackground(Color.blue);
                estados.remove(i);
                colores.remove(i);
                letras.remove(i);
                velocidades.remove(i);
            }
        }
    }

    /**
     * Método para cambiar el color de fondo según se pulse o no la tecla
     * correcta. Se le pasa o 0 o 1.
     *
     * @param x
     */
    public void pintarFondo(int x) {
        if (x == 1) {//Cuando se falla al pular una letra
            this.getContentPane().setBackground(Color.orange);
            this.repaint();
        } else {//volver a pintar azul
            this.getContentPane().setBackground(Color.blue);
            this.repaint();
        }
    }

    /**
     * PANEL DE SALIDA. Se generará al pulsar "Salir" en el menú.
     */
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
        si.setForeground(Color.cyan);
        no.setForeground(Color.cyan);
        si.setBackground(Color.black);
        no.setBackground(Color.black);
        si.addActionListener(c);
        no.addActionListener(c);
        salida.add(mensaje);
        salida.add(si);
        salida.add(no);
        salida.setLayout(null);
        salida.setBounds(0, 0, 600, 600);
        salida.setBackground(Color.darkGray);
        salida.setVisible(true);
        this.add(salida);
    }

   

    /**
     * Metodo para comprobar el nivel y actualizarlo en la vista
     *
     * @param n
     */
    public void cambiarNivel(int n) {
        if (n > 5) {
            fraseNivel.setText("NIVEL " + 5);
        }
        fraseNivel.setText("NIVEL " + n);

    }

    /**
     * Metodo para comprobar las vidas y actualizarlas en la vista
     *
     * @param vidas
     */
    public void restaVidas(int vidas) {
        if (vidas > 0) {
            contadorVidas.setText(String.valueOf(vidas));
        } else {
            contadorVidas.setText(String.valueOf("0"));
        }

    }
    /**
     * METODO DE SELECCION DE COLORES ALEATORIOS. SE LE MANDA UN ENTERO POR PARAMETRO.
     * @return 
     */
    
    public void colorChange(int numero){
        colores.set(numero, colores.get(numero)-1);
        switch(colores.get(numero)){
            case 3:
                letras.get(numero).setForeground(Color.white);
                break;                
            case 2:
                letras.get(numero).setForeground(Color.RED);
                break;
            case 1:
                 letras.get(numero).setForeground(Color.GREEN);
        }
        
    }
    /**
     * METO
     * @param niveles 
     */
    public void speedingSwap(){
        switch(c.getNivel()){
            case 1:
                velocidades.add((int)Math.floor(Math.random()*(3-1)+1));
                break;
            case 2:
                velocidades.add((int)Math.floor(Math.random()*(4-1)+1));
                break;
            case 3:
                 velocidades.add((int)Math.floor(Math.random()*(5-2)+2));
                break;
            case 4:
                 velocidades.add((int)Math.floor(Math.random()*(5-3)+3));
                break;
            case 5:
                 velocidades.add((int)Math.floor(Math.random()*(5-4)+4));
                break;
                        
        }
        
    }
    
    
    /**
     * METODO DE PAUSADO y REANUDADO DE JUEGO 
     */
    public void pause(){
        if(!pause){
            tempo.stop();
            pause=true;
        }else{
            tempo.start();
            pause=false;
        }
    }
    
    
    
    
     /**
     * FIN DEL JUEGO. Se genera la pantalla de fin de juego
     */
    public void gameOver() {
        
        this.getContentPane().setEnabled(false);
        c.pararTimers();
        JLabel b = new JLabel("GAME OVER!");
        b.setFont(b.getFont().deriveFont(40.0f));
        b.setForeground(Color.red);
        b.setBounds(170, 260, 280, 50);
        add(b);
        this.getContentPane().setBackground(Color.black);
        tempo.stop();
        for (int i = 0; i < letras.size(); i++) {
            this.remove(letras.get(i));//eliminacion de todas las letras.
        }
        this.repaint();
    }
    
    
    

    public ArrayList<Integer> getColores() {
        return colores;
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

}
