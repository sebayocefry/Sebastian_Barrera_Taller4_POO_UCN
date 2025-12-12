package Vista;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controlador.Controlador;
import Modelo.Cordinador;
import Modelo.Estudiante;
import Modelo.Usuario;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class LoginFrame extends JFrame {

    private Controlador sistema;
    private JComboBox<String> cmbTipoUsuario;
    private JTextField txtCredencial;
    private JPasswordField txtPass;
    private JLabel lblCredencial;

    public LoginFrame(Controlador sistema) {
        this.sistema = sistema;
        
        setTitle("Acceso Academicore");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10)); 

     
        JLabel lblTitulo = new JLabel("BIENVENIDO", SwingConstants.CENTER);//centramos el texto de las letras al medio y asi no queda tan feo
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo);
        add(new JLabel("   Seleccione tipo de cuenta:"));
        String[] opciones = {"Usuario / Coordinador (Nombre)", "Estudiante (RUT)"};
        cmbTipoUsuario = new JComboBox<>(opciones);
        add(cmbTipoUsuario);
        lblCredencial = new JLabel("Nombre Usuario:");
        add(lblCredencial);
        
        txtCredencial = new JTextField();
        add(txtCredencial);

        cmbTipoUsuario.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (cmbTipoUsuario.getSelectedIndex() == 1) {
                    lblCredencial.setText("   RUT Estudiante:");
                } else {
                    lblCredencial.setText("   Nombre Usuario:");
                }
            }
        });


        JPanel panelPass = new JPanel(new GridLayout(2, 1));
        panelPass.add(new JLabel("   password:"));
        txtPass = new JPasswordField();
        panelPass.add(txtPass);
        add(panelPass);

        JButton btnLogin = new JButton("INGRESAR");
        btnLogin.addActionListener(e -> ejecutarLogin());
        add(btnLogin);
    }

    private void ejecutarLogin() {
        String textoIngresado = txtCredencial.getText();
        String password = new String(txtPass.getPassword());
        
    
        // indx 0 (Usuario)sumamos 1
        // index 1 (Estudiante)sumamos 1
        int tipoParaTuMetodo = cmbTipoUsuario.getSelectedIndex() + 1;
        Usuario logueado = sistema.login(textoIngresado, password, tipoParaTuMetodo);
        if (logueado != null) {
            dispose(); 
            if (logueado instanceof Estudiante) {
                new MenuEstudianteFrame(sistema, (Estudiante) logueado).setVisible(true);
                
            } else if (logueado instanceof Cordinador) {
                new MenuCoordinadorFrame(sistema, (Cordinador) logueado).setVisible(true);
                
            } else {
                new MenuAdminFrame(sistema, logueado).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
        
    }
}