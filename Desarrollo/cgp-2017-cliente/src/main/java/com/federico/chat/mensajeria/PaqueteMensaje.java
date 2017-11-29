package com.federico.chat.mensajeria;

import java.io.Serializable;

public class PaqueteMensaje extends Paquete implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String usuarioEmisor;
	private String usuarioReceptor;
	private String mensaje;
	
	public PaqueteMensaje(String usuarioEmisor, String usuarioReceptor, String mensaje, int operacion) {
		super(operacion);
		this.usuarioEmisor = usuarioEmisor;
		this.usuarioReceptor = usuarioReceptor;
		this.mensaje = mensaje;
	}

	public String getUsuarioEmisor() {
		return usuarioEmisor;
	}

	public String getUsuarioReceptor() {
		return usuarioReceptor;
	}

	public String getMensaje() {
		return mensaje;
	}
}
