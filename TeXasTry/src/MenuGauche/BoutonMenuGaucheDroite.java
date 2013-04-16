package MenuGauche;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import briquesElementaires.Police;



public class BoutonMenuGaucheDroite extends JButton {
	
	public BoutonMenuGaucheDroite(String str) {
		super(str);
		this.setFont(Police.segoe);
		this.setBackground(Color.WHITE);
		this.setHorizontalAlignment(SwingConstants.LEFT);
		this.setBorderPainted(false);
	}

}
