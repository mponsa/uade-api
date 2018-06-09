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
	public JComboBox<String> listasAdmCombo;
	public JComboBox<String> listasPartCombo;

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
	
	public void reInit(){
		this.initialize();
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
		
		listasAdmCombo = new JComboBox<String>();
		listasAdmCombo.setBounds(10, 58, 176, 20);
		frame.getContentPane().add(listasAdmCombo);
		
		JLabel labelListasPart = new JLabel("Listas en las que participa");
		labelListasPart.setBounds(10, 89, 297, 14);
		frame.getContentPane().add(labelListasPart);
		
		listasPartCombo = new JComboBox<String>();
		listasPartCombo.setBounds(10, 114, 176, 20);
		frame.getContentPane().add(listasPartCombo);
		
		actualizarCombos();
		
		JButton btnVisualizarAdm = new JButton("Visualizar ");
		btnVisualizarAdm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorDeLista.getInstancia().setListaAdm(ControladorDeLista.getInstancia().getListaDeRegalo(String.valueOf(listasAdmCombo.getSelectedItem())));
				VisualizarLista window = new VisualizarLista(true);
				window.frame.setVisible(true);
			
			}
		});
		btnVisualizarAdm.setBounds(196, 57, 120, 23);
		frame.getContentPane().add(btnVisualizarAdm);
		
		JButton btnVisualizarPart = new JButton("Visualizar ");
		btnVisualizarPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControladorDeLista.getInstancia().setListaAdm(ControladorDeLista.getInstancia().getListaDeRegalo(String.valueOf(listasPartCombo.getSelectedItem())));
				VisualizarLista window = new VisualizarLista(false);
				window.frame.setVisible(true);
			}
		});
		btnVisualizarPart.setBounds(196, 113, 120, 23);
		frame.getContentPane().add(btnVisualizarPart);
		
		JLabel lblBienvenido = new JLabel("Bienvenido "+ ControladorDeUsuarios.getInstancia().getAdm().getNombre() + "!");
		lblBienvenido.setBounds(10, 11, 297, 14);
		frame.getContentPane().add(lblBienvenido);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out..");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login window = new Login();
				window.frame.setVisible(true);
				ControladorDeLista.getInstancia().setListaAdm(null);
				ControladorDeUsuarios.getInstancia().setAdm(null);
				frame.dispose();
			
			}
		});
		mnSistema.add(mntmLogOut);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		mnSistema.add(mntmSalir);
		
		JMenu menu = new JMenu("");
		menuBar.add(menu);
		
	}

	public void actualizarCombos(){
		List<String> listas = ControladorDeLista.getInstancia().getListasAdm(ControladorDeUsuarios.getInstancia().getAdm());
		if (listas != null) {
		for (String lista : listas) {
			this.listasAdmCombo.setSelectedIndex(-1);
			this.listasAdmCombo.setSelectedItem(lista);
			if(this.listasAdmCombo.getSelectedIndex() < 0){
				this.listasAdmCombo.addItem(lista);
			}
		}
		}
		
		
		List<String> listasPar = ControladorDeLista.getInstancia().getListasPar(ControladorDeUsuarios.getInstancia().getAdm());
		if (listasPar != null) {
		for (String lista : listasPar) {
			this.listasPartCombo.addItem(lista);
		}
		}
	}

}
