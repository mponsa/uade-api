package controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;


import model.ListaDeRegalo;
import model.Participante;
import model.Usuario;
import observer.ObservableModel;
import persistencia.AdmPerListaDeRegalo;


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
		lista.addParticipante(ControladorDeUsuarios.getInstancia().getAdm(),true,false);
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
			//Chequea si quedan deudores en la lista.
			if(lista.getDeudores() == null) {
				
				lista.setActivo(false);
				lista.setEstado(false);
				ControladorMail.getInstancia().enviarMail(lista.getAdminLista().getUsuario().getMail(), "Notificacion de Lista de Regalos finalizada", setMensajeListaPagada(lista,lista.getAdminLista().getUsuario()));
			}
			lista.updateLista();
		}
	}

	public void addParticipante(ListaDeRegalo lista, Usuario usuario, boolean IsAdmin, boolean pagado) {
		// TODO Auto-generated method stub
		if(lista.addParticipante(usuario, IsAdmin, pagado)) {
		ControladorMail.getInstancia().enviarMail(usuario.getMail(), "Notificación de Lista de Regalos.", setMensajeNuevoParticipante(lista, usuario));
		this.notiAll();
		}
	}

	
	public void deleteParticipante(ListaDeRegalo lista, Participante p) {
		if(lista.deleteParticipante(p)) {
		this.notiAll();
		}
	}
	

	
	public List<String> getMailDeudores(ListaDeRegalo l) {
		List<String> result = new ArrayList<String>();
		for(Participante p : l.getDeudores()) {
			result.add(p.getUsuario().getMail());
		}
		return result;
	}
	

	
	public void cerrarLista(ListaDeRegalo lista) {
		
		//Seteo la lista como cerrada antes de hacerle el update
		lista.setEstado(false);
		AdmPerListaDeRegalo.getInstancia().update(lista);
	}

	//TODO: Consultar si esto está bien hacerlo asi
	public void checkVigencia(int i) {
/*		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		today = cal.getTime();
		List<ListaDeRegalo> result = new ArrayList<ListaDeRegalo>();
		
		//Recupera las listas que entren en el periodo de vigencia desde el caché del controlador.
		for (ListaDeRegalo lista : listas) {
			int diff =  (int) TimeUnit.DAYS.convert(today.getTime() - lista.getVigencia().getTime(),TimeUnit.MILLISECONDS);
			if(diff < i) {
				result.add(lista);
			}
		}*/
		List<ListaDeRegalo> bdListas = new ArrayList<ListaDeRegalo>();
		//Recupera las listas que entren en el periodo de vigencia 
		bdListas = AdmPerListaDeRegalo.getInstancia().getListasCalendar(i);
		
		if(i == 0) {
			for (ListaDeRegalo lista : bdListas) {
				
				//Se setean las listas que hayan vencido como Cerradas
				ControladorDeLista.getInstancia().cerrarLista(lista);
				//Se informa al Administrador del cierre de su lista
				ControladorMail.getInstancia().enviarMail(lista.getAdminLista().getUsuario().getMail(),"Lista Cerrada.", setMensajeListaCerrada(lista));
				
			}
		}else {
			for (ListaDeRegalo lista : bdListas) {
				for (Participante p : lista.getDeudores()){
					ControladorMail.getInstancia().enviarMail(p.getUsuario().getMail(),"Notificación de Lista de Regalos.", setMensajeListaAVencer(lista,p.getUsuario()));
				}
			}
		
		}
	}
	
	private String setMensajeNuevoParticipante(ListaDeRegalo l, Usuario u){
		
		return "Hola " + u.getNombre() + ".\n " +
							l.getAdminLista().getUsuario().getNombre() + " " + l.getAdminLista().getUsuario().getApellido() +
							" te ha agregado a la Lista de Regalos de " + l.getAgasajado() + ".\n\n" +
							"El monto a abonar es de $" + l.getMontoPorParticipante() + ".\n\n" +  
							"Saludos y que tengas buen día!";
	}
	
	private String setMensajeListaAVencer(ListaDeRegalo l, Usuario u) {
		String format1 = new SimpleDateFormat("dd/MM/yyyy").format(l.getVigencia());
		return "Hola " + u.getNombre() + ".\n " 
				+ "La lista de regalos: " + l.getNombre() + " está por vencer y todavia no registramos su pago.\n."
				+ "Le recordamos que la vigencia de la lista es hasta el dia: " + format1 + "\n."+
							"El monto a abonar es de $" + l.getMontoPorParticipante() + ".\n\n" +  
							"Saludos y que tengas buen día!";
		
	}
	
	private String setMensajeListaPagada(ListaDeRegalo l, Usuario u) {
		return "Hola " + u.getNombre() + ".\n " 
				+ "La lista de regalos: " + l.getNombre() + " ya se ha pagado en su totalidad.\n"
				+ "El monto recaudado fue de: $" + l.getMonto() + ".\n" +
							"Gracias por usar nuestra aplicación para tu evento!\n" +  
							"Saludos y que tengas buen día!";
		
	}
	
	private String setMensajeListaCerrada(ListaDeRegalo l) {
		return "Hola " + l.getAdminLista().getUsuario().getNombre() + ".\n " 
				+ "La lista de regalos: " + l.getNombre() + " se ha cerrado al haber terminado su vigencia.\n"
				+ "El monto recaudado fue de: $" + l.getMonto() + ".\n" +
							"Gracias por usar nuestra aplicación para tu evento!\n" +  
							"Saludos y que tengas buen día!";
		
	}
	
}