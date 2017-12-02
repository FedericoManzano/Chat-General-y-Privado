package com.federico.chat.menus;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;


import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class MenuGeneral extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnEnviar;
	private JButton btnPrivado;
	private JList<String> listadoConectados;
	private JTextArea areaMensaje;
	private JTextArea areaConversacion;
	private JButton btnRefrescar;
	private DefaultListModel<String> modelo = new DefaultListModel<>();
	

	/**
	 * Create the frame.
	 */
	public MenuGeneral() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
	
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(51, 153, 204));
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(10, 11, 248, 346);
		contentPane.add(panel);
		panel.setLayout(null);
		setTitle("Chat General - Privado");
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 45, 206, 221);
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		listadoConectados = new JList<String>();
		scrollPane.setViewportView(listadoConectados);
		
		btnPrivado = new JButton("Privado");
		btnPrivado.setIcon(new ImageIcon(MenuGeneral.class.getResource("/com/federico/chat/recursos/privado.png")));
		btnPrivado.setBounds(20, 277, 137, 47);
		panel.add(btnPrivado);
		
		JLabel lblConectados = new JLabel("Conectados");
		lblConectados.setForeground(Color.WHITE);
		lblConectados.setIcon(new ImageIcon(MenuGeneral.class.getResource("/com/federico/chat/recursos/usuarios.png")));
		lblConectados.setBounds(20, 21, 111, 23);
		panel.add(lblConectados);
		
		btnRefrescar = new JButton("");
		btnRefrescar.setIcon(new ImageIcon(MenuGeneral.class.getResource("/com/federico/chat/recursos/refresh.png")));
		btnRefrescar.setBounds(167, 277, 59, 47);
		panel.add(btnRefrescar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(0, 102, 153));
		panel_2.setBounds(268, 11, 353, 346);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(20, 46, 310, 187);
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1);
		
		areaConversacion = new JTextArea();
		areaConversacion.setEditable(false);
		scrollPane_1.setViewportView(areaConversacion);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(20, 271, 221, 52);
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_4.add(scrollPane_2);
		
		areaMensaje = new JTextArea();
		scrollPane_2.setViewportView(areaMensaje);
		
		JLabel lblConversacion = new JLabel("Conversacion");
		lblConversacion.setForeground(Color.WHITE);
		lblConversacion.setIcon(new ImageIcon(MenuGeneral.class.getResource("/com/federico/chat/recursos/conversacion.png")));
		lblConversacion.setBounds(20, 22, 107, 24);
		panel_2.add(lblConversacion);
		
		JLabel lblNewLabel = new JLabel("Mensajes");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(MenuGeneral.class.getResource("/com/federico/chat/recursos/send.png")));
		lblNewLabel.setBounds(20, 244, 107, 26);
		panel_2.add(lblNewLabel);
		
		btnEnviar = new JButton("");
		btnEnviar.setIcon(new ImageIcon(MenuGeneral.class.getResource("/com/federico/chat/recursos/enviar.png")));
		btnEnviar.setBounds(254, 271, 76, 52);
		panel_2.add(btnEnviar);
	}
	
	
	public void actualizarListaConectados(String elemento) {
		modelo.addElement(elemento);
		listadoConectados.setModel(modelo);
	}

	public JButton getBtnEnviar() {
		return btnEnviar;
	}

	public JButton getBtnPrivado() {
		return btnPrivado;
	}

	public JList<String> getListadoConectados() {
		return listadoConectados;
	}

	public JTextArea getAreaMensaje() {
		return areaMensaje;
	}

	public JTextArea getAreaConversacion() {
		return areaConversacion;
	}
	
	public void vaciarListado() {
		modelo = new DefaultListModel<>();
		listadoConectados.removeAll();
	}
	
	public String dameSeleccionado() {
		return listadoConectados.getSelectedValue();
	}
	
	public int dameCantidadElementos() {
		return listadoConectados.getModel().getSize();
	}


	public JButton getBtnRefrescar() {
		return btnRefrescar;
	}
	
	public void configurarTitulo(String titulo) {
		setTitle(titulo);
	}
}
