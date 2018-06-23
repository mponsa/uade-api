package controlador;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import model.Pago;

public class ControladorDePagos {
	
	private static ControladorDePagos instancia;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyymmdd");

	public static ControladorDePagos getInstancia(){
		if (instancia == null) {
			instancia = new ControladorDePagos();
			return instancia;
		}else {
			return instancia;
		}
	}
	
	public List<Pago> getPagoFromFile(){

		List<Pago> pagos = new ArrayList<Pago>();
	 
	    // Carga del fichero de propiedades 
	    try 
	    {
	    	String cadena;
	        FileReader f = new FileReader("Pagos.txt");
	        BufferedReader b = new BufferedReader(f);
	        
	        while((cadena = b.readLine())!=null) {
	        	String[] partes = cadena.split(";");
	        	
	            Pago p = new Pago(Float.parseFloat(partes[0])
	            		,formatter.parse(partes[1]),
	            		Integer.parseInt(partes[2]),
	            		partes[3]);
	            
	            pagos.add(p);
	            
	        }
	        b.close();

	     } 
	    catch (Exception e) 
	     {
				System.out.println("Mensaje Error: " + e.getMessage());
				System.out.println("Stack Trace: " + e.getStackTrace());
	     }
	    
	    return pagos;
	}
	
}
