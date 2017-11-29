package com.federico.chat.comandos;

import com.federico.chat.chat.Chat;
import com.federico.chat.mensajeria.PaqueteConectados;
import com.federico.chat.modelos.Conectado;

public class Conexion extends ComandoEscucha {
	
	@Override
	public void ejecutar() {
		PaqueteConectados paq = Comando.gson.fromJson(dameCadenaLeida(), PaqueteConectados.class);
		Chat.listadoConectados = paq.getListadoConectados();
		getChat().getMenuConexion().dispose();
		getChat().getMenuGeneral().setVisible(true);
		for(Conectado con : Chat.listadoConectados) {
			getChat().getMenuGeneral().actualizarListaConectados(con.getUsuario().getNombreUsuario());
		}
		
	}
	
}
 