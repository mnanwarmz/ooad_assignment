package com.mycompany.project1;

import java.awt.Point;
import java.awt.Color;
import java.awt.Label;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class DrawingProgram extends JFrame implements ActionListener, MouseMotionListener, ChangeListener {
    boolean line = true, rect = false, tri = false, pent = false, cir = true, start = true;
    int x, y, x2, y2, time = 0;

    private JButton LineButton, RectangleButton, TriangleButton, PentagonButton, CircleButton, clearBtn;
    //

    private Point mousePnt = new Point();
    public static Color penColor = new Color(0, 0, 0);
    private JSlider penSize = new JSlider(JSlider.HORIZONTAL, 1, 30, 4);
    private Label label = new Label("Line");
    private JPanel jp, toolbar;
    public static int pen = 4;

    public DrawingProgram() {
        super("Painter");
        label.setPreferredSize(new Dimension(60, 30));
        toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jp = new JPanel();

        // test split class
        MyMouseListener listener = new MyMouseListener();
        jp.addMouseListener(listener);
        jp.addMouseMotionListener(listener);
        //

        // button for shape
        this.LineButton = new JButton("Line");
        this.RectangleButton = new JButton("Rectangle");
        this.TriangleButton = new JButton("Triangle");
        this.PentagonButton = new JButton("Pentagon");
        this.CircleButton = new JButton("Circle");
        this.clearBtn = new JButton("Clear");

        x = y = x2 = y2 = 0;

        toolbar.add(label);
        toolbar.add(penSize);

        // add into toolbar
        toolbar.add(LineButton);
        toolbar.add(RectangleButton);
        toolbar.add(TriangleButton);
        toolbar.add(PentagonButton);
        toolbar.add(CircleButton);
        toolbar.add(clearBtn);
        //

        this.add(toolbar, BorderLayout.SOUTH);
        this.add(jp, BorderLayout.CENTER);

        // button listeners
        LineButton.addActionListener(this);
        RectangleButton.addActionListener(this);
        TriangleButton.addActionListener(this);
        PentagonButton.addActionListener(this);
        CircleButton.addActionListener(this);
        clearBtn.addActionListener(this);

        penSize.addChangeListener(this);
        setSize(800, 600);
        toolbar.setVisible(true);
        jp.setVisible(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearBtn) {
            toolbar.updateUI();
            jp.updateUI();
        } else {
            line = false;
            rect = false;
            tri = false;
            pent = false;
            if (e.getSource() == LineButton) {
                System.out.println("Line");
                label.setText("Line");
                line = true;
            }
            if (e.getSource() == RectangleButton) {
                System.out.println("Rectangle");
                label.setText("Rectangle");
                rect = true;
            }
            if (e.getSource() == TriangleButton) {
                System.out.println("Triangle");
                label.setText("Triangle");
                tri = true;
            }
            if (e.getSource() == PentagonButton) {
                System.out.println("Pentagon");
                label.setText("Pentagon");
                pent = true;
            }
            if (e.getSource() == CircleButton) {
                System.out.println("Circle");
                label.setText("Circle");
                cir = true;
            }
        }
        time = 1;
    }

    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEndPoint(int x, int y) {
        x2 = x;
        y2 = y;
    }
    //

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource(); // e.getSource get value from slider
        if (!source.getValueIsAdjusting()) // If slider stops, get value and store in pen
            pen = (int) source.getValue();
    }

    public void paint(Graphics g) {
        ShapesFactory draw = new ShapesFactory();
        if (start) {
            super.paint(g);
            start = false;
        }
        g.setColor(penColor);
        if (line == true)
            g.fillOval(mousePnt.x, mousePnt.y, pen, pen);
        else if (rect == true)
            draw.rect(g, x, y, x2, y2);
        else if (tri == true)
            draw.tri(g, x, y, x2, y2);
        else if (pent == true)
            draw.pentagon(g, x, y, x2, y2);
        else if (cir == true)
            draw.cir(g, x, y, x2, y2);
    }

    public static void main(String[] a) {
        new DrawingProgram();
    }

    class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent me) {
            if (me.getModifiers() == MouseEvent.BUTTON3_MASK) // Button3 waits for right click
            {
                penColor = JColorChooser.showDialog(null, "Change pen color", penColor);
                if (time != 0)
                    time = 0;
            }
            if (line != true) {
                if (time == 0)
                    time++;
                else if (time == 1) {
                    setStartPoint(me.getX(), me.getY());
                    System.out.println("Start point: " + x + " " + y);
                    time++;
                } else if (time == 2) {
                    time = 1;
                    setEndPoint(me.getX(), me.getY());
                    System.out.println("End point: " + x2 + " " + y2);
                    repaint();
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            if (line) {
                mousePnt = me.getPoint();
                repaint();
            }
        }
    }

    // Has to be implemented because of interface
    public void mouseMoved(MouseEvent me) {

    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseDragged(MouseEvent me) {
    }
}
