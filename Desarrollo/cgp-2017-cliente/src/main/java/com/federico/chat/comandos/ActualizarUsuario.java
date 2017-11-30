package com.federico.chat.comandos;

import com.federico.chat.mensajeria.PaqueteConexion;

public class ActualizarUsuario extends ComandoEscucha{

	@Override
	public void ejecutar() {
		PaqueteConexion pa = Comando.gson.fromJson(dameCadenaLeida(), PaqueteConexion.class);
		getChat().getUsuario().setNombreUsuario(pa.getNombreUsuario());
	}
}
