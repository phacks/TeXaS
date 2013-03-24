package MenuGauche;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class MenuGaucheBase extends JPanel {

	private Color grisClair = new Color(234,237,239),  grisFonce = new Color(200,201,202);
	
	
	public MenuGaucheBase() {
		super();
		this.setLayout(new GridLayout(15,1));
	}
	
	public void paintComponent(Graphics g){
		Graphics2D  Shape = (Graphics2D) g;
		Shape.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Choix de la couleur du background (obligatoire, sinon bug)
		GradientPaint gp = new GradientPaint(this.getWidth()-5, this.getHeight(), grisClair, this.getWidth(), this.getHeight(), grisFonce);
		Shape.setPaint(gp);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		
	}      


}
