package com.mycompany.project1;

import java.awt.Graphics;

class ShapesFactory {
	public void rect(Graphics g, int x, int y, int x2, int y2) {
		int px = Math.min(x, x2);
		int py = Math.min(y, y2);
		int pw = Math.abs(x - x2);
		int ph = Math.abs(y - y2);

		g.fillRect(px, py, pw, ph);
	}

	public void tri(Graphics g, int x, int y, int x2, int y2) {
		int top = Math.min(y, y2);
		int bot = Math.max(y, y2);
		int left = Math.min(x, x2);
		int right = Math.max(x, x2);
		int mid = left + ((right - left) / 2);

		int xpoints[] = { left, mid, right };
		int ypoints[] = { bot, top, bot };

		g.fillPolygon(xpoints, ypoints, 3);
	}

	public void pentagon(Graphics g, int x, int y, int x2, int y2) {
		int top = Math.min(y, y2);
		int bot = Math.max(y, y2);
		int left = Math.min(x, x2);
		int right = Math.max(x, x2);
		int midy = bot + ((top - bot) / 2);
		int midx = right + ((left - right) / 2);
		int midoffset = (bot - top) / 8;
		int botoffset = (right - left) / 10;

		int xpoints[] = { left + botoffset, left, midx, right, right - botoffset };
		int ypoints[] = { bot, midy - midoffset, top, midy - midoffset, bot };

		g.fillPolygon(xpoints, ypoints, 5);
	}

	public void cir(Graphics g, int x, int y, int x2, int y2) {
		int xpointmin = Math.min(x, x2);
		int ypointmin = Math.min(y, y2);
		int xpointmax = Math.max(x, x2);
		int ypointmax = Math.max(y, y2);
		int value = Math.min(xpointmax - xpointmin, ypointmax - ypointmin);

		g.fillOval(xpointmin, ypointmin, value, value);
	}
}