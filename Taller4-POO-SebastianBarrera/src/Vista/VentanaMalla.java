//sebastian barrera carvajal
//20.015.335-9
//ITI
package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Notas;

public class VentanaMalla extends JFrame {

    private Estudiante estudiante;
    private ArrayList<Curso> catalogoCursos;

    public VentanaMalla(Estudiante estudiante, ArrayList<Curso> catalogoCursos) {
        this.estudiante = estudiante;
        this.catalogoCursos = catalogoCursos;
        setTitle("Malla Curricular Interactiva - " + estudiante.getNombre());
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana, no la app porque como necesitamos o podemos seguir haciendo cosas en la consola
        //si el programa fuera todo en gui corresponde el que cierre la app
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

     
        JLabel lblTitulo = new JLabel("Malla Curricular de " + estudiante.getCarrera(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel principal que va a tener los semestres
        JPanel panelMalla = new JPanel();
        panelMalla.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        int maxSemestres = 10; //  10 semestres (max duracion icci y industrial,iti 8)
        for (int i = 1; i <= maxSemestres; i++) {
            JPanel columnaSemestre = crearColumnaSemestre(i);
            // Solo agregamos la columna si tiene ramos
            if (columnaSemestre.getComponentCount() > 1) { //titulo ocupa un espaacio
                panelMalla.add(columnaSemestre);
            }
        }


        JScrollPane scrollPane = new JScrollPane(panelMalla);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16); // velocidasd del scroll
        add(scrollPane, BorderLayout.CENTER);
        add(crearPanelLeyenda(), BorderLayout.SOUTH);
    }
    private JPanel crearColumnaSemestre(int numeroSemestre) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(180, 500));

        JLabel lblSem = new JLabel("Semestre " + numeroSemestre);
        lblSem.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSem.setFont(new Font("Arial", Font.BOLD, 14));
        lblSem.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        panel.add(lblSem);
        for (Curso curso : catalogoCursos) {
            if (curso.getSemestre() == numeroSemestre) {
                JButton btnCurso = crearBotonCurso(curso);
                panel.add(btnCurso);
                panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
            }
        }
        return panel;
    }

    private JButton crearBotonCurso(Curso curso) {
  
        String textoBoton = curso.getNomnbre();
        
        JButton btn = new JButton(textoBoton);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(160, 65)); 
        btn.setFocusPainted(false); 
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Notas nota = buscarNotaEstudiante(estudiante, curso.getCodigo());
        
        if (nota == null) {
            // PENDIENTE
            btn.setBackground(new Color(240, 240, 240)); // Gris claro
            btn.setToolTipText("Asignatura Pendiente");
        } else if (nota.getEstado().equalsIgnoreCase("Aprobada")) {
            // APROBADA
            btn.setBackground(new Color(169, 223, 191)); // Verde
            btn.setToolTipText("Aprobada con nota: " + nota.getCalificacion());
        } else if (nota.getEstado().equalsIgnoreCase("Reprobada")) {
            // REPROBADA
            btn.setBackground(new Color(245, 183, 177)); // Rojo
            btn.setToolTipText("Reprobada con nota: " + nota.getCalificacion());
        } else if (nota.getEstado().equalsIgnoreCase("Cursando")) {
            // CURSANDO
            btn.setBackground(new Color(249, 231, 159)); // Amarillo
            btn.setToolTipText("Actualmente cursando");
        }

        btn.addActionListener(e -> mostrarDetalleAsignatura(curso, nota));

        return btn;
    }

   
    private void mostrarDetalleAsignatura(Curso c, Notas n) {
        String estado = (n == null) ? "Pendiente" : n.getEstado();
        String notaStr = (n == null) ? "N/A" : String.valueOf(n.getCalificacion());
        String semestreNota = (n == null) ? "-" : n.getSemestre();
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("ASIGNATURA: ").append(c.getNomnbre()).append("\n");
        mensaje.append("Codigo NRC: ").append(c.getCodigo()).append("\n");
        mensaje.append("Creditos:   ").append(c.getCreditos()).append("\n");
        mensaje.append("Area:       ").append(c.getArea()).append("\n\n");
        
        mensaje.append("--- SITUACIÓN DEL ALUMNO ---\n");
        mensaje.append("Estado:     ").append(estado).append("\n");
        mensaje.append("Nota Final: ").append(notaStr).append("\n");
        mensaje.append("Periodo:    ").append(semestreNota).append("\n");

        if (!c.getCodigo().isEmpty()) {
            mensaje.append("\nPrerrequisitos: ").append(c.getCodigo().toString());
        }

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Detalle Académico", JOptionPane.INFORMATION_MESSAGE);
    }
    private JPanel crearPanelLeyenda() {
        JPanel p = new JPanel();
        p.setBorder(new EmptyBorder(10, 0, 10, 0));
        p.add(new JLabel("Referencias:  "));
        p.add(crearEtiquetaColor(" Aprobada ", new Color(169, 223, 191)));
        p.add(crearEtiquetaColor(" Reprobada ", new Color(245, 183, 177)));
        p.add(crearEtiquetaColor(" Cursando ", new Color(249, 231, 159)));
        p.add(crearEtiquetaColor(" Pendiente ", new Color(240, 240, 240)));
        return p;
    }

    private JLabel crearEtiquetaColor(String texto, Color c) {
        JLabel l = new JLabel(texto);
        l.setOpaque(true); 
        l.setBackground(c);
        l.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        return l;
    }
    private Notas buscarNotaEstudiante(Estudiante e, String codigoCurso) {
        for (Notas n : e.getListaNotasMaterias()) { 
            if (n.getCodigoAsignatura().equalsIgnoreCase(codigoCurso)) {
                return n;
            }
        }
        return null;
    }
}