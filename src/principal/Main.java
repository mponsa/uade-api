package principal;

import view.*;
import controlador.*;
import deamon.DeamonPagos;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Creamos los controladores
		//ControladorDeUsuarios cu = new ControladorDeUsuarios();
		//ControladorDeLista cl = new ControladorDeLista();
		
		//Inicio los Deamons
		DeamonPagos hiloPagos = new DeamonPagos();
		hiloPagos.start();
		
		//Login start
		Login window = new Login();
		window.frame.setVisible(true);
		
	}

}
