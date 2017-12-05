package com.federico.chat.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.federico.chat.chat.Chat;
import com.federico.chat.comandos.Comando;
import com.federico.chat.mensajeria.PaqueteConexion;

public class EventoDesconexion extends WindowAdapter implements ActionListener{
	private Chat chat;
	
	public EventoDesconexion(Chat chat) {
		this.chat = chat;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {		
		cerrarSesion();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int opcion = JOptionPane.showConfirmDialog(chat.getMenuGeneral(), 
				"Esta seguro que quiere cerrar sesion ?", "Cerrar Sesion", 
				JOptionPane.YES_NO_CANCEL_OPTION);
		if(opcion == JOptionPane.YES_OPTION) {
			cerrarSesion();
		}
	}
	
	private void cerrarSesion() {
		PaqueteConexion paqueteEnvio = new PaqueteConexion(chat.getUsuario().getNombreUsuario(),
				chat.getUsuario().getIp(), Comando.DESCONEXION);
		String objetoEnvio = Comando.gson.toJson(paqueteEnvio);
		try {
			chat.getSalida().writeObject(objetoEnvio);
			System.exit(0);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
