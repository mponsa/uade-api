package view;

import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import controlador.ControladorDeUsuarios;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CrearUsuario {

	JFrame frame;
	private JTextField nombreText;
	private JTextField apellidoText;
	private JTextField emailText;
	private JTextField diaText;
	private JTextField mesText;
	private JTextField añoText;
	private JPasswordField passwordText;


	//Constructor
	public CrearUsuario() {
		initialize();
	}
	
	//Inizializa el frame
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle(Parametros.getInstancia().getTitle());
		frame.setResizable(Parametros.getInstancia().getResizable());
	
		//Labels
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 14, 72, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 45, 72, 14);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(10, 73, 72, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblFechaDeNacimientop = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimientop.setBounds(10, 101, 126, 14);
		frame.getContentPane().add(lblFechaDeNacimientop);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(10, 126, 38, 14);
		frame.getContentPane().add(lblDia);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(124, 126, 38, 14);
		frame.getContentPane().add(lblMes);
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(242, 126, 38, 14);
		frame.getContentPane().add(lblAo);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 169, 72, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblError = new JLabel("Usuario ya registrado!");
		lblError.setBounds(234, 231, 190, 14);
		frame.getContentPane().add(lblError);
		lblError.setVisible(false);
		//Fin Labels
		
		//TextFields
		nombreText = new JTextField();
		nombreText.setBounds(92, 11, 224, 20);
		frame.getContentPane().add(nombreText);
		nombreText.setColumns(10);
		
		apellidoText = new JTextField();
		apellidoText.setColumns(10);
		apellidoText.setBounds(92, 42, 224, 20);
		frame.getContentPane().add(apellidoText);
	
		emailText = new JTextField();
		emailText.setColumns(10);
		emailText.setBounds(92, 70, 224, 20);
		frame.getContentPane().add(emailText);
		
		diaText = new JTextField();
		diaText.setColumns(10);
		diaText.setBounds(58, 121, 47, 20);
		frame.getContentPane().add(diaText);
		
		mesText = new JTextField();
		mesText.setColumns(10);
		mesText.setBounds(172, 121, 47, 20);
		frame.getContentPane().add(mesText);
		
		añoText = new JTextField();
		añoText.setColumns(10);
		añoText.setBounds(290, 121, 47, 20);
		frame.getContentPane().add(añoText);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(92, 165, 224, 22);
		frame.getContentPane().add(passwordText);
		//Fin TextFields

		
		//Buttons
		JButton btnNewButton = new JButton("Crear usuario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ControladorDeUsuarios.getInstancia().getUsuario(emailText.getText()) == null) {
				Calendar cal = Calendar.getInstance();
				cal.set(Integer.parseInt(añoText.getText()),Integer.parseInt(mesText.getText()) - 1,Integer.parseInt(diaText.getText()));
				Date date = cal.getTime();
				ControladorDeUsuarios.getInstancia().crearUsuario(nombreText.getText(), apellidoText.getText(), new java.sql.Date(date.getTime()), emailText.getText(), String.valueOf(passwordText.getPassword()), true);
				frame.dispose();
				}else{
					lblError.setVisible(true);
					limpiarPantalla();
				}
			}
		});
		btnNewButton.setBounds(10, 227, 210, 23);
		frame.getContentPane().add(btnNewButton);
		//Fin buttons

		

	}
	
	//Limpia los TextField de la pantalla
	public void limpiarPantalla() {
		for (Component e : frame.getContentPane().getComponents()) {
			if(e instanceof JTextField) {
				((JTextField) e).setText("");
			}
		}
	}

}
