//sebastian barrera carvajal
//20.015.335-9
//ITI
package Controlador;

import Modelo.Certificaciones;
import Modelo.CiberSeguridad;
import Modelo.DesarrolloSoftware;
import Modelo.SistemasInteligentes;

public class FactoryCertificaciones {
	public static Certificaciones crear(String[] partes) {
		String tipo = partes[0];
		switch (tipo.toUpperCase()) {
		case "CERT-001":
			return new SistemasInteligentes(partes[0], partes[1], partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]));
		case "CERT-002":
			return new CiberSeguridad(partes[0], partes[1], partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]));
		case "CERT-003":
			return new DesarrolloSoftware(partes[0], partes[1], partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]));
		}
		return null;
	}
	
}
