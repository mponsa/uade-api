package model;

public class Participante {
	
	private int IdLista;
	private String MailUsuario;
	private boolean Pagado;
	private boolean IsAdmin;
	
	
	public Participante(int idLista, String mailUsuario, boolean IsAdmin){
		setIdLista(idLista);
		setMailUsuario(mailUsuario);
		setPagado(false);
	}
	
	public Participante(int idLista, String mailUsuario, boolean IsAdmin, boolean pagado){
		setIdLista(idLista);
		setMailUsuario(mailUsuario);
		setPagado(pagado);
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

}
