package menubas.menubastableau;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTableUI;

import briquesElementaires.Couleur;

public class MenuBasTableUI extends BasicTableUI {

	private Object[][] contenu;
	private Boolean[][][] fusion;
	private int cellWidth;
	private int cellHeight;

	public MenuBasTableUI(Object[][] contenu, Boolean[][][] fusion) {
		super();
		this.fusion = fusion;
		this.contenu = contenu;
	}

	public void paint(Graphics g, JComponent c) {
		Graphics2D g2d = (Graphics2D) g;
		int nbColonne = fusion[0].length;
		int nbLigne = fusion.length;
		this.cellWidth = table.getColumnModel().getColumn(0).getPreferredWidth();
		this.cellHeight = table.getRowHeight(0); 
//				float[] dash = {10.0f};
//				BasicStroke stroke = new BasicStroke(1.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
				BasicStroke stroke = new BasicStroke();
				g2d.setStroke(stroke);

		g2d.setColor(Couleur.bleuFonceBordure);
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				boolean fusGauche = false, fusDroite = false, fusUp = false, fusDown = false;

				// Si on fusionne avec la colone de droite
				if(this.fusion[i][j][1]){
					fusDroite = true;
				}
				// Si on fusionne avec la colonne de gauche
				if((j-1)>=0 && this.fusion[i][j-1][1]){
					fusGauche = true;
				}
				// Si on fusionne avec la ligne dessous
				if(this.fusion[i][j][0]){
					fusDown = true;
				}
				// Si on fusionne avec la ligne dessus
				if((i-1)>=0 && this.fusion[i-1][j][0]){
					fusUp = true;
				}
				
				// Gestion selection cellule
				if ((Boolean)this.contenu[i][j]){
					g2d.setColor(Couleur.bleuClair.brighter());
					g2d.fillRect(j*cellWidth, i*cellHeight,cellWidth, cellHeight);
					g2d.setColor(Couleur.bleuFonceBordure);
				}
				
				// Gestion bordure cellule
				drawCellule(g2d, i, j, fusGauche, fusDroite, fusUp, fusDown);
			}
			//Fermeture du tableau
			g2d.drawLine(0, c.getHeight()-1, c.getWidth(), c.getHeight()-1);
			g2d.drawLine(c.getWidth()-1, 0, c.getWidth()-1, c.getHeight());
		}
	}
	
	private void lineUp(Graphics2D g2D, int i, int j){
		g2D.drawLine(j*cellWidth, i*cellHeight, (j+1)*cellWidth, i*cellHeight);
	}
	
	private void lineDown(Graphics2D g2D, int i, int j){
		g2D.drawLine(j*cellWidth, (i+1)*cellHeight, (j+1)*cellWidth, (i+1)*cellHeight);
	}
	
	private void lineGauche(Graphics2D g2D, int i, int j){
		g2D.drawLine(j*cellWidth, i*cellHeight, j*cellWidth, (i+1)*cellHeight);
	}
	
	private void lineDroite(Graphics2D g2D, int i, int j){
		g2D.drawLine((j+1)*cellWidth, i*cellHeight, (j+1)*cellWidth, (i+1)*cellHeight);
	}
	
	private void drawCellule(Graphics2D g2D, int i, int j, boolean fusGauche, boolean fusDroite, boolean fusUp, boolean fusDown){
		
		
		if(!fusGauche){
			lineGauche(g2D, i, j);
		}
		if(!fusDroite){
			lineDroite(g2D, i, j);
		}
		if(!fusUp){
			lineUp(g2D, i, j);
		}
		if(!fusDown){
			lineDown(g2D, i, j);
		}
	}
	
}

