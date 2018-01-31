/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;


import java.awt.Label;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author rodry
 */
public class Letra {
    private Random r = new Random();
    private ArrayList<String> letras;
    

    public Letra(){
           letras= new ArrayList();
        //System.out.println("hola ");
    }
    
    public String letraRandom(int nivel) {
     
        int x=nivel*5;
        String letra;
        Boolean rep;
        do {
            letra = ((char) (r.nextInt(x) + 'A')) + "";
            rep = false;
            for (int i = 0; i < letras.size(); i++) {
                if (letras.get(i).equals(letra)) {
                    rep = true;
                    break;
                }
            }
        } while (rep);
        letras.add(letra);
        return letra;
    }
    
    public void quitarLetraArray(char letra){
        for (int i = 0; i < letras.size(); i++) {
            if(letras.get(i).charAt(0)==(letra)){
                letras.remove(letras.get(i));
            }
        }
    }
}
