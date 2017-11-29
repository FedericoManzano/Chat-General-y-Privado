package com.federico.chat.comandos;

import java.io.IOException;
import java.util.LinkedList;

import com.federico.chat.mensajeria.PaqueteConectados;
import com.federico.chat.mensajeria.PaqueteConexion;
import com.federico.chat.modelos.Conectado;
import com.federico.chat.modelos.Usuario;
import com.federico.chat.servidor.EscuchaCliente;
import com.federico.chat.servidor.Servidor;

public class Conexion extends ComandosServidor{

	@Override
	public void ejecutar() {

		PaqueteConexion pa = Comando.gson.fromJson(dameCadenaLeida(), PaqueteConexion.class);
		getEscuchaCliente().setNombreUsuario(pa.getNombreUsuario());
		getEscuchaCliente().setIp(pa.getIp());
		PaqueteConectados paqueteConectados = new PaqueteConectados();
		paqueteConectados.setListadoConectados(generarListadoConectado());
		String cadenaEnviar = gson.toJson(paqueteConectados);
		try 
		{
			for(EscuchaCliente es : Servidor.listadoConectados) {
				es.getSalida().writeObject(cadenaEnviar);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private LinkedList<Conectado> generarListadoConectado(){
		LinkedList<Conectado> listadoAEnviar = new LinkedList<Conectado>();
		for(EscuchaCliente cliente : Servidor.listadoConectados) {
			listadoAEnviar.add(new Conectado(new Usuario(cliente.getNombreUsuario(), cliente.getIp())));
		}
		return listadoAEnviar;
	}

}
