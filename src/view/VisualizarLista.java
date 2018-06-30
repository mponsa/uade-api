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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controlador.ControladorDeLista;
import controlador.ControladorDeUsuarios;
import observer.ObserverModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VisualizarLista implements ObserverModel {

	public JFrame frame;
	private JTextField nombreText;
	private JTextField agasajadoText;
	private JTextField vigenciaDiaText;
	private JTextField montoText;
	private JTextField vigenciaMesText;
	private JTextField vigenciaAñoText;
	private JTextArea textAreaParticipantes;
	private boolean isAdm = false;
	private JTextField textMontoRecaudado;
	
	
	public void setAdm(boolean adm){
		this.isAdm = adm;
	}
	
	public boolean isAdm(){
		return isAdm;
	}
	
	//Constructor
	public VisualizarLista(boolean adm) {
		//Realiza la comparacion para saber si la lista que se está administrando, es administrada por el usuario logueado
		setAdm(adm);
		initialize();
		ControladorDeLista.getInstancia().add(this);
	}

	//Inizializa el frame
	private void initialize() {
		//Calendar para fechas.
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(ControladorDeLista.getInstancia().getListaAdm().getVigencia());
		
		
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
		lblMontoPorParticipante.setBounds(10, 130, 131, 14);
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
		
		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				ControladorDeLista.getInstancia().getListaAdm().setNombre(nombreText.getText());
				calendar.set(Integer.parseInt(vigenciaAñoText.getText()),Integer.parseInt(vigenciaMesText.getText()) - 1,Integer.parseInt(vigenciaDiaText.getText()));
				Date date = calendar.getTime();
				ControladorDeLista.getInstancia().getListaAdm().setVigencia(new java.sql.Date(date.getTime()));
				ControladorDeLista.getInstancia().updateLista(ControladorDeLista.getInstancia().getListaAdm());
		
			}
		});
		btnGuardarCambios.setBounds(114, 227, 185, 23);
		btnGuardarCambios.setVisible(isAdm);
		btnGuardarCambios.setEnabled(false);
		frame.getContentPane().add(btnGuardarCambios);
		
		JButton btnEliminarLista = new JButton("Eliminar lista");
		btnEliminarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorDeLista.getInstancia().deleteLista(ControladorDeLista.getInstancia().getListaAdm());
				frame.dispose();
			}
		});
		btnEliminarLista.setBounds(10, 227, 94, 23);
		btnEliminarLista.setVisible(isAdm);
		frame.getContentPane().add(btnEliminarLista);
		
		JButton btnEliminarParticipante = new JButton("Eliminar Participante");
		btnEliminarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EliminarParticipante window = new EliminarParticipante();
				window.frame.setVisible(true);
			}
		});
		btnEliminarParticipante.setBounds(309, 259, 196, 23);
		btnEliminarParticipante.setVisible(isAdm);
		frame.getContentPane().add(btnEliminarParticipante);
		
		
		JButton btnDarseDeBaja = new JButton("Darse de Baja");
		btnDarseDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorDeLista.getInstancia().deleteParticipante(
						ControladorDeLista.getInstancia().getListaAdm(), 
						ControladorDeLista.getInstancia().getListaAdm().getParticipante(ControladorDeUsuarios.getInstancia().getAdm()));
				frame.dispose();
			}
		});
		btnDarseDeBaja.setBounds(10, 193, 289, 23);
		frame.getContentPane().add(btnDarseDeBaja);
		frame.setVisible(!isAdm);
		///Fin buttons
		
		///Text Fields.
				nombreText = new JTextField();
				nombreText.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						btnGuardarCambios.setEnabled(true);
					}
				});
				nombreText.setBounds(74, 8, 86, 20);
				nombreText.setText(ControladorDeLista.getInstancia().getListaAdm().getNombre());
				frame.getContentPane().add(nombreText);
				nombreText.setEditable(isAdm);
				nombreText.setColumns(10);
				
				agasajadoText = new JTextField();
				agasajadoText.setColumns(10);
				agasajadoText.setBounds(74, 39, 86, 20);
				agasajadoText.setText(ControladorDeLista.getInstancia().getListaAdm().getAgasajado());
				agasajadoText.setEditable(false);
				frame.getContentPane().add(agasajadoText);
				

				
				vigenciaDiaText = new JTextField();
				vigenciaDiaText.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						btnGuardarCambios.setEnabled(true);
					}
				});
				vigenciaDiaText.setColumns(10);
				vigenciaDiaText.setBounds(10, 103, 46, 20);
				vigenciaDiaText.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
				vigenciaDiaText.setEditable(isAdm);
				frame.getContentPane().add(vigenciaDiaText);
				
				vigenciaMesText = new JTextField();
				vigenciaMesText.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						btnGuardarCambios.setEnabled(true);
					}
				});
				vigenciaMesText.setColumns(10);
				vigenciaMesText.setBounds(74, 103, 46, 20);
				vigenciaMesText.setText(String.valueOf(calendar.get(Calendar.MONTH)));
				vigenciaMesText.setEditable(isAdm);
				frame.getContentPane().add(vigenciaMesText);
				
				vigenciaAñoText = new JTextField();
				vigenciaAñoText.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						btnGuardarCambios.setEnabled(true);
					}
				});
				vigenciaAñoText.setColumns(10);
				vigenciaAñoText.setBounds(136, 103, 46, 20);
				vigenciaAñoText.setText(String.valueOf(calendar.get(Calendar.YEAR)));
				vigenciaAñoText.setEditable(isAdm);
				frame.getContentPane().add(vigenciaAñoText);
				
				montoText = new JTextField();
				montoText.setColumns(10);
				montoText.setBounds(151, 127, 86, 20);
				montoText.setText(String.valueOf(ControladorDeLista.getInstancia().getListaAdm().getMontoPorParticipante()));
				montoText.setEditable(false);
				frame.getContentPane().add(montoText);
				
				
				///Fin text fields.
		
		///Text area.
		textAreaParticipantes = new JTextArea();
		textAreaParticipantes.setBounds(309, 25, 196, 192);
		setearParticipantes(textAreaParticipantes);
		textAreaParticipantes.setEditable(false);
		frame.getContentPane().add(textAreaParticipantes);
		
		JLabel lblMontoRecaudado = new JLabel("Monto recaudado:");
		lblMontoRecaudado.setBounds(10, 158, 131, 14);
		frame.getContentPane().add(lblMontoRecaudado);
		
		textMontoRecaudado = new JTextField();
		textMontoRecaudado.setText("0.0");
		textMontoRecaudado.setEditable(false);
		textMontoRecaudado.setColumns(10);
		textMontoRecaudado.setBounds(151, 155, 86, 20);
		textMontoRecaudado.setText(String.valueOf(ControladorDeLista.getInstancia().getListaAdm().getMonto()));
		frame.getContentPane().add(textMontoRecaudado);

		///Fin text area


	}
	
	
	
	//Recorre el TextArea y lo completa con los participantes.
	void setearParticipantes(JTextArea textArea){
		for(String str : ControladorDeLista.getInstancia().getMailParticipantes(ControladorDeLista.getInstancia().getListaAdm())){
			textArea.append(str + "\n");
		}
	}
	
	void limpiarText(JTextArea textArea){
		textArea.setText("");
	}

	@Override
	public void noti() {
		// TODO Auto-generated method stub
		this.limpiarText(textAreaParticipantes);
		this.setearParticipantes(textAreaParticipantes);
		this.textMontoRecaudado.setText(String.valueOf(ControladorDeLista.getInstancia().getListaAdm().getMonto()));
	}
}
