package formule;

public class ContenuItemTexteIntermediaire extends ContenuItem{

	private ItemLayeredPane layeredPane;
	private ContenuItemTexte cit;
	
	
	public ContenuItemTexteIntermediaire(ItemLayeredPane layeredPane){
		cit = new ContenuItemTexte(layeredPane);
	}
	
	
	public String getText(){
		return this.cit.getText();
	}
	
	public ContenuItemTexte getCIT(){
		return this.cit;
	}
	
	public String getType(){
		return this.cit.getType();
	}
	
}
