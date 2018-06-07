package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;

import controlador.ControladorDeUsuarios;

public class AdmListas {
	private static AdmListas instancia;
	public JFrame frame;

	/**
	 * Create the application.
	 */
	
	public static AdmListas getInstancia() {
		if (instancia == null) {
			instancia = new AdmListas();
			return instancia;
		}else {
			return instancia;
		}
	}
	
	public AdmListas() {
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
		
		JButton btnNewButton = new JButton("Crear Lista.");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CrearLista window = new CrearLista();
				window.frame.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(10, 227, 165, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblListasAdm = new JLabel("Listas administradas por: " + ControladorDeUsuarios.getInstancia().getAdm().getMail());
		lblListasAdm.setBounds(10, 38, 297, 14);
		frame.getContentPane().add(lblListasAdm);
	}

}
