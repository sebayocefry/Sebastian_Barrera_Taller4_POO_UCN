package Vista;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Controlador.Controlador;
import Modelo.Usuario;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class MenuAdminFrame extends JFrame {

    private Controlador sistema;
    private Usuario admin;

    public MenuAdminFrame(Controlador sistema, Usuario admin) {
        this.sistema = sistema;
        this.admin = admin;

        setTitle("Administración - " + admin.getNombre());
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        add(new JLabel("PANEL ADMINISTRADOR", SwingConstants.CENTER));

        JButton btn1 = new JButton("1. Crear Cuentas");
        btn1.addActionListener(e -> guiCrearCuenta());
        add(btn1);

        JButton btn2 = new JButton("2. Modificar Cuentas");
        btn2.addActionListener(e -> guiModificarCuenta());
        add(btn2);

        JButton btn3 = new JButton("3. Eliminar Cuentas");
        btn3.addActionListener(e -> guiEliminarCuenta());
        add(btn3);

        JButton btn4 = new JButton("4. Restablecer Contraseña");
        btn4.addActionListener(e -> guiRestablecerPass());
        add(btn4);

        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.addActionListener(e -> {
            dispose();
            new LoginFrame(sistema).setVisible(true);
        });
        add(btnSalir);
    }
    private void guiCrearCuenta() {
        String[] opciones = {"Estudiante", "Coordinador"};
        int tipo = JOptionPane.showOptionDialog(this, "Seleccione Tipo", "Crear", 0, 3, null, opciones, opciones[0]) + 1;
        
        if(tipo == 1) { 
            String nom = JOptionPane.showInputDialog("Nombre:");
            String rut = JOptionPane.showInputDialog("RUT:");
            String mail = JOptionPane.showInputDialog("Correo:");
            String car = JOptionPane.showInputDialog("Carrera:");
            String pass = JOptionPane.showInputDialog("Contraseña:");
            sistema.CrearEstudianteManual(nom, "Estudiante", pass, rut, car, 1, mail); // Semestre 1 por defecto
        } else if (tipo == 2) { 
            String nom = JOptionPane.showInputDialog("Nombre:");
            String pass = JOptionPane.showInputDialog("Contraseña:");
            String area = JOptionPane.showInputDialog("Área:");
            sistema.CrearCoorManual(nom, "Coordinador", pass, area);
        }
        JOptionPane.showMessageDialog(this, "Cuenta creada (verifique en listados).");
    }

    private void guiModificarCuenta() {
        String[] opciones = {"Estudiante", "Coordinador"};
        int tipo = JOptionPane.showOptionDialog(this, "Tipo a Modificar", "Modificar", 0, 3, null, opciones, opciones[0]) + 1;
        
        if (tipo == 1) { 
            String rut = JOptionPane.showInputDialog("RUT del Estudiante a modificar:");
            String mail = JOptionPane.showInputDialog("Nuevo Correo:");
            String car = JOptionPane.showInputDialog("Nueva Carrera:");
            sistema.modificarCuenta(rut, 1, mail, car);
        } else { 
            String nom = JOptionPane.showInputDialog("Nombre del Coordinador a modificar:");
            String pass = JOptionPane.showInputDialog("Nueva Contraseña:");
            String area = JOptionPane.showInputDialog("Nuevo Rol/Area:");
            sistema.modificarCuenta(nom, 2, pass, area);
        }
        JOptionPane.showMessageDialog(this, "Modificación realizada.");
    }

    private void guiEliminarCuenta() {
        String[] opciones = {"Estudiante", "Coordinador"};
        int tipo = JOptionPane.showOptionDialog(this, "Tipo a Eliminar", "Borrar", 0, 3, null, opciones, opciones[0]) + 1;
        String id = JOptionPane.showInputDialog("Ingrese RUT (Estudiante) o Nombre (Coordinador):");
        
        sistema.eliminarCuenta(id, tipo);
        JOptionPane.showMessageDialog(this, "Cuenta eliminada si existía.");
    }

    private void guiRestablecerPass() {
        String[] opciones = {"Estudiante", "Coordinador"};
        int tipo = JOptionPane.showOptionDialog(this, "Tipo", "Password", 0, 3, null, opciones, opciones[0]) + 1;
        String id = JOptionPane.showInputDialog("Ingrese RUT o Nombre:");
        String pass = JOptionPane.showInputDialog("Nueva Contraseña:");
        
        sistema.rrestablecerContraseña(id, tipo, pass);
        JOptionPane.showMessageDialog(this, "Contraseña actualizada.");
    }
}