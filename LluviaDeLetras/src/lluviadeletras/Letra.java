
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
    * MÉTODO PARA LA CREACION DE LETRAS.
    */
    
    public String creacionCaracteres(){
        char letra=(char)Math.floor(Math.random()*(90-65)+65);
        switch(nivel){
            case 1:
                do {
                    if(letra=='A' || letra=='S' || letra=='D'|| letra=='D' || letra=='F'|| letra=='J'|| letra=='K'|| letra=='K'|| letra=='L'){
                        break;
                    }else{
                        letra=(char)Math.floor(Math.random()*(90-65)+65);
                    } 
                } while (true);
           break;
                
                
                
            case 2:
                 do {
                    if(letra=='G' || letra=='H' || letra=='R'|| letra=='U' || letra=='M'|| letra=='C'|| letra=='T'|| letra=='Y'|| letra=='E'){
                        break;
                    }else{
                        letra=(char)Math.floor(Math.random()*(90-65)+65);
                    } 
                } while (true);
                
                break;
                
            case 3:
                 do {
                    if(letra=='Z' || letra=='N' || letra=='Q'|| letra=='W' || letra=='I'|| letra=='O'|| letra=='P'|| letra=='T'|| letra=='X'){
                        break;
                    }else{
                        letra=(char)Math.floor(Math.random()*(90-65)+65);
                    } 
                } while (true);
                 break;
           
            case 4:
                 do {
                    if(letra=='A' || letra=='S' || letra=='D'|| letra=='D' || letra=='F'|| letra=='J'|| letra=='K'|| letra=='K'|| letra=='L'){
                        break;
                    }else{
                        letra=(char)Math.floor(Math.random()*(90-65)+65);
                    } 
                } while (true);
                 break;
            case 5:
                 
                    if((int )Math.floor(Math.random()*(3-1)+1)==1){
                       letra=(char)Math.floor(Math.random()*(90-65)+65); 
                       
                    }else{
                        letra=(char)Math.floor(Math.random()*(57-48)+65);
                    } 
         
                 break;         
                        
        }
        return ""+letra;
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
