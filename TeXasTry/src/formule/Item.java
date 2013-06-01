package formule;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import briquesElementaires.Couleur;
import briquesElementaires.DashedBorder;


/**
 * Classe de base, qui représente un item. Son utilité est de retenir l'état sélectionné
 * ou non de l'item en cours, et de la traduire visuellement (couleur des bordures).
 * Un item est toujours le layer le plus bas d'un ItemLayeredPane
 * @author nicolasgoutay
 *
 * @see ItemLayeredPane
 */
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
		
		this.setBorder(new DashedBorder(Couleur.lightgray));


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
			this.setBorder(new DashedBorder(Couleur.red));
		}
		else if (!isSelected){
			this.setBorder(new DashedBorder(Couleur.lightgray));
		}
		else{
			this.setBorder(new DashedBorder(Couleur.bleuClair));
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
