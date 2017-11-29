package com.federico.chat.comandos;

import com.federico.chat.servidor.EscuchaCliente;

public abstract class ComandosServidor extends Comando {
	protected EscuchaCliente escuchaCliente;

	public EscuchaCliente getEscuchaCliente() {
		return escuchaCliente;
	}

	public void setEscuchaCliente(EscuchaCliente escuchaCliente) {
		this.escuchaCliente = escuchaCliente;
	}
}
