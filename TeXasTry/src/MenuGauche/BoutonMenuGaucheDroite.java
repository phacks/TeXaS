package MenuGauche;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import briquesElementaires.Police;



public class BoutonMenuGaucheDroite extends JButton {
	
	private String name;
	
	public BoutonMenuGaucheDroite(String str) {
		super(str);
		this.name = str;
		this.setFont(Police.segoe);
		this.setBackground(Color.WHITE);
		this.setHorizontalAlignment(SwingConstants.LEFT);
		this.setBorderPainted(false);
		}

}
