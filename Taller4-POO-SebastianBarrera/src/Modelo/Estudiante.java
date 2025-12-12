package Modelo;
import java.util.ArrayList;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class Estudiante extends Usuario{
	private String rut;
	private String carrera;
	private int semestre;
	private String correoE;
	private ArrayList<Notas> listaNotasMaterias;
	private ArrayList<Registros> listCertificaciones;
	
	public Estudiante(String nombre, String password, String rol, String rut, String carrera, int semestre,
			String correoE) {
		super(nombre, password, rol);
		this.rut = rut;
		this.carrera = carrera;
		this.semestre = semestre;
		this.correoE = correoE;
		this.listaNotasMaterias  = new ArrayList<>();
		this.listCertificaciones = new ArrayList<>();
	}
	
	public ArrayList<Notas> getListaNotasMaterias() {
		return listaNotasMaterias;
	}

	public void setListaNotasMaterias(ArrayList<Notas> listaNotasMaterias) {
		this.listaNotasMaterias = listaNotasMaterias;
	}

	public ArrayList<Registros> getListCertificaciones() {
		return listCertificaciones;
	}

	public void setListCertificaciones(ArrayList<Registros> listCertificaciones) {
		this.listCertificaciones = listCertificaciones;
	}

	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	public String getCorreoE() {
		return correoE;
	}
	public void setCorreoE(String correoE) {
		this.correoE = correoE;
	}
	
	public void agregarNota(Notas n) {
		listaNotasMaterias.add(n);
	}
	
	public void agregarCertificacion(Registros r) {
		listCertificaciones.add(r);
	}

	@Override
	public String toString() {
		if(listCertificaciones.isEmpty()) {
			return "Estudiante [rut=" + rut + ", carrera=" + carrera + ", semestre=" + semestre + ", correoE=" + correoE
					+ ", listaNotasMaterias=" + listaNotasMaterias + ", listCertificaciones=" + listCertificaciones + "]";
		}else {
			return "Estudiante [rut=" + rut + ", carrera=" + carrera + ", semestre=" + semestre + ", correoE=" + correoE
					+ ", listaNotasMaterias=" + listaNotasMaterias + " ";
		}
	}
	
	
	
	
	
}
