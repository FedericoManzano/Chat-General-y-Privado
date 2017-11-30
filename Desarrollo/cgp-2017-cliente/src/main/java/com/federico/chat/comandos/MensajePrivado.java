package com.federico.chat.comandos;

import com.federico.chat.chat.Chat;
import com.federico.chat.mensajeria.PaqueteMensaje;
import com.federico.chat.modelos.Conversacion;

public class MensajePrivado extends ComandoEscucha {
	
	@Override
	public void ejecutar() {
		PaqueteMensaje paqueteMensaje = Comando.gson.fromJson(dameCadenaLeida(), PaqueteMensaje.class);
		Conversacion conversacion = null;
		for(Conversacion con : Chat.listadoConectados) {
			if(con.getUsuarioExterno().equals(paqueteMensaje.getUsuarioEmisor())) {
				conversacion = con;
			}
		}
		if(conversacion != null) {
			getChat().notificar(paqueteMensaje, conversacion);
		}
	}
}
