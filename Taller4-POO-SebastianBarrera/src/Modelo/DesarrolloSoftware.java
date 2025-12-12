//sebastian barrera carvajal
//20.015.335-9
//ITI
package Modelo;

import Controlador.Visitor;

public class DesarrolloSoftware extends Certificaciones  {

	public DesarrolloSoftware(String id, String nombre, String descripcion, int minCreditos, int yearsValides) {
		super(id, nombre, descripcion, minCreditos, yearsValides);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void aceptar(Visitor v) {
		// TODO Auto-generated method stub
		v.visitar(this);
	}

}
