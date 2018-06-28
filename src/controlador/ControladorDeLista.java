package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ListaDeRegalo;
import model.Participante;
import model.Usuario;
import observer.ObservableModel;
import persistencia.AdmPerListaDeRegalo;
import persistencia.AdmPerUsuario;

public class ControladorDeLista extends ObservableModel {
	private static ControladorDeLista instancia;
	private List<ListaDeRegalo> listas;
	private ListaDeRegalo listaAdm; //Variable para cuando se administra una lista en particular;
	
	public ControladorDeLista(){
		listas = new ArrayList<ListaDeRegalo>();
	}
	
	public static ControladorDeLista getInstancia() {
		if (instancia == null) {
			instancia = new ControladorDeLista();
			return instancia;
		}else{
			return instancia;
		}
	}
	
	public void crearLista (String nombre , Date vigencia , String agasajado, float monto,boolean estado, boolean activo, float montoPorParticipante) {
		ListaDeRegalo lista = new ListaDeRegalo(nombre,vigencia,agasajado,monto,estado,activo,montoPorParticipante);
		//Inserta el participante a la lista, la tenemos que traer de vuelta de memoria para recuperar el ID que genero la base.
		lista.addParticipante(ControladorDeUsuarios.getInstancia().getAdm(),true);
		//Agregamos la lista al controlador.
		listas.add(lista);
		this.notiAll();
	}
	
	
	public void updateLista(ListaDeRegalo l) {
		l.updateLista();
		this.notiAll();
	}
	
	public void deleteLista(ListaDeRegalo l) {
		//Eliminamos la lista del controlador si existe.
		for (ListaDeRegalo lista : listas) {
			if (lista.getIdLista() == l.getIdLista()) {
				listas.remove(l);
			}
		}
		l.deleteLista();;
		this.notiAll();
	}
	
	public ListaDeRegalo getListaDeRegalo(int id){;
		for (ListaDeRegalo lista : listas) {
			if (lista.getIdLista() == id){
				return lista;
			}
		}
		ListaDeRegalo a = AdmPerListaDeRegalo.getInstancia().getListaDeRegalo(id);
		if (a!=null) {
			//Agrega la lista al controlador.
			listas.add(a);
			return a;
		}
		return null;
	}
	
	public ListaDeRegalo getListaDeRegalo(String nombre){;
	for (ListaDeRegalo lista : listas) {
		if (lista.getNombre().equals(nombre)){
			return lista;
		}
	}
	ListaDeRegalo a = AdmPerListaDeRegalo.getInstancia().getListaDeRegalo(nombre);
	if (a!=null) {
		//Agrega la lista al controlador.
		listas.add(a);
		return a;
	}
	return null;
}
	
	
	//Devuelve los nombres de las listas administradas por un usuario.
	public List<String> getListasAdm(Usuario user){
		List<String> result = new ArrayList<String>();
		List<ListaDeRegalo> listas = AdmPerListaDeRegalo.getInstancia().getListasAdm(user);
		if (listas != null) {
		for (ListaDeRegalo lista : listas) {
			result.add(lista.getNombre());
		}
		if (result.size() == 0) {
			return null;
		}else {
			return result;
		}
		}else {
			return null; 
		}
	}
	
	//Devuelve los nombres de las listas en las que un usuario es participante.
	public List<String> getListasPar(Usuario user){
		List<String> result = new ArrayList<String>();
		List<ListaDeRegalo> listas = AdmPerListaDeRegalo.getInstancia().getListasPar(user);
		if (listas != null) {
		for (ListaDeRegalo lista : listas) {
			result.add(lista.getNombre());
		}
		if (result.size() == 0) {
			return null;
		}else {
			return result;
		}
		}else {
			return null; 
		}
	}
	
	//Devuelve la lista que se está administrando.
	public ListaDeRegalo getListaAdm() {
		return listaAdm;
	}
	//Setea la lista que se está administrando.
	public void setListaAdm(ListaDeRegalo lista) {
		this.listaAdm = lista;
	}
	
	
	public List<String> getMailParticipantes(ListaDeRegalo lista) {
		List<String> result = new ArrayList<String>();
		for (Participante p : lista.getParticipantes()) {
			result.add(p.getUsuario().getMail());
		}
		return result;
	}
	

	public void registrarPago(int IdLista, String mailUser, float monto, Date fecha) {
		ListaDeRegalo lista = this.getListaDeRegalo(IdLista);
		if (lista != null) {
			Usuario user = ControladorDeUsuarios.getInstancia().getUsuario(mailUser);
			lista.getParticipante(user).registrarPago(monto, fecha, lista);
			lista.setMonto(monto);
			lista.updateLista();
		}
	}

	public void addParticipante(ListaDeRegalo lista, Usuario usuario, boolean b) {
		// TODO Auto-generated method stub
		lista.addParticipante(usuario, b);
		this.notiAll();
	}
	
	
	
}