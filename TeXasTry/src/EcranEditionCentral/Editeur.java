package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import formule.Formule;

import briquesElementaires.JPanelDef;


// Classe abstraite permettant la manipulation de tous les type d'éditeurs
public abstract class Editeur extends JPanelDef {

	private boolean selected;
	
	public Editeur(BorderLayout borderLayout) {
		super(borderLayout);
	}

	public Editeur() {
		super();
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Formule getFormule() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
