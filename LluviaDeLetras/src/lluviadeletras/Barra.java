
package lluviadeletras;

import java.awt.Color;
import javax.swing.JPanel;

public class Barra extends Vista{

    /**
     * 
     * @param c CREAMOS LA BARRA , LE PASAMOS EL CONTROLADOR Y LA AÑADIMOS.
     */
    public Barra(Controlador c) {
        super(c);
        JPanel barra=new JPanel();
        barra.setBounds(0, 500, 600, 20);
        barra.setBackground(Color.red);
        this.add(barra);
    }
}
