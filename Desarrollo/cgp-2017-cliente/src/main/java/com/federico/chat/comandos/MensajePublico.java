package com.federico.chat.comandos;

import javax.swing.JOptionPane;

import com.federico.chat.mensajeria.PaqueteMensaje;

public class MensajePublico extends ComandoEscucha{

	@Override
	public void ejecutar() {
		PaqueteMensaje paqueteMensaje = gson.fromJson(dameCadenaLeida(), PaqueteMensaje.class);
		getChat().getMenuGeneral().getAreaConversacion().append(paqueteMensaje.getUsuarioEmisor() + ": " + 
				paqueteMensaje.getMensaje() + "\n");
	}

}
