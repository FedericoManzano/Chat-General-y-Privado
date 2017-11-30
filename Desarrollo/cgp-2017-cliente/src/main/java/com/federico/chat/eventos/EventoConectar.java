package com.federico.chat.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.federico.chat.chat.Chat;
import com.federico.chat.chat.EscuchaMensajes;
import com.federico.chat.comandos.Comando;
import com.federico.chat.mensajeria.PaqueteConexion;
import com.federico.chat.modelos.Usuario;

public class EventoConectar extends KeyAdapter implements ActionListener{

	private Chat chat;
	public EventoConectar(Chat chat) {
		this.chat = chat;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		conectar();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
			conectar();
		}
	}

	private void conectar() {
		String nombreUsuario = chat.getMenuConexion().dameusuario();
		String servidor = chat.getMenuConexion().dameIp();
		int puerto = chat.getMenuConexion().damePuerto();
		
		if(nombreUsuario.equals("") || servidor.equals("") || puerto == 0) {
			JOptionPane.showMessageDialog(
					chat.getMenuConexion(), "Revise los datos ingresados");
			return;
		}
		
		try {
			chat.setServidor(servidor);
			chat.setPuerto(puerto);
			chat.setSocket(new Socket(servidor, puerto));
			chat.setUsuario(new Usuario(nombreUsuario, chat.getSocket().getInetAddress().getHostAddress()));
			chat.setSalida(new ObjectOutputStream(chat.getSocket().getOutputStream()));
			chat.setEntrada(new ObjectInputStream(chat.getSocket().getInputStream()));
			String ip = chat.getSocket().getInetAddress().getHostAddress();
			PaqueteConexion paqueteEnvio = 
					new PaqueteConexion(nombreUsuario, ip, Comando.CONEXION);
			String cadenaObjeto = chat.getGson().toJson(paqueteEnvio);
			chat.getSalida().writeUnshared(cadenaObjeto);
			chat.setEscuchaMensajes(new EscuchaMensajes(chat));
			chat.iniciar();
		} catch (IOException ex) {
			ex.printStackTrace(); 
		}
	}

}
