package com.federico.chat.modelos;

import java.io.Serializable;

public class Conectado implements Obserbador, Serializable{

	private static final long serialVersionUID = 1L;
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
