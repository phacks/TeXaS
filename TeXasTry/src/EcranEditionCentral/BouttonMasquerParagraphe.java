package EcranEditionCentral;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import briquesElementaires.Couleur;

public class BouttonMasquerParagraphe extends JButton implements MouseListener{

	/** Boolean indiquant si les éléments hierarchique inférieur sont masqué ou non.
	 * 
	 */
	private boolean masque = false;

	/** Indique si les éléments hierarchique en dessous de l'éditeur sont masqués ou non
	 * @return Les éléments hierarchique en dessous de l'éditeur sont masqués ou non
	 */
	public boolean isMasque() {
		return masque;
	}

	/** Indique si les éléments hierarchique en dessous de l'éditeur doivent être masqué ou non
	 * @param masque
	 * 			TRUE pour masqué, FALSE pour visible
	 */
	public void setMasque(boolean masque) {
		this.masque = masque;
	}

	/** Boolean indiquant si la souris est sur le bouton ou non
	 * 
	 */
	private boolean reveal = false;
	
	/** Taille de l'icone
	 * 
	 */
	private int size;

	public BouttonMasquerParagraphe(int hierarchie){
		super("coucou");
		this.size=(5-hierarchie)*3;
		this.setBorder(null);
		this.addMouseListener(this);

	}

	public void paintComponent(Graphics g){
		g.setColor(Couleur.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(!masque){
			if(reveal){
				g.setColor(Couleur.bleuTexte);
				int[] xPoints = {0, 2*size, size};
				int[] yPoints = {this.getHeight()/2-size, this.getHeight()/2-size, this.getHeight()/2 + size};
				g.fillPolygon(xPoints, yPoints, 3);
				
			}
		}
		else{
			g.setColor(Couleur.bleuTexte);
			int[] xPoints = {0, 0, 2*size};
			int[] yPoints = {this.getHeight()/2-size, this.getHeight()/2+size, this.getHeight()/2};
			g.drawPolygon(xPoints, yPoints, 3);
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.reveal=true;
		this.repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.reveal=false;
		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	};

}
