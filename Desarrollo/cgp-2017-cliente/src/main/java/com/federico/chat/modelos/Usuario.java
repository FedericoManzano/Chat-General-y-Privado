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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		return true;
	}
}
