package com.federico.chat.mensajeria;

import java.io.Serializable;

public class PaqueteMensaje extends Paquete implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String usuarioEmisor;
	private String usuarioReceptor;
	private String mensaje;
	private String nombreFuente;
	private int tamFuente;
	private int tipoFuente;
	private int rgb;

	
	
	
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

	public String getNombreFuente() {
		return nombreFuente;
	}

	public void setNombreFuente(String nombreFuente) {
		this.nombreFuente = nombreFuente;
	}

	public int getTamFuente() {
		return tamFuente;
	}

	public void setTamFuente(int tamFuente) {
		this.tamFuente = tamFuente;
	}

	public int getTipoFuente() {
		return tipoFuente;
	}

	public void setTipoFuente(int tipoFuente) {
		this.tipoFuente = tipoFuente;
	}

	public int getRgb() {
		return rgb;
	}

	public void setRgb(int rgb) {
		this.rgb = rgb;
	}
}
