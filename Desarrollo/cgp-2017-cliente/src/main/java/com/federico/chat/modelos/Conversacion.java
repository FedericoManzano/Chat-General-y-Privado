package com.federico.chat.modelos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

import com.federico.chat.chat.Chat;
import com.federico.chat.eventos.EventoMensajePrivado;
import com.federico.chat.mensajeria.PaqueteMensaje;
import com.federico.chat.menus.MenuAtributos;
import com.federico.chat.menus.MenuPrivado;

public class Conversacion extends KeyAdapter implements Observador<PaqueteMensaje>, ActionListener{
	
	private Conectado conectado;
	private MenuPrivado menuPrivado;
	private String usuarioExterno;
	private String usuarioInterno;
	private MenuAtributos menuAtributos;
	private Chat chat;
	
	
	public Conversacion(Conectado conectado, MenuPrivado menuPrivado, 
			String usuarioInterno, Chat chat) {
		this.conectado = conectado;
		this.menuPrivado = menuPrivado;
		this.usuarioExterno = conectado.getUsuario().getNombreUsuario();
		this.usuarioInterno = usuarioInterno;
		this.chat = chat;
		menuPrivado.setUsuario(this.usuarioExterno);
		menuPrivado.getBtnEnviar().addActionListener(new EventoMensajePrivado(chat, this));
		menuAtributos = new MenuAtributos();
		menuPrivado.getAreaMensaje().setFont(chat.getFuenteSeleccionada());
		menuPrivado.getAreaMensaje().setForeground(chat.getColorSeleccionado());
		menuPrivado.getLblFuente().addMouseListener(new ManejadorMouse());
		menuPrivado.getAreaMensaje().addKeyListener(this);
		menuPrivado.addWindowListener(new ManejadorVentana());
		menuAtributos.getBtnAplicar().addActionListener(this);
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getSource() == menuPrivado.getAreaMensaje()) {
			menuPrivado.getAreaMensaje().setFont(chat.getFuenteSeleccionada());
			menuPrivado.getAreaMensaje().setForeground(chat.getColorSeleccionado());
			Rectangle rec = new Rectangle(
					menuPrivado.getAreaMensaje().getWidth(), 
					menuPrivado.getAreaMensaje().getHeight(), 
					menuPrivado.getAreaMensaje().getX() + 50, 
					menuPrivado.getAreaMensaje().getY());
			
			int val = menuPrivado.getAreaMensaje().getScrollableUnitIncrement(
					rec, SwingConstants.HORIZONTAL, 1);
			

			if(val < 5) {
				menuPrivado.getAreaMensaje().append(chat.getFuenteSeleccionada(), 
						chat.getColorSeleccionado(), "\n");
				menuPrivado.getScrollPane_1().getHorizontalScrollBar().setValue(
						menuPrivado.getScrollPane_1().getHorizontalScrollBar().getMinimum());
			}
		}
	}
	
	public class ManejadorMouse extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			menuAtributos.setVisible(true);
		}
	}
	
	public class ManejadorVentana extends WindowAdapter {
		@Override
		public void windowOpened(WindowEvent e) {
			menuPrivado.getAreaMensaje().setFont(chat.getFuenteSeleccionada());
			menuPrivado.getAreaMensaje().setForeground(chat.getColorSeleccionado());
			menuPrivado.getAreaMensaje().requestFocus();
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			menuPrivado.getAreaMensaje().requestFocus();
			menuPrivado.dispose();
		}
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
		menuPrivado.aņadirMensaje(new Font("Arial Black",Font.ITALIC, 15), 
				new Color(0,0,0), p.getUsuarioEmisor() + ": \n");
		menuPrivado.aņadirMensaje(dameFuente(p), dameColor(p), p.getMensaje() + "\n");
		menuPrivado.getScrollPane().getVerticalScrollBar().setValue(
				menuPrivado.getScrollPane().getVerticalScrollBar().getMaximum());

	}

	private Font dameFuente(PaqueteMensaje p) {
		return new Font(p.getNombreFuente(), p.getTipoFuente(), p.getTamFuente());
	}
	
	private Color dameColor(PaqueteMensaje p) {
		return new Color(p.getRgb());
	}
	
	public MenuAtributos getMenuAtributos() {
		return menuAtributos;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		int tipoLetra = Font.PLAIN;
		switch(menuAtributos.getTipoLetraCombo().getSelectedItem().toString()) {
		case "Plain": tipoLetra = Font.PLAIN;
			break;
		case "Bold": tipoLetra = Font.BOLD;
			break;
		case "Italic": tipoLetra = Font.ITALIC;
		}
		menuAtributos.setFuenteSeleccionada(new Font(menuAtributos.getListadoFuentes().getSelectedValue(), tipoLetra, menuAtributos.getListadoTam().getSelectedValue()));
		menuAtributos.setColorSeleccionado(menuAtributos.getColorChooser().getColor());
		menuAtributos.dispose();
		chat.configurarFormatoMensajes(menuAtributos);
		menuPrivado.configurarTipoTexto(chat);
		
	}
}
