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

	public MenuBasTableUI(Object[][] contenu, Boolean[][][] fusion) {
		super();
		this.fusion = fusion;
		this.contenu = contenu;
	}

	public void paint(Graphics g, JComponent c) {
		Graphics2D g2d = (Graphics2D) g;
		int nbColonne = fusion[0].length;
		int nbLigne = fusion.length;
		int cellWidth = table.getColumnModel().getColumn(0).getPreferredWidth();
		int cellHeight = table.getRowHeight(0); 

		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;

				float[] dash = {10.0f};
				BasicStroke stroke = new BasicStroke(1.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
//				BasicStroke stroke = new BasicStroke();
				g2d.setStroke(stroke);

		g2d.setColor(Couleur.bleuClair);
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				boolean fusColGauche = false, fusColDroite = false, fusLigneUp = false, fusLigneDown = false;

				x1 = j*cellWidth; 
				x2 = (j+1)*cellWidth;
				y1 = i*cellHeight;
				y2 = (i+1)*cellHeight;
				
				// Si on fusionne avec la colone de droite
				if(this.fusion[i][j][1]){
					fusColDroite = true;
				}
				// Si on fusionne avec la colonne de gauche
				if((j-1)>=0 && this.fusion[i][j-1][1]){
					fusColGauche = true;
				}
				// Si on fusionne avec la ligne dessous
				if(this.fusion[i][j][0]){
					fusLigneDown = true;
				}
				// Si on fusionne avec la ligne dessus
				if((i-1)>=0 && this.fusion[i-1][j][0]){
					fusLigneUp = true;
				}
				
				// Gestion selection cellule
				if ((Boolean)this.contenu[i][j]){
					g2d.setColor(Couleur.bleuClair.brighter().brighter());
					g2d.fillRect(x1+5, y1+5,cellWidth-10, cellHeight-10);
					if(fusColGauche){
						g2d.fillRect(x1, y1+5,cellWidth-5, cellHeight-10);	
					}
					if(fusColDroite){
						g2d.fillRect(x1+5, y1+5,cellWidth-5, cellHeight-10);
						
					}
					if(fusLigneDown){
						g2d.fillRect(x1+5, y1+5,cellWidth-10, cellHeight-5);
						
					}
					if(fusLigneUp){
						g2d.fillRect(x1+5, y1,cellWidth-10, cellHeight-5);
						
					}
					g2d.setColor(Couleur.bleuClair);
				}

				// Gestion fusion colonne
				if (fusColDroite){
					if(fusColGauche){
						g2d.drawLine(x1, y1+5, x2, y1+5);
						g2d.drawLine(x1, y2-5, x2, y2-5);
					}
					else{
						g2d.drawLine(x1+5, y1+5, x2, y1+5);
						g2d.drawLine(x1+5, y2-5, x2, y2-5);
						g2d.drawLine(x1+5, y1+5, x1+5, y2-5);
					}
				}
				else{
					if(fusColGauche){
						g2d.drawLine(x1, y1+5, x2-5, y1+5);
						g2d.drawLine(x1, y2-5, x2-5, y2-5);
						g2d.drawLine(x2-5, y1+5, x2-5, y2-5);
					}
				}
				if(fusLigneDown){
					if(fusLigneUp){
						g2d.drawLine(x1+5, y1, x1+5, y2);
						g2d.drawLine(x2-5, y1, x2-5, y2);
					}
					else{
						g2d.drawLine(x1+5, y1+5, x1+5, y2);
						g2d.drawLine(x2-5, y1+5, x2-5, y2);
						g2d.drawLine(x1+5, y1+5, x2-5, y1+5);
					}
				}
				else{
					if(fusLigneUp){
						g2d.drawLine(x1+5, y1, x1+5, y2-5);
						g2d.drawLine(x2-5, y1, x2-5, y2-5);
						g2d.drawLine(x1+5, y2-5, x2-5, y2-5);
					}
				}
				if(!fusColDroite && !fusColGauche && !fusLigneDown && !fusLigneUp){
					g2d.drawLine(x1+5, y1+5, x2-5, y1+5);
					g2d.drawLine(x2-5, y1+5, x2-5, y2-5);
					g2d.drawLine(x2-5, y2-5, x1+5, y2-5);
					g2d.drawLine(x1+5, y2-5, x1+5, y1+5);
				}
				
				
			}
		}
	}
}

