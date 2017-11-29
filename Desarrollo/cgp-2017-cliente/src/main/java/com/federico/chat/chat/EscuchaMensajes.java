package com.federico.chat.chat;

import java.io.IOException;

import com.federico.chat.comandos.ComandoEscucha;
import com.federico.chat.mensajeria.Paquete;
import com.federico.chat.mensajeria.PaqueteConexion;
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
		
		while(true) {
			String cadenaLeida;
			try {
				cadenaLeida = (String) chat.getEntrada().readObject();
				System.out.println(cadenaLeida);
				paquete = gson.fromJson(cadenaLeida, Paquete.class);
				comando = (ComandoEscucha) paquete.devolverComando(paquete.dameOperacion());
				comando.guardaCadenaLeida(cadenaLeida);
				comando.setChat(chat);
				comando.ejecutar();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			 
		}
		
	}
	
	
}
