package facade;

import java.util.Date;

import controlador.*;

public class Facade_Mobile {
	
	public void crearUsuario(String Nombre, String Apellido, Date FechaNac, String Mail, String password){
		ControladorDeUsuarios.getInstancia().crearUsuario(Nombre,Apellido, FechaNac, Mail, password,true);
	}
	
	public void crearLista(String nombre, Date vigencia, String agasajado, float monto, boolean estado, boolean activo, float montoPorParticipante){
		ControladorDeLista.getInstancia().crearLista(nombre, vigencia, agasajado, monto, estado, activo, montoPorParticipante);
	}
	
}
