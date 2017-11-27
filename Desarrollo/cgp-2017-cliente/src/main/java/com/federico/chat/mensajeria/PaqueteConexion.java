package com.federico.chat.mensajeria;

import java.io.Serializable;

public class PaqueteConexion extends Paquete implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nombreUsuario;
	private String ip;
	
	
	public PaqueteConexion() {
		super(0);
	}
	public PaqueteConexion(String nombreUsuario, String ip, final int operacion) {
		super(operacion);
		this.nombreUsuario = nombreUsuario;
		this.ip = ip;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getIp() {
		return ip;
	}
}
