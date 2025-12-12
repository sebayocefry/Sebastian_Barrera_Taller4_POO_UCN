//sebastian barrera carvajal
//20.015.335-9
//ITI
package Modelo;

public class Asignaturas {
	private String idCertficacion; 
	private String codigoAsignatura;
	public Asignaturas(String idCertficacion, String codigoAsignatura) {
		super();
		this.idCertficacion = idCertficacion;
		this.codigoAsignatura = codigoAsignatura;
	}
	public String getIdCertficacion() {
		return idCertficacion;
	}
	public void setIdCertficacion(String idCertficacion) {
		this.idCertficacion = idCertficacion;
	}
	public String getCodigoAsignatura() {
		return codigoAsignatura;
	}
	public void setCodigoAsignatura(String codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
	}
	@Override
	public String toString() {
		return "Asignaturas [idCertficacion=" + idCertficacion + ", codigoAsignatura=" + codigoAsignatura + "]";
	}
	
	
}
