package io.codeworth.panelmatic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.Icon;

/**
 * Static utility methods for testing.
 * @author michael
 */
public class TestUtils {

	public static class RoundIcon implements Icon {
		
		private Color c;
		private int size;

		public RoundIcon(Color c, int size) {
			this.c = c;
			this.size = size;
		}
		
		@Override
		public void paintIcon(Component comp, Graphics g, int x, int y) {
			g.setColor(c);
			g.fillOval(x, y, size, size);
			g.setColor(Color.yellow);
			g.drawOval(x, y, size, size);
		}

		@Override
		public int getIconWidth() {
			return size;
		}

		@Override
		public int getIconHeight() {
			return size;
		}
	};

	public static class SquareIcon implements Icon {

		private Color c;
		private int size;

		public SquareIcon(Color c, int size) {
			this.c = c;
			this.size = size;
		}

		@Override
		public void paintIcon(Component comp, Graphics g, int x, int y) {
			g.setColor(c);
			g.fillRect(x, y, size, size);
			g.setColor(Color.black);
			g.drawRect(x, y, size-1, size-1);
		}

		@Override
		public int getIconWidth() {
			return size;
		}

		@Override
		public int getIconHeight() {
			return size;
		}
	};

	public static class TriangleIcon implements Icon {

		private Color c;
		private int size;

		public TriangleIcon(Color c, int size) {
			this.c = c;
			this.size = size;
		}

		@Override
		public void paintIcon(Component comp, Graphics g, int x, int y) {
			int[] xs = {x, x+size/2, x+size-1};
			int[] ys = {y+size-1, y, y+size-1};
			g.setColor(c);
			g.fillPolygon(xs, ys, 3);
			g.setColor(Color.black);
			g.drawPolygon(xs, ys, 3);
		}

		@Override
		public int getIconWidth() {
			return size;
		}

		@Override
		public int getIconHeight() {
			return size;
		}
	};

	public static class RandIcon implements Icon {

		private int size;

		public RandIcon(int size) {
			this.size = size;
		}

		@Override
		public void paintIcon(Component comp, Graphics g, int x, int y) {
			Random r = new Random();
			int times = r.nextInt(10)+5;
			for ( int i=0; i<times; i++ ) {
				int curSize = r.nextInt(size/2)+2;
				g.setColor( new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
				int cx = x+r.nextInt(size-curSize);
				int cy = y+r.nextInt(size-curSize);
				g.fillOval(cx, cy, curSize, curSize);
			}

		}

		@Override
		public int getIconWidth() {
			return size;
		}

		@Override
		public int getIconHeight() {
			return size;
		}
	};
}
