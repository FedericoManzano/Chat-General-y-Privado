package com.federico.chat.chat;

import java.awt.Color;
import java.awt.Font;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.federico.chat.eventos.EventoAbrirVentana;
import com.federico.chat.eventos.EventoConectar;
import com.federico.chat.eventos.EventoDesconexion;
import com.federico.chat.eventos.EventoMensajePublico;
import com.federico.chat.eventos.EventoRefrescarLista;
import com.federico.chat.mensajeria.PaqueteMensaje;
import com.federico.chat.menus.MenuAtributos;
import com.federico.chat.menus.MenuConexion;
import com.federico.chat.menus.MenuGeneral;
import com.federico.chat.modelos.Conversacion;
import com.federico.chat.modelos.Observable;
import com.federico.chat.modelos.Usuario;
import com.google.gson.Gson;

public class Chat extends Thread implements Observable<Conversacion ,PaqueteMensaje>{
	
	
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Socket socket;
	private EscuchaMensajes escuchaMensajes;
	public static LinkedList<Conversacion> listadoConectados = new LinkedList<>();
	private int puerto;
	private String servidor;
	private MenuConexion menuConexion;
	private Usuario usuario;
	private Gson gson = new Gson();
	private MenuGeneral menuGeneral;
	private Font fuenteSeleccionada;
	private Color colorSeleccionado;
	
	
	public Chat() {
		
		menuConexion = new MenuConexion();
		menuConexion.setVisible(true);
		menuConexion.getBtnConectarse().addActionListener(new EventoConectar(this));
		menuGeneral = new MenuGeneral();
		cargarValoresDefecto();
		menuGeneral.getBtnEnviar().addActionListener(new EventoMensajePublico(this));
		menuConexion.getPuertoText().addKeyListener(new EventoConectar(this));
		menuConexion.getUsuarioText().addKeyListener(new EventoConectar(this));
		menuGeneral.addWindowListener(new EventoDesconexion(this));
		menuGeneral.getBtnPrivado().addActionListener(new EventoAbrirVentana(this));
		menuGeneral.getBtnRefrescar().addActionListener(new EventoRefrescarLista(this));
		menuGeneral.getAreaMensaje().addKeyListener(new EventoMensajePublico(this));
		fuenteSeleccionada = new Font("Arial", Font.PLAIN, 11);
		colorSeleccionado = new Color(0, 0, 0);
		
	}

	public void run() {
		while(true) {
			escuchaMensajes.actualizarListado();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void cargarValoresDefecto() {
		menuConexion.getServidorText().setText("localhost");
		menuConexion.getPuertoText().setText("2000");
	}
	
	public void iniciar() {
		escuchaMensajes.start();
		menuConexion.conectado();
		this.start();
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

	public void configurarFormatoMensajes(MenuAtributos atributos) {
		setFuenteSeleccionada(atributos.getFuenteSeleccionada());
		setColorSeleccionado(atributos.getColorSeleccionado());
	}

	public static void main(String [] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Chat chat = new Chat();
	}


	public Gson getGson() {
		return gson;
	}


	public void setGson(Gson gson) {
		this.gson = gson;
	}


	public MenuGeneral getMenuGeneral() {
		return menuGeneral;
	}
	
	public LinkedList<Conversacion> dameListadoDeConversaciones(){
		return new LinkedList<>(listadoConectados);
	}

	@Override
	public void notificar(PaqueteMensaje p, Conversacion o) {
		o.update(p);
	}

	public Font getFuenteSeleccionada() {
		return fuenteSeleccionada;
	}

	public void setFuenteSeleccionada(Font fuenteSeleccionada) {
		this.fuenteSeleccionada = fuenteSeleccionada;
	}

	public Color getColorSeleccionado() {
		return colorSeleccionado;
	}

	public void setColorSeleccionado(Color colorSeleccionado) {
		this.colorSeleccionado = colorSeleccionado;
	}
}
