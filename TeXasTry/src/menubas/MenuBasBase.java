package menubas;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MenuBasBase extends JTabbedPane implements ChangeListener, Runnable {


	public MenuBasBase(){
		super(JTabbedPane.BOTTOM);
		this.setBackground(Color.WHITE);
		MenuBasDesignOngletsUI menuBasDesign = new MenuBasDesignOngletsUI();
		this.setUI(menuBasDesign);
		menuBasDesign.overrideContentBorderInsetsOfUI();
		this.addChangeListener(this);
	}


	private int step;
	private BufferedImage buf = null;
	private int previousTab = -1;
	private int animationLongueur = 20;

	public void stateChanged(ChangeEvent evt) {
		new Thread(this).start();
	}


	public void run() {
		step = 0;

		// save the previous tab
		if(previousTab != -1) {
			Component comp = this.getComponentAt(previousTab);
			buf = new BufferedImage(comp.getWidth(),comp.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
			comp.paint(buf.getGraphics());
		}

		for(int i=0; i<=animationLongueur; i++) {
			step = i;
			repaint();
			try {
				Thread.sleep(15);
			} catch (Exception ex) {
				System.out.println("ex: " + ex);
			}
		}
		step = -1;
		previousTab = this.getSelectedIndex();
		repaint();
	}


	public void paintChildren(Graphics g) {
		super.paintChildren(g);
		
		float alpha = 1.0f;
		
		if(step != -1) {
			Rectangle size = this.getComponentAt(0).getBounds();
			Graphics2D g2 = (Graphics2D)g;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha-0.05f*step)); 
			g2.drawImage(buf,(int)size.getX(),(int)size.getY(),null); 
		}
	}

}



