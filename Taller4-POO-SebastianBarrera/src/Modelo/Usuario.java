//sebastian barrera carvajal
//20.015.335-9
//ITI
package Modelo;

public class Usuario {
	private String nombre;
	private String password;
	private String rol;
	public Usuario(String nombre, String password, String rol) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.rol = rol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", password=" + password + ", rol=" + rol + "]";
	}
	
	
}
