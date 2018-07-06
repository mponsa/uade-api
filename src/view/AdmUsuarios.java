package view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.ControladorDeUsuarios;
import observer.ObserverModel;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class AdmUsuarios implements ObserverModel {

	private JFrame frame;
	private JPasswordField antPassword;
	private JPasswordField newPassword;
	private JPasswordField password;
	private JTextField nombreText;
	private JTextField apellidoText;
	private JTextField mailText;
	private JTextField diaText;
	private JTextField mesText;
	private JTextField añoText;

	/**
	 * Create the application.
	 */
	public AdmUsuarios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(false);
		frame.setTitle(Parametros.getInstancia().getTitle());
		
		//Labels
		JLabel lblConfirmar = new JLabel("Confirmar contrase\u00F1a:");
		lblConfirmar.setBounds(254, 146, 170, 14);
		frame.getContentPane().add(lblConfirmar);
		lblConfirmar.setVisible(false);
		
		JLabel lblAntPassword = new JLabel("Contrase\u00F1a anterior:");
		lblAntPassword.setBounds(254, 45, 170, 14);
		frame.getContentPane().add(lblAntPassword);
		lblAntPassword.setVisible(false);
		
		JLabel lblNewPassword = new JLabel("Nueva contrase\u00F1a:");
		lblNewPassword.setBounds(254, 93, 170, 14);
		frame.getContentPane().add(lblNewPassword);
		lblNewPassword.setVisible(false);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(10, 174, 46, 14);
		frame.getContentPane().add(lblDia);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(66, 173, 46, 14);
		frame.getContentPane().add(lblMes);
		
		JLabel lblAño = new JLabel("A\u00F1o");
		lblAño.setBounds(122, 173, 46, 14);
		frame.getContentPane().add(lblAño);
		frame.setVisible(true);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 68, 46, 14);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(10, 118, 46, 14);
		frame.getContentPane().add(lblMail);
		
		JLabel lblError = new JLabel("New label");
		lblError.setBounds(190, 231, 244, 14);
		frame.getContentPane().add(lblError);
		lblError.setVisible(false);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 15, 46, 14);
		frame.getContentPane().add(lblNombre);
		//Fin Labels
		
		//Password Fields
		antPassword = new JPasswordField();
		antPassword.setBounds(254, 62, 170, 20);
		frame.getContentPane().add(antPassword);
		antPassword.setVisible(false);
		
		newPassword = new JPasswordField();
		newPassword.setBounds(254, 115, 170, 20);
		frame.getContentPane().add(newPassword);
		newPassword.setVisible(false);
		
		password = new JPasswordField();
		password.setBounds(254, 167, 170, 20);
		frame.getContentPane().add(password);
		password.setVisible(false);
		//Fin Password Fields
		
		//TextFields
		nombreText = new JTextField();
		nombreText.setBounds(10, 39, 208, 20);
		frame.getContentPane().add(nombreText);
		nombreText.setColumns(10);
		nombreText.setText(ControladorDeUsuarios.getInstancia().getAdm().getNombre());
		nombreText.setEditable(false);
		
		apellidoText = new JTextField();
		apellidoText.setColumns(10);
		apellidoText.setBounds(10, 90, 208, 20);
		apellidoText.setText(ControladorDeUsuarios.getInstancia().getAdm().getApellido());
		frame.getContentPane().add(apellidoText);
		apellidoText.setEditable(false);

		mailText = new JTextField();
		mailText.setColumns(10);
		mailText.setBounds(10, 143, 208, 20);
		mailText.setText(ControladorDeUsuarios.getInstancia().getAdm().getMail());
		frame.getContentPane().add(mailText);
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(ControladorDeUsuarios.getInstancia().getAdm().getFechaNac());
		
		diaText = new JTextField();
		diaText.setBounds(10, 195, 46, 20);
		frame.getContentPane().add(diaText);
		diaText.setColumns(10);
		diaText.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		diaText.setEditable(false);
		
		mesText = new JTextField();
		mesText.setColumns(10);
		mesText.setBounds(66, 195, 46, 20);
		mesText.setText(String.valueOf(cal.get(Calendar.MONTH)+1));
		frame.getContentPane().add(mesText);
		mesText.setEditable(false);
		
		añoText = new JTextField();
		añoText.setColumns(10);
		añoText.setBounds(122, 195, 46, 20);
		añoText.setText(String.valueOf(cal.get(Calendar.YEAR)));
		frame.getContentPane().add(añoText);
		añoText.setEditable(false);
		
		JButton btnGuardarCambiosPass = new JButton("Guardar Cambios");
		btnGuardarCambiosPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblError.setVisible(true);
				lblError.setText(checkPassword(String.valueOf(antPassword.getPassword()),
						String.valueOf(newPassword.getPassword()),
						String.valueOf(password.getPassword())));
				clearPasswords();
			}
		});
		btnGuardarCambiosPass.setBounds(254, 198, 170, 23);
		btnGuardarCambiosPass.setVisible(false);
		frame.getContentPane().add(btnGuardarCambiosPass);
		
		JButton btnCambiarContraseña = new JButton("Cambiar Contrase\u00F1a");
		btnCambiarContraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblAntPassword.setVisible(true);
				antPassword.setVisible(true);
				antPassword.setEditable(true);
				lblNewPassword.setVisible(true);
				newPassword.setVisible(true);
				newPassword.setEditable(true);
				lblConfirmar.setVisible(true);
				password.setVisible(true);
				password.setEditable(true);
				btnGuardarCambiosPass.setVisible(true);
				
			}
		});
		btnCambiarContraseña.setBounds(254, 11, 170, 23);
		frame.getContentPane().add(btnCambiarContraseña);
		

		
		JButton btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorDeUsuarios.getInstancia().getAdm().setMail(mailText.getText());
				ControladorDeUsuarios.getInstancia().getAdm().updateUsuario();
				frame.dispose();
			}
		});
		btnGuardarCambios.setBounds(10, 227, 170, 23);
		frame.getContentPane().add(btnGuardarCambios);

	}
	
	private String checkPassword(String ant,String n,String c) {
		if(ControladorDeUsuarios.getInstancia().getAdm().getPassword().equals(ant)) {
			if(n.equals(c)) {
				ControladorDeUsuarios.getInstancia().getAdm().setPassword(c);
				ControladorDeUsuarios.getInstancia().updateUsuario(ControladorDeUsuarios.getInstancia().getAdm());;
				return "Cambio exitoso";
			}else {
				return "Confirmación errónea";
			}
		}else {
			return "La contraseña anterior es errónea";
		}
	}
	
	private void clearPasswords() {
		for (Component e : frame.getContentPane().getComponents()) {
			if(e instanceof JPasswordField) {
				((JPasswordField) e).setEditable(false);
			}
		}
	
	}

	@Override
	public void noti() {
		// TODO Auto-generated method stub
		
	}

}

	