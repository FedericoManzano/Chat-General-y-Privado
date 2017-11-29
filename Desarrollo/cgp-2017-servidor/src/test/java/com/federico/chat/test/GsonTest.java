package com.federico.chat.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.federico.chat.comandos.Comando;
import com.federico.chat.mensajeria.Paquete;
import com.federico.chat.mensajeria.PaqueteConexion;

public class GsonTest {

	@Test
	public void test() {
		PaqueteConexion pa = new PaqueteConexion("fede", "192.168.1.34", 0);
		String cad = Comando.gson.toJson(pa);
		Paquete paq = Comando.gson.fromJson(cad, Paquete.class);
		System.out.println(cad);
		System.out.println(paq.dameOperacion());
	}

}
