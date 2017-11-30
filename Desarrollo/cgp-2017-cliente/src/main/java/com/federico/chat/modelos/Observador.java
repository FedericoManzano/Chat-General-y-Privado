package com.federico.chat.modelos;

public interface Observador <T,P>{
	public T getMenuPrivado();
	public void update(P p);
}
