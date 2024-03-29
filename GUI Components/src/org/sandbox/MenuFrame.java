package org.sandbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

public class MenuFrame extends JFrame{
	
	private final Color[] colorValues = {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN};
	private JRadioButtonMenuItem[] colorItems;
	private JRadioButtonMenuItem[] fonts;
	private JCheckBoxMenuItem[] styleItems;
	private JLabel displayJLabel;
	private ButtonGroup colorButtonGroup;
	private ButtonGroup fontButtonGroup;
	private int style;
	
	public MenuFrame() {
		super("Using JMenus");
		
		JMenu fileMenu = new JMenu("File"); //create file menu
		fileMenu.setMnemonic('F');
		
		//add about... menu item to file menu
		JMenuItem aboutItem = new JMenuItem("About...");
		aboutItem.setMnemonic('A');
		fileMenu.add(aboutItem);
		aboutItem.addActionListener(
			new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(MenuFrame.this, 
							"This is an example\nof using menus",
							"About", JOptionPane.PLAIN_MESSAGE);
				}
			}
		);
		
		//add exit menu item to file menu
		JMenuItem exitItem = new JMenuItem("Exit");
		aboutItem.setMnemonic('x');
		fileMenu.add(exitItem);
		exitItem.addActionListener(
			new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			}
		);
		
		JMenuBar bar = new JMenuBar(); //create menu bar
		setJMenuBar(bar); //add menu bar to application
		bar.add(fileMenu); //add file menu to menu bar
		
		JMenu formatMenu = new JMenu("Format"); //create format menu
		formatMenu.setMnemonic('r');
		
		//array listing string colors
		String[] colors = {"Black", "Blue", "Red", "Green"};
		
		JMenu colorMenu = new JMenu("Color"); //create color menu
		colorMenu.setMnemonic('C');
		
		colorItems = new JRadioButtonMenuItem[colors.length];
		colorButtonGroup = new ButtonGroup();
		ItemHandler itemHandler = new ItemHandler(); //handler for colors
		
		for(int count = 0; count < colors.length; count++) {
			colorItems[count] = new JRadioButtonMenuItem(colors[count]);
			colorMenu.add(colorItems[count]);
			colorButtonGroup.add(colorItems[count]);
			colorItems[count].addActionListener(itemHandler);
		}
		
		colorItems[0].setSelected(true);
		
		formatMenu.add(colorMenu);
		formatMenu.addSeparator();
		
		String[] fontNames = {"Serif", "Monospaced", "SansSerif"};
		JMenu fontMenu = new JMenu("Font");
		fontMenu.setMnemonic('n');
		
		fonts = new JRadioButtonMenuItem[fontNames.length];
		fontButtonGroup = new ButtonGroup();
		
		for(int i = 0; i < fonts.length; i++) {
			fonts[i] = new JRadioButtonMenuItem(fontNames[i]);
			fontMenu.add(fonts[i]);
			fontButtonGroup.add(fonts[i]);
			fonts[i].addActionListener(itemHandler);
		}
		
		fonts[0].setSelected(true);
		fontMenu.addSeparator();
		
		String[] styleNames = {"Bold", "Italic"};
		styleItems = new JCheckBoxMenuItem[styleNames.length];
		StyleHandler styleHandler = new StyleHandler();
		
		for (int i = 0; i < styleItems.length; i++) {
			styleItems[i] = new JCheckBoxMenuItem(styleNames[i]);
			fontMenu.add(styleItems[i]);
			styleItems[i].addItemListener(styleHandler);
		}
		
		formatMenu.add(fontMenu);
		bar.add(formatMenu);
		
		displayJLabel = new JLabel("Sample Text", SwingConstants.CENTER);
		displayJLabel.setForeground(colorValues[0]);
		displayJLabel.setFont(new Font("Serif", Font.PLAIN, 72));
		
		getContentPane().setBackground(Color.CYAN);
		add(displayJLabel, BorderLayout.CENTER);
	}
	
	private class ItemHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < colorItems.length; i++) {
				if (colorItems[i].isSelected()) {
					displayJLabel.setForeground(colorValues[i]);
					break;
				}
			}
			for (int i = 0; i < fonts.length; i++) {
				if (e.getSource() == fonts[i]) {
					displayJLabel.setFont(new Font(fonts[i].getText(), style, 72));
				}
			}
		repaint();	
		}
	}
	
	private class StyleHandler implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			String name = displayJLabel.getFont().getName();
			Font font;
			
			if (styleItems[0].isSelected() && styleItems[1].isSelected()) {
				font = new Font(name, Font.BOLD + Font.ITALIC, 72);
			} else if (styleItems[0].isSelected()) {
				font = new Font(name, Font.BOLD, 72);
			} else if (styleItems[1].isSelected()) {
				font = new Font(name, Font.ITALIC, 72);
			} else {
				font = new Font(name, Font.PLAIN, 72);
			}
			
			displayJLabel.setFont(font);
			repaint();
		}
		
	}
}
