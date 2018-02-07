/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

/**
 *
 * @author rodry
 */
public class Modelo{
    private Controlador c;
    private Letra l;
    String letra;
    public Modelo (Controlador c){
        this.c=c;
        l = new Letra();
    }
    
    public String recogerLetra(){
        letra = l.letraRandom();
        return letra;
    }
    
    public void mandarLetra(char letra){
        l.quitarLetraArray(letra);
    }

    public void subirNivel(){
        l.setNivel();
    }
    
}
