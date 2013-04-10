package menubas.menubastableau;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTableUI;

import briquesElementaires.Couleur;

public class MenuBasTableUI extends BasicTableUI {

	private Object[][] data;

	public MenuBasTableUI(Object[][] contenu) {
		super();
		this.data = contenu;
	}

	public void paint(Graphics g, JComponent c) {
		Graphics2D g2d = (Graphics2D) g;
		int nbColonne = data[0].length;
		int nbLigne = data.length;
		int cellWidth = table.getColumnModel().getColumn(0).getPreferredWidth();
		int cellHeight = table.getRowHeight(0); 
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {

				if ((Boolean)data[i][j]){
					// Gestion colonne fusionnée
					if((j+1)<nbColonne && (Boolean)data[i][j+1]){
						if((j-1)>0 && (Boolean)data[i][j-1]){
							g2d.setColor(Couleur.vertClair.brighter().brighter());
							g2d.fillRect(j*cellWidth, i*cellHeight+6, cellWidth, cellHeight-12);

						}
						else{
							g2d.setColor(Couleur.vertClair.brighter().brighter());
							g2d.fillRect(j*cellWidth+6, i*cellHeight+6, cellWidth-6, cellHeight-12);
						}
					}
					else{
						g2d.setColor(Couleur.vertClair.brighter().brighter());
						g2d.fillRect(j*cellWidth+6, i*cellHeight+6, cellWidth-6, cellHeight-12);
					}
				}
				else{
					g2d.setColor(Couleur.vertClair);
					float[] dash = {10.0f};
					g2d.setStroke(new BasicStroke(1.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
					g2d.drawRect(j*cellWidth+5, i*cellHeight+5, cellWidth-10, cellHeight-10);
				}
			}
		}
	}
}
