import java.util.ArrayList;

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
	public void mostrarCertficaciones();
	public void cargarRegistros(String[] partes);
	public void cargarMateriasCertificaciones(String[] partes);
	public Curso buscarCurso(String codigo);
	public Certificaciones buscarCertificaciones(String id);
	public void crearUsuarioManual();
	public void modificarCuenta(String nombre,int tipo);
	public void eliminarCuenta(String nombre,int tipo);
	public void rrestablecerContraseña(String nombre, int tipo,String pass);
	public Estudiante buscarE(String n);
	public Cordinador buscarC(String n);
	public void aplicarEstrategia(int i);
	public void modificarCertficacion(String id, String desc, int cred, int year);
	public void emisionDiplomaCertficacion(String idCert);
	public void mostrarPerfilesEsMinor(String idCert);
	public void validarAvanceAcademicoMinor(String rut, String idCert);
	public void verPerfilEstudiante(Usuario uLogin);
	public Usuario login(String usuario,String password);
}
