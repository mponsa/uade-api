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

public class VisualizarUsuario {

	public JFrame frame;
	private JTextField nombreText;
	private JTextField agasajadoText;
	private JTextField vigenciaDiaText;
	private JTextField vigenciaMesText;
	private JTextField vigenciaAñoText;
	private boolean isAdm = false;
	private JTextField textField;
	private JTextField textField_1;
	
	
	public void setAdm(boolean adm){
		this.isAdm = adm;
	}
	
	public boolean isAdm(){
		return isAdm;
	}
	
	//Constructor
	public VisualizarUsuario(boolean adm) {
		//Realiza la comparacion para saber si la lista que se está administrando, es administrada por el usuario logueado
		setAdm(adm);
		initialize();
	}

	//Inizializa el frame
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 401, 248);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle(Parametros.getInstancia().getTitle());
		frame.getContentPane().setLayout(null);
		frame.setResizable(Parametros.getInstancia().getResizable());
		
		///Labels.
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 54, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel Apellido = new JLabel("Agasajado:");
		Apellido.setBounds(10, 42, 75, 14);
		frame.getContentPane().add(Apellido);
		
		JLabel lblFecha = new JLabel("Fecha de nacimiento:");
		lblFecha.setBounds(10, 67, 110, 14);
		frame.getContentPane().add(lblFecha);
		
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
		
		setearTextFields();
		
		JButton btnEliminarLista = new JButton("Eliminar usuario");
		btnEliminarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarLista.setBounds(10, 190, 150, 23);
		btnEliminarLista.setVisible(isAdm);
		frame.getContentPane().add(btnEliminarLista);
		
		JLabel lblEmaik = new JLabel("E-Mail");
		lblEmaik.setBounds(10, 137, 75, 14);
		frame.getContentPane().add(lblEmaik);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setColumns(10);
		textField.setBounds(74, 134, 196, 20);
		frame.getContentPane().add(textField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 165, 75, 14);
		frame.getContentPane().add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setText((String) null);
		textField_1.setColumns(10);
		textField_1.setBounds(74, 162, 196, 20);
		frame.getContentPane().add(textField_1);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.setBounds(299, 161, 89, 23);
		frame.getContentPane().add(btnCambiar);
		
		JButton button = new JButton("Cambiar");
		button.setBounds(299, 133, 89, 23);
		frame.getContentPane().add(button);
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
	
	//Recorre el TextArea y lo completa con los participantes.
	void setearParticipantes(JTextArea textArea){
		for(String str : ControladorDeLista.getInstancia().getListaAdm().getMailParticipantes()){
			textArea.append(str + "\n");
		}
	}
}
