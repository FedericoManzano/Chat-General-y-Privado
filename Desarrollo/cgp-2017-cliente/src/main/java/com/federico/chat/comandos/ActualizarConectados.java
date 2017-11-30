package com.federico.chat.comandos;

import com.federico.chat.chat.Chat;
import com.federico.chat.mensajeria.PaqueteConexion;
import com.federico.chat.menus.MenuPrivado;
import com.federico.chat.modelos.Conectado;
import com.federico.chat.modelos.Conversacion;
import com.federico.chat.modelos.Usuario;

public class ActualizarConectados extends ComandoEscucha {

	@Override
	public void ejecutar() {
		PaqueteConexion paqueteConexion = Comando.gson.fromJson(dameCadenaLeida(), PaqueteConexion.class);
		Conectado con = new Conectado(new Usuario(paqueteConexion.getNombreUsuario(), paqueteConexion.getIp()));
		Conversacion conve = new Conversacion(con, new MenuPrivado(), getChat().getUsuario().getNombreUsuario());
		if(paqueteConexion.dameOperacion() == Comando.AGREGAR_USUARIO) {
			Chat.listadoConectados.add(conve);
		}else {
			Chat.listadoConectados.remove(conve);
		}
		
		getChat().getMenuGeneral().vaciarListado();
		for(Conversacion co : Chat.listadoConectados) {
			getChat().getMenuGeneral().actualizarListaConectados(co.getUsuarioExterno());
		}
	}

}
