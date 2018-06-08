package view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import controlador.ControladorDeLista;
import controlador.ControladorDeUsuarios;

public class VisualizarLista {

	public JFrame frame;
	private JTextField nombreText;
	private JTextField agasajadoText;
	private JTextField vigenciaDiaText;
	private JTextField montoText;
	private JTextField vigenciaMesText;
	private JTextField vigenciaAñoText;
	
	
	private boolean isAdm = false;
	
	public void setAdm(boolean adm){
		this.isAdm = adm;
	}
	
	public boolean isAdm(){
		return isAdm;
	}
	
	/**
	 * Create the application.
	 */
	public VisualizarLista(boolean adm) {
		//Realiza la comparacion para saber si la lista que se está administrando, es administrada por el usuario logueado
		setAdm(adm);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 531, 322);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle(Parametros.getInstancia().getTitle());
		frame.getContentPane().setLayout(null);
		frame.setResizable(Parametros.getInstancia().getResizable());
		
		///Labels.
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 54, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblAgasajado = new JLabel("Agasajado:");
		lblAgasajado.setBounds(10, 42, 75, 14);
		frame.getContentPane().add(lblAgasajado);
		
		JLabel lblFecha = new JLabel("Vigencia:");
		lblFecha.setBounds(10, 67, 110, 14);
		frame.getContentPane().add(lblFecha);
		
		JLabel lblMontoPorParticipante = new JLabel("Monto por participante:");
		lblMontoPorParticipante.setBounds(10, 148, 131, 14);
		frame.getContentPane().add(lblMontoPorParticipante);
		
		JLabel lblParticipantes = new JLabel("Participantes:");
		lblParticipantes.setBounds(309, 8, 94, 14);
		frame.getContentPane().add(lblParticipantes);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(10, 89, 46, 14);
		frame.getContentPane().add(lblDia);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(74, 89, 46, 14);
		frame.getContentPane().add(lblMes);
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(136, 89, 46, 14);
		frame.getContentPane().add(lblAo);
		///Fin Labels.
		
		///Text Fields.
		nombreText = new JTextField();
		nombreText.setBounds(74, 8, 86, 20);
		nombreText.setText(ControladorDeLista.getInstancia().getListaAdm().getNombre());
		frame.getContentPane().add(nombreText);
		nombreText.setColumns(10);
		
		agasajadoText = new JTextField();
		agasajadoText.setColumns(10);
		agasajadoText.setBounds(74, 39, 86, 20);
		agasajadoText.setText(ControladorDeLista.getInstancia().getListaAdm().getAgasajado());
		frame.getContentPane().add(agasajadoText);
		
		//Calendar para fechas.
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(ControladorDeLista.getInstancia().getListaAdm().getVigencia());
		
		vigenciaDiaText = new JTextField();
		vigenciaDiaText.setColumns(10);
		vigenciaDiaText.setBounds(10, 103, 46, 20);
		vigenciaDiaText.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
		frame.getContentPane().add(vigenciaDiaText);
		
		vigenciaMesText = new JTextField();
		vigenciaMesText.setColumns(10);
		vigenciaMesText.setBounds(74, 103, 46, 20);
		vigenciaMesText.setText(String.valueOf(calendar.get(Calendar.MONTH)));
		frame.getContentPane().add(vigenciaMesText);
		
		vigenciaAñoText = new JTextField();
		vigenciaAñoText.setColumns(10);
		vigenciaAñoText.setBounds(136, 103, 46, 20);
		vigenciaAñoText.setText(String.valueOf(calendar.get(Calendar.YEAR)));
		frame.getContentPane().add(vigenciaAñoText);
		
		montoText = new JTextField();
		montoText.setColumns(10);
		montoText.setBounds(151, 145, 86, 20);
		montoText.setText(String.valueOf(ControladorDeLista.getInstancia().getListaAdm().getMontoPorParticipante()));
		frame.getContentPane().add(montoText);
		
		setearTextFields();
		///Fin text fields.
		

		//Buttons.
		JButton btnAgregarParticipante = new JButton("Agregar Participante");
		btnAgregarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AgregarParticipante window = new AgregarParticipante();
				window.frame.setVisible(true);
				
			}
		});
		btnAgregarParticipante.setBounds(309, 228, 196, 23);
		btnAgregarParticipante.setVisible(isAdm);
		frame.getContentPane().add(btnAgregarParticipante);
		
		JButton btnEnviarMsg = new JButton("Enviar mensaje al administrador");
		btnEnviarMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnviarMsg.setBounds(10, 187, 289, 23);
		btnEnviarMsg.setVisible(!isAdm);
		frame.getContentPane().add(btnEnviarMsg);
		
		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.setBounds(114, 227, 185, 23);
		btnGuardarCambios.setVisible(isAdm);
		frame.getContentPane().add(btnGuardarCambios);
		
		JButton btnEliminarLista = new JButton("Eliminar lista");
		btnEliminarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarLista.setBounds(10, 227, 94, 23);
		btnEliminarLista.setVisible(isAdm);
		frame.getContentPane().add(btnEliminarLista);
		///Fin buttons
		
		///Text area.
		JTextArea textAreaParticipantes = new JTextArea();
		textAreaParticipantes.setBounds(309, 25, 196, 192);
		setearParticipantes(textAreaParticipantes);
		frame.getContentPane().add(textAreaParticipantes);
		///Fin text area


	}
	
	//Recorre los textFields y los setea editables o no según la visualización.
	void setearTextFields(){
		for (Component e : frame.getContentPane().getComponents()) {
			if(e instanceof JTextField) {
				((JTextField) e).setEditable(isAdm);
			}
		}
	}
	
	void setearParticipantes(JTextArea textArea){
		for(String str : ControladorDeLista.getInstancia().getListaAdm().getMailParticipantes()){

			textArea.append(str + "\n");
				
			
		}
	}
}
