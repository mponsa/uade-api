package tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import controlador.ControladorDePagos;
import model.Pago;

public class PagosTest {

	@Test
	public void testGetPagos(){
		
		ControladorDePagos cp = ControladorDePagos.getInstancia();
		
		List<Pago> p = cp.getPagoFromFile();
		
		assertTrue(p.size() > 0);
	}
	
}
