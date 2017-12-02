package com.federico.chat.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
				if(!usuarioSeleccionado.equals(con.getUsuarioInterno())) {
					con.getMenuPrivado().setVisible(true);
				}else{
					JOptionPane.showMessageDialog(con.getMenuPrivado(), "No puede enviarse mensajes a usted mismo");
				}
			}
		}
	}
}
