package formule;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class BoutonsAction{
	private JButton split = new JButton("Split");
	private JButton indice = new JButton("Indice");
	private JButton addImage = new JButton("Image");
	
	public BoutonsAction(){
		
	}

	public JButton getBoutonSplit(){
		return this.split;
	}
	
	public JButton getBoutonIndice(){
		return this.indice;
	}
	
	public JButton getBoutonAddImage(){
		return this.addImage;
	}
	
	public void splitAddActionListener(ActionListener al){
		split.addActionListener(al);
	}
	
	public void addImageAddActionListener(ActionListener al){
		addImage.addActionListener(al);
	}
	
}
