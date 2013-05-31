package formule;

import javax.swing.JPanel;


public abstract class ContenuItem {
	
	String getText(){
		return "";
	}
	
	ContenuItemTexte getCIT(){
		return null;
	}
	
	ContenuItemImage getCII(){
		return null;
	}
	
	ContenuItemSplit getCIS(){
		return null;
	}
	
	void setText(){
		
	}
}
