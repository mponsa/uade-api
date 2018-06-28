package view;

import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import controlador.ControladorDeLista;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CrearLista {

	JFrame frame;
	private JTextField nombreText;
	private JTextField agasajadoText;
	private JTextField diaText;
	private JTextField mesText;
	private JTextField añoText;
	private JTextField montoPorParticipanteText;

	//Constructor
	public CrearLista() {
		initialize();
	}
	
	//Inizializa el frame
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle(Parametros.getInstancia().getTitle());
		frame.setResizable(Parametros.getInstancia().getResizable());
		
		//Labels
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 14, 72, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Agasajado");
		lblApellido.setBounds(10, 45, 72, 14);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblVigencia = new JLabel("Vigencia");
		lblVigencia.setBounds(10, 73, 126, 14);
		frame.getContentPane().add(lblVigencia);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(10, 98, 38, 14);
		frame.getContentPane().add(lblDia);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(125, 98, 38, 14);
		frame.getContentPane().add(lblMes);
		
		JLabel lblAño = new JLabel("A\u00F1o");
		lblAño.setBounds(242, 99, 38, 14);
		frame.getContentPane().add(lblAño);
		
		JLabel lblListaCreada = new JLabel("Lista creada!");
		lblListaCreada.setBounds(242, 207, 99, 14);
		frame.getContentPane().add(lblListaCreada);
		lblListaCreada.setVisible(false);
		
		JLabel lblMonto = new JLabel("Monto por participante");
		lblMonto.setBounds(10, 130, 152, 14);
		frame.getContentPane().add(lblMonto);
		
		JLabel lblMontoARecaudar = new JLabel("");
		lblMontoARecaudar.setBounds(242, 129, 166, 14);
		frame.getContentPane().add(lblMontoARecaudar);
		//Fin Labels
		
		//TextFields
		nombreText = new JTextField();
		nombreText.setBounds(92, 11, 224, 20);
		frame.getContentPane().add(nombreText);
		nombreText.setColumns(10);
			
		agasajadoText = new JTextField();
		agasajadoText.setBounds(92, 42, 224, 20);
		agasajadoText.setColumns(10);
		frame.getContentPane().add(agasajadoText);
		
		diaText = new JTextField();
		diaText.setBounds(58, 93, 47, 20);
		diaText.setColumns(10);
		frame.getContentPane().add(diaText);
		
		mesText = new JTextField();
		mesText.setBounds(173, 93, 47, 20);
		mesText.setColumns(10);
		frame.getContentPane().add(mesText);
		
		añoText = new JTextField();
		añoText.setBounds(279, 93, 47, 20);
		añoText.setColumns(10);
		frame.getContentPane().add(añoText);
		
		montoPorParticipanteText = new JTextField();
		montoPorParticipanteText.setBounds(172, 124, 47, 20);
		montoPorParticipanteText.setColumns(10);
		frame.getContentPane().add(montoPorParticipanteText);
		//Fin TextFields
		
		//Buttons
		JButton btnCrearLista = new JButton("Crear lista");
		btnCrearLista.setBounds(10, 203, 210, 23);
		btnCrearLista.addActionListener(new ActionListener() {
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
		frame.getContentPane().add(btnCrearLista);
		//Fin Buttons

		


		

	}
	
	//Limpia los textfield de la pantalla.
	public void limpiarPantalla() {
		for (Component e : frame.getContentPane().getComponents()) {
			if(e instanceof JTextField) {
				((JTextField) e).setText("");
			}
		}
	}
	
	
}
