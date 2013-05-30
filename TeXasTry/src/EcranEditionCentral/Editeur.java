package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import briquesElementaires.JPanelDef;

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
	
	

}
