package com.federico.chat.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Chat {
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Socket socket;
	private EscuchaMensajes escuchaMensajes;
	
	public Chat() {
		
	}
	
	
	public void iniciar() {
		try {
			socket = new Socket("192.168.1.34", 2000);
			entrada = new ObjectInputStream(socket.getInputStream());
			salida = new ObjectOutputStream(socket.getOutputStream());
			escuchaMensajes = new EscuchaMensajes(this);
			escuchaMensajes.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public ObjectInputStream getEntrada() {
		return entrada;
	}


	public void setEntrada(ObjectInputStream entrada) {
		this.entrada = entrada;
	}


	public ObjectOutputStream getSalida() {
		return salida;
	}


	public void setSalida(ObjectOutputStream salida) {
		this.salida = salida;
	}


	public Socket getSocket() {
		return socket;
	}


	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public static void main(String [] args) {
		Chat chat = new Chat();
		chat.iniciar();
	}
	
}
