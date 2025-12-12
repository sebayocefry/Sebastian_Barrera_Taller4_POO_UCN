package Controlador;
import java.util.ArrayList;

import Modelo.Certificaciones;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Notas;
import Modelo.Registros;
import Modelo.Usuario;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class GestionEstudiantes {

    public String listarEstudiantesPorCertificacion(ArrayList<Usuario> listaUsuarios, String idCertificacion) {
    	StringBuilder sb = new StringBuilder();
        sb.append("--- LISTA  DE ESTUDIANTES EN " + idCertificacion.toUpperCase() + " ----\n");
        boolean bandera = false;

        for (Usuario u : listaUsuarios) {
            if (u instanceof Estudiante e) {             
                // buscamos si tiene la certificacion inscrita
                for (Registros r : e.getListCertificaciones()) {
                    if (r.getIdCerftificacion().equalsIgnoreCase(idCertificacion)) {
                        sb.append(imprimirPerfil(e, r));
                        bandera = true;
                    }
                }
            }
        }

        if (!bandera) {
            sb.append(" No se encontraron estudiantes inscritos en esta linea de minor\n");
        }
        return sb.toString();
    }

    public String validarAvance(Estudiante alumno, Certificaciones certificacion) {
        StringBuilder sb = new StringBuilder();
        
        if (alumno == null || certificacion == null) {
            return "Error: Datos de estudiante o certificación invalidos.";
        }


        Registros registroAlumno=null;
        for (Registros r : alumno.getListCertificaciones()) {
            if (r.getIdCerftificacion().equalsIgnoreCase(certificacion.getId())) {
                registroAlumno = r;
                break;
            }
        }

        if (registroAlumno == null) {
            return "--El estudiante " + alumno.getNombre() + " NO tiene inscrito el minor " + certificacion.getId();
        }
        sb.append("Analizando historial del alumno " + alumno.getNombre() + "...\n");
        
       // punto de error debugg(borrar)
        ArrayList<Curso> cursosRequeridos = certificacion.getListaAsignaturasCertificacion(); 
        
        if (cursosRequeridos.isEmpty()) {
            return ">> Error: La certificación no tiene cursos existentes";
        }

        int totalMateria = cursosRequeridos.size();
        int materiasAprobadas = 0;
        for (Curso c : cursosRequeridos) {
        	String nrcRequerido = c.getCodigo();
            if (tieneCursoAprobado(alumno, nrcRequerido)) {materiasAprobadas++;}
        }

        //sctualizar el registro
        int porcentajeReal = (materiasAprobadas * 100) / totalMateria;
        //System.out.println(porcentajeReal);
        registroAlumno.setProgreso(porcentajeReal);
        
        if (porcentajeReal >= 100) {
            registroAlumno.setEstado("Completada");
            sb.append("|-- FELICIDADES!!! Certificacion MINOR completada al 100%\n");
        } else {
            registroAlumno.setEstado("Activa");
            sb.append("!-- Progreso actualizado: " + porcentajeReal + "% (" + materiasAprobadas + "/" + totalMateria + " cursos).\n");
        }
        return sb.toString();
    }

    private boolean tieneCursoAprobado(Estudiante e, String nrc) {
        for (Notas n : e.getListaNotasMaterias()) {
            if (n.getCodigoAsignatura().equalsIgnoreCase(nrc) && n.getEstado().equalsIgnoreCase("Aprobada")) {
                return true;
            }
        }
        return false;
    }

   
    private String imprimirPerfil(Estudiante e, Registros r) {
    	StringBuilder sb = new StringBuilder();
        sb.append("------------------------------------------------\n");     
        sb.append("Alumno:   " + e.getNombre() + "\n"); 
        sb.append("Correo:   " + e.getCorreoE() + "\n");
        sb.append("Carrera:  " + e.getCarrera() + "\n");
        sb.append("Situacion MINOR:\n");
        sb.append("   - Estado:   " + r.getEstado() + "\n");
        sb.append("   - Progreso: " + r.getProgreso() + "%\n");
        sb.append("------------------------------------------------\n");
        return sb.toString();
    }
}