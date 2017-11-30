package com.federico.chat.modelos;

public interface Observable <T, E>{
	public void notificar(E p, T o);
}
