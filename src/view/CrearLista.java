package view;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import controlador.ControladorDeLista;
import controlador.ControladorDeUsuarios;
import model.Usuario;
import controlador.ControladorDeUsuarios;
import model.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class CrearLista {

	JFrame frame;
	private JTextField nombreText;
	private JTextField agasajadoText;
	private JTextField diaText;
	private JTextField mesText;
	private JTextField añoText;
	private JTextField montoPorParticipanteText;


	public CrearLista() {
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
		frame.setTitle(Parametros.getInstancia().getTitle());
		
		nombreText = new JTextField();
		nombreText.setBounds(92, 11, 224, 20);
		frame.getContentPane().add(nombreText);
		nombreText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 14, 72, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("Agasajado");
		lblApellido.setBounds(10, 45, 72, 14);
		frame.getContentPane().add(lblApellido);
		
		agasajadoText = new JTextField();
		agasajadoText.setBounds(92, 42, 224, 20);
		agasajadoText.setColumns(10);
		frame.getContentPane().add(agasajadoText);
		
		JLabel lblFechaDeNacimientop = new JLabel("Vigencia");
		lblFechaDeNacimientop.setBounds(10, 73, 126, 14);
		frame.getContentPane().add(lblFechaDeNacimientop);
		
		diaText = new JTextField();
		diaText.setBounds(58, 93, 47, 20);
		diaText.setColumns(10);
		frame.getContentPane().add(diaText);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(10, 98, 38, 14);
		frame.getContentPane().add(lblDia);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(125, 98, 38, 14);
		frame.getContentPane().add(lblMes);
		
		mesText = new JTextField();
		mesText.setBounds(173, 93, 47, 20);
		mesText.setColumns(10);
		frame.getContentPane().add(mesText);
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(242, 99, 38, 14);
		frame.getContentPane().add(lblAo);
		
		añoText = new JTextField();
		añoText.setBounds(279, 93, 47, 20);
		añoText.setColumns(10);
		frame.getContentPane().add(añoText);
		
		JLabel lblListaCreada = new JLabel("Lista creada!");
		lblListaCreada.setBounds(242, 207, 99, 14);
		frame.getContentPane().add(lblListaCreada);
		lblListaCreada.setVisible(false);
		
		JButton btnNewButton = new JButton("Crear lista");
		btnNewButton.setBounds(10, 203, 210, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ControladorDeLista.getInstancia().getListaDeRegalo(nombreText.getText())== null){
				
				Calendar cal = Calendar.getInstance();
				cal.set(Integer.parseInt(añoText.getText()),Integer.parseInt(mesText.getText()) - 1,Integer.parseInt(diaText.getText()));
				Date date = cal.getTime();
				ControladorDeLista.getInstancia().crearLista(nombreText.getText(), new java.sql.Date(date.getTime()), agasajadoText.getText(), 0,true,true,Float.parseFloat(montoPorParticipanteText.getText()));		
				lblListaCreada.setText("Lista creada!");
				lblListaCreada.setVisible(true);
				limpiarPantalla();
				frame.dispose();
				
				}else {
					lblListaCreada.setText("Lista existente");
					lblListaCreada.setVisible(true);
					limpiarPantalla();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblMonto = new JLabel("Monto por participante");
		lblMonto.setBounds(10, 130, 152, 14);
		frame.getContentPane().add(lblMonto);
		
		montoPorParticipanteText = new JTextField();
		montoPorParticipanteText.setBounds(172, 124, 47, 20);
		montoPorParticipanteText.setColumns(10);
		frame.getContentPane().add(montoPorParticipanteText);
		
		JLabel lblMontoARecaudar = new JLabel("");
		lblMontoARecaudar.setBounds(242, 129, 166, 14);
		frame.getContentPane().add(lblMontoARecaudar);
		
		

	}
	
	public void limpiarPantalla() {
		for (Component e : frame.getContentPane().getComponents()) {
			if(e instanceof JTextField) {
				((JTextField) e).setText("");
			}
		}
	}
}
