package com.federico.chat.servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.federico.chat.comandos.Comando;
import com.federico.chat.mensajeria.Paquete;
import com.federico.chat.mensajeria.PaqueteConexion;
import com.google.gson.Gson;

public class Servidor extends Thread{
	private ServerSocket servidor;
	private Gson gson = new Gson();
	public void run() {
		try {
			servidor = new ServerSocket(2000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				Socket soc = servidor.accept();
				String ip = soc.getInetAddress().getHostAddress();
				Paquete pa = new PaqueteConexion("",ip, Comando.CONEXION);
				ObjectOutputStream salida = new ObjectOutputStream(soc.getOutputStream());
				String envio = gson.toJson(pa);
				salida.writeObject(envio);
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
