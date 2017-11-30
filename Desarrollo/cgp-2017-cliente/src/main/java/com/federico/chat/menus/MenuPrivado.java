package com.federico.chat.menus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.io.Serializable;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MenuPrivado extends JFrame implements Serializable{


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnEnviar;
	private JTextArea areaMensaje;
	private JTextArea areaConversacion;
	private JLabel lblUsuario;

	public MenuPrivado() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 464, 402);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Mensajes Privados");
		setResizable(false);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(10, 11, 438, 352);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(10, 11, 411, 236);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setIcon(new ImageIcon(MenuPrivado.class.getResource("/com/federico/chat/recursos/usuario.png")));
		lblUsuario.setBounds(10, 11, 247, 32);
		panel_1.add(lblUsuario);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(10, 46, 388, 179);
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		areaConversacion = new JTextArea();
		areaConversacion.setEditable(false);
		scrollPane.setViewportView(areaConversacion );
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaptionBorder);
		panel_3.setBounds(10, 258, 411, 83);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(10, 11, 280, 61);
		panel_3.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1);
		
		areaMensaje = new JTextArea();
		scrollPane_1.setViewportView(areaMensaje );
		
		btnEnviar = new JButton("");
		btnEnviar.setIcon(new ImageIcon(MenuPrivado.class.getResource("/com/federico/chat/recursos/enviar.png")));
		btnEnviar.setBounds(307, 11, 89, 61);
		panel_3.add(btnEnviar);
	}

	public JButton getBtnEnviar() {
		return btnEnviar;
	}

	public JTextArea getAreaMensaje() {
		return areaMensaje;
	}

	public JTextArea getAreaConversacion() {
		return areaConversacion;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}
	
	public void setUsuario(String usuario) {
		lblUsuario.setText(usuario);
	}
	
	
}
