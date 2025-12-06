import java.util.ArrayList;

public class VisualizacionPerfilEstudiante {
	public void mostrarDatosPersonales(Estudiante e) {
        System.out.println("-----PERFIL DEL ESTUDIANTE------");
        System.out.println();
        System.out.println("Nombre:   " + e.getNombre());
        System.out.println("RUT:      " + e.getRut()); 
        System.out.println("Correo electronico:   " + e.getCorreoE());
        System.out.println("Carrera:  " + e.getCarrera());
        System.out.println("Semestre: " + e.getSemestre());
        System.out.println("------------------------------------\n");
    }
	
	public void mostrarMalla(Estudiante e, ArrayList<Curso> catalogoCursos) {
        System.out.println("--- MALLA CURRICULAR ---");
        //hago reserva de espacios para que todo se ordene con el mismo espacio como un jtable o una excel
        System.out.printf("%-10s %-30s %-15s %-5s\n", "CODIGO", "ASIGNATURA", "ESTADO", "NOTA");
        System.out.println("----------------------------------------------------------------");
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
            System.out.printf("%-10s %-30s %-15s %-5s\n", curso.getCodigo(),nombreMostrar,estado,notaStr);
        }
        System.out.println("----------------------------------------------------------------\n");
    }
	public void mostrarPromedios(Estudiante e) {
        ArrayList<Notas> historia = e.getListaNotasMaterias();
        
        if (historia.isEmpty()) {System.out.println("No hay notas para calcular promedios.");return;}
        System.out.println("--- Informe de notas ---");
        double sumaTotal = 0;
        int countTotal = 0;
        for (Notas n : historia) {
            sumaTotal += n.getCalificacion();
            countTotal++;
        }
        double promedioGeneral = (countTotal > 0) ? sumaTotal / countTotal : 0.0;
        System.out.printf("Promedio General: %.2f\n\n", promedioGeneral);
        System.out.println("Promedios por Semestre:");
        ArrayList<String> semestresCalculados = new ArrayList<>();

        for (Notas n : historia) {
            String sem = n.getSemestre();
            
            if (!semestresCalculados.contains(sem)) {
                double promSem = calcularPromedioDeUnSemestre(historia, sem);
                System.out.printf("   - Semestre %s: %.2f\n", sem, promSem);
                semestresCalculados.add(sem);
            }
        }
        System.out.println("");
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
