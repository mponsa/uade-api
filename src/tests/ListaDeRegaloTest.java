package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;


import model.ListaDeRegalo;
import model.Participante;

public class ListaDeRegaloTest {

	
	@Test
	public void testCreateLista(){

		//Usuario admin = new Usuario();
		
		ListaDeRegalo lista = new ListaDeRegalo(
				"Cumple David"
				, new java.sql.Date(new Date().getTime())
				, "David"
				, 0
				,false
				,true
				,100);
		
		System.out.println(lista.getIdLista());

	}
	
}
