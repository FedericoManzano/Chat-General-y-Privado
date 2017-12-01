package com.federico.chat.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.federico.chat.menus.MenuConexion;

public class MenuConexionTest {

	@Test
	public void probarMenuConexion() {
		MenuConexion menu = new MenuConexion();
		menu.getPuertoText().setToolTipText("-1");
		menu.getServidorText().setText("sassasa");
		menu.getUsuarioText().setText("");
		assertEquals(0, menu.damePuerto());
		assertEquals("", menu.dameIp());
		assertEquals("", menu.dameusuario());
	}

}
