package model;

import java.util.Date;

public class Usuario {

	private int IdUsuario; //Identity en la base
	private String Nombre;
	private String Apellido;
	private Date FechaNac;
	private String Mail;
	private String Password;
	private boolean Activo;
	
	public Usuario(){}
	
	//Constructor para la creacion de usuarios en la base
	public Usuario(String n, String a, Date f, String m, String p, boolean aa){
		this.setNombre(n);
		this.setApellido(a);
		this.setFechaNac(f);
		this.setMail(m);
		this.setPassword(p);
		this.setActivo(aa);
	}
	
	//Constructor para la creacion de usuarios en el sistema
		public Usuario(String n, String a, Date f, String m){
			this.setNombre(n);
			this.setApellido(a);
			this.setFechaNac(f);
			this.setMail(m);
		}
	
	//Sobrecarga del constructor para cuando se lean usuarios de la base
	public Usuario(int id,String n, String a, Date f, String m, String p, boolean aa){
		this.setIdUsuario(id);
		this.setNombre(n);
		this.setApellido(a);
		this.setFechaNac(f);
		this.setMail(m);
		this.setPassword(p);
		this.setActivo(aa);
	}
	
	public String getMail() {
		return Mail;
	}
	public void setMail(String mail) {
		Mail = mail;
	}
	public Date getFechaNac() {
		return FechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.FechaNac = fechaNac;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public boolean getActivo() {
		return Activo;
	}
	public boolean isActivo() {
		return Activo;
	}
	public void setActivo(boolean activo) {
		Activo = activo;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
		
}
