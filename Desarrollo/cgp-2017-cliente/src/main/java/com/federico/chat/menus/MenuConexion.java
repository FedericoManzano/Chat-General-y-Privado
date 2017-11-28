package com.federico.chat.menus;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MenuConexion extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usuarioText;
	private JTextField servidorText;
	private JTextField puertoText;
	private JLabel estado;
	private JButton btnConectarse;
	
	public MenuConexion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usuarioText = new JTextField();
		usuarioText .setBounds(26, 40, 198, 20);
		contentPane.add(usuarioText );
		usuarioText .setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(26, 15, 46, 14);
		contentPane.add(lblUsuario);
		
		servidorText = new JTextField();
		servidorText.setBounds(26, 95, 198, 20);
		contentPane.add(servidorText);
		servidorText.setColumns(10);
		
		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setBounds(26, 71, 102, 14);
		contentPane.add(lblServidor);
		
		puertoText = new JTextField();
		puertoText.setBounds(26, 153, 198, 20);
		contentPane.add(puertoText);
		puertoText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Puerto");
		lblNewLabel.setBounds(26, 128, 46, 14);
		contentPane.add(lblNewLabel);
		
		estado = new JLabel("Imagen 128 * 128");
		estado.setBounds(234, 15, 128, 127);
		contentPane.add(estado);
		desconectado();
		
		btnConectarse = new JButton("Conectarse");
		btnConectarse.setBounds(256, 152, 106, 23);
		contentPane.add(btnConectarse);
	}
	
	public void conectado() {
		estado.setIcon(new ImageIcon(MenuConexion.class.getResource(RutaRecursos.RUTA + "conectarse.png")));
	}
	
	public void desconectado() {
		estado.setIcon(new ImageIcon(MenuConexion.class.getResource(RutaRecursos.RUTA + "Desconectado.png")));
	}

	public JTextField getUsuarioText() {
		return usuarioText;
	}

	public JTextField getServidorText() {
		return servidorText;
	}

	public JTextField getPuertoText() {
		return puertoText;
	}

	public JLabel getEstado() {
		return estado;
	}

	public JButton getBtnConectarse() {
		return btnConectarse;
	}
	
}
