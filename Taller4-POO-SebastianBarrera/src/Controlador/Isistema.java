package Controlador;
import java.util.ArrayList;

import Modelo.Certificaciones;
import Modelo.Cordinador;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Usuario;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public interface Isistema {
	
	ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	ArrayList<Curso> listaCursos = new ArrayList<>();
	ArrayList<Certificaciones> listaCertificaciones = new ArrayList<>();
	
	public void cargarUsuarios(String[] partes, String rol);
	public void cargarEstudiantes(String[] partes, String rol);
	public Usuario buscarUsuarioNombre(String nombre);
	public void mostrarUsuarios();
	public void cargarCursos(String[] partes);
	public void mostrarCursos();
	public void cargarNotas(String[] partes);
	public Usuario buscarUserRut(String rut);
	public void cargarCertficaciones(String[] partes);
	// este lo hice solo para ir verificando si me cargaba o no los datos
	public void mostrarCertficaciones();
	public void cargarRegistros(String[] partes);
	public void cargarMateriasCertificaciones(String[] partes);
	public Curso buscarCurso(String codigo);
	public Certificaciones buscarCertificaciones(String id);
	public void crearUsuarioManual();
	public void modificarCuenta(String a,int tipoCuent,String b,String c);
	public void eliminarCuenta(String nombre,int tipo);
	public void rrestablecerContraseña(String nombre, int tipo,String pass);
	public Estudiante buscarE(String n);
	public Cordinador buscarC(String n);
	public String aplicarEstrategia(int i);
	public void modificarCertficacion(String id, String desc, int cred, int year);
	public String emisionDiplomaCertficacion(String idCert);
	public String mostrarPerfilesEsMinor(String idCert);
	public String validarAvanceAcademicoMinor(String rut, String idCert);
	public String verPerfilEstudiante(Usuario uLogin);
	public Usuario login(String usuario,String password,int i);
	public void mostrarMallaGrafica(Usuario uLogin);
	public String mostrarCertificacionesLindo();
	public String inscribirAsignaturas(String idCert,Estudiante e);
	public String verDashBoard(Estudiante e);
	public void CrearEstudianteManual(String nombre,String rol, String pass, String rut,String carrera,int semestre,String correo);
	public void CrearCoorManual(String nombre,String rol, String pass, String info);
}
