package Vista;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Controlador.Controlador;
import Modelo.Cordinador;
import Modelo.Estudiante;
import Modelo.Usuario;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class Main {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Controlador c = Controlador.getInstance();
		Usuario usuarioLogueado = null;
		LeerUsuarios(c);
		LeerEstudiantes(c);
		LeerCursos(c);
		LeerNotas(c);
		LeerCertificaciones(c);
		LeerRegistros(c);
		LeerAsignaturasCert(c);
		//c.mostrarUsuarios();
		//c.mostrarCursos();
		//c.mostrarCertficaciones();
		new LoginFrame(c).setVisible(true);
		while(usuarioLogueado == null) {
			System.out.println("Bienvenido");
            System.out.println("1)si es usuario");
            System.out.println("2)si es estudiante");
            int tipoU = sc.nextInt();
            sc.nextLine();
            System.out.print(" Usuario/RUT: ");
            String user = sc.next();
            
            System.out.print(" Contraseña:  ");
            String pass = sc.next();
            System.out.println("Cargando");
            usuarioLogueado = c.login(user, pass,tipoU);
            
            if (usuarioLogueado == null) {
                System.out.println("\n ERROR: Credenciales incorrectas. \nIntente nuevamente.");
            }
		}
		
		System.out.println("\nBienvenido " + usuarioLogueado.getNombre());
		if (usuarioLogueado instanceof Estudiante) {
            menuEstudiante(c, (Estudiante) usuarioLogueado);
            
        } else if (usuarioLogueado instanceof Cordinador) {
            menuCoordinador(c, (Cordinador) usuarioLogueado);
            
        } else if (usuarioLogueado instanceof Usuario) {
            menuAdministrador(c, usuarioLogueado);
        }
 
	}
	// menus 
	public static void menuEstudiante(Controlador sistema, Estudiante alumno) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENU ESTUDIANTE ---");
            System.out.println("1. Ver Perfil");
            System.out.println("2. Ver Malla Interactiva");
            System.out.println("3. Mi Dashboard (progreso del minor)");
            System.out.println("4. Inscribir Minor");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine(); 

                switch (opcion) {
                    case 1:
                    	sistema.verPerfilEstudiante(alumno);
                        break;
                    case 2:
                    	sistema.mostrarMallaGrafica(alumno);
                        break;
                    case 3:
                    	sistema.verDashBoard(alumno);
                        break;
                    case 4:
                    	sistema.mostrarCertificacionesLindo();
                    	System.out.println("Ingrese el id del minor que desea inscribir");
                    	String minor = sc.nextLine();
                    	sistema.inscribirAsignaturas(minor, alumno);
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un numero.");
                sc.nextLine(); 
            }
        }
    }
	
	public static void menuCoordinador(Controlador sistema, Cordinador coord) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENU COORDINADOR (" + coord.getAreaCordinador() + ") ---");
            System.out.println("1. Reporte Inscripciones");
            System.out.println("2. Reporte Asignaturas De alto Riesgo");
            System.out.println("3. Modificar Certificacion");
            System.out.println("4. Generar Diplomas/titulos");
            System.out.println("5. Mostrar Estudiantes por minor");
            System.out.println("6. Validar Avance del Alumno");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine(); 

                switch (opcion) {
                    case 1:
                        sistema.aplicarEstrategia(1);
                        break;
                    case 2:
                        sistema.aplicarEstrategia(2);
                        break;
                    case 3:
                        System.out.print("ID Certificacion: ");
                        String id = sc.nextLine();
                        System.out.print("Nueva Descripcion: ");
                        String desc = sc.nextLine();
                        System.out.print("Nuevos Creditos: ");
                        int cred = sc.nextInt();
                        System.out.print("Nueva Validez: ");
                        int val = sc.nextInt();
                        sistema.modificarCertficacion(id, desc, cred, val);
                        
                        break;
                    case 4:
                        System.out.print("ID Certificación: ");
                        String idDip = sc.nextLine();
                        sistema.emisionDiplomaCertficacion(idDip);
                        break;
                    case 5:
                        System.out.print("ID Certificación: ");
                        String idL = sc.nextLine();
                        sistema.mostrarPerfilesEsMinor(idL);
                        break;
                    case 6:
                        System.out.print("RUT Alumno: ");
                        String rut = sc.nextLine();
                        System.out.print("ID Certificación: ");
                        String idVal = sc.nextLine();
                        sistema.validarAvanceAcademicoMinor(rut, idVal);
                        
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un numero.");
                sc.nextLine();
            }
        }
    }
	
	public static void menuAdministrador(Controlador sistema, Usuario u) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENU ADMINISTRADOR (" + u.getNombre() + ") ---");
            System.out.println("1. Crear Cuentas de estudiante o coordinador");
            System.out.println("2. Modificar cuentas (estudiante/coordinador)");
            System.out.println("3. Eliminar cuentas");
            System.out.println("4. Restablecer contraseñas");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine(); 

                switch (opcion) {
                    case 1:
                    	System.out.print("Tipo de cuenta: ");
                        System.out.println("1)Estudiante");
                        System.out.println("2)Coordinador");
                        int tipoC = sc.nextInt();
                        sc.nextLine();
                        if(tipoC==1) {
                        	System.out.println("ingrese nombre");
                            String nombre = sc.nextLine();
                        	System.out.println("ingrese rut");
                            String rut = sc.nextLine();
                            System.out.println("Ingrese el  correo");
                            String correo = sc.nextLine();
                            System.out.println("Ingrese la  carrera");
                            String carrera = sc.nextLine();
                            System.out.println("Ingrese una contraseña");
                            String contraseña = sc.nextLine();
                            sistema.CrearEstudianteManual(nombre, "Estudiante", contraseña, rut, carrera, tipoC, correo);
                  
                        }else if(tipoC==2) {
                        	System.out.println("ingrese nombre");
                            String nombre = sc.nextLine();
                            System.out.println("Ingrese contraseña");
                            String contraseña = sc.nextLine();
                            System.out.println("Info adicional ej; Desarrollo de software");
                            String info = sc.nextLine();
                            sistema.CrearCoorManual(nombre, "Coordinador", contraseña, info);
                        }
                        break;
                    case 2:
                    	System.out.print("Tipo de cuenta: ");
                        System.out.println("1)Estudiante");
                        System.out.println("2)Coordinador");
                        int tipoCuent = sc.nextInt();
                        sc.nextLine();
                        if(tipoCuent==1) {
                        	System.out.println("ingrese rut");
                            String rut = sc.nextLine();
                            System.out.println("Ingrese el nuevo correo");
                            String correo = sc.nextLine();
                            System.out.println("Ingrese la nueva carrera");
                            String carrera = sc.nextLine();
                            sistema.modificarCuenta(rut, tipoCuent, correo, carrera);
                        }else if(tipoCuent==2) {
                        	System.out.println("ingrese nombre");
                            String nombre = sc.nextLine();
                            System.out.println("Ingrese nueva contraseña");
                            String contraseña = sc.nextLine();
                            System.out.println("Ingrese rol");
                            String area = sc.nextLine();
                            sistema.modificarCuenta(nombre, tipoCuent,contraseña,area);
                        }
                        
                        
                        break;
                    case 3:
                        System.out.print("Tipo de cuenta: ");
                        System.out.println("1)Estudiante");
                        System.out.println("2)Coordinador");
                        int tipoCuenta = sc.nextInt();
                        sc.nextLine();
                        System.out.println("ingrese user/rut");
                        String id = sc.nextLine();
                        sistema.eliminarCuenta(id, tipoCuenta);   
                        break;
                    case 4:
                    	System.out.print("Tipo de cuenta: ");
                        System.out.println("1)Estudiante");
                        System.out.println("2)Coordinador");
                        int tipoCuente = sc.nextInt();
                        sc.nextLine();
                        System.out.println("ingrese user/rut");
                        String id1 = sc.nextLine();
                        System.out.println("Ingrese la nueva contraseña");
                        String password = sc.nextLine();
                        sistema.rrestablecerContraseña(id1, tipoCuente, password);
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un numero.");
                sc.nextLine();
            }
        }
    }

