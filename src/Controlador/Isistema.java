package Controlador;
import java.util.ArrayList;

import Modelo.Certificaciones;
import Modelo.Cordinador;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Usuario;

/**
 * Interfaz que define el contrato de servicios principales del sistema académico (Academicore) siguiendo la forma que señaloa seguir para 
 * la documentacion el profesor  alejando paolini .
 * Todos los métodos de gestión y acceso a datos que debe ofrecer el Controlador 
 * están declarados aquí.
 * * Sebastian Barrera Carvajal
 * 20.015.335-9
 * ITI
 */
public interface Isistema {
	
	ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	ArrayList<Curso> listaCursos = new ArrayList<>();
	ArrayList<Certificaciones> listaCertificaciones = new ArrayList<>();
	
	/**
	 * Carga datos de usuarios genéricos (Admin/Coordinador) en el sistema.
	 * @param partes Array de Strings con la información del usuario.
	 * @param rol El rol que identifica el tipo de usuario a crear.
	 */
	public void cargarUsuarios(String[] partes, String rol);

	/**
	 * Carga datos de estudiantes en el sistema.
	 * @param partes Array de Strings con la información del estudiante.
	 * @param rol El rol (estudiante) que identifica el tipo de usuario a crear.
	 */
	public void cargarEstudiantes(String[] partes, String rol);

	/**
	 * Busca un usuario por su nombre. Utilizado principalmente para Administradores y Coordinadores.
	 * @param nombre El nombre o username del usuario a buscar.
	 * @return El objeto Usuario encontrado, o null si no existe.
	 */
	public Usuario buscarUsuarioNombre(String nombre);
	
	/**
	 * Muestra una lista de todos los usuarios cargados en el sistema (Generalmente para depuración).
	 */
	public void mostrarUsuarios();

	/**
	 * Carga la información de un curso al catálogo de la universidad.
	 * @param partes Array de Strings con los datos del curso (código, nombre, créditos, etc.).
	 */
	public void cargarCursos(String[] partes);
	
	/**
	 * Muestra una lista de todos los cursos cargados.
	 */
	public void mostrarCursos();
	
	/**
	 * Carga una nota y la asigna al historial del estudiante correspondiente.
	 * @param partes Array de Strings con la nota, código de asignatura, estado, etc.
	 */
	public void cargarNotas(String[] partes);
	
	/**
	 * Busca un estudiante por su Rol Único Tributario (RUT), asi podemos dar mejor experiencia de uso.
	 * @param rut El RUT del estudiante.
	 * @return El objeto Estudiante encontrado, o null.
	 */
	public Usuario buscarUserRut(String rut);
	
	/**
	 * Carga una nueva certificación (Minor) al catálogo usando el Factory.
	 * @param partes Array de Strings con los datos de la certificación.
	 */
	public void cargarCertficaciones(String[] partes);
	
	// este lo hice solo para ir verificando si me cargaba o no los datos
	/**
	 * Muestra una lista de todas las certificaciones disponibles.
	 */
	public void mostrarCertficaciones();

	/**
	 * Carga un registro de inscripción de un alumno a una certificación.
	 * @param partes Array de Strings con la información del registro (RUT, ID Certificación, fecha, etc.).
	 */
	public void cargarRegistros(String[] partes);

	/**
	 * Asocia los códigos de las asignaturas a la certificación correspondiente.
	 * @param partes Array de Strings con el ID de la certificación y el código de la asignatura.
	 */
	public void cargarMateriasCertificaciones(String[] partes);

	/**
	 * Busca un curso por su código NRC en el catálogo.
	 * @param codigo El código del curso.
	 * @return El objeto Curso, o null.
	 */
	public Curso buscarCurso(String codigo);
	
	/**
	 * Busca una certificación por su ID.
	 * @param id El ID de la certificación (ej: CERT-001).
	 * @return El objeto Certificaciones, o null.
	 */
	public Certificaciones buscarCertificaciones(String id);

	/**
	 * Método para crear usuarios manualmente (utilizado por el Administrador).
	 */
	public void crearUsuarioManual();

	/**
	 * Modifica datos de una cuenta existente (utilizado por el Administrador).
	 * @param a Identificador (RUT/Nombre).
	 * @param tipoCuent Tipo de cuenta (1=Estudiante, 2=Coordinador).
	 * @param b Nuevo correo/contraseña.
	 * @param c Nueva carrera/rol.
	 */
	public void modificarCuenta(String a,int tipoCuent,String b,String c);
	
