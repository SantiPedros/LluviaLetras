
package lluviadeletras;


public class Modelo{
    private Controlador c;
    private Letra l;
    String letra;
    public Modelo (Controlador c){
        this.c=c;
        l = new Letra();
    }
    /***
     * 
     * @return ESTE METODO COGE LA LETRA ALEATORIA DE LA CLASE LETRA Y SE LA VA A MANDAR AL CONTROLADOR 
     */
    public String recogerLetra(){
        letra = l.letraRandom();
        return letra;
    }
    
    /**
     * 
     * @param letra ESTE METODO LLAMA AL METODO DE LA CLASE LETRA Y ELIMINA LA LETRA QUE SE LE PASE
     */
    public void mandarLetra(char letra){
        l.quitarLetraArray(letra);
    }
    
    /**
     * ESTE METODO SIRVE PARA IR CAMBIANDO EL NIVEL PROGRESIVAMENTE
     */
    public void subirNivel(){
        l.setNivel();
    }
       /**
     * ESTE METODO SIRVE PARA CAMBIAR EL NIVEL QUE SE LO VAMOS A PASAR DESDE EL CONTROLADOR A TRAVES DEL PARAMETRO X
     */
    public void cambiarNivel(int x) {
        l.cambiarNivel(x);
    }
    
}
