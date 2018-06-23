package model;

import java.util.Date;

public class Pago {

	private float Monto;
	private Date Fecha;
	private int IdLista;
	private String MailUsuario;
	
	public Pago(){}
	
	public Pago(float monto, Date fecha, int idLista, String mailUsuario){
		setMonto(monto);
		setFecha(fecha);
		setIdLista(idLista);
		setMailUsuario(mailUsuario);
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
}
