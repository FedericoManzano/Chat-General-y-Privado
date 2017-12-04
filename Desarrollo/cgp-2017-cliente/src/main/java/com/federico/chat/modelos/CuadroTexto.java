package com.federico.chat.modelos;



import javax.swing.JTextPane;
import javax.swing.text.*;
import java.awt.Color;
import java.awt.Font;

public class CuadroTexto extends JTextPane{

	private static final long serialVersionUID = 1L;
	private SimpleAttributeSet attrs = new SimpleAttributeSet();
	
//	public void append(Font fuente, Color color, String mensaje) {
//		int cantidad = 0;
//		int longitudTexto = fuente.getSize();
//		int contador = 0;
//		cantidad = (getWidth() + 360 )/ longitudTexto;
//		for(int i = 0; i < mensaje.length(); i ++) {
//			String letra = String.valueOf(mensaje.charAt(i));
//			if(contador > cantidad) {
//				escribir(fuente, color, letra + "\n");
//				contador  = 0;
//			}
//			else
//				escribir(fuente, color, letra);
//			contador  ++;
//		}
//	}
//	
	public void append(Font fuente, Color color, String mensaje)  {
		StyleConstants.setFontFamily(attrs, fuente.getFamily());
		StyleConstants.setFontSize(attrs, fuente.getSize());
		StyleConstants.setBold(attrs, fuente.isBold());
		StyleConstants.setItalic(attrs, fuente.isItalic());
		StyleConstants.setForeground(attrs, color);
		try {
			getStyledDocument().insertString(getStyledDocument().getLength(), mensaje, attrs);
		}catch(BadLocationException e) {
			e.printStackTrace();
		}
	} 
}
