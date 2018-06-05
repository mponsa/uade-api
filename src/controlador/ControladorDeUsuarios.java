package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Usuario;

public class ControladorDeUsuarios {
	
	//Lista de usuarios manejados por el controlador.
	private static List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public ControladorDeUsuarios() {

	}
	
	public void crearUsuario(String n, String a, Date f, String m, String p, boolean aa) {
		Usuario user = new Usuario(n,a,f,m,p,aa);
		usuarios.add(user);
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
		return null;
	}
}
