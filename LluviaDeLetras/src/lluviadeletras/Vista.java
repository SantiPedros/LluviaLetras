/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.Color;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
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
    private JPanel barra, bloque;
    private MenuBar barraMenu;
    private Menu archivo,level;
    private MenuItem salir,guardar,cargar;
    private MenuItem level1,level2,level3,level4,level5;
   
    
    

    private int y = 0;
    private int x = 0;

    public Vista(Controlador c) {
        this.setTitle("LLuvia de Letras");
        this.c = c;
        this.setLayout(null);
        this.crearMenu();
        this.menuAddition();
        y = -10;
        this.setResizable(false);
        y = -20;
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
        this.setBounds(50, 50, 600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    //inicializar elementos de la Barra de Menu superior.
    public void crearMenu(){
        barraMenu=new MenuBar();
        archivo=new Menu("Archivo");
        level=new Menu("Level");
        guardar=new MenuItem("Guardar");
        cargar=new MenuItem("Cargar");
        salir=new MenuItem("Salir");
        level1=new MenuItem("Level 1");
        level2=new MenuItem("Level 2");
        level3=new MenuItem("Level 3");
        level4=new MenuItem("Level 4");
        level5=new MenuItem("Level 5");
    }    
    // Añadimos los componentes de menu a la Bara de menu y a la vista.
    public void menuAddition(){        
        archivo.add(guardar);
        archivo.add(cargar);
        archivo.add(salir);
        
        level.add(level1);
        level.add(level2);
        level.add(level3);
        level.add(level4);
        level.add(level5);
        
        barraMenu.add(archivo);
        barraMenu.add(level);
        this.setMenuBar(barraMenu);      
    }
    
//método de creación de letras
    public void crearLetras(String letra) {
        System.out.println(letra);
        x = (int) (Math.random() * 600);
        lb = new Label();
        lb.setText(letra);
        lb.setBounds(x, 10, 25, 25);
        this.add(lb);
        letras.add(lb);
    }

    public void cambiarY() {
        for (int i = 0; i < letras.size(); i++) {
            letras.get(i).setBounds(letras.get(i).getX(), letras.get(i).getY() + 10, 25, 25);
        }
        this.repaint();
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
        if(bloque.getX()<=550){
            bloque.setBounds(bloque.getX() + 10, bloque.getY(), 85, 20);           
        }       
        
    }
 //método para mover bloque
    public void moverBloqueIzquierda() {
        if(bloque.getX()>=0){
            bloque.setBounds(bloque.getX() - 10, bloque.getY(), 85, 20);            
        }        
    }
   
    //método para eliminar las letras de pantalla
    public void eliminarLetra (char letra){
        for (int i = 0; i < letras.size(); i++) {
            if(letras.get(i).getText().equals(""+letra)){
                this.remove(letras.get(i));
                letras.remove(letras.get(i));
                lb.setText("");
            }
        }
        this.repaint();
        repaint();
    }

    public ArrayList<Label> getLetras() {
        return letras;
    }

 

    public Label getLb() {
        return lb;
    }
    public void pintarFondo(){
        System.out.println(" pintalo de amarillo ");
        this.setBackground(Color.yellow);
        this.repaint();
    }

    

}
