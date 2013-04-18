package menubas.menubasMathematiques;

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

public class BoutonMenuMathematiqueSymbole extends JButton implements MouseListener {

	private Image imageBouton;

	private boolean survol;
	private boolean pressed;

	private File fichierBouton;


	public BoutonMenuMathematiqueSymbole(File sousFichier) {
		super();
		this.fichierBouton = sousFichier;
		try {
			imageBouton = ImageIO.read(sousFichier);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		}

		// Si le bouton est pressé
		if (this.pressed){
			Shape.setColor(Couleur.bleuClairMenuGauche);
		}
		
		// Coordonnées pour le dessin du carré de séléction
		int x1 = this.getWidth()/2-this.getHeight()/2;
		int w1 = this.getHeight();
		int y1 = 3;
		int h1 = this.getHeight()-6;

		int x2 = 0;
		int w2 = this.getWidth()-1 ; 
		int y2 = 0;
		int h2 = this.getHeight()-1;

		Shape.fillRect(x2,y2,w2,h2);


		// Dessin de l'icône
		
		if (imageBouton.getWidth(this)<=this.getWidth() && imageBouton.getHeight(this)<=this.getHeight()){
			Shape.drawImage(imageBouton, this.getWidth()/2-imageBouton.getWidth(this)/2, this.getHeight()/2-imageBouton.getHeight(this)/2, this);
		}
		else{
			int largeur = imageBouton.getWidth(this);
			int hauteur = imageBouton.getHeight(this);
			
			while(largeur>this.getWidth()-5 || hauteur>this.getHeight()-5){
				largeur = (int) (largeur * 0.95);
				hauteur = (int) (hauteur * 0.95);
			}
			int x = Math.max(4,this.getWidth()/2-largeur/2);
			int y = Math.max(4,this.getHeight()/2-hauteur/2);
			
			Shape.drawImage(imageBouton,x,y,largeur,hauteur,this);
		}
		
		// Dessin du cadre
		Shape.setColor(Couleur.lightgray);
		Shape.drawRect(x2,y2,w2,h2);
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
	
	public File getFichierBouton() {
		return fichierBouton;
	}


}
