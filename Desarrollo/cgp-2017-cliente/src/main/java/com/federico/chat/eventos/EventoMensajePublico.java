package com.federico.chat.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.federico.chat.chat.Chat;
import com.federico.chat.comandos.Comando;
import com.federico.chat.mensajeria.PaqueteMensaje;

public class EventoMensajePublico extends KeyAdapter implements ActionListener{

	private Chat chat;
	
	public EventoMensajePublico(Chat chat) {
		this.chat = chat;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		enviarMensaje();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
			enviarMensaje();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
			chat.getMenuGeneral().getAreaMensaje().setText("");
		}
	}
	private void enviarMensaje() {
		String usuarioEmisor = chat.getUsuario().getNombreUsuario();
		String usuarioReceptor = "";
		String mensaje = chat.getMenuGeneral().getAreaMensaje().getText();
		if(mensaje.equals("")) {
			JOptionPane.showMessageDialog(chat.getMenuGeneral(), "Campo del mensaje esta vacio");
			return;
		}
		PaqueteMensaje paqueteMensaje = new PaqueteMensaje(usuarioEmisor, usuarioReceptor, mensaje, Comando.MENSAJE_PUBLICO);
		chat.getMenuGeneral().getAreaConversacion().append(usuarioEmisor + ": " + mensaje + "\n");
		chat.getMenuGeneral().getAreaMensaje().setText("");
		String objeto = chat.getGson().toJson(paqueteMensaje);
		chat.getMenuGeneral().getAreaMensaje().setText("");
		try {
			chat.getSalida().writeObject(objeto);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
