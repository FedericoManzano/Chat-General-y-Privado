package com.federico.chat.servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.federico.chat.comandos.Comando;
import com.federico.chat.mensajeria.Paquete;
import com.federico.chat.mensajeria.PaqueteConectados;
import com.federico.chat.mensajeria.PaqueteConexion;
import com.federico.chat.modelos.Conectado;
import com.federico.chat.modelos.Usuario;
import com.google.gson.Gson;

public class Servidor extends Thread implements ActionListener{
	private ServerSocket servidor;
	public static LinkedList<EscuchaCliente> listadoConectados = 
			new LinkedList<EscuchaCliente>();
	public static MenuServidor menuServidor = new MenuServidor();
	private int puerto;
	public Servidor() {
		menuServidor.getBtnConectar().addActionListener(this);
	}
	
	public void run() {
		try {
			servidor = new ServerSocket(2000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				menuServidor.mensaje("Esperando conexiones ...");
				Socket soc = servidor.accept();
				ObjectOutputStream salida = new ObjectOutputStream(soc.getOutputStream());
				ObjectInputStream entrada = new ObjectInputStream(soc.getInputStream());
				EscuchaCliente clienteConectados = new EscuchaCliente(soc, salida, entrada, "", "");
				listadoConectados.add(clienteConectados);
				clienteConectados.start();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
	}
	
	public void iniciar() {
		menuServidor.setVisible(true);
	}
	
	public static void main(String [] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		Servidor se = new Servidor();
		se.iniciar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			puerto = Integer.parseInt(menuServidor.getPuertoText().getText());
			this.start();
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(menuServidor, "Puerto incorrecto");
		}
	}
}
