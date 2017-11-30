package com.federico.chat.comandos;

import java.io.IOException;

import com.federico.chat.mensajeria.PaqueteMensaje;
import com.federico.chat.servidor.EscuchaCliente;
import com.federico.chat.servidor.Servidor;

public class MensajePrivado extends ComandosServidor{

	@Override
	public void ejecutar() {
		PaqueteMensaje paqueteMensaje = Comando.gson.fromJson(dameCadenaLeida(), PaqueteMensaje.class);
		for(EscuchaCliente es : Servidor.listadoConectados) {
			if(es.getNombreUsuario().equals(paqueteMensaje.getUsuarioReceptor())) {
				String objetoEnvio = Comando.gson.toJson(paqueteMensaje);
				try {
					es.getSalida().writeObject(objetoEnvio);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
