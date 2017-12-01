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
	public static int cantidadRepetidos = 1; 
	
	public Servidor() {
		menuServidor.getBtnConectar().addActionListener(this);
	}
	
	public synchronized void run() {
		try {
			servidor = new ServerSocket(puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		menuServidor.mensaje("Esperando conexiones ...");
		while(true) {
			try {
				Socket soc = servidor.accept();
				ObjectOutputStream salida = new ObjectOutputStream(soc.getOutputStream());
				ObjectInputStream entrada = new ObjectInputStream(soc.getInputStream());
				EscuchaCliente clienteConectados = new EscuchaCliente(soc, salida, entrada, "", "");
				listadoConectados.add(clienteConectados);
				notifyAll();
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
		javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
	
	public static LinkedList<EscuchaCliente> dameListadoConectados(){
		return new LinkedList<>(listadoConectados);
	}
}
