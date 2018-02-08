/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author rodry
 */
public class Letra {

    private Random r = new Random();
    private ArrayList<String> letras;
    private int nivel=1;
        private int velocidadCaida;
    private String fraseNivel;

    public Letra() {
        letras = new ArrayList();
    }
    

    public String letraRandom() {
        String letra;
        Boolean rep;
        do {
            letra = ((char) (r.nextInt(nivel * 5) + 'A')) + "";
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

    public void quitarLetraArray(char letra) {
        for (int i = 0; i < letras.size(); i++) {
            if (letra == letras.get(i).charAt(0)) {
                letras.remove(letras.get(i));
            }
        }
    }

    //aumentar nivel hasta 5, que se queda estable
    public void setNivel() {
        nivel++;
        if(nivel>=5){
            nivel=5;
        }
    }
        public void nivel1() {
            //velocidadCaida = 100;
            nivel = 1;
            
    }

    public void nivel2() {
           // velocidadCaida = 600;
            nivel =2;
    }

    public void nivel3() {
           // velocidadCaida = 1100;
            nivel = 3;
    }

    public void nivel4() {
            //velocidadCaida = 1600;
            nivel = 4;
            
    }

    public void nivel5() {
            //velocidadCaida = 2100;
            nivel = 5;
    }
    
    
}
