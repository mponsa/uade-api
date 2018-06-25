package deamon;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import model.ListaDeRegalo;
import persistencia.AdmPerListaDeRegalo;

public class DeamonVigencia extends Thread{

	public DeamonVigencia () throws InterruptedException{}
	
	
public void run(){
		
		//Correrá siempre
		while(true){
			Calendar calendario = new GregorianCalendar();
			
			System.out.println(" Arranca");
		
			
				try{
					if(calendario.get(Calendar.HOUR_OF_DAY) == 14 && calendario.get(Calendar.MINUTE) == 16){
					
					//Obtengo las listas de Regalo cuya vigencia sea menor a x dias
					List<ListaDeRegalo> lista = AdmPerListaDeRegalo.getInstancia().getListasCalendar(10);
					System.out.println(" Entro!");
					//TODO: Ver como aplicar
					//Para cada miembro de la lista, antes de enviar el mail, verificar que su atributo pagado = 1
					}
					Thread.sleep(60000);
					
				}catch (Exception e) 
			     {
						System.out.println("Mensaje Error: " + e.getMessage());
						System.out.println("Stack Trace: " + e.getStackTrace());
			     }
			
			
			
			
			
		}
		
}
}
