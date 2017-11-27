package com.federico.chat.mensajeria;

import java.io.Serializable;

import com.federico.chat.comandos.Comando;

public abstract class Paquete implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int operacion;

	public int dameOperacion() {
		return operacion;
	}

	public void guardaOperacion(int operacion) {
		this.operacion = operacion;
	}
	
	public Paquete devolverPaquete(final int comando) {
		Paquete paq = null;
		try {
			paq = (Paquete) Class.forName(
					Comando.NOMBRE_PAQUETE + "." + Comando.NOMBRE_CLASES[comando]).newInstance();
		} catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
		} 
		return paq;
	}
}
