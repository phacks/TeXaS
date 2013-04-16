package menubas;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import briquesElementaires.Couleur;
import briquesElementaires.JPanelDef;

public class PanelDesignNorth extends JPanelDef {

	public PanelDesignNorth(){
		super();
	}

	public void paintComponent(Graphics g){
		Graphics2D  Shape = (Graphics2D) g;
		Shape.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Choix de la couleur du background (obligatoire, sinon bug)
		GradientPaint gp = new GradientPaint(0, this.getHeight()-5, Couleur.grisClair ,0, this.getHeight(), Couleur.grisFonce);
		Shape.setPaint(gp);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

	}
}
