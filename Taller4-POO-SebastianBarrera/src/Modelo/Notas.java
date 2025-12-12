//sebastian barrera carvajal
//20.015.335-9
//ITI
package Modelo;

public class Notas {
	private String rutEstudiante;
	private String codigoAsignatura;
	private double calificacion;
	private String estado;
	private String semestre;
	
	public Notas(String rutEstudiante, String codigoAsignatura, double calificacion, String estado, String semestre) {
		super();
		this.rutEstudiante = rutEstudiante;
		this.codigoAsignatura = codigoAsignatura;
		this.calificacion = calificacion;
		this.estado = estado;
		this.semestre = semestre;
	}
	
	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	public String getRutEstudiante() {
		return rutEstudiante;
	}
	public void setRutEstudiante(String rutEstudiante) {
		this.rutEstudiante = rutEstudiante;
	}
	public String getCodigoAsignatura() {
		return codigoAsignatura;
	}
	public void setCodigoAsignatura(String codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	@Override
	public String toString() {
		return "Notas [rutEstudiante=" + rutEstudiante + ", codigoAsignatura=" + codigoAsignatura + ", calificacion="
				+ calificacion + ", estado=" + estado + ", semestre=" + semestre + "]";
	}
	
	
	
}
