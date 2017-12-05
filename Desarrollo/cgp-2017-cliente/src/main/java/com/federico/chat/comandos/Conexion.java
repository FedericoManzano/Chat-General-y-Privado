package com.federico.chat.comandos;

import java.util.LinkedList;

import com.federico.chat.chat.Chat;
import com.federico.chat.mensajeria.PaqueteConectados;
import com.federico.chat.menus.MenuPrivado;
import com.federico.chat.modelos.Conectado;
import com.federico.chat.modelos.Conversacion;

public class Conexion extends ComandoEscucha {
	
	@Override
	public void ejecutar() {
		PaqueteConectados paq = Comando.gson.fromJson(dameCadenaLeida(), PaqueteConectados.class);
		cargarListaConectados(paq.getListadoConectados());
		getChat().getMenuConexion().dispose();	
		getChat().getEscuchaMensajes().actualizarListado();
		getChat().getMenuGeneral().configurarTitulo(getChat().getMenuGeneral().getTitle() + 
				" | " + getChat().getUsuario().getNombreUsuario() + "  " + "Bienvenido");
		getChat().getMenuGeneral().setVisible(true);
	}
	
	private void cargarListaConectados(LinkedList<Conectado> listado) {
		for(Conectado con : listado) {
			Chat.listadoConectados.add(
					new Conversacion(
							con, 
							new MenuPrivado(), 
							getChat().getUsuario().getNombreUsuario(), 
							getChat())
					);
		}
	}
	
}
 