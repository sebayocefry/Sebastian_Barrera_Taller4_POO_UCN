package Controlador;
import java.util.ArrayList;

import Modelo.Certificaciones;
import Modelo.Estudiante;
import Modelo.Registros;
import Modelo.Usuario;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class GestionCertificaciones {
	
	// metodo para modificar alguna certificacion
	public boolean modificar(ArrayList<Certificaciones> listaCertificaciones, String id, String nuevaDesc, int nuevosCreditos, int nuevaValidez) {
		for (Certificaciones certificaciones : listaCertificaciones) {
			if(certificaciones.getId().equalsIgnoreCase(id)) {
				certificaciones.setDescripcion(nuevaDesc);
				certificaciones.setMinCreditos(nuevosCreditos);
				certificaciones.setYearsValides(nuevaValidez);
				return true;
			}
		}
		return false;
	}
	
	// reporte certificado 
	
	public String generar(ArrayList<Usuario> listaUsuarios, String idCertificacion) {
		StringBuilder sb = new StringBuilder();
		boolean bandera = false;
		sb.append("--- Procesando titulo para certificacion " + idCertificacion + " ---\n");
		for (Usuario usuario : listaUsuarios) {
			if(usuario instanceof Estudiante e) {
				for (Registros r : e.getListCertificaciones()) {
					if(r.getIdCerftificacion().equalsIgnoreCase(idCertificacion)&&r.getEstado().equalsIgnoreCase("completada")) {
						sb.append(imprimirTitulo(e,r));
						bandera = true;
					}
					
				}
			}
		}
		if(!bandera) {
			sb.append("UPS, LO LAMENTAMOS NO HAY ESTUDIANTES QUE CUMPLAN EL PERFIL\n");
		}
		return sb.toString();
	}
	
	private String imprimirTitulo(Estudiante e, Registros r) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n========================================================\n");
	    sb.append("               UNIVERSIDAD CATOLICA DE MISH            \n");
	    sb.append("                 CERTIFICACION OFICIAL                    \n");
	    sb.append("========================================================\n");
	    sb.append("Se otorga el presente certificado a:\n");
	    sb.append("   " + e.getNombre().toUpperCase() + " (RUT: " + e.getRut() + ")\n");
	    sb.append("\n");
	    sb.append("Por haber completado satisfactoriamente el minor de:\n");
	    sb.append("   MINOR ID: " + r.getIdCerftificacion() + "\n");
	    sb.append("\n");
	    sb.append("Fecha de completitud: " + java.time.LocalDate.now() + "\n"); // libreria para que siempre tome la fecha del pc
	    sb.append("Estado Final: " + r.getEstado() + " (" + r.getProgreso() + "%)\n");
	    sb.append("========================================================\n");
	    return sb.toString();
	}
	
}
