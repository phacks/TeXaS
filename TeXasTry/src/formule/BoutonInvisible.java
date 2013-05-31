package formule;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;


public class BoutonInvisible extends JButton{

	private ItemLayeredPane layeredPane;

	public BoutonInvisible(ItemLayeredPane layeredPane){
		
		this.layeredPane = layeredPane; 
		
		this.setSize(this.layeredPane.getPreferredSize().width, this.layeredPane.getPreferredSize().height);
		
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}

	public void redefinirApparence() {
		
		this.setSize(this.layeredPane.getPreferredSize().width, this.layeredPane.getPreferredSize().height);
		
	}

}
