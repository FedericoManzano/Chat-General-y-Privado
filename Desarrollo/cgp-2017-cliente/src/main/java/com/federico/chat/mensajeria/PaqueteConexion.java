package com.federico.chat.mensajeria;

import java.io.Serializable;

public class PaqueteConexion extends Paquete implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	private String nombreUsuario;
	private String ip;
	
	public PaqueteConexion() {
		
	}
	
	public PaqueteConexion(String nombreUsuario, String ip, final int operacion) {
		guardaOperacion(operacion);
		this.nombreUsuario = nombreUsuario;
		this.ip = ip;
	} 

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getIp() {
		return ip;
	}
	
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}
