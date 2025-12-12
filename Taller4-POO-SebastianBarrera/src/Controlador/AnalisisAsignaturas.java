package Controlador;
import java.util.ArrayList;
import java.util.Collections;

import Modelo.Estudiante;
import Modelo.Notas;
import Modelo.Usuario;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class AnalisisAsignaturas implements Estrategia {

	@Override
	public String analizar(ArrayList<Usuario> lista) {
		// TODO Auto-generated method stub
		// listas paralelas para hacer los conteo
		ArrayList<String> codigosCarrera = new ArrayList<>();
        ArrayList<Integer> veces = new ArrayList<>();
        
        for (Usuario u : lista) {
			if(u instanceof Estudiante est) {
				for (Notas nota : est.getListaNotasMaterias()) {
					if(nota.getEstado().equalsIgnoreCase("reprobado")) {
						String codigo = nota.getCodigoAsignatura();
						int indice = codigo.indexOf(codigo);
						if(indice == -1) {
							codigosCarrera.add(codigo);
							veces.add(1);
						}else {
							veces.set(indice, veces.get(indice)+1);
						}
							
					}
				}
			}
		}
        
        if (veces.isEmpty()) return "No hay reprobados registrados.";

        // Buscamos el número más alto en la lista paralela
        // uso un collection.max porque ya pudiendo usar el .sort para ordenar no veo necesidad de buscar el mayor de forma manual
        
        int maxReprobados = Collections.max(veces);
        
        // Buscamos en qué posición (índice) está ese número
        int indiceGanador = veces.indexOf(maxReprobados);
        String materia = codigosCarrera.get(indiceGanador);
        
		return "/---- Asignatura Brigida----\n"+
        		"Asignatura: "+ materia+
        		"Reprobaciones: "+ maxReprobados;
	}

}
