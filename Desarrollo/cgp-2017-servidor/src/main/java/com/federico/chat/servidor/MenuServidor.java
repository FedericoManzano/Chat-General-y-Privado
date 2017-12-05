package com.federico.chat.servidor;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;

public class MenuServidor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField puertoText;
	private JTextArea areaEstado;
	private JButton btnConectar;
	

	public MenuServidor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuServidor.class.getResource("/com/federico/chat/servidor/usuarioConectado.png")));
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 388, 244);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		areaEstado = new JTextArea();
		areaEstado.setEditable(false);
		scrollPane.setViewportView(areaEstado);
		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setBounds(10, 272, 46, 14);
		contentPane.add(lblPuerto);
		
		puertoText = new JTextField();
		puertoText.setText("2000");
		puertoText.setBounds(67, 269, 100, 20);
		contentPane.add(puertoText);
		puertoText.setColumns(10);
		
	    btnConectar = new JButton("Conectar");
		btnConectar.setBounds(309, 266, 89, 35);
		contentPane.add(btnConectar);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getPuertoText() {
		return puertoText;
	}

	public JTextArea getAreaEstado() {
		return areaEstado;
	}

	public JButton getBtnConectar() {
		return btnConectar;
	}
	
	public void mensaje(String mensaje) {
		areaEstado.append(mensaje + "\n");
	}
}
