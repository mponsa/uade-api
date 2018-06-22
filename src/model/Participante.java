package model;

public class Participante {
	
	//private int IdLista;
	//private String MailUsuario;
	private boolean Pagado;
	private boolean IsAdmin;
	private boolean Activo;
	
	private Usuario usuario;//agregar solo aca y no en la base
	
	public Participante(int idLista, String mailUsuario, boolean IsAdmin){
		setIdLista(idLista);
		setMailUsuario(mailUsuario);
		setPagado(false);
		setActivo(true);
	}
	
	public Participante(int idLista, String mailUsuario, boolean IsAdmin, boolean pagado){
		setIdLista(idLista);
		setMailUsuario(mailUsuario);
		setAdmin(IsAdmin);
		setPagado(pagado);
		setActivo(true);
	}
	
	public boolean isPagado() {
		return Pagado;
	}
	public void setPagado(boolean pagado) {
		Pagado = pagado;
	}

	public int getIdLista() {
		return IdLista;
	}
	public void setIdLista(int idLista) {
		IdLista = idLista;
	}

	public String getMailUsuario() {
		return MailUsuario;
	}

	public void setMailUsuario(String mailUsuario) {
		MailUsuario = mailUsuario;
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

}
