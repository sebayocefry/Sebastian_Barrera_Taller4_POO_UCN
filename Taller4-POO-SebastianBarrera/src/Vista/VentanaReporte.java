//sebastian barrera carvajal
//20.015.335-9
//ITI
package Vista;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaReporte extends JFrame {
    public VentanaReporte(String titulo, String contenido) {
        setTitle(titulo);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        JTextArea area = new JTextArea(contenido);
        area.setEditable(false); 
        area.setMargin(new Insets(10,10,10,10));
        area.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
        add(new JScrollPane(area)); 
    }
}