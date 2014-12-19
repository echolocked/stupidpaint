package stupidPaint.org;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StupidPaint extends JApplet {

	public static void main(String[] args) {
		JFrame window = new JFrame("SimplePaint");
		SimplePaintPanel panel = new SimplePaintPanel();
		window.setContentPane(panel);
		window.setSize(600, 480);
		window.setLocation(100, 100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public void init() {
		setContentPane(new SimplePaintPanel());
	}
	
	public static class SimplePaintPanel extends JPanel implements MouseListener, MouseMotionListener {
		
		private final static int BLACK = 0,
				RED = 1,
				GREEN = 2,
				BLUE = 3,
				CYAN = 4,
				MAGENTA = 5,
				YELLOW = 6;
		int prex, prey;
		boolean isDragging;
		private Graphics drawG;
		private int currentColor;
		
		
		public SimplePaintPanel() {
			setBackground(Color.white);
			addMouseListener(this);
			addMouseMotionListener(this);
			isDragging = false;
			currentColor = RED;
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			/* Draw the boarder. */
			g.setColor(Color.GRAY);
			int width = getWidth();
			int height = getHeight();
			int colorHeight = (height-56)/7;
			g.drawRect(0, 0, width-1, height-1);
			g.drawRect(1, 1, width-3, height-3);
			g.drawRect(2, 2, width-5, height-5);
			
			/* Draw the clear rectangle. */
			g.fillRect(width-56, 0, 56, height);
			g.setColor(Color.white);
			g.fillRect(width-53, height-53, 50, 50);
			g.setColor(Color.black);
			// need test here
			g.drawRect(width-53, height-53, 49, 49);
			g.drawString("Clear", width-40, height-40);
			
			/* Draw the seven color rectangles. */
			g.setColor(Color.black);
			g.fillRect(width-53, 3+0*colorHeight, 50, colorHeight-3);
			g.setColor(Color.RED);
			g.fillRect(width-53, 3 + 1*colorHeight, 50, colorHeight-3);
			g.setColor(Color.GREEN);
			g.fillRect(width-53, 3 + 2*colorHeight, 50, colorHeight-3);
			g.setColor(Color.BLUE);
			g.fillRect(width-53, 3 + 3*colorHeight, 50, colorHeight-3);
			g.setColor(Color.CYAN);
			g.fillRect(width-53, 3 + 4*colorHeight, 50, colorHeight-3);
			g.setColor(Color.MAGENTA);
			g.fillRect(width-53, 3 + 5*colorHeight, 50, colorHeight-3);
			g.setColor(Color.YELLOW);
			g.fillRect(width-53, 3 + 6*colorHeight, 50, colorHeight-3);
			
			/* Draw a boarder around the current color selected */
			g.setColor(Color.white);
			g.drawRect(width-55, 1+currentColor*colorHeight, 53, colorHeight-1);
			g.drawRect(width-54, 2+currentColor*colorHeight, 51, colorHeight-3);
			
			
		}
		
		public void changeColor(int y) {
			int width = getWidth();
			int height = getHeight();
			int colorHeight = (height-56)/7;
			int newColor = y / colorHeight;
			if (newColor < 0 || newColor > 6) return;
			Graphics g = getGraphics();
	        g.setColor(Color.GRAY);
	        g.drawRect(width-55, 1 + currentColor*colorHeight, 53, colorHeight);
	        g.drawRect(width-54, 2 + currentColor*colorHeight, 51, colorHeight-2);
	        currentColor = newColor;
	        g.setColor(Color.WHITE);
	        g.drawRect(width-55, 1 + currentColor*colorHeight, 53, colorHeight);
	        g.drawRect(width-54, 2 + currentColor*colorHeight, 51, colorHeight-2);
	        g.dispose();
	        
			
		}
		
		public void setUpGraphics() {
			drawG = getGraphics();
	        switch(currentColor) {
	        case 0:
	        	drawG.setColor(Color.black);
	        	break;
	        case 1:
	        	drawG.setColor(Color.red);
	        	break;
	        case 2:
	        	drawG.setColor(Color.green);
	        	break;
	        case 3:
	        	drawG.setColor(Color.blue);
	        	break;
	        case 4:
	        	drawG.setColor(Color.cyan);
	        	break;
	        case 5:
	        	drawG.setColor(Color.magenta);
	        	break;
	        case 6:
	        	drawG.setColor(Color.yellow);
	        	break;
	        }
		}
		
		public void mouseDragged(MouseEvent e) {
			if (isDragging == false) return;
			int x = e.getX();
			int y = e.getY();
			
			int width = getWidth();
			int height = getHeight();
			if (x < 3) x = 3;
			else if (x > width - 57) x = width-57;
			if (y < 3) y = 3;
			else if (y > height - 4) y = height-4;
			
			drawG.drawLine(prex, prey, x, y);
			prex = x;
			prey = y;
			
		}

		
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		public void mousePressed(MouseEvent e) {
			if (isDragging == true) return;
			
			int width = getWidth();
			int height = getHeight();
			int x = e.getX();
			int y = e.getY();
			
			if (x > width-53) {
				if (y > height-53) {
					repaint();
					return;
				}
				else {
					changeColor(y);
				}
			}
			else if (x > 3 && x < width-56 && y > 3 && y < height-3) {
				prex = x;
				prey = y;
				isDragging = true;
				setUpGraphics();
			}
		}

		
		public void mouseReleased(MouseEvent e) {
			if (isDragging == false)
				return;
			isDragging = false;
			drawG.dispose();
		}

		
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
