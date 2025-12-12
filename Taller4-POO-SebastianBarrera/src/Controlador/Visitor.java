//sebastian barrera carvajal
//20.015.335-9
//ITI
package Controlador;

import Modelo.CiberSeguridad;
import Modelo.DesarrolloSoftware;
import Modelo.SistemasInteligentes;

public interface Visitor {
	public void visitar(SistemasInteligentes sis);
	public void visitar(CiberSeguridad cs);
	public void visitar(DesarrolloSoftware ds);
}
