package com.federico.chat.mensajeria;

import java.io.Serializable;

import com.federico.chat.comandos.Comando;

public abstract class Paquete implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int operacion;

	public Paquete(final int op) {
		this.operacion = op;
	}
	
	public int dameOperacion() {
		return operacion;
	}

	public void guardaOperacion(int operacion) {
		this.operacion = operacion;
	}
	
	public Comando devolverComando(final int comando) {
		Comando com = null;
		try {
			com = (Comando) Class.forName(
					Comando.NOMBRE_PAQUETE + "." + 
			Comando.NOMBRE_CLASES[comando]).newInstance();
		} catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
		} 
		return com;
	}
}
