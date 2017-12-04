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
	public synchronized void ejecutar() {
		LinkedList<EscuchaCliente> listadoCopia = Servidor.dameListadoConectados();
		PaqueteConexion pa = Comando.gson.fromJson(dameCadenaLeida(), PaqueteConexion.class);
		estaRepetido(pa, listadoCopia);
		getEscuchaCliente().setNombreUsuario(pa.getNombreUsuario());
		getEscuchaCliente().setIp(pa.getIp());
		
		pa.guardaOperacion(Comando.AGREGAR_USUARIO);
		Servidor.menuServidor.mensaje(pa.getNombreUsuario() + " Conectado al servidor ...");
		String paqueteEnviar = Comando.gson.toJson(pa);
		
		PaqueteConectados paqueteConectados = new PaqueteConectados();
		paqueteConectados.setListadoConectados(generarListadoConectado(listadoCopia));
		paqueteConectados.guardaOperacion(Comando.CONEXION);
		String cadenaEnviar = gson.toJson(paqueteConectados);
		
		try 
		{
			for(EscuchaCliente es : listadoCopia) {
				if(!es.getNombreUsuario().equals(pa.getNombreUsuario()))
					es.getSalida().writeObject(paqueteEnviar);
				else {
					pa.guardaOperacion(Comando.ACTUALIZAR_USUARIO);
					String objetoUsuario = Comando.gson.toJson(pa);
					es.getSalida().writeObject(objetoUsuario);
					es.getSalida().writeObject(cadenaEnviar);
				}
					
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private LinkedList<Conectado> generarListadoConectado(LinkedList<EscuchaCliente> listadoCopia){
		LinkedList<Conectado> listadoAEnviar = new LinkedList<Conectado>();
		for(EscuchaCliente cliente : listadoCopia) {
			listadoAEnviar.add(new Conectado(new Usuario(cliente.getNombreUsuario(), cliente.getIp())));
		}
		return listadoAEnviar;
	}
	
	private void estaRepetido(PaqueteConexion paqueteConexion, LinkedList<EscuchaCliente> listadoCopia) {
		for(EscuchaCliente es : listadoCopia) {
			if(paqueteConexion.getNombreUsuario().equals(es.getNombreUsuario())) {
				paqueteConexion.setNombreUsuario(paqueteConexion.getNombreUsuario() + 
						"-" + Servidor.cantidadRepetidos);
				Servidor.cantidadRepetidos ++;
			}
		}
	}
}
