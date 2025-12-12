//sebastian barrera carvajal
//20.015.335-9
//ITI
package Controlador;

import Modelo.Cordinador;
import Modelo.Estudiante;
import Modelo.Usuario;

public class FactoryUsuario {
	public static Usuario crearU(String[] partes,String rol) {
		
		switch (rol.toLowerCase()) {
		case "admin":
			return new Usuario(partes[0], partes[1], partes[2]);
		case "coordinador":
			return new Cordinador(partes[0], partes[1], partes[2],partes[3]);
		case "estudiante":
			return new Estudiante(partes[1], partes[5], "estudiante", partes[0], partes[2], Integer.parseInt(partes[3]),partes[4]);
		}
		
		return null;
	}
}
