package Controlador;
import java.util.ArrayList;

import Modelo.Estudiante;
import Modelo.Registros;
import Modelo.Usuario;
//sebastian barrera carvajal
//20.015.335-9
//ITI
// a diferencia del taller pasado retorno todo en datos en vez de solo mostrar para asi poder usarlo en una futura visualizzacion grafica
public class AnalisiInscripcion implements Estrategia {
	
	@Override
	public String analizar(ArrayList<Usuario> lista) {
		// TODO Auto-generated method stub
		int iA = 0;
	    int cyber = 0;
	    int soft = 0;
	    for (Usuario usuario : lista) {
			if(usuario instanceof Estudiante) {
				Estudiante e = (Estudiante)usuario;
				for (Registros r : e.getListCertificaciones()) {
					if(r.getIdCerftificacion().equalsIgnoreCase("CERT-001")) {iA++;}
					if(r.getIdCerftificacion().equalsIgnoreCase("CERT-002")) {cyber++;}
					if(r.getIdCerftificacion().equalsIgnoreCase("CERT-003")) {soft++;}
				}
			}
		}
	    
		return "Reporte de tasa de inscripcion:\n"+
		"| Sistemas Inteligentes:  "+iA +" alumnos totales.\n"+
		"| Cyber Seguridad:  "+cyber +" alumnos totales.\n"+
		"| Desarrolo de software:  "+soft +" alumnos totales.\n";
	}

}
