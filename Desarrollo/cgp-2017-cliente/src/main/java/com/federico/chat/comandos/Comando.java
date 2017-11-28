package com.federico.chat.comandos;

import com.google.gson.Gson;

public abstract class Comando {
	
	
	public static final String NOMBRE_PAQUETE = "com.federico.chat.comandos";
	public static final String [] NOMBRE_CLASES = {
			"Conexion",
			"MensajePrivado"
	};
	
	public static final int CONEXION = 0;
	public static final int MENSAJE_PRIVADO = 1;
	
	private String cadenaLeida;
	public static Gson gson = new Gson();
	
	public abstract void ejecutar();
	public Comando() {
		this.guardaCadenaLeida("");
	}
	
	public String dameCadenaLeida() {
		return cadenaLeida;
	}
	
	public void guardaCadenaLeida(final String cadenaLeida) {
		this.cadenaLeida = cadenaLeida;
	}
}
