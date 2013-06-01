package formule;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import briquesElementaires.Couleur;


public class PanelLigneHorizontale extends JPanel {

	public PanelLigneHorizontale() {
		// TODO Auto-generated constructor stub
	}

	public PanelLigneHorizontale(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public PanelLigneHorizontale(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public PanelLigneHorizontale(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Couleur.white);
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);

	}

}
