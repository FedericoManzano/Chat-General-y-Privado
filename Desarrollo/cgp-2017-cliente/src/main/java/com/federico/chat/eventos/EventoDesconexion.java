package com.federico.chat.eventos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import com.federico.chat.chat.Chat;
import com.federico.chat.comandos.Comando;
import com.federico.chat.mensajeria.PaqueteConexion;

public class EventoDesconexion extends WindowAdapter{
	private Chat chat;
	
	public EventoDesconexion(Chat chat) {
		this.chat = chat;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		PaqueteConexion paqueteEnvio = new PaqueteConexion(chat.getUsuario().getNombreUsuario(),
				chat.getUsuario().getIp(), Comando.DESCONEXION);
		String objetoEnvio = Comando.gson.toJson(paqueteEnvio);
		try {
			chat.getSalida().writeObject(objetoEnvio);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
