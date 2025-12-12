package Controlador;
import java.time.LocalDate;
import java.util.ArrayList;

import Modelo.Certificaciones;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Notas;
import Modelo.Registros;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class InscripcionCertficaciones {
	public String ofertaCertificaciones(ArrayList<Certificaciones> lista) {
		StringBuilder sb = new StringBuilder();
		sb.append("---CERTIFICACIONES---\n");
		for (Certificaciones c : lista) {
			sb.append("\n");
			sb.append("ID:             " + c.getId() + "\n");
            sb.append("Nombre:        " + c.getNombre() + "\n");
            sb.append("Descripcion:   " + c.getDescripcion() + "\n");
            sb.append("----REQUISITOS:----\n");
            sb.append("Creditos Requeridos: " + c.getMinCreditos() + "\n"); 
            sb.append("Validez del minor:        " + c.getYearsValides() + " años\n");
            sb.append("\n");
		}
		return sb.toString();
	}
	
	public String incribirRamo(Estudiante e, String idCert,ArrayList<Certificaciones> listacert,ArrayList<Curso> listaCurso) {
		Certificaciones certificacionesSeleccionada = null;
		for (Certificaciones c :listacert) {
			if(c.getId().equalsIgnoreCase(idCert)) {
				certificacionesSeleccionada = c;
				break;
			}
		}
		
		if(certificacionesSeleccionada==null) {
			return "Error: Certificacion no existente";
		}
		
		//validamos que no este inscrito 
		for (Registros curso : e.getListCertificaciones()) {
			if(curso.getIdCerftificacion().equalsIgnoreCase(idCert)) {
				return "Error: La certificacion ya esta inscrita en la linea del estudiante";
			}
		}
		
		int creditosAlumno = calcularCreditos(e, listaCurso);
		int creditosNecesarios = certificacionesSeleccionada.getMinCreditos();
		//sino nos alcanzan los creditos
		if(creditosAlumno< creditosNecesarios) {
			return "----RECHAZADO:---\nNo cumples con los requisitos academicos.\n" +
                   "   Tus Creditos: " + creditosAlumno + "\n" +
                   "   Requeridos:   " + creditosNecesarios;
		}
		// localDate.now para que se ponga la fecha del pc 
		Registros nR = new Registros(e.getRut(), certificacionesSeleccionada.getId(), LocalDate.now(), "Activa", 0);
		e.agregarCertificacion(nR);
		
		return "-----------------------------\n" +
               "INSCRIPCION EXITOSA!\n" +
               "Has sido matriculado en: " + certificacionesSeleccionada.getNombre() + "\n" +
               "Fecha: " + LocalDate.now() + "\n";
	}
	/**
	 * como nos dice que el alumno necesita tener una cantidad x de creditos aporbados en el total 
	 * de la carrera para poder tomar una linea de minor, entonces aqui es como un sumador que suma un credito por ramo con situacion aprobada
	 * @param e estudiante 
	 * @param listaCursos lista de los cursos,como un catalogo por decir asi
	 * @return
	 */
	private int calcularCreditos(Estudiante e, ArrayList<Curso> listaCursos) {
		int base = 0;
		for (Notas n : e.getListaNotasMaterias()) {
			 if(n.getEstado().equalsIgnoreCase("Aprobada")) {
				 for (Curso curso : listaCursos) {
					if(curso.getCodigo().equalsIgnoreCase(n.getCodigoAsignatura())) {
						base += curso.getCreditos();
						break;
					}
				}
			 }
		}
		
		return base;
	}
}