	/**
	 * Elimina una cuenta del sistema (utilizado por el Administrador).
	 * @param nombre Identificador (RUT/Nombre).
	 * @param tipo Tipo de cuenta (1=Estudiante, 2=Coordinador).
	 */
	public void eliminarCuenta(String nombre,int tipo);
	
	/**
	 * Restablece la contraseña de un usuario (utilizado por el Administrador).
	 * @param nombre Identificador (RUT/Nombre).
	 * @param tipo Tipo de cuenta.
	 * @param pass La nueva contraseña.
	 */
	public void rrestablecerContraseña(String nombre, int tipo,String pass);
	
	/**
	 * Busca y devuelve un estudiante por RUT.
	 * @param n El RUT del estudiante.
	 * @return El objeto Estudiante, o null.
	 */
	public Estudiante buscarE(String n);
	
	/**
	 * Busca y devuelve un Coordinador por nombre.
	 * @param n El nombre del coordinador.
	 * @return El objeto Cordinador, o null.
	 */
	public Cordinador buscarC(String n);
	
	/**
	 * Ejecuta la estrategia de reporte seleccionada (Inscripciones o Riesgo).
	 * @param i Índice de la estrategia (1 o 2).
	 * @return El String con el reporte generado.
	 */
	public String aplicarEstrategia(int i);
	
	/**
	 * Modifica los metadatos de una certificación (descripción, créditos, validez).
	 * @param id ID de la certificación.
	 * @param desc Nueva descripción.
	 * @param cred Nuevos créditos requeridos.
	 * @param year Nueva validez en años.
	 */
	public void modificarCertficacion(String id, String desc, int cred, int year);
	
	/**
	 * Genera un diploma para los estudiantes que han completado una certificación.
	 * @param idCert ID de la certificación a verificar.
	 * @return El String con el texto del diploma o el mensaje de error.
	 */
	public String emisionDiplomaCertficacion(String idCert);
	
	/**
	 * Muestra la lista de estudiantes inscritos en una certificación específica.
	 * @param idCert ID de la certificación.
	 * @return El String con el listado de perfiles.
	 */
	public String mostrarPerfilesEsMinor(String idCert);
	
	/**
	 * Valida el avance académico de un alumno en un minor específico, actualizando su progreso y estado.
	 * @param rut RUT del alumno.
	 * @param idCert ID de la certificación.
	 * @return El String con el resultado de la validación.
	 */
	public String validarAvanceAcademicoMinor(String rut, String idCert);
	
	/**
	 * Muestra el perfil completo del estudiante (datos personales, malla y promedios).
	 * @param uLogin El objeto Estudiante logueado.
	 * @return El String con toda la información del perfil.
	 */
	public String verPerfilEstudiante(Usuario uLogin);
	
	/**
	 * Realiza la autenticación del usuario.
	 * @param usuario RUT o Nombre de usuario.
	 * @param password Contraseña.
	 * @param i Tipo de cuenta (1=Usuario/Coord, 2=Estudiante).
	 * @return El objeto Usuario/Estudiante/Coordinador logueado, o null si falla.
	 */
	public Usuario login(String usuario,String password,int i);
	
	/**
	 * Abre la ventana gráfica de la Malla Curricular.
	 * @param uLogin El usuario logueado (debe ser Estudiante).
	 */
	public void mostrarMallaGrafica(Usuario uLogin);
	
	/**
	 * Muestra la oferta completa de certificaciones disponibles.
	 * @return El String con la lista y descripción de las ofertas.
	 */
	public String mostrarCertificacionesLindo();
	
	/**
	 * Inscribe al estudiante en la certificación si cumple los prerrequisitos de créditos.
	 * @param idCert ID de la certificación a inscribir.
	 * @param e El estudiante.
	 * @return El String con el resultado de la inscripción (éxito o error).
	 */
	public String inscribirAsignaturas(String idCert,Estudiante e);
	
	/**
	 * Genera el Dashboard de progreso del estudiante, mostrando el estado de sus minors.
	 * @param e El estudiante.
	 * @return El String con el Dashboard completo.
	 */
	public String verDashBoard(Estudiante e);
	
	/**
	 * Crea un nuevo Estudiante de forma manual (utilizado por el Administrador).
	 */
	public void CrearEstudianteManual(String nombre,String rol, String pass, String rut,String carrera,int semestre,String correo);
	
	/**
	 * Crea un nuevo Coordinador de forma manual (utilizado por el Administrador).
	 */
	public void CrearCoorManual(String nombre,String rol, String pass, String info);
}