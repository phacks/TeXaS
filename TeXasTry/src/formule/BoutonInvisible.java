package formule;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;


/**
 * Classe implémentant le bouton associé à chaqque item. Il est invisible pour laisser
 * apparaître le contenu en dessous
 * @author nicolasgoutay
 * 
 * @param layeredPane Le ItemLayeredPane duquel est issu le bouton invisible
 *
 */
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
