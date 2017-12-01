package com.federico.chat.comandos;

import java.io.IOException;
import java.util.LinkedList;

import com.federico.chat.mensajeria.PaqueteMensaje;
import com.federico.chat.servidor.EscuchaCliente;
import com.federico.chat.servidor.Servidor;

public class MensajePublico extends ComandosServidor{

	@Override
	public synchronized void ejecutar() {
		PaqueteMensaje paqueteMensaje = Comando.gson.fromJson(dameCadenaLeida(), PaqueteMensaje.class);
		String usuario = paqueteMensaje.getUsuarioEmisor();
		LinkedList<EscuchaCliente> listadoCopia = Servidor.dameListadoConectados();
		for(EscuchaCliente es : listadoCopia) {
			if(!es.getNombreUsuario().equals(usuario)) {
				try 
				{
					es.getSalida().writeObject(dameCadenaLeida());
					Servidor.menuServidor.mensaje(usuario + " Envio un mensaje publico ...");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
