//sebastiaan barrera carvajal
//20.015.335-9
//ITI
package Controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Modelo.Certificaciones;
import Modelo.Cordinador;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Notas;
import Modelo.Registros;
import Modelo.Usuario;
import Vista.VentanaMalla;

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
	public void modificarCuenta(String a,int tipoCuent,String b,String c) {
		// TODO Auto-generated method stub
		if(tipoCuent==1) {
			Estudiante e = buscarE(a);
			e.setRut(a);
			e.setCorreoE(b);
			e.setCarrera(c);
		}else if(tipoCuent==2) {
			Usuario coor = buscarUsuarioNombre(a);
			coor.setNombre(a);
			coor.setPassword(b);
			coor.setRol(c);
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
	public String aplicarEstrategia(int i) {
		if(i==1) {
			misReportes.setMiEstrategia(new AnalisiInscripcion());
		}else if(i==2) {
			misReportes.setMiEstrategia(new AnalisisAsignaturas());
		}
		
		return misReportes.ejecutarReporte(listaUsuarios);
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
	public String emisionDiplomaCertficacion(String idCert) {
        return gesCertificaciones.generar(listaUsuarios, idCert);
    }

	@Override
	public String mostrarPerfilesEsMinor(String idCert) {
		// TODO Auto-generated method stub
		//aqui me ajile y debi hacer una extraccion del obejto antes para ahorrar code en la otra clase
		return gesEstudiantes.listarEstudiantesPorCertificacion(listaUsuarios, idCert);
	}

	@Override
	public String validarAvanceAcademicoMinor(String rut, String idCert) {
		// aqui ya no me paso, aqui anduve mas despierto y extrai el objeto antes
		Usuario u = buscarUserRut(rut);
		Certificaciones c = buscarCertificaciones(idCert);
		if(u!=null&&c!=null) {
			Estudiante e = (Estudiante)u;
			return gesEstudiantes.validarAvance(e, c);
		}else {
			return "ERROR: No se pudo realizar la validacion.\n" +
                   "- Verifique que el RUT pertenezca a un Estudiante.\n" +
                   "- Verifique que el ID de certificacion exista.";
		}
	}

	@Override
	public String verPerfilEstudiante(Usuario uLogin) {
		// TODO Auto-generated method stub
		if(uLogin instanceof Estudiante) {
			Estudiante e = (Estudiante)uLogin;
            StringBuilder sb = new StringBuilder();
            sb.append(VPerfilEstudiante.mostrarDatosPersonales(e)).append("\n");
            sb.append(VPerfilEstudiante.mostrarMalla(e, listaCursos)).append("\n");
            sb.append(VPerfilEstudiante.mostrarPromedios(e));
            return sb.toString();
		}else {
			return "ERROR: USTED NO ES ESTUDIANTE";
		}
	}

	@Override
	public Usuario login(String usuario, String password,int i) {
		if(i==1) {
			for (Usuario u : listaUsuarios) {
				if(u.getNombre().equalsIgnoreCase(usuario)&&u.getPassword().equals(password)) {
					return u;
				}
			}
		}else if(i==2) {
			for (Usuario u : listaUsuarios) {
				if(u instanceof Estudiante e) {
					if(e.getRut().equalsIgnoreCase(usuario)&&e.getPassword().equals(password)) {
						return e;
					}
				}
				
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
	public String mostrarCertificacionesLindo() {
		// TODO Auto-generated method stub
		return inscripcionCert.ofertaCertificaciones(listaCertificaciones);
	}

	@Override
	public String inscribirAsignaturas(String idCert, Estudiante e) {
		// TODO Auto-generated method stub
		if(e!=null) {
			return inscripcionCert.incribirRamo(e, idCert, listaCertificaciones, listaCursos);
		}
		return "No existe el estudiante";
		
	}

	@Override
	public String verDashBoard(Estudiante e) {
		
		if(e==null || e.getListCertificaciones().isEmpty()) {
			return "El estudiante no tiene certificaciones o no existe";
		}
        StringBuilder sb = new StringBuilder();
		sb.append(dashBoar.mostrarTitlo(e));
		// dentro de la clase que llamaremos aplicaremos el visitor
		for (Registros r : e.getListCertificaciones()) {
			Certificaciones c = buscarCertificaciones(r.getIdCerftificacion());
			if(c != null) {
				sb.append(dashBoar.mostrarProgresoUnitario(e, r, c));
			}else {
				sb.append("no se encontro la certificacion\n");
			}
		}
        return sb.toString();
		
	}

	@Override
	public void CrearEstudianteManual(String nombre, String rol, String pass, String rut, String carrera, int semestre,
			String correo) {
		// TODO Auto-generated method stub
		Estudiante e = new Estudiante(nombre, pass, rol, rut, carrera, semestre, correo);
		listaUsuarios.add(e);
	}

	@Override
	public void CrearCoorManual(String nombre, String rol, String pass, String info) {
		// TODO Auto-generated method stub
		Cordinador c = new Cordinador(nombre, pass, rol, info);
		listaUsuarios.add(c);
	}

}
