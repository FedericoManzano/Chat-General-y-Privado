package com.federico.chat.modelos;

public class Conectado implements Obserbador{

	
	private Usuario usuario;
	
	
	public Conectado(Usuario usuario) {
		this.usuario = usuario;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public void update() {
		
	}

}
