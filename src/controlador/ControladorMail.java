package controlador;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ControladorMail {
	
	
	private static ControladorMail instancia;
	
	public static ControladorMail getInstancia(){
		if (instancia == null) {
			instancia = new ControladorMail();
			return instancia;
		}else {
			return instancia;
		}
	}
	
	public void enviarMail(String to, String from, String text){
		
	}
	
	
}
