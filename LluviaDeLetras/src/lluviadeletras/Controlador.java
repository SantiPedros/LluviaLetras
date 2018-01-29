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
public class Controlador {
    private Vista v;
    private Modelo m;
    private Letra l;
    public Controlador(){
        v = new Vista(this);
        m = new Modelo(this);
        l = new Letra(this);
      }
    
}
