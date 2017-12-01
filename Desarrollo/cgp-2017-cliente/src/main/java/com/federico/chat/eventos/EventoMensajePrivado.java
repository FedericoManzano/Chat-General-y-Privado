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
	private Font fuenteUsuario = new Font("Arial",Font.BOLD, 15);
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
		conversacion.getMenuPrivado().getAreaMensaje().setText("");
		conversacion.getMenuPrivado().getAreaConversacion().append(fuenteUsuario, colorUsuario, conversacion.getUsuarioInterno() + ": ");
		conversacion.getMenuPrivado().getAreaConversacion().append(
				dameFuenteSeleccionada(),dameColorSeleccionado(), paq.getMensaje() + "\n");
		
		
		
		String objetoEnviar = Comando.gson.toJson(paq);
		try {
			chat.getSalida().writeObject(objetoEnviar);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private PaqueteMensaje configurarDatosPrincipales() {
		String emisor = chat.getUsuario().getNombreUsuario();
		String receptor = conversacion.getUsuarioExterno();
		String mensaje = conversacion.getMenuPrivado().getAreaMensaje().getText();
		PaqueteMensaje pa = new PaqueteMensaje(emisor, receptor, mensaje, Comando.MENSAJE_PRIVADO);
		pa.setNombreFuente(conversacion.getMenuAtributos().getFuenteSeleccionada().getName());
		pa.setTamFuente(conversacion.getMenuAtributos().getFuenteSeleccionada().getSize());
		pa.setTipoFuente(conversacion.getMenuAtributos().getFuenteSeleccionada().getStyle());
		pa.setRgb(conversacion.getMenuAtributos().getColorSeleccionado().getRGB());
		return pa;
	}
	
	
	private Font dameFuenteSeleccionada() {
		return conversacion.getMenuAtributos().getFuenteSeleccionada();
	}
	
	private Color dameColorSeleccionado() {
		return conversacion.getMenuAtributos().getColorSeleccionado();
	}

}
