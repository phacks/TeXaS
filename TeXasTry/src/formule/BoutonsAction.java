package formule;

import java.awt.event.ActionListener;

import javax.swing.JButton;


public class BoutonsAction{
	private JButton fraction = new JButton("Fraction");
	private JButton indice = new JButton("Indice");
	private JButton addImage = new JButton("Image");
	
	public BoutonsAction(){
		
	}

	public JButton getBoutonFraction(){
		return this.fraction;
	}
	
	public JButton getBoutonIndice(){
		return this.indice;
	}
	
	public JButton getBoutonAddImage(){
		return this.addImage;
	}
	
	public void fractionAddActionListener(ActionListener al){
		fraction.addActionListener(al);
	}
	
	public void indiceAddActionListener(ActionListener al){
		indice.addActionListener(al);
	}
	
	public void addImageAddActionListener(ActionListener al){
		addImage.addActionListener(al);
	}
	
}
