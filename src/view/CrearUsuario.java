package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CrearUsuario {

	private JFrame frmListaDeRegalos;
	private JTextField textField;
	private JLabel lblEmail;
	private JTextField textField_1;
	private JLabel lblFechaDeNacimiento;
	private JTextField textField_2;
	private JLabel lblPassword;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearUsuario window = new CrearUsuario();
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
	public CrearUsuario() {
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
		
		JLabel lblNuevoUsuario = new JLabel("Nuevo usuario");
		lblNuevoUsuario.setBounds(10, 11, 69, 14);
		frmListaDeRegalos.getContentPane().add(lblNuevoUsuario);
		
		textField = new JTextField();
		textField.setBounds(124, 36, 86, 20);
		frmListaDeRegalos.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(20, 39, 46, 14);
		frmListaDeRegalos.getContentPane().add(lblNewLabel);
		
		lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(20, 70, 46, 14);
		frmListaDeRegalos.getContentPane().add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(124, 67, 86, 20);
		frmListaDeRegalos.getContentPane().add(textField_1);
		
		lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(20, 107, 98, 14);
		frmListaDeRegalos.getContentPane().add(lblFechaDeNacimiento);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(124, 104, 86, 20);
		frmListaDeRegalos.getContentPane().add(textField_2);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(20, 145, 46, 14);
		frmListaDeRegalos.getContentPane().add(lblPassword);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(124, 142, 86, 20);
		frmListaDeRegalos.getContentPane().add(textField_3);
	}

}
