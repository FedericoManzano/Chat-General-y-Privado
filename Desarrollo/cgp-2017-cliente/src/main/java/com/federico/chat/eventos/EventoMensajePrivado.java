package com.federico.chat.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.federico.chat.chat.Chat;
import com.federico.chat.comandos.Comando;
import com.federico.chat.mensajeria.PaqueteMensaje;
import com.federico.chat.modelos.Conversacion;

public class EventoMensajePrivado implements ActionListener{

	private Chat chat;
	private Conversacion conversacion;
	public EventoMensajePrivado(Chat chat, Conversacion con) {
		this.chat = chat;
		this.conversacion = con;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String emisor = chat.getUsuario().getNombreUsuario();
		String receptor = conversacion.getUsuarioExterno();
		String mensaje = conversacion.getMenuPrivado().getAreaMensaje().getText();
		
		PaqueteMensaje paq = new PaqueteMensaje(emisor, receptor, mensaje, Comando.MENSAJE_PRIVADO);
		String objetoEnviar = Comando.gson.toJson(paq);
		try {
			chat.getSalida().writeObject(objetoEnviar);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
