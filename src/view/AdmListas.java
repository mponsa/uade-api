package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;

import controlador.ControladorDeLista;
import controlador.ControladorDeUsuarios;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
		frame.setBounds(100, 100, 472, 214);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle(Parametros.getInstancia().getTitle());
		frame.setResizable(Parametros.getInstancia().getResizable());
		
		
		JButton btnNewButton = new JButton("Crear Lista");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CrearLista window = new CrearLista();
				window.frame.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(326, 57, 120, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblListasAdm = new JLabel("Listas administradas");
		lblListasAdm.setBounds(10, 36, 297, 14);
		frame.getContentPane().add(lblListasAdm);
		
		JComboBox<String> listasAdmCombo = new JComboBox();
		listasAdmCombo.setBounds(10, 58, 176, 20);
		List<String> listas = ControladorDeLista.getInstancia().getListasAdm(ControladorDeUsuarios.getInstancia().getAdm());
		if (listas != null) {
		for (String lista : listas) {
			listasAdmCombo.addItem(lista);
		}
		}
		frame.getContentPane().add(listasAdmCombo);
		
		JLabel labelListasPart = new JLabel("Listas en las que participa");
		labelListasPart.setBounds(10, 89, 297, 14);
		frame.getContentPane().add(labelListasPart);
		
		JComboBox listasPartCombo = new JComboBox();
		listasPartCombo.setBounds(10, 114, 176, 20);
		frame.getContentPane().add(listasPartCombo);
		
		JButton btnVisualizar = new JButton("Visualizar ");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorDeLista.getInstancia().setListaAdm(ControladorDeLista.getInstancia().getListaDeRegalo(String.valueOf(listasAdmCombo.getSelectedItem())));
				VisualizarLista window = new VisualizarLista();
				window.frame.setVisible(true);
			
			}
		});
		btnVisualizar.setBounds(196, 57, 120, 23);
		frame.getContentPane().add(btnVisualizar);
		
		JButton button = new JButton("Visualizar ");
		button.setBounds(196, 113, 120, 23);
		frame.getContentPane().add(button);
		
		JLabel lblBienvenido = new JLabel("Bienvenido "+ ControladorDeUsuarios.getInstancia().getAdm().getNombre() + "!");
		lblBienvenido.setBounds(10, 11, 297, 14);
		frame.getContentPane().add(lblBienvenido);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out..");
		mnSistema.add(mntmLogOut);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnSistema.add(mntmSalir);
		
		JMenu menu = new JMenu("");
		menuBar.add(menu);
	}
}
