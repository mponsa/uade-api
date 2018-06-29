package tests;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import controlador.ControladorMail;

public class SendMailTest {

	@Test
	public void testSendMail() {
		
		ControladorMail.getInstancia().enviarMail("manuponsa@gmail.com","Lista Cerrada.", "Prueba Lista.\n Sarasa");
		
		assertTrue(true);
	}
	
}
