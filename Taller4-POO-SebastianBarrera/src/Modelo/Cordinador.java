//sebastian barrera carvajal
//20.015.335-9
//ITI
package Modelo;

public class Cordinador extends Usuario {
	private String areaCordinador;

	public Cordinador(String nombre, String password, String rol, String areaCordinador) {
		super(nombre, password, rol);
		this.areaCordinador = areaCordinador;
	}

	public String getAreaCordinador() {
		return areaCordinador;
	}

	public void setAreaCordinador(String areaCordinador) {
		this.areaCordinador = areaCordinador;
	}

	@Override
	public String toString() {
		String d = super.toString();
		
		return d.substring(0,d.length()-1)+" Cordinador area Cordinador: " + areaCordinador + " ";
	}
	
}
