package deamon;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import controlador.ControladorDeLista;

public class DeamonVigencia extends Thread{

	public DeamonVigencia () throws InterruptedException{}
	
	
public void run(){
		
	String configuracion = "Config.txt";
    Properties propiedades;
    Calendar calendario = new GregorianCalendar();
	
	
	
	//Correrá siempre
//		while(true){
			
			try{
				
				FileInputStream f = new FileInputStream(configuracion);	 
			    propiedades = new Properties();
			    propiedades.load(f);
			    f.close();
			    
			    int hora = Integer.parseInt(propiedades.getProperty("HoraVigencia"));
			    int minutos = Integer.parseInt(propiedades.getProperty("MinutoVigencia"));
			    int dias = Integer.parseInt(propiedades.getProperty("DiasVigencia"));
				
//				if(calendario.get(Calendar.HOUR_OF_DAY) == hora && calendario.get(Calendar.MINUTE) == minutos){
					//Controlo las listas que esten por vencer en 10 dias
					ControladorDeLista.getInstancia().checkVigencia(dias);
					
					//Controlo las listas que hayan vencido hoy
					ControladorDeLista.getInstancia().checkVigencia(0);
//				}
				Thread.sleep(60000);
				
			}catch (Exception e) 
		     {
					System.out.println("Mensaje Error: " + e.getMessage());
					System.out.println("Stack Trace: " + e.getStackTrace());
		     }
//		}
			
			
			
			
		
		
}
}
