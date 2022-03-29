package com.mycompany.project1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawingProgram extends JFrame implements MouseMotionListener {
	private Point mousePnt = new Point();

	public DrawingProgram() {
		super("Painter");
		JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp = new JPanel();
		toolbar.add(new Label("Drag mouse to draw"));
		this.add(toolbar, BorderLayout.SOUTH);
		this.add(jp, BorderLayout.CENTER);
		jp.addMouseMotionListener(this);
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void mouseMoved(MouseEvent me) {

	}

	public void mouseDragged(MouseEvent me) {
		mousePnt = me.getPoint();
		repaint();
	}

	public void paint(Graphics g) {
		g.fillOval(mousePnt.x, mousePnt.y, 4, 4);
	}

	public static void main(String[] a) {
		new DrawingProgram();
	}
}
