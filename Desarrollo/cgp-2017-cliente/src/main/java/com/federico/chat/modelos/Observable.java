package com.federico.chat.modelos;

public interface Observable <T>{
	public void notificar(Observador obs, T obj);
}
