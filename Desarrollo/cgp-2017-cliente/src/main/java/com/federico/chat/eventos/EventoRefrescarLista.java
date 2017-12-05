package com.federico.chat.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.federico.chat.chat.Chat;

public class EventoRefrescarLista implements ActionListener{
	
	private Chat chat;
	
	public EventoRefrescarLista(Chat chat) {
		this.chat = chat;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		chat.getEscuchaMensajes().actualizarListado();
	}
}
