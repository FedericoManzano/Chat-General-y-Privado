package com.federico.chat.modelos;



import javax.swing.JTextPane;
import javax.swing.text.*;
import java.awt.Color;
import java.awt.Font;

public class CuadroTexto extends JTextPane{

	private static final long serialVersionUID = 1L;
	private SimpleAttributeSet attrs = new SimpleAttributeSet();
	
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
