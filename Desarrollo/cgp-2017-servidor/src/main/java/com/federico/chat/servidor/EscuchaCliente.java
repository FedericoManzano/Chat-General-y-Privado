package com.federico.chat.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import com.federico.chat.comandos.Comando;
import com.federico.chat.comandos.ComandosServidor;
import com.federico.chat.mensajeria.Paquete;
import com.federico.chat.mensajeria.PaqueteConexion;


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

	public synchronized void run() {
		ComandosServidor comando;
		Paquete paquete;
		String cadenaLeida = null;
		try {
			cadenaLeida = (String) entrada.readObject();
			while(!((paquete = Comando.gson.fromJson(cadenaLeida, Paquete.class)).dameOperacion() == Comando.DESCONEXION)) {
				paquete = Comando.gson.fromJson(cadenaLeida, Paquete.class);
				comando = (ComandosServidor)paquete.devolverComando(paquete.dameOperacion());
				comando.guardaCadenaLeida(cadenaLeida);
				comando.setEscuchaCliente(this);
				synchronized (Servidor.listadoConectados) {
					comando.ejecutar();
				}
				
				notifyAll();
				cadenaLeida = (String) entrada.readObject();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			salida.close();
			entrada.close();
			socket.close();
			
			
			PaqueteConexion pa = Comando.gson.fromJson(cadenaLeida, PaqueteConexion.class);
			LinkedList<EscuchaCliente> listadoCopia = Servidor.dameListadoConectados();
			for(EscuchaCliente es : listadoCopia) {
				if(es.getNombreUsuario().equals(pa.getNombreUsuario())) {
					Servidor.listadoConectados.remove(es);
					Servidor.menuServidor.mensaje(pa.getNombreUsuario() + " Desconectado del servidor ...");
				}
			}
			
			pa.guardaOperacion(Comando.BORRAR_USUARIO);
			String eliminado = Comando.gson.toJson(pa);
			
			for(EscuchaCliente es : Servidor.listadoConectados) {
				es.salida.writeObject(eliminado);
			}
			
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
