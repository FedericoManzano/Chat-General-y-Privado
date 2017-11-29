package com.federico.chat.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class EventoConectar implements ActionListener{

	private Chat chat;
	public EventoConectar(Chat chat) {
		this.chat = chat;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String nombreUsuario = chat.getMenuConexion().dameusuario();
		String servidor = chat.getMenuConexion().dameIp();
		int puerto = chat.getMenuConexion().damePuerto();
		
		if(nombreUsuario.equals("") || servidor.equals("") || puerto == 0) {
			JOptionPane.showMessageDialog(
					chat.getMenuConexion(), "Revise los datod ingresados");
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
			System.out.println(cadenaObjeto);
			chat.getSalida().writeUnshared(cadenaObjeto);
			chat.setEscuchaMensajes(new EscuchaMensajes(chat));
			chat.iniciar();
		} catch (IOException ex) {
			ex.printStackTrace(); 
		}
	}

}
