package com.mycompany.project1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class DrawingProgram2 extends JFrame implements MouseMotionListener, MouseListener, ChangeListener {
	private Point mousePnt = new Point();
	public static Color penColor = new Color(0, 0, 0);
	private JSlider penSize = new JSlider(JSlider.HORIZONTAL, 1, 30, 4);
	public static int pen = 4;

	public DrawingProgram2() {
		super("Painter");
		JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp = new JPanel();
		toolbar.add(new Label("Drag mouse to draw"));
		toolbar.add(penSize);
		this.add(toolbar, BorderLayout.SOUTH);
		this.add(jp, BorderLayout.CENTER);
		jp.addMouseMotionListener(this);
		jp.addMouseListener(this);
		penSize.addChangeListener(this);
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

	public void mouseClicked(MouseEvent me) {
		if (me.getModifiers() == MouseEvent.BUTTON3_MASK) {
			penColor = JColorChooser.showDialog(null, "Change pen colour", penColor);
		}
	}

	public void mouseEntered(MouseEvent me) {
	}

	public void mouseExited(MouseEvent me) {
	}

	public void mousePressed(MouseEvent me) {
	}

	public void mouseReleased(MouseEvent me) {
	}

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			pen = (int) source.getValue();
		}

	}

	public void paint(Graphics g) {
		// g.setStroke(new BasicStroke(pen));
		g.setColor(penColor);
		g.fillOval(mousePnt.x, mousePnt.y, pen, pen);
	}

	public static void main(String[] a) {
		new DrawingProgram2();
	}
}
