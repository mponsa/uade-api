package model;

import java.util.ArrayList;
import java.util.List;
import observer.*;

public class ListaDeRegalo extends Observable implements IObserver{
	private static int maxIntegrantes = 10;
		
	
	private String nombre;
	private Fecha fecha;
	private String agasajado;
	private Usuario admin;
	private List<Participante> usuarios = new ArrayList<Participante>();
	private float monto;
	private boolean estado;
	
	
	public ListaDeRegalo (String nombre , Fecha fecha , String agasajado, float monto, Usuario admin){
	
		setNombre(nombre);
		setFecha(fecha);
		setAgasajado(agasajado);
		setMonto(monto);
		setAdmin(admin); 
		this.estado = false;
		
	}
	
	public String addUser(Participante user){	
		if (this.usuarios.size() <= 10){
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

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
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
	
	public Usuario getAdmin() {
		return admin;
	}

	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}

	@Override
	public void noti() {
		// TODO Auto-generated method stub
		
	}

}
