// la verdad no entendi muy bien la funcion especifica que se pedia en el visitor, asi que mostarre mensajes
package Controlador;

import Modelo.CiberSeguridad;
import Modelo.DesarrolloSoftware;
import Modelo.SistemasInteligentes;

public class DashBoard implements Visitor{
	//sebastian barrera carvajal
	//20.015.335-9
	//ITI
	@Override
	public void visitar(SistemasInteligentes sis) {
		System.out.println("[Minor IA]: Sistemas inteligentes en Python");
        System.out.println("[ACCION]:  Usando librerias numpy  y pandas");
        System.out.println("..");
        System.out.println(".");
		
	}

	@Override
	public void visitar(CiberSeguridad cs) {
		System.out.println("[Minor SEC]: CiberSeguridad");
        System.out.println("[ACCION]:  Hacking Etico");
        System.out.println("..");
        System.out.println(".");
	}

	@Override
	public void visitar(DesarrolloSoftware ds) {
		System.out.println("[Minor DEV]: Arquitectura de software y gestion de recursos.");
        System.out.println("[ACCION]:  Diseñando sistemas");
        System.out.println("..");
        System.out.println(".");
	}

}
