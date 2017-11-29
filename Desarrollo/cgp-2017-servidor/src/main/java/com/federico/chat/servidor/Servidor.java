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
	public static LinkedList<EscuchaCliente> listadoConectados = 
			new LinkedList<EscuchaCliente>();
	
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
				EscuchaCliente clienteConectados = new EscuchaCliente(soc, salida, entrada, "", "");
				listadoConectados.add(clienteConectados);
				clienteConectados.start();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
	}
	
	public static void main(String [] args) {
		Servidor se = new Servidor();
		se.start();
	}
	
	
}
