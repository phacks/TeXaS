package MenuGauche;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class MenuGaucheDroite extends JPanel {
	
	public MenuGaucheDroite(){
		super();
		this.setLayout(new GridLayout(18,1));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(200,201,202)));
	}
	
	
}
