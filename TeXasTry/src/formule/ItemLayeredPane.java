package formule;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLayeredPane;


/**
 * Réceptacle de chaque item. Le système de layered pane permet de superposer plusieurs couches
 * (layer) : un Item comme couche de base, et les couches 2 et 3 alternent entre le BoutonInvisible 
 * et le ContenuItem associés
 * @author nicolasgoutay
 *
 * @see Item
 * @see ContenuItem
 * @see BoutonInvisible
 * 
 * @param formuleMere La formule de laquelle est issue le ItemLayeredPane
 *
 */
public class ItemLayeredPane extends JLayeredPane{
	
	private int depth;
	private int width;
	private int height;
	private Formule formuleMere;
	private boolean agrandi = false;
	
	public boolean getAgrandi() {
		return agrandi;
	}

	public void setAgrandi(boolean agrandi) {
		this.agrandi = agrandi;
	}

	public ItemLayeredPane(int depth, Formule formuleMere){
		
		
		// Gestion de la taille des items en fonction de leur profondeur
		if (depth <= 3){
			this.depth = depth;
			}
			else{
				this.depth = 3;
			}
		this.formuleMere = formuleMere;
		
		this.width = 50;
		this.height = 50;
		
		for(int i = 0; i< this.depth - 1; i++){
			this.width = (int) this.width *3/4 - 2;
			this.height = (int) this.height *3/4 - 2;
		}
		
		
		this.setPreferredSize(new Dimension(this.width,this.height));		
	}
	
	public void setWidth(int w){
		this.width = w;
	}
	
	public void setHeight(int h){
		this.height = h;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public void redefinirApparence(){
		this.setPreferredSize(new Dimension(this.width, this.height));
	}	
	
	public int getDepth(){
		return this.depth;
	}
	
	public Formule getFormuleMere(){
		return this.formuleMere;		
	}

	/* 
	 * Lors de la création d'une structure (ex. fraction), permet d'agrandir la hauteur de l'item
	 * en accord avec sa profondeur 
	 */
	public void agrandirEnCascade(int num, int denom) {
		

		this.setHeight((int) this.getPreferredSize().height * num / denom);
		this.redefinirApparence();
		((Item) this.getComponentsInLayer(0)[0]).redefinirApparence();
		((BoutonInvisible) this.getComponentsInLayer(1)[0]).redefinirApparence();
		
		if(this.getComponentsInLayer(2)[0].getClass().toString().equals("class formule.ContenuItemSplit")){
			((ContenuItemSplit)this.getComponentsInLayer(2)[0]).redefinirApparence();
		}
		
		if(formuleMere.getlayeredPaneParent() != null){
			formuleMere.getlayeredPaneParent().agrandirEnCascade(num + denom, denom * 2);
		}
		
		this.getFormuleMere().repaintRevalidate();
		
	}
	
	public void agrandirEnCascade() {
		

		this.setHeight((int) this.getPreferredSize().height + 28);
		this.redefinirApparence();
		((Item) this.getComponentsInLayer(0)[0]).redefinirApparence();
		((BoutonInvisible) this.getComponentsInLayer(1)[0]).redefinirApparence();
		
		if(this.getComponentsInLayer(2)[0].getClass().toString().startsWith("class formule.ContenuItemSplit")){
			((ContenuItemSplit)this.getComponentsInLayer(2)[0]).redefinirApparence();
		}
		
		if(formuleMere.getlayeredPaneParent() != null){
			formuleMere.getlayeredPaneParent().agrandirEnCascade();
		}
		
		this.getFormuleMere().repaintRevalidate();
		
	}

}
