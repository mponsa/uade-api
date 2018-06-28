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

public class AgregarParticipante {

	public JFrame frame;


	//Constructor
	public AgregarParticipante() {
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
		//Fin ComboBox
		
		//Button.
		JButton btnAgPart = new JButton("Agregar participante");
		btnAgPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorDeLista.getInstancia().addParticipante(ControladorDeLista.getInstancia().getListaAdm(),ControladorDeUsuarios.getInstancia().getUsuario(comboBox.getSelectedItem().toString()), false);
				frame.dispose();
			}
		});
		btnAgPart.setBounds(252, 35, 180, 23);
		frame.getContentPane().add(btnAgPart);
		//Fin buttons
	}

	

}
