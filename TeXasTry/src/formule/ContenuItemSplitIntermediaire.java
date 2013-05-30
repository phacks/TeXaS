package formule;

import javax.swing.JPanel;


public class ContenuItemSplitIntermediaire extends ContenuItem{
	
	private ContenuItemSplit cis;
	
	public ContenuItemSplitIntermediaire(BoutonsAction ba,ItemLayeredPane layeredPane, String type){
		this.cis = new ContenuItemSplit(ba,layeredPane, type);
	}
	
	public String getText(){
		return this.cis.getText();
	}
	
	public ContenuItemSplit getCIS(){
		return this.cis;
	}

}
