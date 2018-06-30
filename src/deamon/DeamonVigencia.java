package deamon;

import java.util.Calendar;
import java.util.GregorianCalendar;
import controlador.ControladorDeLista;

public class DeamonVigencia extends Thread{

	public DeamonVigencia () throws InterruptedException{}
	
	
public void run(){
		


				try{
					//Controlo las listas que esten por vencer en 10 dias
					ControladorDeLista.getInstancia().checkVigencia(10);
					
					//Controlo las listas que hayan vencido hoy
					ControladorDeLista.getInstancia().checkVigencia(0);
					
					Thread.sleep(60000);
					
				}catch (Exception e) 
			     {
						System.out.println("Mensaje Error: " + e.getMessage());
						System.out.println("Stack Trace: " + e.getStackTrace());
			     }
			
			
			
			
			
		
		
}
}
