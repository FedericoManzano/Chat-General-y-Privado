package com.federico.chat.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.federico.chat.chat.Chat;
import com.federico.chat.modelos.Conversacion;

public class EventoAbrirVentana implements ActionListener{

	private Chat chat;
	
	public EventoAbrirVentana(Chat chat) {
		this.chat = chat;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String usuarioSeleccionado = chat.getMenuGeneral().dameSeleccionado();
		for(Conversacion con : Chat.listadoConectados) {
			if(con.getUsuarioExterno().equals(usuarioSeleccionado)) {
				con.getMenuPrivado().setVisible(true);
			}
		}
	}

}
