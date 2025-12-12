package Modelo;
import java.time.LocalDate;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class Registros {
   private String rutEstudiante;
   private String idCerftificacion;
   private LocalDate fecha;
   private String estado;
   private int progreso;
   public Registros(String rutEstudiante, String idCerftificacion, LocalDate fecha, String estado, int progreso) {
	super();
	this.rutEstudiante = rutEstudiante;
	this.idCerftificacion = idCerftificacion;
	this.fecha = fecha;
	this.estado = estado;
	this.progreso = progreso;
   }
   public String getRutEstudiante() {
	return rutEstudiante;
   }
   public void setRutEstudiante(String rutEstudiante) {
	this.rutEstudiante = rutEstudiante;
   }
   public String getIdCerftificacion() {
	return idCerftificacion;
   }
   public void setIdCerftificacion(String idCerftificacion) {
	this.idCerftificacion = idCerftificacion;
   }
   public LocalDate getFecha() {
	return fecha;
   }
   public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
   }
   public String getEstado() {
	return estado;
   }
   public void setEstado(String estado) {
	this.estado = estado;
   }
   public int getProgreso() {
	return progreso;
   }
   public void setProgreso(int progreso) {
	this.progreso = progreso;
   }
   @Override
   public String toString() {
	return "Registros [rutEstudiante=" + rutEstudiante + ", idCerftificacion=" + idCerftificacion + ", fecha=" + fecha
			+ ", estado=" + estado + ", progreso=" + progreso + "]";
   }
   
   
}
