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
	private Font fuenteUsuario = new Font("Arial Black",Font.ITALIC, 15);
	private Color colorUsuario = new Color(0,0,0);
	
	public EventoMensajePrivado(Chat chat, Conversacion con) {
		this.chat = chat;
		this.conversacion = con;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String mensaje = conversacion.getMenuPrivado().getAreaMensaje().getText();
		if(mensaje.equals("")) {
			JOptionPane.showMessageDialog(conversacion.getMenuPrivado(), "Campo del mensaje esta vacio");
			return;
		}
		if(conversacion.getMenuPrivado().dameEstado().equals("Offline")) {
			JOptionPane.showMessageDialog(conversacion.getMenuPrivado(), "Usuario Desconectado");
			return;
		}
		PaqueteMensaje paq = configurarDatosPrincipales();
		conversacion.getMenuPrivado().limpiarAreaMensaje();
		conversacion.getMenuPrivado().añadirMensaje(
				fuenteUsuario, colorUsuario, conversacion.getUsuarioInterno() + ": \n");
		conversacion.getMenuPrivado().añadirMensaje(
				chat.getFuenteSeleccionada(), chat.getColorSeleccionado(), paq.getMensaje() + "\n");
		conversacion.getMenuPrivado().getScrollPane().getVerticalScrollBar().setValue(conversacion.getMenuPrivado().getScrollPane().getVerticalScrollBar().getMaximum());
		String objetoEnviar = Comando.gson.toJson(paq);
		try {
			chat.getSalida().writeObject(objetoEnviar);
			conversacion.getMenuPrivado().getAreaMensaje().requestFocus();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private PaqueteMensaje configurarDatosPrincipales() {
		String emisor = chat.getUsuario().getNombreUsuario();
		String receptor = conversacion.getUsuarioExterno();
		String mensaje = conversacion.getMenuPrivado().getAreaMensaje().getText();
		String nombreFuente = chat.getFuenteSeleccionada().getName();
		int tamFuente = chat.getFuenteSeleccionada().getSize();
		int tipoFuente = chat.getFuenteSeleccionada().getStyle();
		int rgb = chat.getColorSeleccionado().getRGB();
		return new PaqueteMensaje(
				emisor, receptor, mensaje, Comando.MENSAJE_PRIVADO, 
				nombreFuente, tamFuente, tipoFuente, rgb
		);
	}

}
