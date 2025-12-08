import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.*;
public class Controlador implements Isistema{
	private static Controlador miInstancia;
	// para poder llamar la estaregia que completa el punto 2 del menu coor
	private Reportes misReportes;
	// para poder llamar a la clase que realiza la gestion de certificaciones del menu coor
	private GestionCertificaciones gesCertificaciones;
	private GestionEstudiantes gesEstudiantes;
	private VisualizacionPerfilEstudiante VPerfilEstudiante;
	private InscripcionCertficaciones inscripcionCert;
	private GestionSeguimientoDashBoard dashBoar;
	ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	ArrayList<Curso> listaCursos = new ArrayList<>();
	ArrayList<Certificaciones> listaCertificaciones = new ArrayList<>();
	
	private Controlador(){
		this.misReportes = new Reportes();
		this.gesCertificaciones = new GestionCertificaciones();
		this.gesEstudiantes = new GestionEstudiantes();
		this.VPerfilEstudiante = new VisualizacionPerfilEstudiante();
		this.inscripcionCert = new InscripcionCertficaciones();
		this.dashBoar = new GestionSeguimientoDashBoard();
	}
	
	public static Controlador getInstance() {
		if(miInstancia == null) {
			miInstancia = new Controlador();
		}
		return miInstancia;
	}
	@Override
	public void cargarUsuarios(String[] partes,String rol) {
		// TODO Auto-generated method stub
		Usuario u = FactoryUsuario.crearU(partes,rol);
		listaUsuarios.add(u);
	}
	@Override
	public void cargarEstudiantes(String[] partes, String rol) {
		// TODO Auto-generated method stub
		Usuario e = FactoryUsuario.crearU(partes, rol);
		listaUsuarios.add(e);
	}

