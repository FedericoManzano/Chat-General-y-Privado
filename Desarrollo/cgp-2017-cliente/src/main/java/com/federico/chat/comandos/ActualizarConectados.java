package com.federico.chat.comandos;

import java.util.LinkedList;

import com.federico.chat.chat.Chat;
import com.federico.chat.mensajeria.PaqueteConexion;
import com.federico.chat.menus.MenuPrivado;
import com.federico.chat.modelos.Conectado;
import com.federico.chat.modelos.Conversacion;
import com.federico.chat.modelos.Usuario;

public class ActualizarConectados extends ComandoEscucha {

	@Override
	public void ejecutar() {
		PaqueteConexion paqueteConexion =
				Comando.gson.fromJson(dameCadenaLeida(), PaqueteConexion.class);
		Conectado con = new Conectado(
				new Usuario(paqueteConexion.getNombreUsuario(), paqueteConexion.getIp()));
		Conversacion conve = new Conversacion(
				con, new MenuPrivado(), getChat().getUsuario().getNombreUsuario(), getChat());
		LinkedList<Conversacion> listadoCopia = getChat().dameListadoDeConversaciones();

		if(paqueteConexion.dameOperacion() == Comando.AGREGAR_USUARIO) {
			Chat.listadoConectados.add(conve);
		}else {
			int indice = listadoCopia.indexOf(conve);
			if(indice != -1) {
				listadoCopia.get(indice).getMenuPrivado().cambiarEstadoConexion();
			}
			Chat.listadoConectados.remove(conve);
		}
		getChat().getEscuchaMensajes().actualizarListado();
	}
}
