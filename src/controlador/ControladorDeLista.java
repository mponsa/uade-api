package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ListaDeRegalo;
import model.Participante;

public class ControladorDeLista {
	private static ControladorDeLista instancia;
	private List<ListaDeRegalo> listas;
	
	public ControladorDeLista(){
		listas = new ArrayList<ListaDeRegalo>();
	}
	
	public static ControladorDeLista getInstancia() {
		if (instancia == null) {
			return new ControladorDeLista();
		}else{
			return instancia;
		}
	}
	
	public void crearLista (String nombre , Date vigencia , String agasajado, float monto,boolean estado, boolean activo, float montoPorParticipante) {
		ListaDeRegalo lista = new ListaDeRegalo(nombre,vigencia,agasajado,monto,estado, activo,montoPorParticipante);
		listas.add(lista);
	}
	
	public ListaDeRegalo getLista(Participante admin){;
		
		return null;
	}
	
	public List<ListaDeRegalo> getListas(Participante participante){
		
		return null;
	}
}