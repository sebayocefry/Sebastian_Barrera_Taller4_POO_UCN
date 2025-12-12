package Controlador;
import java.util.ArrayList;

import Modelo.Usuario;
//sebastian barrera carvajal
//20.015.335-9
//ITI
public class Reportes {
	private Estrategia miEstrategia;

	public void setMiEstrategia(Estrategia miEstrategia) {
		this.miEstrategia = miEstrategia;
	}
	
	public String ejecutarReporte(ArrayList<Usuario> usuarios) {
        if (this.miEstrategia == null) {
            return "Error: No se ha seleccionado una estrategia valida.";
        }
        return this.miEstrategia.analizar(usuarios);
    }
}
