package formule;

import javax.swing.JPanel;


public abstract class ContenuItem {
	
	public String getText(){
		return "";
	}
	
	public String getType(){
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
	
	public Formule[] getArraySplit(){
		return null;
	}
	
	void setText(){
		
	}
}
