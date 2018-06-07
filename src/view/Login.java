package view;

import controlador.ControladorDeUsuarios;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {
	
	public JFrame frame;
	private JTextField mailText;
	private JTextField passwordText;
	private JLabel lblError;
	private JButton btnCrearUsuario;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login window = new Login();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	/**Constructor para diseño de la ventana.
	 * @wbp.parser.constructor
	 */
	public Login() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("E-Mail");
		lblUsuario.setBounds(10, 29, 92, 14);
		frame.getContentPane().add(lblUsuario);
		
		mailText = new JTextField();
		mailText.setBounds(112, 26, 179, 20);
		frame.getContentPane().add(mailText);
		mailText.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 57, 92, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordText = new JTextField();
		passwordText.setColumns(10);
		passwordText.setBounds(112, 54, 179, 20);
		frame.getContentPane().add(passwordText);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Se llama al controlador para que recupere el usuario ingresado por mail.
				//Si coincide se pregunta por el password, y si el password coincide el usuario accede al sistema.
				if(ControladorDeUsuarios.getInstancia().getUsuario(mailText.getText()) == null){
					lblError.setText("Usuario inexistente!");
					lblError.setVisible(true);
				}
				else {
					if (!ControladorDeUsuarios.getInstancia().getUsuario(mailText.getText()).getPassword().equals(passwordText.getText())) {
						lblError.setText("Contraseña errónea");
						lblError.setVisible(true);
					}else {
						//ACCESS GRANTED!!
						//Setea el administrador de la sesión.
						ControladorDeUsuarios.getInstancia().setAdm(ControladorDeUsuarios.getInstancia().getUsuario(mailText.getText()));
						
						AdmListas window = AdmListas.getInstancia();
						window.frame.setVisible(true);

						frame.dispose();
					}
				}
				
				
				
			
			}
		});
		btnIngresar.setBounds(10, 82, 89, 23);
		frame.getContentPane().add(btnIngresar);
		
		lblError = new JLabel("Usuario inexistente!");
		lblError.setBounds(301, 29, 123, 14);
		lblError.setVisible(false);
		frame.getContentPane().add(lblError);
		
		btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CrearUsuario window = new CrearUsuario();
				window.frame.setVisible(true);
				
				
			}
		});
		btnCrearUsuario.setBounds(10, 227, 128, 23);
		frame.getContentPane().add(btnCrearUsuario);
	}
}
