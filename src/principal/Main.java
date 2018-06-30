package principal;

import view.*;
import controlador.*;
import deamon.DeamonPagos;
import deamon.DeamonVigencia;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		//Inicio los Deamons
		ControladorDeamons.getInstancia().addDeamon(new DeamonPagos());
		ControladorDeamons.getInstancia().addDeamon(new DeamonVigencia());
		new AdmDeamon();
		
		//Login start
		Login window = new Login();
		window.frame.setVisible(true);
		
	}

}
