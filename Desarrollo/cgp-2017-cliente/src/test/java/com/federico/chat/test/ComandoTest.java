package com.federico.chat.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.federico.chat.comandos.Comando;
import com.federico.chat.mensajeria.Paquete;
import com.federico.chat.mensajeria.PaqueteConexion;

public class ComandoTest {

	@Test
	public void probarComandos() {
		Paquete pa = new PaqueteConexion();
		Comando com = pa.devolverComando(Comando.CONEXION);
		com.ejecutar();
		
	}

}
