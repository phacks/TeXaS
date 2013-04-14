package menubas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import briquesElementaires.Couleur;
import briquesElementaires.Police;

public class BoutonMenuBasValidate extends JButton implements MouseListener{

	private String name;
	private boolean survol;
	private boolean pressed;
	
	

	public BoutonMenuBasValidate(String str) {
		super(str);
		this.name = str;
		this.setBorderPainted(false);
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g){
		Graphics2D  Shape = (Graphics2D) g;
		Shape.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Choix de la couleur du background (obligatoire, sinon bug)
		Shape.setColor(Color.WHITE);
		Shape.fillRect(0, 0, this.getWidth(), this.getHeight());

		
		
		// Dessin du texte en position centrée
		Shape.setFont(Police.segoe);
		Shape.setColor(Color.black);
		FontMetrics fm = Shape.getFontMetrics();
		int x = (this.getWidth() - fm.stringWidth(this.name)) / 2;
		int y = (fm.getAscent() + (this.getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
		Shape.drawString(this.name, x, y);
		
		Shape.setColor(Couleur.grisFonce);
		Shape.drawLine(0, 0, 0, this.getHeight());
		
		// Si le bouton est survolé
		if (this.survol){
			Shape.setColor(Couleur.bleuClairMenuGauche);
			Shape.fillRect(0, 0, this.getWidth(), this.getHeight());
			Shape.setColor(Couleur.white);
			Shape.drawString(this.name, x, y);
		}
		
		// Si le bouton est pressé
		if (this.pressed){
			Shape.setColor(Couleur.bleuMedianMenuGauche);
			Shape.fillRect(0, 0, this.getWidth(), this.getHeight());
			Shape.setColor(Couleur.white);
			Shape.drawString(this.name, x, y);
		}



	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.survol = true;
		this.repaint();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.survol = false;
		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.pressed=true;
		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.pressed=false;
		this.repaint();
	}
}      


