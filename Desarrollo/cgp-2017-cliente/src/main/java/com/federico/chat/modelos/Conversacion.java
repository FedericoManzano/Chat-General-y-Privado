package com.federico.chat.modelos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.federico.chat.chat.Chat;
import com.federico.chat.eventos.EventoMensajePrivado;
import com.federico.chat.mensajeria.PaqueteMensaje;
import com.federico.chat.menus.MenuPrivado;

public class Conversacion implements Observador<PaqueteMensaje>{
	
	private Conectado conectado;
	private MenuPrivado menuPrivado;
	private String usuarioExterno;
	private String usuarioInterno;

	public Conversacion(Conectado conectado, MenuPrivado menuPrivado, String usuarioInterno, Chat chat) {
		this.conectado = conectado;
		this.menuPrivado = menuPrivado;
		this.usuarioExterno = conectado.getUsuario().getNombreUsuario();
		this.usuarioInterno = usuarioInterno;
		menuPrivado.setUsuario(this.usuarioExterno);
		menuPrivado.getBtnEnviar().addActionListener(new EventoMensajePrivado(chat, this));
	}
	
	
	
	public Conectado getConectado() {
		return conectado;
	}



	public void setConectado(Conectado conectado) {
		this.conectado = conectado;
	}



	public MenuPrivado getMenuPrivado() {
		return menuPrivado;
	}



	public void setMenuPrivado(MenuPrivado menuPrivado) {
		this.menuPrivado = menuPrivado;
	}



	public String getUsuarioExterno() {
		return usuarioExterno;
	}



	public void setUsuarioExterno(String usuarioExterno) {
		this.usuarioExterno = usuarioExterno;
	}



	public String getUsuarioInterno() {
		return usuarioInterno;
	}



	public void setUsuarioInterno(String usuarioInterno) {
		this.usuarioInterno = usuarioInterno;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conversacion other = (Conversacion) obj;
		if (conectado == null) {
			if (other.conectado != null)
				return false;
		} else if (!conectado.equals(other.conectado))
			return false;
		return true;
	}

	@Override
	public void update(PaqueteMensaje p) {
		if(!menuPrivado.isVisible()) {
			menuPrivado.setExtendedState(JFrame.ICONIFIED);
			menuPrivado.setVisible(true);
		}
		menuPrivado.getAreaConversacion().append(p.getUsuarioEmisor() + ": " + p.getMensaje() + "\n");
	}

}
