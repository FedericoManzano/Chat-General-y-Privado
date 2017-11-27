package com.federico.chat.comandos;

import com.federico.chat.chat.Chat;

public abstract class ComandoEscucha extends Comando{
	private Chat chat;

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}
}
