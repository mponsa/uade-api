package model;

public class Participante {
	
	//private int IdLista;
	//private String MailUsuario;
	private boolean Pagado;
	private boolean IsAdmin;
	private boolean Activo;
	private Usuario usuario;//agregar solo aca y no en la base
	
	public Participante(Usuario usuario,boolean IsAdmin){
		setAdmin(IsAdmin);
		setPagado(false);
		setActivo(true);
	}
	
	public boolean isPagado() {
		return Pagado;
	}
	public void setPagado(boolean pagado) {
		Pagado = pagado;
	}

	public boolean isAdmin() {
		return IsAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.IsAdmin = isAdmin;
	}
	
	public void setActivo(boolean activo) {
		this.Activo = activo;
	}
	
	public boolean isActivo() {
		return this.Activo;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

}
