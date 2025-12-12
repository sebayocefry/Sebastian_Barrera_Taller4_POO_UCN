package Modelo;
import java.util.ArrayList;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class Curso {
	private String codigo;
	private String nomnbre;
	private int semestre;
	private int creditos;
	private String area;
	

	ArrayList<String> cursosPreRequisitos;

	public Curso(String codigo, String nomnbre, int semestre, int creditos, String area) {
		super();
		this.codigo = codigo;
		this.nomnbre = nomnbre;
		this.semestre = semestre;
		this.creditos = creditos;
		this.area = area;
		this.cursosPreRequisitos=  new ArrayList<>();
	}

	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomnbre() {
		return nomnbre;
	}

	public void setNomnbre(String nomnbre) {
		this.nomnbre = nomnbre;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public ArrayList<String> getCursosPreRequisitos() {
		return cursosPreRequisitos;
	}

	public void setCursosPreRequisitos(ArrayList<String> cursosPreRequisitos) {
		this.cursosPreRequisitos = cursosPreRequisitos;
	}

	public void agregarCursosPreRequisitos(String curso) {
		cursosPreRequisitos.add(curso);
	}



	@Override
	public String toString() {
		
		if(cursosPreRequisitos.isEmpty()) {
			return "Curso [codigo=" + codigo + ", nomnbre=" + nomnbre + ", semestre=" + semestre + ", creditos=" + creditos
					+ ", area=" + area  + " ";
		}else {
			return "Curso [codigo=" + codigo + ", nomnbre=" + nomnbre + ", semestre=" + semestre + ", creditos=" + creditos
					+ ", area=" + area + ", cursosPreRequisitos=" + cursosPreRequisitos + "]";
		}
		
	}
	
	
}
