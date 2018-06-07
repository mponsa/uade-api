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
		listas.add(lista);
		AdmPerListaDeRegalo.getInstancia().insert(lista);
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
	
	public List<ListaDeRegalo> getListas(Participante participante){
		
		return null;
	}
}