package controlador;

import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.ListaDeRegalo;
import model.Usuario;


public class ControladorMail {
	
	private final Properties properties = new Properties();
	private static ControladorMail instancia;
	private String password;
	private Session session;
	
	public static ControladorMail getInstancia(){
		if (instancia == null) {
			instancia = new ControladorMail();
			return instancia;
		}else {
			return instancia;
		}
	}
	


	private void init() {

		properties.put("mail.smtp.host", "smtp.live.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port",587);
		properties.put("mail.smtp.mail.sender","api_g25@outlook.com.ar");
		properties.put("mail.smtp.user", "api_g25@outlook.com.ar");
		properties.put("mail.smtp.auth", "Interactivas25");

		session = Session.getDefaultInstance(properties);

	}
	
	public void enviarMail(String to, String subject, String text){
		
		init();
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			Transport t = session.getTransport("smtp");
			t.connect((String)properties.get("mail.smtp.user"), "Interactivas25");
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}catch (MessagingException me)
		{
			me.printStackTrace();
			//Aqui se deberia o mostrar un mensaje de error o en lugar
            //de no hacer nada con la excepcion, lanzarla para que el modulo
            //superior la capture y avise al usuario con un popup, por ejemplo.
			return;
		}
	}
	
	
	public String setMessage(ListaDeRegalo l, Usuario u, int cod) {
		switch (cod){
		case 1: //Lista creada
			return "Hola " + u.getNombre() + ".\n " +
			l.getAdminLista().getUsuario().getNombre() + " " + l.getAdminLista().getUsuario().getApellido() +
			" te ha agregado a la Lista de Regalos de " + l.getAgasajado() + ".\n\n" +
			"El monto a abonar es de $" + l.getMontoPorParticipante() + ".\n\n" +  
			"Saludos y que tengas buen día!";
		case 2: //Lista a vencer
			String format1 = new SimpleDateFormat("dd/MM/yyyy").format(l.getVigencia());
			return "Hola " + u.getNombre() + ".\n " 
					+ "La lista de regalos: " + l.getNombre() + " está por vencer y todavia no registramos su pago.\n."
					+ "Le recordamos que la vigencia de la lista es hasta el dia: " + format1 + "\n."+
								"El monto a abonar es de $" + l.getMontoPorParticipante() + ".\n\n" +  
								"Saludos y que tengas buen día!";
		case 3: //Lista pagada
			return "Hola " + u.getNombre() + ".\n " 
			+ "La lista de regalos: " + l.getNombre() + " ya se ha pagado en su totalidad.\n"
			+ "El monto recaudado fue de: $" + l.getMonto() + ".\n" +
						"Gracias por usar nuestra aplicación para tu evento!\n" +  
						"Saludos y que tengas buen día!";
	
		case 4: //Lista cerrada
			return "Hola " + l.getAdminLista().getUsuario().getNombre() + ".\n " 
			+ "La lista de regalos: " + l.getNombre() + " se ha cerrado al haber terminado su vigencia.\n"
			+ "El monto recaudado fue de: $" + l.getMonto() + ".\n" +
						"Gracias por usar nuestra aplicación para tu evento!\n" +  
						"Saludos y que tengas buen día!";
		}
		return "Mensaje predeterminado";
	}

}
