package com.federico.chat.menus;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MenuAtributos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String [] fuentes;
	private GraphicsEnvironment environment;
	private JButton btnColor;
	private JList<String> listadoFuentes;
	private JList<Integer> listadoTam;
	private JColorChooser colorChooser = new JColorChooser();
	private JLabel colorMostrar;
	private Font fuenteSeleccionada = new Font("Arial", Font.PLAIN, 15);
	private Color colorSeleccionado = new Color(0, 0, 0);
	private JButton btnAplicar;
	
	private JComboBox<String> tipoLetraCombo;

	
	public MenuAtributos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 358, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fuentes = environment.getAvailableFontFamilyNames();
		JPanel panel = new JPanel();
		panel.setBounds(10, 26, 155, 225);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		listadoFuentes = new JList<>();
		scrollPane.setViewportView(listadoFuentes);
		
		btnColor = new JButton("Color");
		btnColor.setBounds(242, 180, 89, 30);
		contentPane.add(btnColor);
		
		btnAplicar = new JButton("Aplicar");
		btnAplicar.setBounds(242, 221, 89, 30);
		contentPane.add(btnAplicar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(175, 26, 57, 225);
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		listadoTam = new JList<>();
		scrollPane_1.setViewportView(listadoTam);
		
		JLabel lblFuente = new JLabel("Fuente");
		lblFuente.setBounds(10, 11, 46, 14);
		contentPane.add(lblFuente);
		
		JLabel lblTam = new JLabel("Tam");
		lblTam.setBounds(175, 11, 46, 14);
		contentPane.add(lblTam);
		
		colorMostrar= new JLabel("");
		colorMostrar.setBackground(Color.BLACK);
		colorMostrar.setBounds(242, 97, 89, 72);
		colorMostrar.setOpaque(true);
		contentPane.add(colorMostrar);
		
		tipoLetraCombo = new JComboBox<>();
		tipoLetraCombo.setBounds(242, 26, 89, 20);
		contentPane.add(tipoLetraCombo);
		
		cargarListadoFuentes();
		cargarListadoTam();
		cargarTipoLetra();
		colorChooser.setColor(new Color(0, 0, 0));
		btnColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(colorChooser, "Seleccion de color", new Color(0,0,0));
				colorMostrar.setBackground(color);
				colorChooser.setColor(color);
			}
		});
		
	}
	
	private void cargarTipoLetra() {
		tipoLetraCombo.addItem("Plain");
		tipoLetraCombo.addItem("Bold");
		tipoLetraCombo.addItem("Italic");
	}
	
	private void cargarListadoFuentes() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		for(int i = 0; i < fuentes.length; i ++) {
			modelo.addElement(fuentes[i]);
		}
		listadoFuentes.setModel(modelo);
		listadoFuentes.setSelectedIndex(0);
	}
	
	private void cargarListadoTam() {
		DefaultListModel<Integer> modelo = new DefaultListModel<>();
		modelo.addElement(8);
		modelo.addElement(9);
		modelo.addElement(10);
		modelo.addElement(11);
		modelo.addElement(12);
		modelo.addElement(14);
		modelo.addElement(16);
		modelo.addElement(18);
		modelo.addElement(20);
		modelo.addElement(24);
		modelo.addElement(28);
		modelo.addElement(36);
		modelo.addElement(42);
		listadoTam.setModel(modelo);
		listadoTam.setSelectedIndex(2);
	}

	public Font getFuenteSeleccionada() {
		return new Font(fuenteSeleccionada.getFontName(), fuenteSeleccionada.getStyle(), fuenteSeleccionada.getSize());
	}

	public void setFuenteSeleccionada(Font fuenteSeleccionada) {
		this.fuenteSeleccionada = fuenteSeleccionada;
	}

	public Color getColorSeleccionado() {
		return new Color(colorSeleccionado.getRGB());
	}

	public void setColorSeleccionado(Color colorSeleccionado) {
		this.colorSeleccionado = colorSeleccionado;
	}


	@Override
	public String toString() {
		return "MenuAtributos [fuenteSeleccionada=" + fuenteSeleccionada.getFontName() 
				+ "]";
	}


	public JButton getBtnAplicar() {
		return btnAplicar;
	}

	public JList<String> getListadoFuentes() {
		return listadoFuentes;
	}

	public JList<Integer> getListadoTam() {
		return listadoTam;
	}

	public JComboBox<String> getTipoLetraCombo() {
		return tipoLetraCombo;
	}

	public JColorChooser getColorChooser() {
		return colorChooser;
	}
	
	
	
}
