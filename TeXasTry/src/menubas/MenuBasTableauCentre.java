package menubas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import briquesElementaires.Couleur;
import briquesElementaires.JPanelDef;

public class MenuBasTableauCentre extends JPanelDef {

	private int nbLigne = 2;
	private int nbColonne = 3;
	private JTable structureTableau;
	
	public MenuBasTableauCentre(){
		super();
		nbLigne = 2;
		nbColonne = 3;
		structureTableau = new JTable(nbLigne, nbColonne);
		JScrollPane scrollpane = new JScrollPane(structureTableau);
		scrollpane.setPreferredSize(new Dimension(200,200));
		this.add(scrollpane);
	}

	public void redessiner() {
		this.removeAll();
		this.setLayout(new GridLayout(nbColonne, nbLigne,10,10));
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				JPanel cellule = new JPanel();
				cellule.setBackground(Couleur.green);
				this.add(cellule);
			}
		}
		super.revalidate();
	}
	
	
}
