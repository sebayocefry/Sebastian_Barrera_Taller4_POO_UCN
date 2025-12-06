import java.util.ArrayList;

public class GestionEstudiantes {

    public void listarEstudiantesPorCertificacion(ArrayList<Usuario> listaUsuarios, String idCertificacion) {
        System.out.println("--- LISTA  DE ESTUDIANTES EN " + idCertificacion.toUpperCase() + " ----");
        boolean bandera = false;

        for (Usuario u : listaUsuarios) {
            if (u instanceof Estudiante e) {             
                // buscamos si tiene la certificacion inscrita
                for (Registros r : e.getListCertificaciones()) {
                    if (r.getIdCerftificacion().equalsIgnoreCase(idCertificacion)) {
                        imprimirPerfil(e, r);
                        bandera = true;
                    }
                }
            }
        }

        if (!bandera) {
            System.out.println(" No se encontraron estudiantes inscritos en esta linea de minor");
        }
    }

    public void validarAvance(Estudiante alumno, Certificaciones certificacion) {
        
        if (alumno == null || certificacion == null) {
            System.out.println("Error: Datos de estudiante o certificación invalidos.");
            return;
        }


        Registros registroAlumno=null;
        for (Registros r : alumno.getListCertificaciones()) {
            if (r.getIdCerftificacion().equalsIgnoreCase(certificacion.getId())) {
                registroAlumno = r;
                break;
            }
        }

        if (registroAlumno == null) {
            System.out.println("--El estudiante " + alumno.getNombre() + " NO tiene inscrito el minor " + certificacion.getId());
            return;
        }
        System.out.println("Analizando historial del alumno " + alumno.getNombre() + "...");
        
       // punto de error debugg(borrar)
        ArrayList<Curso> cursosRequeridos = certificacion.getListaAsignaturasCertificacion(); 
        
        if (cursosRequeridos.isEmpty()) {
            System.out.println(">> Error: La certificación no tiene cursos existentes");
            return;
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
            System.out.println("|-- FELICIDADES!!! Certificacion MINOR completada al 100%");
        } else {
            registroAlumno.setEstado("Activa");
            System.out.println("!-- Progreso actualizado: " + porcentajeReal + "% (" + materiasAprobadas + "/" + totalMateria + " cursos).");
        }
    }

    private boolean tieneCursoAprobado(Estudiante e, String nrc) {
        for (Notas n : e.getListaNotasMaterias()) {
            if (n.getCodigoAsignatura().equalsIgnoreCase(nrc) && n.getEstado().equalsIgnoreCase("Aprobada")) {
                return true;
            }
        }
        return false;
    }

   
    private void imprimirPerfil(Estudiante e, Registros r) {
        System.out.println("------------------------------------------------");     
        System.out.println("Alumno:   " + e.getNombre()); 
        System.out.println("Correo:   " + e.getCorreoE());
        System.out.println("Carrera:  " + e.getCarrera());
        System.out.println("Situacion MINOR:");
        System.out.println("   - Estado:   " + r.getEstado());
        System.out.println("   - Progreso: " + r.getProgreso() + "%");
        System.out.println("------------------------------------------------");
    }
}