package model;

import java.util.Date;

import persistencia.AdmPerListaDeRegalo;

public class Participante {
	
	//private int IdLista;
	//private String MailUsuario;
	private boolean Pagado;
	private boolean IsAdmin;
	private boolean Activo;
	private Usuario usuario;//agregar solo aca y no en la base
	
	public Participante(ListaDeRegalo lista, Usuario usuario , boolean IsAdmin, boolean pagado,boolean base){
		setAdmin(IsAdmin);
		setPagado(pagado);
		setActivo(true);
		setUsuario(usuario);
		if(base){
		AdmPerListaDeRegalo.getInstancia().insertParticipante(lista, this);
		}
	}
	
	public void updateParticipante(ListaDeRegalo lista) {
		AdmPerListaDeRegalo.getInstancia().updateParticipante(lista, this);
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

	public void registrarPago(float monto, Date fecha, ListaDeRegalo lista) {
		new Pago(monto,fecha,lista,this);
		this.setPagado(true);
		this.updateParticipante(lista);
	}
	
}
