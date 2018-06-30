package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import observer.*;
import persistencia.AdmPerListaDeRegalo;

public class ListaDeRegalo implements ObserverModel{
	private static int maxIntegrantes = 10;
		
	private int IdLista; //Identity en la base.
	private String nombre;
	private Date vigencia;
	private String agasajado;
	private List<Participante> participantes; 
	private float monto;
	private boolean estado;//True: Sin finalizar - False: Finalizada
	private boolean activo;//True: Activa - False: No activa
	private float montoPorParticipante;
	
	//Metodo para crear la lista e instertarla en la base de datos.
	public ListaDeRegalo (String nombre , Date vigencia , String agasajado, float monto, boolean estado, boolean activo,float montoPorParticipante){
		setNombre(nombre);
		setVigencia(vigencia);
		setAgasajado(agasajado);
		setMonto(monto);
		setMontoPorParticipante(montoPorParticipante);
		setEstado(false);
		setActivo(activo);
		this.participantes = new ArrayList<Participante>();
		setIdLista(AdmPerListaDeRegalo.getInstancia().insert(this));
	}
	
	//Metodo que genera la lista cuando se busco en la base de datos.
	public ListaDeRegalo (int IdLista, String nombre , Date vigencia , String agasajado, float monto, boolean estado, boolean activo,float montoPorParticipante){
		setNombre(nombre);
		setVigencia(vigencia);
		setAgasajado(agasajado);
		setMonto(monto);
		setMontoPorParticipante(montoPorParticipante);
		setEstado(false);
		setActivo(activo);
		this.participantes = new ArrayList<Participante>();
		setIdLista(IdLista);
	}
	

	public void updateLista() {
		AdmPerListaDeRegalo.getInstancia().update(this);
	}
	
	public void deleteLista() {
		AdmPerListaDeRegalo.getInstancia().delete(this);
		AdmPerListaDeRegalo.getInstancia().deleteParticipantes(this);
	}
	
	
	public boolean addParticipante(Usuario user, boolean isAdmin, boolean pagado){
		if (this.participantes.size() <= maxIntegrantes){
			Participante p = new Participante(this,user,isAdmin,pagado);
			this.participantes.add(p);		
			return true;
		}else{
			return false;	
		}
	}
	
	public void updateParticipante(Participante p) {
		AdmPerListaDeRegalo.getInstancia().updateParticipante(this, p);
	}
	
	public boolean deleteParticipante(Participante p) {
		try {
			p.setActivo(false);
			participantes.remove(p);
			AdmPerListaDeRegalo.getInstancia().deleteParticipante(this, p);
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
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
		this.monto += monto;
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
	public List<Participante> getParticipantes() {
		return participantes;
	}
	
	
	//Devuelve el participante administrador.
	public Participante getAdminLista(){
		for (Participante p : participantes){
			if (p.isAdmin())
				return p;
		}
		return null;
	}
	
	public Participante getParticipante(Usuario usuario) {
		for (Participante p : participantes){
			if (p.getUsuario().getIdUsuario() == usuario.getIdUsuario())
				return p;
		}
		return null;
	}
	
	//Devuelve los participantes que no pagaron.
	public List<Participante> getDeudores() {
		List<Participante> result = new ArrayList<Participante>();
		for (Participante p : participantes) {
			if(!p.isPagado()) {
				result.add(p);
			}
		}
		// TODO Auto-generated method stub
		return result;
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
