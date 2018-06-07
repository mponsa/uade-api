package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import controlador.ControladorDeLista;
import controlador.ControladorDeUsuarios;
import model.Usuario;

public class AgregarParticipante {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarParticipante window = new AgregarParticipante();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AgregarParticipante() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 130);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Agregar participante");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
			
			}
		});
		btnNewButton.setBounds(293, 33, 131, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblIngresarEmailDel = new JLabel("Seleccione un usuario:");
		lblIngresarEmailDel.setBounds(10, 11, 259, 14);
		frame.getContentPane().add(lblIngresarEmailDel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 36, 264, 20);
		frame.getContentPane().add(comboBox);
	}

	
	//Devuelve una lista con los Mails de los usuarios que pueden agregarse a la lista
}