//lectura de arch
	public static void LeerUsuarios(Controlador c) throws FileNotFoundException {
		File arch = new File("usuarios.txt");
		Scanner lector = new Scanner(arch);
		while(lector.hasNextLine()) {
			String line = lector.nextLine();
			String[] partes = line.split(";");
			String rol = partes[2];
			c.cargarUsuarios(partes,rol);
		}
		lector.close();
	}
	
	public static void LeerEstudiantes(Controlador c) throws FileNotFoundException {
		File arch = new File("estudiantes.txt");
		Scanner lector = new Scanner(arch);
		while(lector.hasNextLine()) {
			String line = lector.nextLine();
			String[] partes = line.split(";");
			String rol = "estudiante";
			c.cargarUsuarios(partes,rol);
		}
		lector.close();
	}
	
	public static void LeerCursos(Controlador c) throws FileNotFoundException {
		File arch = new File("cursos.txt");
		Scanner lector = new Scanner(arch);
		while(lector.hasNextLine()) {
			String line = lector.nextLine();
			String[] partes = line.split(";");
			c.cargarCursos(partes);
		}
		lector.close();
	}
	public static void LeerNotas(Controlador c) throws FileNotFoundException {
		File arch = new File("notas.txt");
		Scanner lector = new Scanner(arch);
		while(lector.hasNextLine()) {
			String line = lector.nextLine();
			String[] partes = line.split(";");
			c.cargarNotas(partes);
		}
		lector.close();
	}
	
	public static void LeerCertificaciones(Controlador c) throws FileNotFoundException {
		File arch = new File("certificaciones.txt");
		Scanner lector = new Scanner(arch);
		while(lector.hasNextLine()) {
			String line = lector.nextLine();
			String[] partes = line.split(";");
			c.cargarCertficaciones(partes);
		}
		lector.close();
	}
	
	public static void LeerRegistros(Controlador c) throws FileNotFoundException {
		File arch = new File("registros.txt");
		Scanner lector = new Scanner(arch);
		while(lector.hasNextLine()) {
			String line = lector.nextLine();
			String[] partes = line.split(";");
			c.cargarRegistros(partes);
		}
		lector.close();
	}
	public static void LeerAsignaturasCert(Controlador c) throws FileNotFoundException {
		File arch = new File("asignaturas_certificaciones.txt");
		Scanner lector = new Scanner(arch);
		while(lector.hasNextLine()) {
			String line = lector.nextLine();
			String[] partes = line.split(";");
			c.cargarMateriasCertificaciones(partes);
		}
		lector.close();
	}
	
}
