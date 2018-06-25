package tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class PagosTest {

		
	@Test
	public void testLeerArhivo(){
		
		File archivo = new File("Pagos.txt");
		
		
		
		assertTrue(archivo.exists() );
	}
	
	@Test
	public void testCopiarArchivo(){
		File archivo = new File("Pagos.txt");
		
		boolean t = false;
		try{
			 t = archivo.renameTo(new File("HistoricoPagos/Pagos" + new SimpleDateFormat("yyyymmddhhmm").format(new Date().getTime()) +".txt"));
			 
			 if(t)
				 archivo.delete();
		 } 
	    catch (Exception e) 
	     {
				System.out.println("Mensaje Error: " + e.getMessage());
				System.out.println("Stack Trace: " + e.getStackTrace());
	     }
		
		assertTrue(t );
	}
	
}