	@Override
	public Usuario buscarUsuarioNombre(String nombre) {
		// TODO Auto-generated method stub
		for (Usuario u : listaUsuarios) {
			if(u.getNombre().equalsIgnoreCase(nombre)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public void mostrarUsuarios() {
		// TODO Auto-generated method stub
		for (Usuario usuario : listaUsuarios) {
			System.out.println(usuario.toString());
		}
	}

	@Override
	public void cargarCursos(String[] partes) {
		// TODO Auto-generated method stub
		Curso c = new Curso(partes[0],partes[1],Integer.parseInt(partes[2]),Integer.parseInt(partes[3]),partes[4]);
		listaCursos.add(c);
		if(partes.length>5) {
			String preRequisito = partes[5];
			c.agregarCursosPreRequisitos(preRequisito);
		}
		
	}

	@Override
	public void mostrarCursos() {
		// TODO Auto-generated method stub
		for (Curso curso : listaCursos) {
			System.out.println(curso.toString());
		}
	}

	@Override
	public void cargarNotas(String[] partes) {
		// TODO Auto-generated method stub
		Notas n = new Notas(partes[0], partes[1],Double.parseDouble(partes[2]), partes[3], partes[4]);
		String rut = n.getRutEstudiante();
		Usuario e = buscarUserRut(rut);
		if(e instanceof Estudiante && e !=null) {
			((Estudiante) e).agregarNota(n);
		}else {
			System.out.println("ERROR NO ES ESTUDIANTE");
		}
	}

	@Override
	public Usuario buscarUserRut(String rut) {
		// TODO Auto-generated method stub
		for (Usuario u : listaUsuarios) {
			if(u instanceof Estudiante) {
				if(((Estudiante) u).getRut().equalsIgnoreCase(rut)) {
					return u;
				}
			}
		}
		return null;
	}

	@Override
	public void cargarCertficaciones(String[] partes) {
		// TODO Auto-generated method stub
		Certificaciones c = FactoryCertificaciones.crear(partes);
		listaCertificaciones.add(c);
	}

	@Override
	public void mostrarCertficaciones() {
		// TODO Auto-generated method stub
		for (Certificaciones certificaciones : listaCertificaciones) {
			System.out.println(certificaciones.toString());
		}
	}

	@Override
	public void cargarRegistros(String[] partes) {
		// TODO Auto-generated method stub
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    
	    // Usamos ese formato para setear o parsear como se prefiera decirle porque sino da error
	    LocalDate fecha = LocalDate.parse(partes[2], formato);
		Registros r = new Registros(partes[0], partes[1],fecha,partes[3],Integer.parseInt(partes[4]));
		Usuario u = buscarUserRut(r.getRutEstudiante());
		if(u instanceof Estudiante && u!=null) {
			((Estudiante) u).agregarCertificacion(r);
		}
		
	}

	@Override
	public void cargarMateriasCertificaciones(String[] partes) {
		// TODO Auto-generated method stub
		Certificaciones certificacion = buscarCertificaciones(partes[0]);
		Curso curso = buscarCurso(partes[1]);
		certificacion.agregarLista(curso);
	}

	@Override
	public Curso buscarCurso(String codigo) {
		// TODO Auto-generated method stub
		for (Curso c : listaCursos) {
			if(c.getCodigo().equalsIgnoreCase(codigo)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public Certificaciones buscarCertificaciones(String id) {
		// TODO Auto-generated method stub
		for (Certificaciones certificaciones : listaCertificaciones) {
			if(certificaciones.getId().equalsIgnoreCase(id)) {
				return certificaciones;
			}
		}
		return null;
	}

	@Override
	public void crearUsuarioManual() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarCuenta(String nombre, int tipo) {
		// TODO Auto-generated method stub
		if(tipo==1) {
			Estudiante e = buscarE(nombre);
			
		}else if(tipo==2) {
			Usuario c = buscarUsuarioNombre(nombre);
		}
	}

	@Override
	public void eliminarCuenta(String nombre, int tipo) {
		// TODO Auto-generated method stub
		if(tipo==1) {
			Estudiante e = buscarE(nombre);
			listaUsuarios.remove(e);
		}else if(tipo==2) {
			Usuario c = buscarUsuarioNombre(nombre);
			listaUsuarios.remove(c);
		}
	}

	@Override
	public void rrestablecerContraseña(String nombre, int tipo,String pass) {
		if(tipo==1) {
			Estudiante e = buscarE(nombre);
			e.setPassword(pass);
		}else if(tipo==2) {
			Usuario c = buscarUsuarioNombre(nombre);
			c.setPassword(pass);
		}
	}

	@Override
	public Estudiante buscarE(String n) {
		for (Usuario u : listaUsuarios) {
			if(u instanceof Estudiante) {
				if(((Estudiante) u).getRut().equalsIgnoreCase(n)) {
					return (Estudiante) u;
				}
			}
		}
		return null;
	}

	@Override
	public Cordinador buscarC(String n) {
		for (Usuario u : listaUsuarios) {
			if(u instanceof Cordinador) {
				Cordinador c = (Cordinador)u;
				if(c.getNombre().equalsIgnoreCase(n)) {
					return c;
				}
			}
		}
		return null;
	}

	@Override
	public void aplicarEstrategia(int i) {
		if(i==1) {
			misReportes.setMiEstrategia(new AnalisiInscripcion());
		}else if(i==2) {
			misReportes.setMiEstrategia(new AnalisisAsignaturas());
		}
		
		String reporte = misReportes.ejecutarReporte(listaUsuarios);
		
		System.out.println(reporte);
	}

	@Override
	public void modificarCertficacion(String id, String desc, int cred, int year) {
		boolean bandera = gesCertificaciones.modificar(listaCertificaciones, id, desc, cred, year);
		if(bandera) {
			System.out.println("Cambio realizado con exito");
		}else {
			System.out.println("Error: \nLa certificacion no fue encontrada");
		}
	}

	@Override
	public void emisionDiplomaCertficacion(String idCert) {gesCertificaciones.generar(listaUsuarios, idCert);}

	@Override
	public void mostrarPerfilesEsMinor(String idCert) {
		// TODO Auto-generated method stub
		//aqui me ajile y debi hacer una extraccion del obejto antes para ahorrar code en la otra clase
		gesEstudiantes.listarEstudiantesPorCertificacion(listaUsuarios, idCert);
	}

	@Override
	public void validarAvanceAcademicoMinor(String rut, String idCert) {
		// aqui ya no me paso, aqui anduve mas despierto y extrai el objeto antes
		Usuario u = buscarUserRut(rut);
		Certificaciones c = buscarCertificaciones(idCert);
		if(u!=null&&c!=null) {
			Estudiante e = (Estudiante)u;
			gesEstudiantes.validarAvance(e, c);
		}else {
			System.out.println("ERROR: No se pudo realizar la validacion.");
            System.out.println("- Verifique que el RUT pertenezca a un Estudiante.");
            System.out.println("- Verifique que el ID de certificacion exista.");
		}
	}

	@Override
	public void verPerfilEstudiante(Usuario uLogin) {
		// TODO Auto-generated method stub
		if(uLogin instanceof Estudiante) {
			Estudiante e = (Estudiante)uLogin;
			VPerfilEstudiante.mostrarDatosPersonales(e);
			VPerfilEstudiante.mostrarMalla(e, listaCursos);
			VPerfilEstudiante.mostrarPromedios(e);
		}else {
			System.out.println("ERROR: USTED NO ES ESTUDIANTE");
		}
	}

	@Override
	public Usuario login(String usuario, String password) {
		for (Usuario u : listaUsuarios) {
			if(u.getNombre().equalsIgnoreCase(usuario)&&u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public void mostrarMallaGrafica(Usuario uLogin) {
		// TODO Auto-generated method stub
		if(uLogin instanceof Estudiante e) {
			System.out.println("Abriendo la malla");
			VentanaMalla v = new VentanaMalla(e, listaCursos);
			v.setVisible(true);
		}else {
			System.out.println("usted no es estudiante");
		}
	}

	@Override
	public void mostrarCertificacionesLindo() {
		// TODO Auto-generated method stub
		inscripcionCert.ofertaCertificaciones(listaCertificaciones);
	}

	@Override
	public void inscribirAsignaturas(String idCert, String rut) {
		// TODO Auto-generated method stub
		Estudiante e = buscarE(rut);
		if(e!=null) {
			inscripcionCert.incribirRamo(e, idCert, listaCertificaciones, listaCursos);
			return;
		}
		System.out.println("No existe el estudiante");
		
	}

	@Override
	public void verDashBoard(String rut) {
		Estudiante e = buscarE(rut);
		if(e==null || e.getListCertificaciones().isEmpty()) {
			System.out.println("El estudiante no tiene certificaciones o no existe");
			return;
		}
		dashBoar.mostrarTitlo(e);
		// dentro de la clase que llamaremos aplicaremos el visitor
		for (Registros r : e.getListCertificaciones()) {
			Certificaciones c = buscarCertificaciones(r.getIdCerftificacion());
			if(c != null) {
				dashBoar.mostrarProgresoUnitario(e, r, c);
			}else {
				System.out.println("no se encontro la certificacion");
			}
		}
		
	}



}
