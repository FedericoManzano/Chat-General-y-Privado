package com.federico.chat.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.federico.chat.comandos.Comando;
import com.federico.chat.mensajeria.Paquete;
import com.federico.chat.mensajeria.PaqueteConexion;

public class ComandoTest {

	@Test
	public void probarComandosConectar() {
		Paquete pa = new PaqueteConexion();
		Comando com = pa.devolverComando(Comando.CONEXION);
		com.ejecutar();
		
	}
	
	@Test
	public void probarComandosMensajePrivado() {
		Paquete pa = new PaqueteConexion();
		Comando com = pa.devolverComando(Comando.MENSAJE_PRIVADO);
		com.ejecutar();
	}

}
