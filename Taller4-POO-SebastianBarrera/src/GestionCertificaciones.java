import java.util.ArrayList;

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
	
	public void generar(ArrayList<Usuario> listaUsuarios, String idCertificacion) {
		boolean bandera = false;
		System.out.println("--- Procesando titulo para certificacion " + idCertificacion + " ---");
		for (Usuario usuario : listaUsuarios) {
			if(usuario instanceof Estudiante e) {
				for (Registros r : e.getListCertificaciones()) {
					if(r.getIdCerftificacion().equalsIgnoreCase(idCertificacion)&&r.getEstado().equalsIgnoreCase("completada")) {
						imprimirTitulo(e,r);
						bandera = true;
					}
					
				}
			}
		}
		if(!bandera) {
			System.out.println("UPS, LO LAMENTAMOS NO HAY ESTUDIANTES QUE CUMPLAN EL PERFIL");
		}
	}
	
	private void imprimirTitulo(Estudiante e, Registros r) {
		System.out.println("\n========================================================");
	    System.out.println("               UNIVERSIDAD CATOLICA DE MISH            ");
	    System.out.println("                 CERTIFICACION OFICIAL                    ");
	    System.out.println("========================================================");
	    System.out.println("Se otorga el presente certificado a:");
	    System.out.println("   " + e.getNombre().toUpperCase() + " (RUT: " + e.getRut() + ")");
	    System.out.println("");
	    System.out.println("Por haber completado satisfactoriamente el minor de:");
	    System.out.println("   MINOR ID: " + r.getIdCerftificacion());
	    System.out.println("");
	    System.out.println("Fecha de completitud: " + java.time.LocalDate.now()); // libreria para que siempre tome la fecha del pc
	    System.out.println("Estado Final: " + r.getEstado() + " (" + r.getProgreso() + "%)");
	    System.out.println("========================================================\n");
	}
	
}
