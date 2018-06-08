package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import observer.*;
import persistencia.AdmPerListaDeRegalo;

public class ListaDeRegalo extends Observable implements IObserver{
	private static int maxIntegrantes = 10;
		
	private int IdLista; //Identity en la base.
	private String nombre;
	private Date vigencia;
	private String agasajado;
	private List<Participante> usuarios; 
	private float monto;
	private boolean estado;
	private boolean activo;
	private float montoPorParticipante;
	

	public ListaDeRegalo (String nombre , Date vigencia , String agasajado, float monto, boolean estado, boolean activo,float montoPorParticipante){
		
		setNombre(nombre);
		setVigencia(vigencia);
		setAgasajado(agasajado);
		setMonto(monto);
		setMontoPorParticipante(montoPorParticipante);
		setEstado(false);
		setActivo(activo);
		this.usuarios = new ArrayList<Participante>();
	}
	
	//Sobrecarga del constructor para cuando se lean usuarios de la base
	public ListaDeRegalo (int IdLista, String nombre , Date vigencia , String agasajado, float monto,boolean estado, boolean activo, float montoPorParticipante){
		setIdLista(IdLista);
		setNombre(nombre);
		setVigencia(vigencia);
		setAgasajado(agasajado);
		setMonto(monto);
		setEstado(estado);	
		setActivo(activo);
		setMontoPorParticipante(montoPorParticipante);
		this.usuarios = new ArrayList<Participante>();
	}
	
	
	
	public String addUser(Participante user){	
		if (this.usuarios.size() <= maxIntegrantes){
			this.usuarios.add(user);
			AdmPerListaDeRegalo.getInstancia().insertParticipantesALista(this,user);
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
	
	//Devuelve la lista de usuarios.
	public List<Participante> getUsuarios() {
		return usuarios;
	}
	
	//Devuelve la lista de mails de usuarios.
	public List<String> getMailParticipantes(){
		List<String> result = new ArrayList<String>();
		for (Participante p : usuarios){
			result.add(p.getMailUsuario());
		}
		return result;
	}
	
	public Participante getAdminLista(){
		for (Participante p : usuarios){
			if (p.isAdmin())
				return p;
		}
		return null;
	}
	
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
	@Override
	public void noti() {
		// TODO Auto-generated method stub
		
	}

}
