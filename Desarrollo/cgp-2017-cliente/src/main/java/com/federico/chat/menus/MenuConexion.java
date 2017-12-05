package com.federico.chat.menus;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;


import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;

public class MenuConexion extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usuarioText;
	private JTextField servidorText;
	private JTextField puertoText;
	private JLabel estado;
	private JButton btnConectarse;
	
	public MenuConexion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuConexion.class.getResource("/com/federico/chat/recursos/conectarse.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0, 102, 153));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Conexion");
		usuarioText = new JTextField();
		usuarioText .setBounds(26, 40, 198, 30);
		contentPane.add(usuarioText );
		usuarioText .setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(SystemColor.controlLtHighlight);
		lblUsuario.setBounds(26, 26, 46, 14);
		contentPane.add(lblUsuario);
		
		servidorText = new JTextField();
		servidorText.setBounds(26, 97, 198, 30);
		contentPane.add(servidorText);
		servidorText.setColumns(10);
		
		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setForeground(SystemColor.controlLtHighlight);
		lblServidor.setBounds(26, 81, 102, 14);
		contentPane.add(lblServidor);
		
		puertoText = new JTextField();
		puertoText.setBounds(26, 154, 198, 30);
		contentPane.add(puertoText);
		puertoText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Puerto");
		lblNewLabel.setForeground(SystemColor.controlLtHighlight);
		lblNewLabel.setBounds(26, 138, 46, 14);
		contentPane.add(lblNewLabel);
		
		estado = new JLabel("Imagen 128 * 128");
		estado.setBounds(234, 15, 127, 124);
		contentPane.add(estado);
		desconectado();
		
		btnConectarse = new JButton("Conectar");
		btnConectarse.setIcon(new ImageIcon(MenuConexion.class.getResource("/com/federico/chat/recursos/btnConectar.png")));
		btnConectarse.setBounds(244, 145, 117, 41);
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
	
	public String dameusuario() {
		return usuarioText.getText().length() > 2 && usuarioText.getText().length() <= 20 ? usuarioText.getText().trim() : "";
	}
	
	public String dameIp() {
		String [] numeros = servidorText.getText().split("\\.");
		if(servidorText.getText().equals("localhost"))
			return "localhost";
		for(String num : numeros) {
			try {
				Integer.parseInt(num);
			}catch(NumberFormatException ex) {
				return "";
			}
		}
		return numeros.length == 4 ? servidorText.getText() : "";
	}
	
	public int damePuerto() {
		int puerto = 0;
		try {
			puerto = Integer.parseInt(puertoText.getText());
		}catch(NumberFormatException ex) {
			return 0;
		}
		return puerto > 0 ? puerto : 0;
	}
	
}
