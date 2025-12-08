import java.util.ArrayList;

public class GestionSeguimientoDashBoard {
    private DashBoard visitor;

    public  GestionSeguimientoDashBoard() {
        this.visitor = new DashBoard();
    }

    // hago el metodo aparte para el titulo porque o sino se me mostraba cada vez que iteraba el for en el sistema
    public void mostrarTitlo(Estudiante alumno) {
    	System.out.println("\n-----------------------------------------------------------");
        System.out.println("       DASHBOARD DE PROGRESO: " + alumno.getNombre().toUpperCase());
        System.out.println();
    }
    public void mostrarProgresoUnitario(Estudiante alumno, Registros registro, Certificaciones cert) {
    	
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Minor: " + cert.getNombre().toUpperCase());
        System.out.println("ID:            " + cert.getId());
        System.out.println("Estado Actual: " + registro.getEstado());
        
        imprimirBarraProgreso(registro.getProgreso());

        
        System.out.println("Mensaje del Mentor Virtual:");
        cert.aceptar(visitor);
        mostrarPendientes(alumno, cert);
        
        System.out.println("------------------------------------------------------------");
    }
    
    private void mostrarPendientes(Estudiante alumno, Certificaciones cert) {
        System.out.println("\n| ASIGNATURAS PENDIENTES:");
        
        ArrayList<Curso> requeridos = cert.getListaAsignaturasCertificacion();
        boolean bandera = true;

        for (Curso req : requeridos) {
            if (!tieneCursoAprobado(alumno, req.getCodigo())) {
                System.out.println("   [ ] " + req.getCodigo() + " - " + req.getNomnbre());
                bandera = false;
            }
        }

        if (bandera) {
            System.out.println("Ninguna! Has completado todos los requisitos académicos.");
        }
    }
// esta parte me costo mas y fue una de las pocas partes que tuve que buscar en google, youtube y pregunte hasta en un foro, dejare todo en el readme
    private void imprimirBarraProgreso(int porcentaje) {
        System.out.print("Progreso:---|");
        int barras = porcentaje / 10; // Cada 10 porciento es un bloque |-| que es como si fuese una carga o algo asi, no se me ocurrio como representar la carga mas que eso |-|
        for (int i = 0; i < 10; i++) {
            if (i < barras) System.out.print("|-|");
            else System.out.print("-");
        }
        System.out.println("|---" + porcentaje + "%");
    }
// funcion que nos dice si hay una materia aprobada o no devolviendo un bolenao
    private boolean tieneCursoAprobado(Estudiante e, String nrc) {
        for (Notas n : e.getListaNotasMaterias()) {
            if (n.getCodigoAsignatura().equalsIgnoreCase(nrc) && n.getEstado().equalsIgnoreCase("Aprobada")) {
                return true;
            }
        }
        return false;
    }
}
