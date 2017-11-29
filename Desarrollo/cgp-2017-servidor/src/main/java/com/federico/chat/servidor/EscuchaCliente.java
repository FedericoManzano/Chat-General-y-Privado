package com.federico.chat.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.federico.chat.comandos.Comando;
import com.federico.chat.comandos.ComandosServidor;
import com.federico.chat.mensajeria.Paquete;
import com.federico.chat.mensajeria.PaqueteConexion;
import com.google.gson.Gson;

public class EscuchaCliente extends Thread{

	private Socket socket;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private String ip;
	private String nombreUsuario;
	
	public EscuchaCliente(Socket socket, ObjectOutputStream salida, ObjectInputStream entrada, String ip,
			String nombreUsuario) {
		this.socket = socket;
		this.salida = salida;
		this.entrada = entrada;
		this.ip = ip;
		this.nombreUsuario = nombreUsuario;
	}

	public void run() {
		ComandosServidor comando;
		Paquete paquete;
		
		try {
			String cadenaLeida = (String) entrada.readObject();
			while(!((paquete = Comando.gson.fromJson(cadenaLeida, Paquete.class)).dameOperacion() == 4)) {
				paquete = Comando.gson.fromJson(cadenaLeida, Paquete.class);
				comando = (ComandosServidor)paquete.devolverComando(paquete.dameOperacion());
				comando.guardaCadenaLeida(cadenaLeida);
				comando.setEscuchaCliente(this);
				comando.ejecutar();
				cadenaLeida = (String) entrada.readObject();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
