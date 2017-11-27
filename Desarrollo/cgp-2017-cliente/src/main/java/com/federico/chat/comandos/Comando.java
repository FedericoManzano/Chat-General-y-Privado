package com.federico.chat.comandos;

public abstract class Comando {
	
	
	public static final String NOMBRE_PAQUETE = "com.federico.chat";
	public static final String [] NOMBRE_CLASES = {
			"Conexion"
	};
	
	
	public static final int CONEXION = 0;
	private String cadenaLeida;
	
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
