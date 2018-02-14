
package lluviadeletras;

import java.util.ArrayList;
import java.util.Random;

public class Letra {

    private Random r = new Random();
    private ArrayList<String> letras;
    private int nivel=1;
        private int velocidadCaida;
    private String fraseNivel;

    public Letra() {
        letras = new ArrayList();
    }
    
    
    /**
     * 
     * @return ESTE METODO TE RETORNA UNA LETRA ALEATORIA QUE SERA UN STRING.
     */
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
    /**
     * 
     * @param letra CON ESTE METODO ELIMINA LA LETRA QUE LE PASAMOS DEL ARRAY
     */
    public void quitarLetraArray(char letra) {
        for (int i = 0;i<letras.size();i++) {
            if (letra == letras.get(i).charAt(0)) {
                letras.remove(letras.get(i));
            }
        }
    }

   /**
    * aumentar nivel hasta 5 PROGRESIVAMENTE
    */
    public void setNivel() {
        nivel++;
        if(nivel>=5){
            nivel=5;
        }
    }
    /**
     * 
     * @param x CAMBIA EL NIVEL PONIENDO EL QUE SE PASE MEDIANTE LA X.
     */
        public void cambiarNivel(int x) {
            //velocidadCaida = 100;
            nivel = x;
            if(nivel>=5){
            nivel=5;
        }
    }   
    
}
