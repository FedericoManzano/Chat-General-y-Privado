package com.federico.chat.comandos;

import com.federico.chat.chat.Chat;
import com.federico.chat.mensajeria.PaqueteConexion;
import com.federico.chat.modelos.Conectado;
import com.federico.chat.modelos.Usuario;

public class ActualizarConectados extends ComandoEscucha {

	@Override
	public void ejecutar() {
		PaqueteConexion paqueteConexion = Comando.gson.fromJson(dameCadenaLeida(), PaqueteConexion.class);
		Conectado con = new Conectado(new Usuario(paqueteConexion.getNombreUsuario(), paqueteConexion.getIp()));
		if(paqueteConexion.dameOperacion() == Comando.AGREGAR_USUARIO) {
			Chat.listadoConectados.add(con);
		}else {
			Chat.listadoConectados.remove(con);
		}
		
		getChat().getMenuGeneral().vaciarListado();
		for(Conectado co : Chat.listadoConectados) {
			getChat().getMenuGeneral().actualizarListaConectados(co.getUsuario().getNombreUsuario());
		}
	}

}
