package com.federico.chat.mensajeria;

import java.io.Serializable;

public abstract class Paquete implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int operacion;

	public int dameOperacion() {
		return operacion;
	}

	public void guardaOperacion(int operacion) {
		this.operacion = operacion;
	}
	
}
