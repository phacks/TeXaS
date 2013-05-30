package formule;

import java.awt.Dimension;

import javax.swing.JTextField;


public class JTextFieldItem extends JTextField{
	private int width = 100;
	private int height = 100;

	public JTextFieldItem(){
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.setSize(new Dimension(this.width, this.height));
	}
	
	public void setWidth(int w){
		this.width = w;
	}
	
	public void setHeight(int h){
		this.width = h;
	}
	
}
