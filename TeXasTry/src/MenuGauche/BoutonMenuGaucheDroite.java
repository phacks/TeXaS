package MenuGauche;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;



public class BoutonMenuGaucheDroite extends JButton {
	
	private String name;
	
	public BoutonMenuGaucheDroite(String str) {
		super(str);
		this.name = str;
		this.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		this.setBackground(Color.WHITE);
		this.setHorizontalAlignment(SwingConstants.LEFT);
		this.setBorderPainted(false);
		}

}
