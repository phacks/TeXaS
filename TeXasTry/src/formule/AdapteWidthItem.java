package formule;
import javax.swing.JPanel;

/**
 * Thread qui permet de gérer la largeur d'un item en fonction de la largeur 
 * de la formule à l'intérieur (dans le cas d'une structure par exemple)
 * @author nicolasgoutay
 *
 */
public class AdapteWidthItem extends Thread {

	private Formule[] formuleArray;
	private ItemLayeredPane layeredPaneParent;
	private int maxWidth = 0;
	
	public AdapteWidthItem(Formule[] formuleArray, ItemLayeredPane layeredPaneParent){
		super();
		this.formuleArray = formuleArray;
		this.layeredPaneParent = layeredPaneParent;

		this.start();
		
	}
	
	public void run(){
		while(true){
			
			maxWidth = 0;
			
			for(int i = 0; i < formuleArray.length; i++){
				int w = formuleArray[i].getWidthFormule();
				if ( w > maxWidth){
					maxWidth = w;
				}
			}
			
			
			if(maxWidth != this.layeredPaneParent.getPreferredSize().width){

				this.layeredPaneParent.setWidth(this.maxWidth);
				this.layeredPaneParent.redefinirApparence();
				((Item) this.layeredPaneParent.getComponentsInLayer(0)[0]).redefinirApparence();
				((BoutonInvisible) this.layeredPaneParent.getComponentsInLayer(1)[0]).redefinirApparence();
				((ContenuItemSplit) this.layeredPaneParent.getComponentsInLayer(2)[0]).redefinirApparence();
								
				this.layeredPaneParent.getFormuleMere().repaintRevalidate();
//				
			}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}
	
}
