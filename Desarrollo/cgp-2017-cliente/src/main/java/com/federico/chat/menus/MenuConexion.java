package com.federico.chat.menus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MenuConexion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuConexion frame = new MenuConexion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuConexion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(26, 40, 198, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(26, 15, 46, 14);
		contentPane.add(lblUsuario);
		
		textField_1 = new JTextField();
		textField_1.setBounds(26, 95, 198, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setBounds(26, 71, 102, 14);
		contentPane.add(lblServidor);
		
		textField_2 = new JTextField();
		textField_2.setBounds(26, 153, 198, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Puerto");
		lblNewLabel.setBounds(26, 128, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblImagen = new JLabel("Imagen 128 * 128");
		lblImagen.setIcon(new ImageIcon(MenuConexion.class.getResource(RutaRecursos.RUTA + "Desconectado.png")));
		lblImagen.setBounds(234, 15, 128, 127);
		contentPane.add(lblImagen);
		
		JButton btnConectarse = new JButton("Conectarse");
		btnConectarse.setBounds(256, 152, 106, 23);
		contentPane.add(btnConectarse);
	}
}
