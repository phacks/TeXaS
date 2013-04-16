package menubas.menubasFigures;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import briquesElementaires.Couleur;
import briquesElementaires.Police;

public class BoutonMenuBasFigure extends JButton implements MouseListener{

	private String name;
	private Image img;
	private boolean survol;
	private boolean pressed;

	public BoutonMenuBasFigure(String str){
		super(str);
		this.name = str;
		try {
			this.img = ImageIO.read(new File("boutImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setBorderPainted(false);
		this.addMouseListener(this);
	}

	protected void paintComponent(Graphics g) {
		Graphics2D Shape = (Graphics2D) g;
		Shape.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		FontMetrics fm = Shape.getFontMetrics();

		// Choix de la couleur du background (obligatoire, sinon bug)
		Shape.setColor(Couleur.white);
		Shape.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Si le bouton est survolé
		if (this.survol){
			Shape.setColor(Couleur.grisClair);
			Shape.fillRect(this.getWidth()/2-(img.getWidth(this))*5/8, 0, img.getWidth(this)*5/4, this.getHeight());
		}

		// Si le bouton est pressé
		if (this.pressed){
			Shape.setColor(Couleur.bleuClairMenuGauche);
			Shape.fillRect(this.getWidth()/2-(img.getWidth(this))*5/8, 0, img.getWidth(this)*5/4, this.getHeight());
		}
		// Dessin du texte en position centrée en bas
		Shape.setFont(Police.segoe);
		Shape.setColor(Couleur.black);
		if(this.pressed){
			Shape.setColor(Couleur.white);
		}
		int x = (this.getWidth() - fm.stringWidth(this.name)) / 2;
		int y = this.getHeight()-fm.getAscent()/2;
		Shape.drawString(this.name, x, y);

		Shape.drawImage(img, this.getWidth()/2-img.getWidth(this)/2, this.getHeight()-2*fm.getAscent()-img.getHeight(this), this);
		Shape.setColor(Couleur.lightgray);
		Shape.drawRect(this.getWidth()/2-(img.getWidth(this))*5/8, 0, img.getWidth(this)*5/4, this.getHeight()-1);
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
