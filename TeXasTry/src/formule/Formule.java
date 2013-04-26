package formule;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Formule implements ActionListener, KeyListener{

	private JPanel formuleContainer = new JPanel();
	private JPanel formule = new JPanel();
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<BoutonInsererItem> boutonsInsererItem = new ArrayList<BoutonInsererItem>();
	private ArrayList<BoutonInvisible> boutonsInvisibles = new ArrayList<BoutonInvisible>();
	private ArrayList<ItemLayeredPane> layeredpanes = new ArrayList<ItemLayeredPane>();
	private ArrayList<ContenuItem> contenuItems = new ArrayList<ContenuItem>();
	private BoutonsAction boutonsAction;
	private int depth;
	private ItemLayeredPane layeredPaneParent;
	private Formule[] formuleArray;
	private int width = 0;
	private AdapteWidthItem adapteWidthItem;
	private Image image;


	public Formule(BoutonsAction ba, JPanel c, int depth, ItemLayeredPane layeredPaneParent){

		this.formuleContainer = c;
		this.boutonsAction = ba;
		this.depth = depth;
		this.layeredPaneParent = layeredPaneParent;

		formuleContainer.add(formule);

		formule.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));


		layeredpanes.add(new ItemLayeredPane(this.depth, this));
		boutonsInvisibles.add(new BoutonInvisible(layeredpanes.get(0)));

		items.add(new Item(this.depth, layeredpanes.get(0), boutonsInvisibles.get(0)));



		contenuItems.add(new ContenuItemVide());

		layeredpanes.get(0).setFocusable(true);

		boutonsInvisibles.get(0).addActionListener(this);

		layeredpanes.get(0).add(items.get(0),new Integer(0));
		layeredpanes.get(0).add(boutonsInvisibles.get(0),new Integer(1));
		layeredpanes.get(0).addKeyListener(this);

		boutonsAction.splitAddActionListener(this);
		boutonsAction.addImageAddActionListener(this);


		gestionItems();

		//	if (formulesFreres != null){
		//		this.formuleArray = new Formule[this.formulesFreres.length +1];
		//	
		//	for(int i = 0; i < this.formulesFreres.length; i++){
		//		this.formuleArray[i] = this.formulesFreres[i];
		//	}
		//	
		//	formuleArray[this.formuleArray.length - 1] = this;
		//	}
		//	else{
		//		this.formuleArray = new Formule[1];
		//		this.formuleArray[0] = this;
		//	}
		//	
		//	if (this.layeredPaneParent != null){
		//		this.adapteWidthItem = new AdapteWidthItem(this.formuleArray, this.layeredPaneParent);
		//	}

	}



	public JPanel getFormuleContainer(){
		return this.formule;
	}

	private void gestionItems(){
		creeBoutonsInsererItem();

		afficheFormule();


		for(int i = 0; i < items.size() +1; i++){
			boutonsInsererItem.get(i).addActionListener(this);
		}
	}



	private void creeBoutonsInsererItem() {

		boutonsInsererItem = new ArrayList<BoutonInsererItem>();

		for(int i = 0; i < items.size() +1; i++){
			boutonsInsererItem.add(new BoutonInsererItem(this.depth));
		}

	}

	private void afficheFormule() {

		formule.removeAll();

		for(int i = 0; i < items.size(); i++){
			formule.add(boutonsInsererItem.get(i));

			formule.add(layeredpanes.get(i));

		}

		formule.add(boutonsInsererItem.get(items.size()));


		formule.revalidate();
		formule.repaint();

	}


	public void deselectItemParent(){
		if(layeredPaneParent != null){
			((Item) layeredPaneParent.getComponentsInLayer(0)[0]).setSelected(false);
			layeredPaneParent.redefinirApparence();
		}
	}


	public void repaintRevalidate(){
		formule.repaint();
		formule.revalidate();
	}



	public ItemLayeredPane getlayeredPaneParent(){
		return this.layeredPaneParent;
	}

	public int getWidthFormule(){

		this.width = 0;

		for(int i = 0; i < items.size(); i++){
			this.width += boutonsInsererItem.get(i).getPreferredSize().width;
			this.width = this.width + layeredpanes.get(i).getPreferredSize().width;
		}
		this.width = this.width + boutonsInsererItem.get(items.size()).getPreferredSize().width;

		return this.width;		
	}

	public void actionPerformed(ActionEvent arg0) {

		for(int i = 0; i < items.size() +1; i++){

			if(arg0.getSource() == boutonsInsererItem.get(i)){

				contenuItems.add(i, new ContenuItemVide());
				layeredpanes.add(i, new ItemLayeredPane(this.depth, this));
				boutonsInvisibles.add(i, new BoutonInvisible(layeredpanes.get(i)));

				items.add(i, new Item(this.depth, layeredpanes.get(i), boutonsInvisibles.get(i)));



				layeredpanes.get(i).add(items.get(i),new Integer(0));
				layeredpanes.get(i).add(boutonsInvisibles.get(i),new Integer(1));
				boutonsInvisibles.get(i).addActionListener(this);
				layeredpanes.get(i).addKeyListener(this);
				gestionItems();
			}
		}

		for(int i = 0; i < items.size(); i++){

			if(arg0.getSource() == boutonsInvisibles.get(i)){
				
				
				if (layeredPaneParent != null){
					ArrayList<ItemLayeredPane> layeredPaneParentArrayList = new ArrayList<ItemLayeredPane>();
					
					deselectAllItems(false, layeredPaneParentArrayList, items.get(i));
				}
				else{
					deselectAllItems(true, null, items.get(i));
				}
				
				if(layeredpanes.get(i).highestLayer() == 2){
					layeredpanes.get(i).setLayer(layeredpanes.get(i).getComponentsInLayer(1)[0], 2);
					layeredpanes.get(i).setLayer(boutonsInvisibles.get(i), 1);
				}


				if ( items.get(i).getSelected() && layeredpanes.get(i).highestLayer() == 1) {

					contenuItems.set(i, new ContenuItemTexteIntermediaire(layeredpanes.get(i)));
					layeredpanes.get(i).add(contenuItems.get(i).getCIT(), new Integer(2));
				}
				else{
					items.get(i).setSelected(true);
					items.get(i).setToBeDeleted(false);
					layeredpanes.get(i).requestFocusInWindow();
				}

				for(int k = 0; k < items.size(); k++){
					items.get(k).redefinirApparence();
				}
				
				formule.revalidate();
				formule.repaint();

			}
		}



		if (arg0.getSource() == boutonsAction.getBoutonSplit()){
			for (int i=0; i< items.size(); i++){


				if (items.get(i).getSelected() && layeredpanes.get(i).highestLayer() == 1){

					if (layeredPaneParent != null){

						if (this.depth >= 3){
							layeredPaneParent.agrandirEnCascade();
						}
						else{
							layeredPaneParent.agrandirEnCascade(5, 4);
						}

					}

					if (this.depth >= 3){
						layeredpanes.get(i).setHeight(layeredpanes.get(i).getPreferredSize().height + 56);					
					}
					else{
						layeredpanes.get(i).setHeight(layeredpanes.get(i).getPreferredSize().height * 3/2);
					}

					layeredpanes.get(i).redefinirApparence();
					items.get(i).redefinirApparence();
					boutonsInvisibles.get(i).redefinirApparence();

					contenuItems.set(i, new ContenuItemSplitIntermediaire(boutonsAction, layeredpanes.get(i)));

					layeredpanes.get(i).add(contenuItems.get(i).getCIS(), new Integer(2));


					formule.revalidate();
					formule.repaint();
				}
			}

		}

		if (arg0.getSource() == boutonsAction.getBoutonAddImage()){


			try {
				image = ImageIO.read(new File("chattoutpetit.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for(int i = 0; i < items.size(); i++){
				if (items.get(i).getSelected() && layeredpanes.get(i).highestLayer() == 1){
					contenuItems.set(i, new ContenuItemImageIntermediaire(layeredpanes.get(i)));
					layeredpanes.get(i).add(contenuItems.get(i).getCII(), new Integer(2));
					contenuItems.get(i).getCII().setText("Bonjour");
					contenuItems.get(i).getCII().setImage(image);
					contenuItems.get(i).getCII().repaint();
					formule.revalidate();
					formule.repaint();
				}
			}


		}
	}



	public void deselectAllItems(boolean aAtteintFormuleMere, ArrayList<ItemLayeredPane> layeredPaneParentArrayList, Item item) {

		if (layeredPaneParent != null && ! aAtteintFormuleMere){
			layeredPaneParentArrayList.add(this.layeredPaneParent);
			this.layeredPaneParent.getFormuleMere().deselectAllItems(false, layeredPaneParentArrayList, item);
		}
		else{

			for(int k = 0; k < items.size(); k++){

				if (items.get(k) != item){
				items.get(k).setSelected(false);
				items.get(k).setToBeDeleted(false);
				}

				if ((layeredpanes.get(k).highestLayer() == 2 && layeredPaneParentArrayList == null) 
					|| (layeredpanes.get(k).highestLayer() == 2 && layeredPaneParentArrayList.size() == 0)
					|| (layeredpanes.get(k).highestLayer() == 2 && layeredpanes.get(k) != layeredPaneParentArrayList.get(layeredPaneParentArrayList.size() - 1))){
					layeredpanes.get(k).setLayer(layeredpanes.get(k).getComponentsInLayer(2)[0], 1);
					layeredpanes.get(k).setLayer(boutonsInvisibles.get(k), 2);
				}

				if (	(layeredpanes.get(k).highestLayer() == 2 && layeredpanes.get(k).getComponentsInLayer(1)[0].getClass().toString().equals("class ContenuItemSplit")
						 && layeredPaneParentArrayList == null)
						 || (layeredpanes.get(k).highestLayer() == 2 && layeredpanes.get(k).getComponentsInLayer(1)[0].getClass().toString().equals("class ContenuItemSplit")
								 && layeredPaneParentArrayList.size() == 0)
						|| (layeredpanes.get(k).highestLayer() == 2 && layeredpanes.get(k).getComponentsInLayer(1)[0].getClass().toString().equals("class ContenuItemSplit")
					 && layeredpanes.get(k) != layeredPaneParentArrayList.get(layeredPaneParentArrayList.size() - 1))
					 ){
					
					ContenuItemSplit cis = (ContenuItemSplit) layeredpanes.get(k).getComponentsInLayer(1)[0];
					
					if (layeredPaneParentArrayList != null && layeredPaneParentArrayList.size() != 0 && layeredpanes.get(k) == layeredPaneParentArrayList.get(layeredPaneParentArrayList.size() - 1)){
					layeredPaneParentArrayList.remove(layeredPaneParentArrayList.size() - 1);
					cis.getSplitHaut().deselectAllItems(true, layeredPaneParentArrayList, item);
					cis.getSplitBas().deselectAllItems(true, layeredPaneParentArrayList, item);
					}
					else{
						cis.getSplitHaut().deselectAllItems(true, layeredPaneParentArrayList, item);
						cis.getSplitBas().deselectAllItems(true, layeredPaneParentArrayList, item);
					}
				}
				
				if ((layeredpanes.get(k).highestLayer() == 2 && layeredpanes.get(k).getComponentsInLayer(2)[0].getClass().toString().equals("class ContenuItemSplit"))
						 && layeredpanes.get(k) == layeredPaneParentArrayList.get(layeredPaneParentArrayList.size() - 1)){
						ContenuItemSplit cis = (ContenuItemSplit) layeredpanes.get(k).getComponentsInLayer(2)[0];
						
						if (layeredPaneParentArrayList != null && layeredPaneParentArrayList.size() != 0 && layeredpanes.get(k) == layeredPaneParentArrayList.get(layeredPaneParentArrayList.size() - 1)){
						layeredPaneParentArrayList.remove(layeredPaneParentArrayList.size() - 1);
						cis.getSplitHaut().deselectAllItems(true, layeredPaneParentArrayList, item);
						cis.getSplitBas().deselectAllItems(true, layeredPaneParentArrayList, item);
						}
						else{
							cis.getSplitHaut().deselectAllItems(true, layeredPaneParentArrayList, item);
							cis.getSplitBas().deselectAllItems(true, layeredPaneParentArrayList, item);
						}
					}

				items.get(k).redefinirApparence();
			}

			formule.revalidate();
			formule.repaint();

		}

	}

	//			for(int k = 0; k < items.size(); k++){
	//				if(k != i){
	//				items.get(k).setSelected(false);
	//				items.get(k).setToBeDeleted(false);
	//					
	//					if (layeredpanes.get(k).highestLayer() == 2 && bool){
	//						layeredpanes.get(k).setLayer(layeredpanes.get(k).getComponentsInLayer(2)[0], 1);
	//						layeredpanes.get(k).setLayer(boutonsInvisibles.get(k), 2);
	//					}
	//					
	//					if (layeredpanes.get(k).highestLayer() == 2 && layeredpanes.get(k).getComponentsInLayer(1)[0].getClass().toString().equals("class ContenuItemSplit")){
	//						ContenuItemSplit cis = (ContenuItemSplit) layeredpanes.get(k).getComponentsInLayer(1)[0];
	//						cis.getSplitHaut().deselectAllItems();
	//						cis.getSplitBas().deselectAllItems();
	//					}
	//				}
	//				
	//			if(k == i && layeredpanes.get(k).highestLayer() == 2){
	//				layeredpanes.get(k).setLayer(layeredpanes.get(k).getComponentsInLayer(1)[0], 2);
	//				layeredpanes.get(k).setLayer(boutonsInvisibles.get(k), 1);
	//			}
	//				
	//			}
	//			
	//			if(i > -1){
	//			
	//				if (layeredpanes.get(i).highestLayer() == 2 && layeredpanes.get(i).getComponentsInLayer(2)[0].getClass().toString().equals("class ContenuItemSplit")){
	//					ContenuItemSplit cis = (ContenuItemSplit) layeredpanes.get(i).getComponentsInLayer(2)[0];
	//					cis.getSplitHaut().deselectAllItems();
	//					cis.getSplitBas().deselectAllItems();
	//				}
	//				
	//				if (layeredpanes.get(i).getDepth() > 1){
	//					((Item) this.layeredPaneParent.getComponentsInLayer(0)[0]).setSelected(false);
	//					((Item) this.layeredPaneParent.getComponentsInLayer(0)[0]).redefinirApparence();
	//				}
	//				
	//				if ( items.get(i).getSelected() && layeredpanes.get(i).highestLayer() == 1) {
	//					
	//					contenuItems.set(i, new ContenuItemTexteIntermediaire(layeredpanes.get(i)));
	//					layeredpanes.get(i).add(contenuItems.get(i).getCIT(), new Integer(2));
	//					
	//				}
	//				else{
	//					items.get(i).setSelected(true);
	//					items.get(i).setToBeDeleted(false);
	//					layeredpanes.get(i).requestFocusInWindow();
	//				}
	//			
	//			}
	//			
	//			for(int k = 0; k < items.size(); k++){
	//				items.get(k).redefinirApparence();
	//			}
	//			formule.revalidate();
	//			formule.repaint();
	//			
	//			if (this.layeredPaneParent != null){
	//				this.layeredPaneParent.getFormuleMere().deselectAllItemsExcept(-1, false);
	//			}








	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == 8){
			for (int i=0; i< items.size(); i++){

				if (items.get(i).getToBeDeleted()){
					items.remove(i);
					boutonsInvisibles.remove(i);
					layeredpanes.remove(i);
					gestionItems();
					return;
				}

				if (items.get(i).getSelected()){
					items.get(i).setToBeDeleted(true);
					items.get(i).setSelected(false);
					items.get(i).redefinirApparence();
				}

			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}


