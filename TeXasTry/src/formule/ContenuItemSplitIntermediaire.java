package formule;

import javax.swing.JPanel;


public class ContenuItemSplitIntermediaire extends ContenuItem{
	
	private ContenuItemSplit cis;
	
	public ContenuItemSplitIntermediaire(ItemLayeredPane layeredPane, String type){
		this.cis = new ContenuItemSplit(layeredPane, type);
	}
	
	public String getText(){
		return this.cis.getText();
	}
	
	public ContenuItemSplit getCIS(){
		return this.cis;
	}

}
