package tests;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import model.Fecha;
import model.ListaDeRegalo;
import model.Usuario;

public class ListaDeRegaloTest {

	
	
	@Test
	public void testCreateLista(){
		
		String nombre = "Cumple David";
		Fecha fecha = new Fecha(); 
		String agasajado = "David";
		float monto = 500;
		Usuario admin = new Usuario();
		
		ListaDeRegalo lista = new ListaDeRegalo(nombre, fecha, agasajado, monto, admin);
		
		assertTrue(lista.getMonto() > 0);
	}
	
	

}
