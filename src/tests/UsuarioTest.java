package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import model.Usuario;
import persistencia.AdmPerUsuario;

public class UsuarioTest {

	@Test
	public void testAltaUsuario() {
		
		Usuario a = new Usuario();
		a.setNombre("David");
		a.setApellido("Malagueño");
		a.setFechaNac(new Date());
		a.setActivo(true);
		a.setMail("dmala@gmail.com");
		a.setPassword("Admin1234");
		
		
		AdmPerUsuario.getInstancia().insert(a);
		
		assertTrue(a != null);
	}

}
