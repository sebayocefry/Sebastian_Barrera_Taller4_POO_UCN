package Controlador;
import java.util.ArrayList;

import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Notas;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class VisualizacionPerfilEstudiante {
	public String mostrarDatosPersonales(Estudiante e) {
		StringBuilder sb = new StringBuilder();
        sb.append("-----PERFIL DEL ESTUDIANTE------\n");
        sb.append("\n");
        sb.append("Nombre:   ").append(e.getNombre()).append("\n");
        sb.append("RUT:      ").append(e.getRut()).append("\n"); 
        sb.append("Correo electronico:   ").append(e.getCorreoE()).append("\n");
        sb.append("Carrera:  ").append(e.getCarrera()).append("\n");
        sb.append("Semestre: ").append(e.getSemestre()).append("\n");
        sb.append("------------------------------------\n");
        return sb.toString();
    }
	
	public String mostrarMalla(Estudiante e, ArrayList<Curso> catalogoCursos) {
		StringBuilder sb = new StringBuilder();
        sb.append("--- MALLA CURRICULAR ---\n");
        //hago reserva de espacios para que todo se ordene con el mismo espacio como un jtable o una excel
        sb.append(String.format("%-10s %-30s %-15s %-5s\n", "CODIGO", "ASIGNATURA", "ESTADO", "NOTA"));
        sb.append("----------------------------------------------------------------\n");
        for (Curso curso : catalogoCursos) {
            Notas notaRegistrada = buscarNota(e, curso.getCodigo());
            String estado = "PENDIENTE";
            String notaStr = "-";
            if (notaRegistrada != null) {
                estado = notaRegistrada.getEstado().toUpperCase();
                notaStr = String.valueOf(notaRegistrada.getCalificacion());
            }
            String nombreMostrar = curso.getNomnbre();
            if (nombreMostrar.length() > 28) {
                nombreMostrar = nombreMostrar.substring(0, 28) + "..";
            }
            sb.append(String.format("%-10s %-30s %-15s %-5s\n", curso.getCodigo(),nombreMostrar,estado,notaStr));
        }
        sb.append("----------------------------------------------------------------\n");
        return sb.toString();
    }
	public String mostrarPromedios(Estudiante e) {
		StringBuilder sb = new StringBuilder();
        ArrayList<Notas> historia = e.getListaNotasMaterias();
        
        if (historia.isEmpty()) {return "No hay notas para calcular promedios.";}
        sb.append("--- Informe de notas ---\n");
        double sumaTotal = 0;
        int countTotal = 0;
        for (Notas n : historia) {
            sumaTotal += n.getCalificacion();
            countTotal++;
        }
        double promedioGeneral = (countTotal > 0) ? sumaTotal / countTotal : 0.0;
        sb.append(String.format("Promedio General: %.2f\n\n", promedioGeneral));
        sb.append("Promedios por Semestre:\n");
        ArrayList<String> semestresCalculados = new ArrayList<>();

        for (Notas n : historia) {
            String sem = n.getSemestre();
            
            if (!semestresCalculados.contains(sem)) {
                double promSem = calcularPromedioDeUnSemestre(historia, sem);
                sb.append(String.format("   - Semestre %s: %.2f\n", sem, promSem));
                semestresCalculados.add(sem);
            }
        }
        sb.append("\n");
        return sb.toString();
    }
	
	private double calcularPromedioDeUnSemestre(ArrayList<Notas> notas, String semestre) {
        double suma = 0;
        int contador = 0;
        for (Notas n : notas) {
            if (n.getSemestre().equalsIgnoreCase(semestre)) {
                suma += n.getCalificacion();
                contador++;
            }
        }
        return (contador > 0) ? suma / contador : 0.0;// si contador es mayor a 0 devolvemos la div sino 0 asi evitamos errores
    }
	
	private Notas buscarNota(Estudiante e, String codigoCurso) {
        for (Notas n : e.getListaNotasMaterias()) {
            if (n.getCodigoAsignatura().equalsIgnoreCase(codigoCurso)) {
                return n;
            }
        }
        return null;
    }
}
