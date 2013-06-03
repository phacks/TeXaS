package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import formule.Formule;

import briquesElementaires.JPanelDef;


/** Classe abstraite permettant la manipulation de tous les type d'éditeurs
 * 
 * @author Kilian
 *
 */
public abstract class Editeur extends JPanelDef {

	/**Boolean indiquant si l'editeur à le focus ou non
	 * 
	 */
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
