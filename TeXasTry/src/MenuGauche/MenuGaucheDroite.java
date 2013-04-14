package MenuGauche;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class MenuGaucheDroite extends JPanel {
	
	public MenuGaucheDroite(){
		super();
		this.setLayout(new GridLayout(18,1));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(200,201,202)));
	}
	
	
}
