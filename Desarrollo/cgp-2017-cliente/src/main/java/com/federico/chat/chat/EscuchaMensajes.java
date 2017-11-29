package com.federico.chat.chat;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.federico.chat.comandos.ComandoEscucha;
import com.federico.chat.mensajeria.Paquete;
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
			} catch (ClassNotFoundException | IOException e) {
				JOptionPane.showMessageDialog(chat.getMenuGeneral(), "Desconectado del servidor");
			}
		}
	}
}
