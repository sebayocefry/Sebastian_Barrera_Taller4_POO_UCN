package Controlador;
import java.util.ArrayList;

import Modelo.Certificaciones;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Notas;
import Modelo.Registros;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class GestionSeguimientoDashBoard {
    private DashBoard visitor;

    public  GestionSeguimientoDashBoard() {
        this.visitor = new DashBoard();
    }

    // hago el metodo aparte para el titulo porque o sino se me mostraba cada vez que iteraba el for en el sistema
    public String mostrarTitlo(Estudiante alumno) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("\n-----------------------------------------------------------\n");
        sb.append("       DASHBOARD DE PROGRESO: " + alumno.getNombre().toUpperCase() + "\n");
        sb.append("\n");
        return sb.toString();
    }
    public String mostrarProgresoUnitario(Estudiante alumno, Registros registro, Certificaciones cert) {
    	StringBuilder sb = new StringBuilder();
        sb.append("\n------------------------------------------------------------\n");
        sb.append("Minor: " + cert.getNombre().toUpperCase() + "\n");
        sb.append("ID:            " + cert.getId() + "\n");
        sb.append("Estado Actual: " + registro.getEstado() + "\n");
        
        sb.append(imprimirBarraProgreso(registro.getProgreso()));

        
        sb.append("Mensaje del Mentor Virtual:\n");
        // Nota: Como el visitor imprime en consola, aqui podriamos adaptarlo
        // pero por simplicidad dejaremos que imprima o lo ideal seria que el visitor tambien retorne String.
        // Asumiendo que visitor imprime, se vera en consola, pero el resto se vera en GUI.
        cert.aceptar(visitor); 
        
        sb.append(mostrarPendientes(alumno, cert));
        
        sb.append("------------------------------------------------------------\n");
        return sb.toString();
    }
    
    private String mostrarPendientes(Estudiante alumno, Certificaciones cert) {
    	StringBuilder sb = new StringBuilder();
        sb.append("\n| ASIGNATURAS PENDIENTES:\n");
        
        ArrayList<Curso> requeridos = cert.getListaAsignaturasCertificacion();
        boolean bandera = true;

        for (Curso req : requeridos) {
            if (!tieneCursoAprobado(alumno, req.getCodigo())) {
                sb.append("   [ ] " + req.getCodigo() + " - " + req.getNomnbre() + "\n");
                bandera = false;
            }
        }

        if (bandera) {
            sb.append("Ninguna! Has completado todos los requisitos académicos.\n");
        }
        return sb.toString();
    }
// esta parte me costo mas y fue una de las pocas partes que tuve que buscar en google, youtube y pregunte hasta en un foro, dejare todo en el readme
    private String imprimirBarraProgreso(int porcentaje) {
    	StringBuilder sb = new StringBuilder();
        sb.append("Progreso:---|");
        int barras = porcentaje / 10; // Cada 10 porciento es un bloque |-| que es como si fuese una carga o algo asi, no se me ocurrio como representar la carga mas que eso |-|
        for (int i = 0; i < 10; i++) {
            if (i < barras) sb.append("|-|");
            else sb.append("-");
        }
        sb.append("|---" + porcentaje + "%\n");
        return sb.toString();
    }
// funcion que nos dice si hay una materia aprobada o no devolviendo un bolenao
    private boolean tieneCursoAprobado(Estudiante e, String nrc) {
        for (Notas n : e.getListaNotasMaterias()) {
            if (n.getCodigoAsignatura().equalsIgnoreCase(nrc) && n.getEstado().equalsIgnoreCase("Aprobada")) {
                return true;
            }
        }
        return false;
    }
}