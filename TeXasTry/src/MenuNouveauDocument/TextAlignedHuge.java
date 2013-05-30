package MenuNouveauDocument;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

import briquesElementaires.Couleur;
import briquesElementaires.Police;

public class TextAlignedHuge extends JLabel {


	private String name;

	public TextAlignedHuge(String name){
		super(name);
		this.name = name;
		this.setFont(Police.segoeHuge);
	}

	protected void paintComponent(Graphics g) {
		Graphics2D  Shape = (Graphics2D) g;
		Shape.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Choix de la couleur du background (obligatoire, sinon bug)
		g.setColor(Couleur.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Dessin du texte en position centrée
		g.setColor(Couleur.black);
		FontMetrics fm = g.getFontMetrics();
		int x = 2*fm.getWidths()[0];
		int y = fm.getAscent() + (this.getHeight() - (fm.getAscent() + fm.getDescent())) / 2;
		g.drawString(this.name, x, y);
		
//		// Dessin d'une ligne de séparation
//		
//		g.setColor(Couleur.lightgray);
//		g.drawLine(0, this.getHeight()-1, this.getWidth(), this.getHeight()-1);


	}

}
