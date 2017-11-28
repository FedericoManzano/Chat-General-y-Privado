package com.federico.chat.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import com.federico.chat.eventos.EventoConectar;
import com.federico.chat.menus.MenuConexion;
import com.federico.chat.modelos.Conectado;
import com.federico.chat.modelos.Usuario;

public class Chat {
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Socket socket;
	private EscuchaMensajes escuchaMensajes;
	public static LinkedList<Conectado> listadoConectados = new LinkedList<>();
	private int puerto;
	private String servidor;
	private MenuConexion menuConexion;
	private Usuario usuario;
	
	
	public Chat() {
		menuConexion = new MenuConexion();
		menuConexion.setVisible(true);
		menuConexion.getBtnConectarse().addActionListener(new EventoConectar(this));
	}
	
	
	public void iniciar() {
		escuchaMensajes.start();
		menuConexion.conectado();
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
	
	


	public EscuchaMensajes getEscuchaMensajes() {
		return escuchaMensajes;
	}


	public void setEscuchaMensajes(EscuchaMensajes escuchaMensajes) {
		this.escuchaMensajes = escuchaMensajes;
	}


	public int getPuerto() {
		return puerto;
	}


	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}


	public String getServidor() {
		return servidor;
	}


	public void setServidor(String servidor) {
		this.servidor = servidor;
	}


	public MenuConexion getMenuConexion() {
		return menuConexion;
	}


	public void setMenuConexion(MenuConexion menuConexion) {
		this.menuConexion = menuConexion;
	}

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public static void main(String [] args) {
		Chat chat = new Chat();
	}
	
}
