package com.federico.chat.comandos;

import com.federico.chat.mensajeria.PaqueteConexion;

public class Conexion extends ComandoEscucha {
	
	@Override
	public void ejecutar() {
		PaqueteConexion paq = Comando.gson.fromJson(dameCadenaLeida(), PaqueteConexion.class);
		System.out.println(paq.getIp());
	}
	
}
