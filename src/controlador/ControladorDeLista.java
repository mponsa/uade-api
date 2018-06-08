package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ListaDeRegalo;
import model.Participante;
import model.Usuario;
import persistencia.AdmPerListaDeRegalo;
import persistencia.AdmPerUsuario;

public class ControladorDeLista {
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
		AdmPerListaDeRegalo.getInstancia().insert(lista);
		//Inserta el participante a la lista, la tenemos que traer de vuelta de memoria para recuperar el ID que genero la base.
		lista.setIdLista(AdmPerListaDeRegalo.getInstancia().getListaDeRegalo(lista.getNombre()).getIdLista());
		lista.addUser(new Participante(lista.getIdLista(),ControladorDeUsuarios.getInstancia().getAdm().getMail(),true,false));
		listas.add(lista);
	}
	
	public ListaDeRegalo getListaDeRegalo(String nombre){;
		for (ListaDeRegalo lista : listas) {
			if (lista.getNombre().equals(nombre)){
				return lista;
			}
		}
		ListaDeRegalo a = AdmPerListaDeRegalo.getInstancia().getListaDeRegalo(nombre);
		if (a!=null)
			return a;
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
	public void setListaAdm(ListaDeRegalo listaAdm) {
		this.listaAdm = listaAdm;
	}
	
	public Participante crearParticipante(int idLista, String mailUsuario, boolean IsAdmin, boolean pagado){
		return new Participante(idLista,mailUsuario,IsAdmin,pagado);
	}
}