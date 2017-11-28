package com.federico.chat.servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class ClientesConectados implements Serializable{
	private static final long serialVersionUID = 1L;
	private Socket socket;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private String ip;
	private String nombreUsuario;
	
	
	public ClientesConectados(Socket socket, ObjectOutputStream salida, ObjectInputStream entrada,
			String ip, String nombreUsuario) {
		super();
		this.socket = socket;
		this.salida = salida;
		this.entrada = entrada;
		this.ip = ip;
		this.nombreUsuario = nombreUsuario;
	}


	public Socket getSocket() {
		return socket;
	}


	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public ObjectOutputStream getSalida() {
		return salida;
	}


	public void setSalida(ObjectOutputStream salida) {
		this.salida = salida;
	}


	public ObjectInputStream getEntrada() {
		return entrada;
	}


	public void setEntrada(ObjectInputStream entrada) {
		this.entrada = entrada;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
}
