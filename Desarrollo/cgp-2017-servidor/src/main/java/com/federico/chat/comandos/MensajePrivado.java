package com.federico.chat.comandos;

import java.io.IOException;
import java.util.LinkedList;

import com.federico.chat.mensajeria.PaqueteMensaje;
import com.federico.chat.servidor.EscuchaCliente;
import com.federico.chat.servidor.Servidor;

public class MensajePrivado extends ComandosServidor{

	@Override
	public synchronized void ejecutar() {
		PaqueteMensaje paqueteMensaje = Comando.gson.fromJson(dameCadenaLeida(), PaqueteMensaje.class);
		LinkedList<EscuchaCliente> listadoCopia = Servidor.dameListadoConectados();
		for(EscuchaCliente es : listadoCopia) {
			if(es.getNombreUsuario().equals(paqueteMensaje.getUsuarioReceptor())) {
				String objetoEnvio = Comando.gson.toJson(paqueteMensaje);
				try {
					es.getSalida().writeObject(objetoEnvio);
					Servidor.menuServidor.mensaje(paqueteMensaje.getUsuarioEmisor() + 
							" Envio un mensaje privado a " + paqueteMensaje.getUsuarioReceptor());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
