package com.federico.chat.mensajeria;

import java.io.Serializable;
import java.util.LinkedList;

import com.federico.chat.modelos.Conectado;

public class PaqueteConectados extends Paquete implements Serializable{

	private static final long serialVersionUID = 1L;
	private LinkedList<Conectado> listadoConectados;
	
	public PaqueteConectados() {
		listadoConectados = new LinkedList<>();
	}

	public LinkedList<Conectado> getListadoConectados() {
		return new LinkedList<>(listadoConectados);
	}
 
	public void setListadoConectados(LinkedList<Conectado> listadoConectados) {
		this.listadoConectados = new LinkedList<>(listadoConectados);
	}
}
