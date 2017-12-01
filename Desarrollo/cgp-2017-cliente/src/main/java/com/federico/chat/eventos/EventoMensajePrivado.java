package com.federico.chat.eventos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

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
		if(mensaje.equals("")) {
			JOptionPane.showMessageDialog(conversacion.getMenuPrivado(), "Campo del mensaje esta vacio");
			return;
		}
		if(conversacion.getMenuPrivado().dameEstado().equals("Offline")) {
			JOptionPane.showMessageDialog(conversacion.getMenuPrivado(), "Usuario Desconectado");
			return;
		}
		conversacion.getMenuPrivado().getAreaMensaje().setText("");
		conversacion.getMenuPrivado().getAreaConversacion().append(new Font("Arial",Font.BOLD, 15), new Color(0,0,0), conversacion.getUsuarioInterno() + ": ");
		conversacion.getMenuPrivado().getAreaConversacion().append(
				conversacion.getMenuAtributos().getFuenteSeleccionada(),
				conversacion.getMenuAtributos().getColorSeleccionado(),mensaje + "\n");
		PaqueteMensaje paq = new PaqueteMensaje(emisor, receptor, mensaje, Comando.MENSAJE_PRIVADO);
		configurarFuente(paq);
		String objetoEnviar = Comando.gson.toJson(paq);
		try {
			chat.getSalida().writeObject(objetoEnviar);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void configurarFuente(PaqueteMensaje pa) {
		pa.setNombreFuente(conversacion.getMenuAtributos().getFuenteSeleccionada().getName());
		pa.setTamFuente(conversacion.getMenuAtributos().getFuenteSeleccionada().getSize());
		pa.setTipoFuente(conversacion.getMenuAtributos().getFuenteSeleccionada().getStyle());
		pa.setRgb(conversacion.getMenuAtributos().getColorSeleccionado().getRGB());
	}

}
