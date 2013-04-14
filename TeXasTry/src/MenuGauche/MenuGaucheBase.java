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

import briquesElementaires.Couleur;


public class MenuGaucheBase extends JPanel {
	
	public MenuGaucheBase() {
		super();
		this.setLayout(new GridLayout(18,1));
	}
	
	public void paintComponent(Graphics g){
		Graphics2D  Shape = (Graphics2D) g;
		Shape.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Choix de la couleur du background (obligatoire, sinon bug)
		GradientPaint gp = new GradientPaint(this.getWidth()-4, this.getHeight(), Couleur.grisClair, this.getWidth(), this.getHeight(), Couleur.grisFonce);
		Shape.setPaint(gp);
		Shape.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		
		
	}      


}
