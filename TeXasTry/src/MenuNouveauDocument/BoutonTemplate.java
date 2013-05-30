package MenuNouveauDocument;

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

public class BoutonTemplate extends JButton implements MouseListener{

	private static final int RAPPORT_DE_STAGE = 0;
	private static final int RAPPORT_DE_PROJET = 1;
	private static final int LETTRE_FORMELLE = 2;
	private static final int COURS = 3;
	private static final int FICHE_DE_REVISION = 4;
	private static final int ARTICLE_COURT = 5;

	private String name;
	private Image img;
	private String nomImage;
	private boolean survol;
	private boolean pressed;

	public BoutonTemplate(int icone){
		super();
		switch (icone) {
		default:
		case RAPPORT_DE_STAGE:
			this.name = "Raport de stage";
			this.nomImage = "rapportDeStage.png";
			break;
		case RAPPORT_DE_PROJET:
			this.name = "Raport de projet";
			this.nomImage = "rapportDeProjet.png";
			break;
		case LETTRE_FORMELLE:
			this.name = "Lettre formelle";
			this.nomImage = "lettreFormelle.png";
			break;
		case COURS:
			this.name = "Cours";
			this.nomImage = "cours.png";
			break;
		case FICHE_DE_REVISION:
			this.name = "Fiche de révision";
			this.nomImage = "ficheDeRevision.png";
			break;
		case ARTICLE_COURT:
			this.name = "Article court";
			this.nomImage = "articleCourt.png";
			break;
		}	

		try {
			this.img = ImageIO.read(new File("images/Templates/"+this.nomImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setName(name);
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
			Shape.fillRect(this.getWidth()/2-(img.getWidth(this))*5/8, this.getHeight()-6*fm.getAscent()-img.getHeight(this), img.getWidth(this)*5/4, this.getHeight());
		}

		// Si le bouton est pressé
		if (this.pressed){
			Shape.setColor(Couleur.bleuClairMenuGauche);
			Shape.fillRect(this.getWidth()/2-(img.getWidth(this))*5/8, this.getHeight()-6*fm.getAscent()-img.getHeight(this), img.getWidth(this)*5/4, this.getHeight());
		}


		// Dessin du texte en position centrée en bas
		Shape.setFont(Police.segoe);
		Shape.setColor(Couleur.black);
		int x = (this.getWidth() - fm.stringWidth(this.name)) / 2;
		int y = this.getHeight()-fm.getAscent()/2;
		Shape.drawString(this.name, x, y);
		
		Shape.drawImage(img, this.getWidth()/2-img.getWidth(this)/2, this.getHeight()-3*fm.getAscent()-img.getHeight(this), this);

		
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
