package view;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import controlador.ControladorDeLista;
import controlador.ControladorDeUsuarios;

public class EliminarParticipante {

	public JFrame frame;


	//Constructor
	public EliminarParticipante() {
		initialize();
	}

	//Inizializa el frame
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 130);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle(Parametros.getInstancia().getTitle());
		frame.setResizable(Parametros.getInstancia().getResizable());
		
		//Labels
		JLabel lblIngresarEmailDel = new JLabel("Seleccione un usuario:");
		lblIngresarEmailDel.setBounds(10, 11, 259, 14);
		frame.getContentPane().add(lblIngresarEmailDel);
		//Fin labels
		
		//ComboBox
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 36, 230, 20);
		List<String> participantes = ControladorDeLista.getInstancia().getMailDeudores(ControladorDeLista.getInstancia().getListaAdm());
		for(String mail : participantes){
			//Agrego los usuarios deudores para eliminar. No se puede eliminar un usuario de una lista que ya pago.
			//Tampoco puedo eliminarme como participante siendo el administrador.
			if(!mail.equals(ControladorDeUsuarios.getInstancia().getAdm().getMail())) {
			comboBox.addItem(mail);
			}

		}
		
		frame.getContentPane().add(comboBox);
		//Fin ComboBox
		
		//Button.
		JButton btnElPart = new JButton("Eliminar Participante");
		btnElPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorDeLista.getInstancia().deleteParticipante(ControladorDeLista.getInstancia().getListaAdm(),ControladorDeLista.getInstancia().getListaAdm().getParticipante(ControladorDeUsuarios.getInstancia().getUsuario(comboBox.getSelectedItem().toString())));
				frame.dispose();
			}
		});
		btnElPart.setBounds(252, 35, 180, 23);
		frame.getContentPane().add(btnElPart);
		//Fin buttons
	}

	

}
