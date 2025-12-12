package Modelo;
import java.util.ArrayList;

import Controlador.Visitor;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public abstract class Certificaciones {
	private String id;
	private String nombre;
	private String descripcion;
	private int minCreditos;
	private int yearsValides;
	ArrayList<Curso> listaAsignaturasCertificacion;
	public Certificaciones(String id, String nombre, String descripcion, int minCreditos, int yearsValides) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.minCreditos = minCreditos;
		this.yearsValides = yearsValides;
		this.listaAsignaturasCertificacion = new ArrayList<>();
	}
	
	public ArrayList<Curso> getListaAsignaturasCertificacion() {
		return listaAsignaturasCertificacion;
	}

	public void setListaAsignaturasCertificacion(ArrayList<Curso> listaAsignaturasCertificacion) {
		this.listaAsignaturasCertificacion = listaAsignaturasCertificacion;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getMinCreditos() {
		return minCreditos;
	}
	public void setMinCreditos(int minCreditos) {
		this.minCreditos = minCreditos;
	}
	public int getYearsValides() {
		return yearsValides;
	}
	public void setYearsValides(int yearsValides) {
		this.yearsValides = yearsValides;
	}
	
	public abstract void aceptar(Visitor v);
	
	public void agregarLista(Curso c) {
		listaAsignaturasCertificacion.add(c);
	}

	@Override
	public String toString() {
		return "Certificaciones [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", minCreditos="
				+ minCreditos + ", yearsValides=" + yearsValides + ", listaAsignaturasCertificacion="
				+ listaAsignaturasCertificacion + "]";
	}
	
}
