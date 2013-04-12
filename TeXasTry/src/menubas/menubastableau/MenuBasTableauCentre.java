package menubas.menubastableau;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import briquesElementaires.Couleur;
import briquesElementaires.JPanelDef;

public class MenuBasTableauCentre extends JPanelDef {

	private int nbLigne = 2;
	private int nbColonne = 3;
	private MenubasTableauTable structureTableau;

	public MenuBasTableauCentre(){
		super();
		nbLigne = 2;
		nbColonne = 3;
		redessiner();
	}

	public void redessiner() {
		this.removeAll();
		structureTableau = new MenubasTableauTable(nbLigne, nbColonne);
		JScrollPane scrollpane = new JScrollPane(structureTableau, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setPreferredSize(new Dimension(420,220));
		scrollpane.getViewport().setBackground(Couleur.white);
		scrollpane.setBorder(BorderFactory.createEmptyBorder());
		this.repaint();
		for(int i = 0; i < nbColonne; i++){
			if(nbColonne*60<400){
				structureTableau.getColumnModel().getColumn(i).setPreferredWidth(400/nbColonne);
			}
			else{
				structureTableau.getColumnModel().getColumn(i).setPreferredWidth(60);
			}
		}
		for (int i = 0; i < nbLigne; i++) {
			if(nbLigne*60<200){
				structureTableau.setRowHeight(i, 200/nbLigne);
			} else {
				structureTableau.setRowHeight(i, 60);
			}
		}
		this.add(scrollpane);
		this.revalidate();
	}

	public int getNbLigne() {
		return nbLigne;
	}

	public void setNbLigne(int nbLigne) {
		this.nbLigne = nbLigne;
	}

	public int getNbColonne() {
		return nbColonne;
	}

	public void setNbColonne(int nbColonne) {
		this.nbColonne = nbColonne;
	}

	public void fusionner() {
		structureTableau.fusionner();		
	}

}
