package formule;

public class ContenuItemImageIntermediaire extends ContenuItem{
	
	private ContenuItemImage cii;
	
	public ContenuItemImageIntermediaire(ItemLayeredPane layeredPane){
		this.cii = new ContenuItemImage(layeredPane);
	}
	
	public String getText(){
		return this.cii.getText();
	}
	
	public ContenuItemImage getCII(){
		return this.cii;
	}
	

}
