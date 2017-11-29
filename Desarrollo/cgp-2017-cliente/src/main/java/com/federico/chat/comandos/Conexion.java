package com.federico.chat.comandos;

import java.util.LinkedList;

import com.federico.chat.chat.Chat;
import com.federico.chat.mensajeria.PaqueteConectados;
import com.federico.chat.modelos.Conectado;

public class Conexion extends ComandoEscucha {
	
	@Override
	public void ejecutar() {
		PaqueteConectados paq = Comando.gson.fromJson(dameCadenaLeida(), PaqueteConectados.class);
		Chat.listadoConectados = new LinkedList<>(paq.getListadoConectados()) ;
		getChat().getMenuConexion().dispose();
		getChat().getMenuGeneral().setVisible(true);
		getChat().getMenuGeneral().vaciarListado();
		System.out.println(Chat.listadoConectados.size());
		for(Conectado con : Chat.listadoConectados) {
			getChat().getMenuGeneral().actualizarListaConectados(con.getUsuario().getNombreUsuario());
		}
		
	}
	
}
 