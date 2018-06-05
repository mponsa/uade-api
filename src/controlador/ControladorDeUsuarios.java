package controlador;

import persistencia.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Usuario;

public class ControladorDeUsuarios {
	
	private static ControladorDeUsuarios instancia;
	//Lista de usuarios manejados por el controlador.
	private List<Usuario> usuarios;
	
	public ControladorDeUsuarios() {
		usuarios = new ArrayList<Usuario>();
	}
	
	public static ControladorDeUsuarios getInstancia() {
		if (instancia == null) {
			return new ControladorDeUsuarios();
		}
		else {
			return instancia;
		}
	}
	
	//Crea el usuario, lo guarda en la lista y ademas lo persiste en la base de datos.
	public void crearUsuario(String n, String a, Date f, String m, String p, boolean aa) {
		Usuario user = new Usuario(n,a,f,m,p,aa);
		usuarios.add(user);
		AdmPerUsuario.getInstancia().insert(user);
	}
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public Usuario getUsuario(String email) {
		for (Usuario a : usuarios) {
			if (a.getMail().equals(email)) {
				return a;
			}
		}
		//Si no se encontro en la memoria del controlador, se busca en la base de datos.
		Usuario a = AdmPerUsuario.getInstancia().getUsuario(email);
		if (a!=null) {
			return a;
		}
		return null;
	}
}
