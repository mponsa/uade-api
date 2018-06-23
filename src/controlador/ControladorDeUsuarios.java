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

	//Usuario en sesión. Se setea luego del Login.
	private Usuario adm;
	
	/***
	 * Constructor de la clase
	 */
	public ControladorDeUsuarios() {
		usuarios = new ArrayList<Usuario>();
	}
	
	/***
	 * Devuelve la instancia del controlador de usuarios activa.
	 * @return ControladorDeUsuarios 
	 */
	public static ControladorDeUsuarios getInstancia() {
		if (instancia == null) {
			instancia = new ControladorDeUsuarios();
			return instancia;
		}
		else {
			return instancia;
		}
	}
	
	
	/***
	 * Crea el usuario y lo almacena en la base de datos.
	 * @param n Nombre.
	 * @param a Apellido .
	 * @param f Fecha de nacimiento.
	 * @param m Mail del usuario.
	 * @param p Password del usuario.
	 * @param aa Activo - Estado activo para recuperar o no en la base de datos.
	 */
	public void crearUsuario(String n, String a, Date f, String m, String p, boolean aa) {
		usuarios.add(new Usuario(n,a,f,m,p,aa));
	}
	
	/***
	 * Devuelve los usuarios de la base de datos y los almacena en la lista del controlador.
	 * @return List<Usuarios>
	 */
	public List<Usuario> getUsuarios(){
		setUsuarios(AdmPerUsuario.getInstancia().buscarUsuarios(null));
		return usuarios;
	}
	
	/***
	 * Setea la lista de usuarios manejada por el controlador.
	 * @param usuarios
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	/***
	 * Devuelve los mails pertenecientes a los usuarios creados en el sistema.
	 * @return List<String>
	 */
	public List<String> getMailsUsuarios(){
		List<String>  mails = new ArrayList<String>();
 		List<Usuario> usuarios = getUsuarios();
		for(Usuario user : usuarios){
			mails.add(user.getMail());
		}
		
		return mails;
	}
	
	public Usuario getUsuario(int id) {
		for (Usuario a : usuarios) {
			if (a.getIdUsuario() == id) {
				return a;
			}
		}
		//Si no se encontro en la memoria del controlador, se busca en la base de datos.
		Usuario a = AdmPerUsuario.getInstancia().getUsuario(id);
		if (a!=null) {
			//Agrego a la lista de usuarios el usuario.
			this.usuarios.add(a);
			return a;
		}
		return null;
	}
	
	public Usuario getUsuario(String mail) {
		for (Usuario a : usuarios) {
			if (a.getMail().equals(mail)) {
				return a;
			}
		}
		//Si no se encontro en la memoria del controlador, se busca en la base de datos.
		Usuario a = AdmPerUsuario.getInstancia().getUsuario(mail);
		if (a!=null) {
			//Agrego a la lista de usuarios el usuario.
			this.usuarios.add(a);
			return a;
		}
		return null;
	}
	
	
	
	public Usuario getAdm() {
		return adm;
	}

	public void setAdm(Usuario adm) {
		this.adm = adm;
	}

}
