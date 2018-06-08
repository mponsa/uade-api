package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import controlador.ControladorDeLista;

public class VisualizarLista {

	public JFrame frame;
	private JTextField nombreText;
	private JTextField agasajadoText;
	private JTextField vigenciaText;
	private JTextField montoText;
	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Create the application.
	 */
	public VisualizarLista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 531, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(Parametros.getInstancia().getTitle());
		frame.getContentPane().setLayout(null);
		
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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(309, 25, 196, 192);
		frame.getContentPane().add(textArea);
		
		JButton btnAgregarParticipante = new JButton("Agregar Participante");
		btnAgregarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAgregarParticipante.setBounds(309, 228, 196, 23);
		frame.getContentPane().add(btnAgregarParticipante);
		
		JButton btnEliminarLista = new JButton("Eliminar lista");
		btnEliminarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarLista.setBounds(10, 227, 94, 23);
		frame.getContentPane().add(btnEliminarLista);
		
		nombreText = new JTextField();
		nombreText.setBounds(74, 8, 86, 20);
		nombreText.setText(ControladorDeLista.getInstancia().getListaAdm().getNombre());
		frame.getContentPane().add(nombreText);
		nombreText.setColumns(10);
		
		agasajadoText = new JTextField();
		agasajadoText.setColumns(10);
		agasajadoText.setBounds(74, 39, 86, 20);
		frame.getContentPane().add(agasajadoText);
		
		vigenciaText = new JTextField();
		vigenciaText.setColumns(10);
		vigenciaText.setBounds(10, 103, 46, 20);
		frame.getContentPane().add(vigenciaText);
		
		montoText = new JTextField();
		montoText.setColumns(10);
		montoText.setBounds(151, 145, 86, 20);
		frame.getContentPane().add(montoText);
		
		JButton btnNewButton = new JButton("Enviar mensaje al administrador");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 187, 289, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.setBounds(114, 227, 185, 23);
		frame.getContentPane().add(btnGuardarCambios);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(10, 89, 46, 14);
		frame.getContentPane().add(lblDia);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(74, 89, 46, 14);
		frame.getContentPane().add(lblMes);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(74, 103, 46, 20);
		frame.getContentPane().add(textField);
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(136, 89, 46, 14);
		frame.getContentPane().add(lblAo);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(136, 103, 46, 20);
		frame.getContentPane().add(textField_1);
	}
}
