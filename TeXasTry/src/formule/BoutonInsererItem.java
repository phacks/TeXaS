package formule;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;


public class BoutonInsererItem extends JButton implements MouseListener {
	
	private boolean hover = false;
	private int depth;
	private int width;
	private int height;
	
	public BoutonInsererItem(int depth){
		
		if (depth <= 3){
		this.depth = depth;
		}
		else{
			this.depth = 3;
		}
		
		this.width = 20;
		this.height = 100;
		
		for(int i = 0; i< this.depth - 1; i++){
			if (this.width > 10){
			this.width = this.width - 5;
			}
			this.height = this.height *3/4 - 5;
		}
		
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.setBorder(BorderFactory.createEmptyBorder());
		
		this.addMouseListener(this);
		
	}

	public void paintComponent (Graphics g){
		
		g.setColor(Color.WHITE);

		g.fillRect(0, 0, this.width, this.height);
		
	}
	
	public void setWidth(int w){
		this.width = w;
	}
	
	public void setHeight(int h){
		this.height = h;
	}
	
	public void redefinirApparence(){
		this.setPreferredSize(new Dimension(this.width, this.height));
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
