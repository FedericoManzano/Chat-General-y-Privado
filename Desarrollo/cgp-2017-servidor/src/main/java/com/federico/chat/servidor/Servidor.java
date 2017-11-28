package com.federico.chat.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import com.federico.chat.comandos.Comando;
import com.federico.chat.mensajeria.Paquete;
import com.federico.chat.mensajeria.PaqueteConectados;
import com.federico.chat.mensajeria.PaqueteConexion;
import com.federico.chat.modelos.Conectado;
import com.federico.chat.modelos.Usuario;
import com.google.gson.Gson;

public class Servidor extends Thread{
	private ServerSocket servidor;
	private Gson gson = new Gson();
	public static LinkedList<ClientesConectados> listadoConectados = 
			new LinkedList<ClientesConectados>();
	public void run() {
		try {
			servidor = new ServerSocket(2000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				Socket soc = servidor.accept();
				ObjectOutputStream salida = new ObjectOutputStream(soc.getOutputStream());
				ObjectInputStream entrada = new ObjectInputStream(soc.getInputStream());
				String cadenaLeida = (String) entrada.readObject();
				PaqueteConexion paquete = gson.fromJson(cadenaLeida, PaqueteConexion.class);
				String ip = paquete.getIp();
				String usuario = paquete.getNombreUsuario();
				ClientesConectados clienteConectados = 
						new ClientesConectados(soc, salida, entrada, ip, usuario);
				listadoConectados.add(clienteConectados);
				PaqueteConectados paqueteConectados = new PaqueteConectados();
				paqueteConectados.setListadoConectados(generarListadoConectado());
				String cadenaEnviar = gson.toJson(paqueteConectados);
				salida.writeObject(cadenaEnviar);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private LinkedList<Conectado> generarListadoConectado(){
		LinkedList<Conectado> listadoAEnviar = new LinkedList<Conectado>();
		for(ClientesConectados cliente:listadoConectados) {
			listadoAEnviar.add(new Conectado(new Usuario(cliente.getNombreUsuario(), cliente.getIp())));
		}
		
		return listadoAEnviar;
	}
	
	public static void main(String [] args) {
		Servidor se = new Servidor();
		se.start();
	}
	
	
}
