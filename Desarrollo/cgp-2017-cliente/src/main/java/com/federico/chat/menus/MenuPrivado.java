package com.federico.chat.menus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.io.Serializable;

import javax.swing.border.LineBorder;

import com.federico.chat.modelos.CuadroTexto;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;

public class MenuPrivado extends JFrame implements Serializable{


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnEnviar;
	private CuadroTexto areaMensaje;
	private CuadroTexto  areaConversacion;
	private JLabel lblUsuario;
	private JLabel lblConexion;
	private JLabel lblFuente;
	public MenuPrivado() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 464, 421);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Mensajes Privados");
		setResizable(false);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(10, 11, 438, 371);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 102, 153));
		panel_1.setBounds(10, 11, 418, 246);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setIcon(new ImageIcon(MenuPrivado.class.getResource("/com/federico/chat/recursos/receptor.png")));
		lblUsuario.setBounds(10, 11, 247, 32);
		panel_1.add(lblUsuario);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(10, 46, 398, 189);
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		areaConversacion = new CuadroTexto();
		areaConversacion.setEditable(false);
		scrollPane.setViewportView(areaConversacion );
		
		lblConexion = new JLabel("Online");
		lblConexion.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
		lblConexion.setForeground(Color.GREEN);
		lblConexion.setIcon(new ImageIcon(MenuPrivado.class.getResource("/com/federico/chat/recursos/happy.png")));
		lblConexion.setBounds(288, 11, 110, 26);
		panel_1.add(lblConexion);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 102, 153));
		panel_3.setBounds(10, 258, 418, 102);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 28, 315, 63);
		panel_3.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1);
		
		areaMensaje = new CuadroTexto();
		scrollPane_1.setViewportView(areaMensaje );
		
		btnEnviar = new JButton("");
		btnEnviar.setIcon(new ImageIcon(MenuPrivado.class.getResource("/com/federico/chat/recursos/enviar.png")));
		btnEnviar.setBounds(335, 23, 73, 68);
		panel_3.add(btnEnviar);
		
		JLabel lblMensajes = new JLabel("Mensajes");
		lblMensajes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMensajes.setIcon(new ImageIcon(MenuPrivado.class.getResource("/com/federico/chat/recursos/send.png")));
		lblMensajes.setForeground(Color.WHITE);
		lblMensajes.setBounds(10, 0, 119, 25);
		panel_3.add(lblMensajes);
		
		lblFuente = new JLabel("");
		lblFuente.setIcon(new ImageIcon(MenuPrivado.class.getResource("/com/federico/chat/recursos/fonts.png")));
		lblFuente.setBounds(303, 3, 22, 22);
		panel_3.add(lblFuente);
	}

	
	public void cambiarEstadoConexion() {
		lblConexion.setForeground(new Color(187, 33, 0));
		lblConexion.setText("Offline");
		lblConexion.setIcon(new ImageIcon(MenuPrivado.class.getResource("/com/federico/chat/recursos/sad.png")));
	}
	
	public JButton getBtnEnviar() {
		return btnEnviar;
	}

	public String dameEstado() {
		return lblConexion.getText();
	}
	
	public CuadroTexto getAreaMensaje() {
		return areaMensaje;
	}

	public CuadroTexto getAreaConversacion() {
		return areaConversacion;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}
	
	public void setUsuario(String usuario) {
		lblUsuario.setText(usuario);
	}


	public JLabel getLblFuente() {
		return lblFuente;
	}


	public void setLblFuente(JLabel lblFuente) {
		this.lblFuente = lblFuente;
	}
}
