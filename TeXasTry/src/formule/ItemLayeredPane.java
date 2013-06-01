package formule;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLayeredPane;


public class ItemLayeredPane extends JLayeredPane{
	
	private int depth;
	private int width;
	private int height;
	private Formule formuleMere;
	
	public ItemLayeredPane(int depth, Formule formuleMere){
		
		
		
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
		
//		this.addFocusListener(this);
		
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
//	
//	@Override
//	public void focusGained(FocusEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	@Override
//	public void focusLost(FocusEvent arg0) {
//		// TODO Auto-generated method stub
//		System.out.println("FocusLost");
//		this.formuleMere.layeredPaneHasLostFocus();
//	}
}
