package model;

import java.util.Date;

import persistencia.AdmPerPago;

public class Pago {

	private int IdPago;
	private int IdLista;
	private String MailUsuario;
	private float Monto;
	private Date Fecha;
	
	
	public Pago(){}
	
	public Pago(int idLista, String mailUsuario,float monto, Date fecha){
		setMonto(monto);
		setFecha(fecha);
		setIdLista(idLista);
		setMailUsuario(mailUsuario);
		setIdPago(AdmPerPago.getInstancia().insert(this));
	}
	
	public float getMonto() {
		return Monto;
	}
	public void setMonto(float monto) {
		Monto = monto;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public int getIdLista() {
		return IdLista;
	}
	public void setIdLista(int idLista) {
		this.IdLista = idLista;
	}
	public String getMailUsuario() {
		return MailUsuario;
	}
	public void setMailUsuario(String mailUsuario) {
		this.MailUsuario = mailUsuario;
	}

	public int getIdPago() {
		return IdPago;
	}

	public void setIdPago(int idPago) {
		IdPago = idPago;
	}
}
