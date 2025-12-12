package Vista;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controlador.Controlador;
import Modelo.Cordinador;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class MenuCoordinadorFrame extends JFrame {
    
    private Controlador sistema;
    private Cordinador coord; 

    public MenuCoordinadorFrame(Controlador sistema, Cordinador coord) {
        this.sistema = sistema;
        this.coord = coord;

        setTitle("Coordinacion: " + coord.getAreaCordinador());
        setSize(1200, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 1, 10, 10)); 

        JLabel lblTitulo = new JLabel("Bienvenido " + coord.getNombre(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo);

        JButton btn1 = new JButton("1. Reporte Inscripciones");
        btn1.addActionListener(e -> {
            String texto = sistema.aplicarEstrategia(1);
            new VentanaReporte("Reporte Inscripciones", texto).setVisible(true);
        });
        add(btn1);

        JButton btn2 = new JButton("2. Reporte Asignaturas Riesgo");
        btn2.addActionListener(e -> {
            String texto = sistema.aplicarEstrategia(2);
            new VentanaReporte("Reporte Riesgo", texto).setVisible(true);
        });
        add(btn2);


        JButton btn3 = new JButton("3. Modificar Certificación");
        btn3.addActionListener(e -> modificarCertificacionGUI());
        add(btn3);


        JButton btn4 = new JButton("4. Generar Diplomas");
        btn4.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("ID Certificación:");
            if(id != null) {
                String res = sistema.emisionDiplomaCertficacion(id);
                new VentanaReporte("Emisión Diplomas", res).setVisible(true);
            }
        });
        add(btn4);

        JButton btn5 = new JButton("5. Estudiantes por Minor");
        btn5.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("ID Certificación:");
            if(id != null) {
                String res = sistema.mostrarPerfilesEsMinor(id);
                new VentanaReporte("Listado Alumnos", res).setVisible(true);
            }
        });
        add(btn5);
        
        JButton btn6 = new JButton("6. Validar Avance Alumno");
        btn6.addActionListener(e -> {
            String rut = JOptionPane.showInputDialog("RUT Estudiante:");
            String id = JOptionPane.showInputDialog("ID Certificación:");
            if(rut != null && id != null) {
                String res = sistema.validarAvanceAcademicoMinor(rut, id);
                new VentanaReporte("Resultado Validación", res).setVisible(true);
            }
        });
        add(btn6);

        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.addActionListener(e -> {
            dispose();
            new LoginFrame(sistema).setVisible(true);
        });
        add(btnSalir);
    }

    private void modificarCertificacionGUI() {
        JTextField txtId = new JTextField();
        JTextField txtDesc = new JTextField();
        JTextField txtCred = new JTextField();
        JTextField txtVal = new JTextField();

        Object[] message = {
            "ID Certificacion:", txtId,
            "Nueva Descripcion:", txtDesc,
            "Nuevos Creditos:", txtCred,
            "Nueva Validez:", txtVal
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Modificar", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                sistema.modificarCertficacion(
                    txtId.getText(), 
                    txtDesc.getText(), 
                    Integer.parseInt(txtCred.getText()), 
                    Integer.parseInt(txtVal.getText())
                );
                JOptionPane.showMessageDialog(this, "Modificacion enviada (Revise consola por si hubo un error si hubo error).");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: Ingrese numeros validos en creditos/validez.");
            }
        }
    }
}