package model;

import java.util.Date;

import persistencia.AdmPerPago;

public class Pago {

	private int IdPago;
	private float Monto;
	private Date Fecha;
	private ListaDeRegalo Lista; 
	private Participante participante;
	
	

	
	public Pago(float monto, Date fecha,ListaDeRegalo lista, Participante p){
		setMonto(monto);
		setFecha(fecha);
		setLista(lista);
		setParticipante(p);
		setIdPago(AdmPerPago.getInstancia().insert(this));
	}
	
	public void setLista(ListaDeRegalo lista) {
		this.Lista = lista;
	}
	
	public ListaDeRegalo getListaDeRegalo() {
		return Lista;
	}
	
	public void setParticipante(Participante participante) {
		// TODO Auto-generated method stub
		this.participante = participante;
	}
	
	public Participante getParticipante() {
		return participante;
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

	public int getIdPago() {
		return IdPago;
	}

	public void setIdPago(int idPago) {
		IdPago = idPago;
	}
}
