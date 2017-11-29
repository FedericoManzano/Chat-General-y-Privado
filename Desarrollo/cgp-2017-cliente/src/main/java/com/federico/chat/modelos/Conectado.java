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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conectado other = (Conectado) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
 
	
	
}
