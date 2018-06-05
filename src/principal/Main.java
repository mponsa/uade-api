package principal;

import view.*;
import controlador.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Creamos los controladores
		ControladorDeUsuarios cu = new ControladorDeUsuarios();
		
		//Login start
		Login window = new Login();
		window.frame.setVisible(true);
		
	}

}
