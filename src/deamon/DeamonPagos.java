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

import controlador.ControladorDeLista;
import model.Pago;
import observer.ObserverModel;
import persistencia.AdmPerListaDeRegalo;
import persistencia.PoolConnection;

public class DeamonPagos extends Thread{
	
	public DeamonPagos () throws InterruptedException{}
	SimpleDateFormat formatter = new SimpleDateFormat("yyyymmdd");

	public void run(){
		
		//Correrá siempre
		while(true){
			
			//Al iniciar, tengo que verificar que haya un archivo de pagos
			File archivo = new File("Pagos.txt");
			
			//Si existe el archivo, leo todas las lineas
			if(archivo.exists()){
				
			    try 
			    {
			    	String linea;
			        FileReader f = new FileReader("Pagos.txt");
			        BufferedReader b = new BufferedReader(f);
			        
			        while((linea = b.readLine())!=null) {
			        	
			        	//Parseo cada linea e instancio una clase pago
			        	String[] partes = linea.split(";");
			        	Date parsed = formatter.parse(partes[3]);
			        	java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
			            ControladorDeLista.getInstancia().registrarPago(Integer.parseInt(partes[0]),partes[1],Float.parseFloat(partes[2]),sqlDate);
			        }
			        b.close();

			        //Mover el archivo original a una carpeta de historicos, renombrandolo
			        boolean t = archivo.renameTo(new File("HistoricoPagos/Pagos" + new SimpleDateFormat("yyyyMMddhhmm").format(new Date().getTime()) +".txt"));
					 
					if(t)
						archivo.delete();
			        
					
					 
			     } 
			    catch (Exception e) 
			     {
						System.out.println("Mensaje Error: " + e.getMessage());
						System.out.println("Stack Trace: " + e.getStackTrace());
			     }
			}

			 try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
	}

}
