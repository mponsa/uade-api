package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import controlador.ControladorDeLista;
import controlador.ControladorDeUsuarios;
import model.Usuario;

public class AgregarParticipante {

	public JFrame frame;

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
		frame.setTitle(Parametros.getInstancia().getTitle());
		frame.setResizable(Parametros.getInstancia().getResizable());
				
		JLabel lblIngresarEmailDel = new JLabel("Seleccione un usuario:");
		lblIngresarEmailDel.setBounds(10, 11, 259, 14);
		frame.getContentPane().add(lblIngresarEmailDel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 36, 230, 20);
		List<String> mailsUsuarios = ControladorDeUsuarios.getInstancia().getMailsUsuarios();
		//List<String> participantes = ControladorDeLista.getInstancia().getListaAdm().getMailParticipantes();
		
		for(String mail : mailsUsuarios){
			//for(String mailPart  : participantes){
				if(!mail.equals(ControladorDeUsuarios.getInstancia().getAdm().getMail())){
			
							comboBox.addItem(mail);
					
				}
			//}
		}
		
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Agregar participante");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorDeLista.getInstancia().getListaAdm().addUser(
						ControladorDeLista.getInstancia().crearParticipante(
								ControladorDeLista.getInstancia().getListaAdm().getIdLista(), comboBox.getSelectedItem().toString(), false, false));
				frame.dispose();
			}
		});
		btnNewButton.setBounds(252, 35, 180, 23);
		frame.getContentPane().add(btnNewButton);
	}

	
	//Devuelve una lista con los Mails de los usuarios que pueden agregarse a la lista
}
