package Vista;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controlador.Controlador;
import Modelo.Estudiante;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class MenuEstudianteFrame extends JFrame {
    public MenuEstudianteFrame(Controlador sistema, Estudiante alumno) {
        setTitle("Menu Estudiante: " + alumno.getNombre());
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton btn1 = new JButton("1. Ver Perfil");
        btn1.addActionListener(e -> {
            String texto = sistema.verPerfilEstudiante(alumno);
            new VentanaReporte("Mi Perfil", texto).setVisible(true);
        });
        add(btn1);

        JButton btn2 = new JButton("2. Malla Interactiva");
        btn2.addActionListener(e -> sistema.mostrarMallaGrafica(alumno));
        add(btn2);

 
        JButton btn3 = new JButton("3. Dashboard Progreso");
        btn3.addActionListener(e -> {
            String reporte = sistema.verDashBoard(alumno);
            new VentanaReporte("Dashboard", reporte).setVisible(true);
        });
        add(btn3);

        JButton btn4 = new JButton("4. Inscribir Minor");
        btn4.addActionListener(e -> {
            String oferta = sistema.mostrarCertificacionesLindo();
            new VentanaReporte("Oferta Disponible", oferta).setVisible(true);
            String id = JOptionPane.showInputDialog(this, "Ingrese el ID del Minor a inscribir:");
            if (id != null && !id.isEmpty()) {
                String resultado = sistema.inscribirAsignaturas(id, alumno);
                JOptionPane.showMessageDialog(this, resultado);
            }
        });
        add(btn4);

        JButton btnSalir = new JButton("Cerrar Sesion");
        btnSalir.addActionListener(e -> {
            dispose();
            new LoginFrame(sistema).setVisible(true);
        });
        add(btnSalir);
    }
}