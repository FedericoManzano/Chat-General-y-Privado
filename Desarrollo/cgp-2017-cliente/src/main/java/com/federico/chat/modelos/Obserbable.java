package com.federico.chat.modelos;

public interface Obserbable {
	public void notificarEmision(Obserbador obs);
	public void notificarRecepcion(Obserbador obs);
}
