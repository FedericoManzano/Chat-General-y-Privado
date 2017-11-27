package com.federico.chat.modelos;


public class Usuario{
	private String nombreUsuario;
	private String ip;
	
	
	public Usuario(String nombreUsuario, String ip) {
		this.nombreUsuario = nombreUsuario;
		this.ip = ip;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}
}
