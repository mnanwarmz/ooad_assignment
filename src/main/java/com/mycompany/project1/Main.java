package com.mycompany.project1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.add(new TestPane());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public class TestPane extends JPanel {

		private Point startPoint;
		private Rectangle2D rectangle;
		private Point currentPoint;

		public TestPane() {
			MouseAdapter mouseAdapter = new MouseAdapter() {
				@Override
				// Onclick
				public void mouseClicked(MouseEvent e) {
					if (startPoint != null && rectangle != null) {
						startPoint = e.getPoint();
						rectangle = null;
					} else if (startPoint == null) {
						startPoint = e.getPoint();
					} else {
						// You could use a List of some kind to
						// keep track of all the shapes you've created
						Point endPoint = e.getPoint();
						rectangle = makeRectangle(startPoint, endPoint);
					}
					repaint();
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					currentPoint = e.getPoint();
					repaint();
				}
			};
			addMouseListener(mouseAdapter);
			addMouseMotionListener(mouseAdapter);
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(800, 600);
		}

		protected Rectangle2D makeRectangle(Point startPoint, Point endPoint) {
			int minX = Math.min(startPoint.x, endPoint.x);
			int minY = Math.min(startPoint.y, endPoint.y);
			int maxX = Math.max(startPoint.x, endPoint.x);
			int maxY = Math.max(startPoint.y, endPoint.y);
			return new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			if (rectangle != null) {
				g2d.draw(rectangle);
			} else if (startPoint != null && currentPoint != null) {
				// These are guide lines, you can get rid of them if you prefer
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.drawLine(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
				g2d.draw(makeRectangle(startPoint, currentPoint));
			}
			g2d.dispose();
		}

	}
}