package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import observer.*;

public class ListaDeRegalo extends Observable implements IObserver{
	private static int maxIntegrantes = 10;
		
	private int IdLista; //Identity en la base.
	private String nombre;
	private Date vigencia;
	private String agasajado;
	private List<Participante> usuarios = new ArrayList<Participante>();
	private float monto;
	private boolean estado;
	private float montoPorParticipante;
	

	public ListaDeRegalo (String nombre , Date vigencia , String agasajado, float monto,float montoPorParticipante){
		
		setNombre(nombre);
		setVigencia(vigencia);
		setAgasajado(agasajado);
		setMonto(monto);
		setMontoPorParticipante(montoPorParticipante);
		this.estado = false;	
	}
	
	//Sobrecarga del constructor para cuando se lean usuarios de la base
	public ListaDeRegalo (int IdLista, String nombre , Date vigencia , String agasajado, float monto, float montoPorParticipante){
		setIdLista(IdLista);
		setNombre(nombre);
		setVigencia(vigencia);
		setAgasajado(agasajado);
		setMonto(monto);
		setMontoPorParticipante(montoPorParticipante);
		this.estado = false;	
	}
	
	
	
	public String addUser(Participante user){	
		if (this.usuarios.size() <= maxIntegrantes){
			this.usuarios.add(user);	
			return "Usuario añadido!";
		}else{
			return "Limite de usuarios alcanzado!";	
		}
	}

	
	//Getters and Setters.
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getVigencia() {
		return vigencia;
	}

	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}

	public String getAgasajado() {
		return agasajado;
	}

	public void setAgasajado(String agasajado) {
		this.agasajado = agasajado;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	
	public int getIdLista() {
		return IdLista;
	}

	public void setIdLista(int idLista) {
		IdLista = idLista;
	}
	
	
	public float getMontoPorParticipante() {
		return montoPorParticipante;
	}

	public void setMontoPorParticipante(float montoPorParticipante) {
		this.montoPorParticipante = montoPorParticipante;
	}

	@Override
	public void noti() {
		// TODO Auto-generated method stub
		
	}

}
