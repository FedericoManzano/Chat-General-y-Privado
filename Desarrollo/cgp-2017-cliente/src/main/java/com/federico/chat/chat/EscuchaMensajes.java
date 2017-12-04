package com.federico.chat.chat;

import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.federico.chat.comandos.ComandoEscucha;
import com.federico.chat.mensajeria.Paquete;
import com.federico.chat.modelos.Conversacion;
import com.google.gson.Gson;

public class EscuchaMensajes extends Thread{
	
	private Chat chat;
	private Gson gson = new Gson();
	
	
	public EscuchaMensajes(Chat chat) {
		this.chat = chat;
	}
	
	
	public void run() {
		ComandoEscucha comando;
		Paquete paquete;
		String cadenaLeida;
		while(true) {
			
			try {
				cadenaLeida = (String) chat.getEntrada().readObject();
				paquete = gson.fromJson(cadenaLeida, Paquete.class);
				comando = (ComandoEscucha) paquete.devolverComando(paquete.dameOperacion());
				comando.guardaCadenaLeida(cadenaLeida);
				comando.setChat(chat);
				comando.ejecutar();
				actualizarListado();
			} catch (ClassNotFoundException | IOException e) {
				JOptionPane.showMessageDialog(chat.getMenuGeneral(), "Desconectado del servidor");
			}
		}
	}
	
	public void actualizarListado() {
		LinkedList<Conversacion> listado = chat.dameListadoDeConversaciones();
		chat.getMenuGeneral().vaciarListado();
		for(Conversacion co : listado)
			chat.getMenuGeneral().actualizarListaConectados(co.getUsuarioExterno());
		chat.getMenuGeneral().dameCantidadElementos();
	}
	
}
