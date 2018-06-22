package model;

import java.util.Date;

public class Pago {

	private float Monto;
	private String Fecha;
	private int IdLista;
	private String MailUsuario;
	
	public Pago(){}
	
	public Pago(float monto, String fecha, int idLista, String mailUsuario){
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
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
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
