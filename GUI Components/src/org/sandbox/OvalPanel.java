package org.sandbox;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OvalPanel extends JPanel {

	private int diameter = 10;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillOval(10, 10, diameter, diameter);
	}
	
	public void setDiameter(int diameter) {
		this.diameter = (diameter >= 0 ? diameter : 10);
		repaint();
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}
	
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
	
}
