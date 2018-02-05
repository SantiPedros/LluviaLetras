/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author WINDOWS
 */
public class Barra extends Vista{

    public Barra(Controlador c) {
        super(c);
        JPanel barra=new JPanel();
        barra.setBounds(0, 500, 600, 20);
        barra.setBackground(Color.red);
        this.add(barra);
    }
}
