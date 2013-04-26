package formule;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class Item extends JPanel{
	
	//private int couleur = (int) Math.round(5 * Math.random());
	private boolean isSelected = false;
	private boolean toBeDeleted = false;
	private ItemLayeredPane layeredPane;
	private BoutonInvisible boutonInvisible;
	private Formule formuleMere;
	
	
	public Item (int depth, ItemLayeredPane itemLayeredPane, BoutonInvisible boutonInvisible){
		
		this.layeredPane = itemLayeredPane;
		this.boutonInvisible = boutonInvisible;
		
		
		
		this.setSize(this.layeredPane.getPreferredSize().width, this.layeredPane.getPreferredSize().height);		
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));


		this.setBackground(Color.white);

	}
		
	
	public JPanel getItemPanel(){
		return this;
	}

	public void setSelected(boolean b){
		this.isSelected = b;
		this.repaint();
	}
	
	public boolean getSelected(){
		return this.isSelected;
	}
	
	public void setToBeDeleted(boolean b){
		this.toBeDeleted = b;
		this.repaint();
	}
	
	public boolean getToBeDeleted(){
		return this.toBeDeleted;
	}
	
	public void redefinirApparence(){
		
		this.setSize(this.layeredPane.getPreferredSize().width, this.layeredPane.getPreferredSize().height);	
		
		if (toBeDeleted){
			this.setBorder(BorderFactory.createLineBorder(Color.red));
		}
		else if (!isSelected){
			this.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		else{
			this.setBorder(BorderFactory.createLineBorder(Color.green));
		}
	}


	public ItemLayeredPane getLayeredPane() {
		return this.layeredPane;
	}
	public BoutonInvisible getBoutonInvisible() {
		return this.boutonInvisible;
	}
	
	public Formule getFormuleMere(){
		return this.formuleMere;		
	}
	
}
