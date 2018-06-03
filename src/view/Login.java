package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmListaDeRegalos;
	private JTextField emailField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmListaDeRegalos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListaDeRegalos = new JFrame();
		frmListaDeRegalos.setTitle("Lista de Regalos");
		frmListaDeRegalos.setBounds(100, 100, 450, 300);
		frmListaDeRegalos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListaDeRegalos.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(10, 36, 46, 14);
		frmListaDeRegalos.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(10, 61, 46, 14);
		frmListaDeRegalos.getContentPane().add(lblPassword);
		
		emailField = new JTextField();
		emailField.setBounds(66, 33, 86, 20);
		frmListaDeRegalos.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(66, 58, 86, 20);
		frmListaDeRegalos.getContentPane().add(passwordField);
		
		JLabel lblIniciarSesin = new JLabel("Iniciar sesi\u00F3n");
		lblIniciarSesin.setBounds(10, 11, 142, 14);
		frmListaDeRegalos.getContentPane().add(lblIniciarSesin);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mail = emailField.getText();
				String password = passwordField.getText();
					
			}
		});
		btnIngresar.setBounds(10, 86, 89, 23);
		frmListaDeRegalos.getContentPane().add(btnIngresar);
		
		JButton btnCrearUsuario = new JButton("Crear usuario..");
		btnCrearUsuario.setBounds(10, 227, 142, 23);
		frmListaDeRegalos.getContentPane().add(btnCrearUsuario);
	}
}
