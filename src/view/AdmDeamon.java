package view;



import javax.swing.JFrame;

import controlador.ControladorDeamons;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdmDeamon {

	private JFrame frmControladorDeHilos;

	/**
	 * Create the application.
	 */
	public AdmDeamon() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmControladorDeHilos = new JFrame();
		frmControladorDeHilos.setTitle("Controlador de Hilos");
		frmControladorDeHilos.setBounds(100, 100, 206, 151);
		frmControladorDeHilos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmControladorDeHilos.getContentPane().setLayout(null);
		frmControladorDeHilos.setVisible(true);
		
		JButton btnNewButton = new JButton("Play");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControladorDeamons.getInstancia().startDeamons();
			}
		});
		btnNewButton.setBounds(10, 11, 171, 39);
		frmControladorDeHilos.getContentPane().add(btnNewButton);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorDeamons.getInstancia().interruptDeamons();
			}
		});
		btnStop.setBounds(10, 61, 171, 39);
		frmControladorDeHilos.getContentPane().add(btnStop);
	}
}
