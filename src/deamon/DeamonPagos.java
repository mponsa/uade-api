package deamon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Pago;
import observer.ObserverSP;
import persistencia.AdmPerListaDeRegalo;
import persistencia.PoolConnection;

public class DeamonPagos extends Thread implements ObserverSP{
	
	public DeamonPagos () throws InterruptedException{}
	SimpleDateFormat formatter = new SimpleDateFormat("yyyymmdd");

	public void run(){
		
		//Correrá siempre
		while(true){
			
			//Al iniciar, tengo que verificar que haya un archivo de pagos
			File archivo = new File("Pagos.txt");
			try 
		    {
				//Si existe el archivo, leo todas las lineas
				if(archivo.exists()){
					
					List<Pago> pagos = new ArrayList<Pago>();
				    
				    	String linea;
				        FileReader f = new FileReader("Pagos.txt");
				        BufferedReader b = new BufferedReader(f);
				        
				        while((linea = b.readLine())!=null) {
				        	
				        	//Parseo cada linea e instancio una clase pago
				        	String[] partes = linea.split(";");
				        	
				            Pago p = new Pago(
				            		Integer.parseInt(partes[0]),
				            		partes[1],
				            		Float.parseFloat(partes[2])
				            		,formatter.parse(partes[3]));
				            
							//Actualizar al participante y la lista
				            AdmPerListaDeRegalo.getInstancia().resgistrarPago(p);
				            
				            AdmPerListaDeRegalo.getInstancia().actualizarMontoLista(p);
				            
				            //TODO: Aun no se que haremos con esta lista
				            pagos.add(p);
				        }
				        b.close();
	
				      //Mover el archivo original a una carpeta de historicos, renombrandolo
				        boolean t = archivo.renameTo(new File("HistoricoPagos/Pagos" + new SimpleDateFormat("yyyymmddhhmm").format(new Date().getTime()) +".txt"));
						 
						 if(t)
							 archivo.delete();
				}
				
				Thread.sleep(60000);
					 
		    } 
			    catch (Exception e) 
			     {
						System.out.println("Mensaje Error: " + e.getMessage());
						System.out.println("Stack Trace: " + e.getStackTrace());
			     }
			

						
		}
		
	}

	
	
	
	@Override
	public void noti() {
		// TODO Auto-generated method stub
		
	}
	
}
