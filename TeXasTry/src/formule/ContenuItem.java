package formule;

import javax.swing.JPanel;


/**
 * Classe abstraite pour tout ce qui a trait aux contenus des items
 * @author nicolasgoutay
 *
 * @see ContenuItemTexte
 * @see ContenuItemImage
 * @see ContenuItemSplit
 */
public abstract class ContenuItem {
	
	// Renvoie le texte (ou le nom de l'image) de l'item considéré
	public String getText(){
		return "";
	}
	
	// Revoie le type de l'item considéré
	public String getType(){
		return "";
	}
	
	// Renvoie l'objet de classe ContenuItemTexte
	ContenuItemTexte getCIT(){
		return null;
	}
	
	// Renvoie l'objet de classe ContenuItemImage
	ContenuItemImage getCII(){
		return null;
	}
	
	// Renvoie l'objet de classe ContenuItemSplit
	ContenuItemSplit getCIS(){
		return null;
	}
	
	// Dans le cas d'une structure (par ex. fraction), renvoie un tableau des formules créées
	public Formule[] getArraySplit(){
		return null;
	}
	
	void setText(){
		
	}
}
