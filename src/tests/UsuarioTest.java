package tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import model.Usuario;
import persistencia.AdmPerUsuario;

public class UsuarioTest {

	@Test
	public void testAltaUsuario() {
		
		Usuario a = new Usuario(
				"David"
				,"Malagueño"
				, new java.sql.Date(new Date().getTime())
				,"dmala@gmail.com"
				,"Admin1234"
				,true );
				
		AdmPerUsuario.getInstancia().insert(a);
		
		System.out.println(a.getIdUsuario());
		
		assertTrue(a != null);
	}
	
	@Test
	public void testDeleteUsuario(){
		Usuario a = new Usuario(
				"David"
				,"Malagueño"
				, new java.sql.Date(new Date().getTime())
				,"dmala@gmail.com"
				,"Admin1234"
				,true );
		
		AdmPerUsuario.getInstancia().delete(a);
		
		assertTrue(a != null);
	}
	
	@Test
	public void testUpdateUsuario(){
		
		Usuario a = new Usuario(
				"Nicolas"
				,"Gariboldi"
				, new java.sql.Date(new Date().getTime())
				,"dmala@gmail.com"
				,"Admin1234"
				,true );
				
		AdmPerUsuario.getInstancia().update(a);
		
		assertTrue(a != null);
	}
	
	@Test
	public void testBuscarUsuario(){
		
		Usuario a = AdmPerUsuario.getInstancia().buscarUsuarios("dmala@gmail.com").get(0);
		
		assertTrue(a != null);
	}
	
	@Test
	public void testBuscarUsuarios(){
		
		List<Usuario> a = AdmPerUsuario.getInstancia().buscarUsuarios(null);
		
		assertTrue(a.size() > 0);
	}

}
