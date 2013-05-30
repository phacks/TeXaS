package formule;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;


public class BoutonInsererItem extends JButton {
	
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
	}

	public void paintComponent (Graphics g){
		
		g.setColor(Color.lightGray);

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
	
	
	
}
