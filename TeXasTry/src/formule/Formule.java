package formule;

import java.awt.Color;
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

import briquesElementaires.Couleur;

import EcranEditionCentral.EditeurFormule;


/**
 * Classe de base de la gestion des formules. Tous les �l�ments qui participent � la cr�ation et
 * � la gestion des formules y sont rattach�s. Elle fait aussi la gestion avec la classe EditeurFormule, qui g�re la partie affichage.
 * @author nicolasgoutay
 *
 * @param formuleContainer JPanel qui contient la formule
 * @param depth Entier d�signant la profondeur hi�rarchique de la formule
 * @param layeredPaneParent Dans le cas d'une formule de profondeur au moins 2, d�signe le LayeredPane dans lequel est contenue la formule
 *
 *
 * @see LayeredPane
 * @see Item
 * @see BoutonInsererItem
 * @see BoutonInvisible
 * @see ContenuItem
 * @see ContenuItemTexte
 * @see ContenuItemImage
 * @see ContenuItemSplit
 * @see EditeurFormule
 */
public class Formule implements ActionListener, KeyListener{

	private JPanel formuleContainer = new JPanel();
	private JPanel formule = new JPanel();
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<BoutonInsererItem> boutonsInsererItem = new ArrayList<BoutonInsererItem>();
	private ArrayList<BoutonInvisible> boutonsInvisibles = new ArrayList<BoutonInvisible>();
	private ArrayList<ItemLayeredPane> layeredpanes = new ArrayList<ItemLayeredPane>();
	private ArrayList<ContenuItem> contenuItems = new ArrayList<ContenuItem>();
	//	private BoutonsAction boutonsAction;
	private int depth;
	private ItemLayeredPane layeredPaneParent;
	private Formule[] formuleArray;
	private int width = 0;
	private AdapteWidthItem adapteWidthItem;
	private Image image;
	private EditeurFormule editeur = null;
	private ActionListenerFormule actionListenerFormule = new ActionListenerFormule("");
	private boolean layeredPaneParentAgrandi = false;


	public Formule(JPanel jPanel, int depth, ItemLayeredPane layeredPaneParent){
		
		this.actionListenerFormule.setFormuleEnCours(this);
		
		
		formule.addKeyListener(this);

		this.formuleContainer = jPanel;

		//		this.boutonsAction = ba;
		this.depth = depth;
		this.layeredPaneParent = layeredPaneParent;

		formuleContainer.add(formule);


		this.formule.setBackground(Couleur.white);
		this.formuleContainer.setBackground(Couleur.white);

		formule.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));


		layeredpanes.add(new ItemLayeredPane(this.depth, this));
		boutonsInvisibles.add(new BoutonInvisible(layeredpanes.get(0)));

		items.add(new Item(this.depth, layeredpanes.get(0), boutonsInvisibles.get(0)));

		contenuItems.add(new ContenuItemVide());

		layeredpanes.get(0).setFocusable(true);

		boutonsInvisibles.get(0).addActionListener(this);

		layeredpanes.get(0).add(items.get(0),new Integer(0));
		layeredpanes.get(0).add(boutonsInvisibles.get(0),new Integer(1));
		

		gestionItems(0);

		layeredpanes.get(0).requestFocusInWindow();
		layeredpanes.get(0).grabFocus();

		
		layeredpanes.get(0).addKeyListener(this);
		
		items.get(0).setSelected(false);
		items.get(0).setToBeDeleted(false);
		
		
		items.get(0).redefinirApparence();
		
		this.repaintRevalidate();
		


	}
	
	
	public Formule(EditeurFormule editeur, JPanel jPanel, int depth, ItemLayeredPane layeredPaneParent){
		
		this(jPanel,depth,layeredPaneParent);
		this.editeur = editeur;
		
	}
	


	public JPanel getFormuleContainer(){
		return this.formule;
	}

	/**
	 * Permet de mettre en place l'affichage de la formule en cr�ant et en ins�rant le bon nombre de BoutonInsererItem
	 * @param k permet de donner le focus � l'item d'indice k
	 */
	private void gestionItems(int k){
		creeBoutonsInsererItem();

		afficheFormule();


		for(int i = 0; i < items.size() +1; i++){
			boutonsInsererItem.get(i).addActionListener(this);
		}
		
		if (k != -1){
		layeredpanes.get(k).requestFocus();
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

	public ArrayList<ContenuItem> getContenuItemArrayList(){
		return this.contenuItems;
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
				
				insererItem(i);

				
			}
		}

		for(int i = 0; i < items.size(); i++){

			if(arg0.getSource() == boutonsInvisibles.get(i)){

				/* Si l'on clique sur un "bouton invisible", l'item correspondant va devenir 
				 * s�lectionn� (changement de couleur de bordure), et les autres items 
				 * sont d�selectionn�s .
				 * De plus, si l'item n'est pas vide, le contenu de l'item va passer sur le 
				 * layer sup�rieur et le bouton invisible sur le layer inf�rieur. 
				 */
				
				// S�lection de la formule en cours en cas d'int�raction avec les boutons du ruban
				this.actionListenerFormule.setFormuleEnCours(this);




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

				items.get(i).setSelected(true);
				items.get(i).setToBeDeleted(false);
				layeredpanes.get(i).requestFocus();


				for(int k = 0; k < items.size(); k++){
					items.get(k).redefinirApparence();
				}

				formule.revalidate();
				formule.repaint();

			}
		}

	}

	/**
	 * Lors d'un clic sur un bouton ins�rer item, un bouton du ruban ou une saisie clavier, 
	 * un item est ins�r� (sous certaines conditions).
	 * @param i Indice d'iinsertion de l'item
	 */
	private void insererItem(int i) {
		// TODO Auto-generated method stub
		this.actionListenerFormule.setFormuleEnCours(this);

		contenuItems.add(i, new ContenuItemVide());
		layeredpanes.add(i, new ItemLayeredPane(this.depth, this));
		boutonsInvisibles.add(i, new BoutonInvisible(layeredpanes.get(i)));

		items.add(i, new Item(this.depth, layeredpanes.get(i), boutonsInvisibles.get(i)));



		layeredpanes.get(i).add(items.get(i),new Integer(0));
		layeredpanes.get(i).add(boutonsInvisibles.get(i),new Integer(1));
		boutonsInvisibles.get(i).addActionListener(this);
		layeredpanes.get(i).addKeyListener(this);

		if (layeredPaneParent != null){
			ArrayList<ItemLayeredPane> layeredPaneParentArrayList = new ArrayList<ItemLayeredPane>();

			deselectAllItems(false, layeredPaneParentArrayList, items.get(i));
		}
		else{
			deselectAllItems(true, null, items.get(i));
		}

		items.get(i).setSelected(true);
		items.get(i).requestFocus();
		items.get(i).redefinirApparence();
		
		gestionItems(i);
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



				if (	(layeredpanes.get(k).highestLayer() == 2 && layeredpanes.get(k).getComponentsInLayer(1)[0].getClass().toString().startsWith("class formule.ContenuItemSplit")
						&& layeredPaneParentArrayList == null)
						|| (layeredpanes.get(k).highestLayer() == 2 && layeredpanes.get(k).getComponentsInLayer(1)[0].getClass().toString().startsWith("class formule.ContenuItemSplit")
						&& layeredPaneParentArrayList.size() == 0)
						|| (layeredpanes.get(k).highestLayer() == 2 && layeredpanes.get(k).getComponentsInLayer(1)[0].getClass().toString().startsWith("class formule.ContenuItemSplit")
						&& layeredpanes.get(k) != layeredPaneParentArrayList.get(layeredPaneParentArrayList.size() - 1))
						){

					ContenuItemSplit cis = (ContenuItemSplit) layeredpanes.get(k).getComponentsInLayer(1)[0];


					if (layeredPaneParentArrayList != null && layeredPaneParentArrayList.size() != 0 && layeredpanes.get(k) == layeredPaneParentArrayList.get(layeredPaneParentArrayList.size() - 1)){
						layeredPaneParentArrayList.remove(layeredPaneParentArrayList.size() - 1);

						for (int i = 0; i < cis.getArraySplit().length; i++){
							cis.getArraySplit()[i].deselectAllItems(true, layeredPaneParentArrayList, item);
						}

					}
					else{
						for (int i = 0; i < cis.getArraySplit().length; i++){
							cis.getArraySplit()[i].deselectAllItems(true, layeredPaneParentArrayList, item);
						}
					}
				}

				if ((layeredpanes.get(k).highestLayer() == 2 && layeredpanes.get(k).getComponentsInLayer(2)[0].getClass().toString().equals("class formule.ContenuItemSplit"))
						&& layeredpanes.get(k) == layeredPaneParentArrayList.get(layeredPaneParentArrayList.size() - 1)){
					ContenuItemSplit cis = (ContenuItemSplit) layeredpanes.get(k).getComponentsInLayer(2)[0];


					if (layeredPaneParentArrayList != null && layeredPaneParentArrayList.size() != 0 && layeredpanes.get(k) == layeredPaneParentArrayList.get(layeredPaneParentArrayList.size() - 1)){
						layeredPaneParentArrayList.remove(layeredPaneParentArrayList.size() - 1);
						for (int i = 0; i < cis.getArraySplit().length; i++){
							cis.getArraySplit()[i].deselectAllItems(true, layeredPaneParentArrayList, item);
						}
					}
					else{
						for (int i = 0; i < cis.getArraySplit().length; i++){
							cis.getArraySplit()[i].deselectAllItems(true, layeredPaneParentArrayList, item);
						}
					}
				}

				items.get(k).redefinirApparence();
			}

			formule.revalidate();
			formule.repaint();

		}

	}







	@Override
	public void keyPressed(KeyEvent arg0) {
		long lastPressProcessed = 0;
				
		/* 
		 * Sur pression de la touche backspace, l'item s�lectionn� devient rouge.
		 * Sur une deuxi�me pression, l'item est supprim�
		 */
		if(arg0.getKeyCode() == 8){
			for (int i=0; i< items.size(); i++){
				
				if (items.get(i).getToBeDeleted()){
					items.remove(i);
					boutonsInvisibles.remove(i);
					layeredpanes.remove(i);
					gestionItems(-1);
					if (this.items.size() == 0 && this.editeur != null){
					this.editeur.deleteFormule();
					}
				}
				
				else if (items.get(i).getSelected()){
					items.get(i).setToBeDeleted(true);
					items.get(i).setSelected(false);
					items.get(i).redefinirApparence();
				}

			}
		}
		
		/*
		 * Sur une pression d'une touche alphanum�rique, il y a cr�ation d'un nouvel item et / ou 
		 * remplissage de l'item s�lectionn� 
		 */
		if((! arg0.isActionKey()) && (arg0.getKeyCode() != KeyEvent.VK_ENTER) && (arg0.getKeyCode() != 16)){
			
			// Le timer est l� pour �viter de saisir deux �v�nements KeyPressed sur une pression prolong�e
			if(System.currentTimeMillis() - lastPressProcessed > 500) {
	            
				for (int i=0; i< items.size(); i++){
					
					if (items.get(i).getSelected() && layeredpanes.get(i).highestLayer() == 1){
						contenuItems.set(i, new ContenuItemTexteIntermediaire(layeredpanes.get(i)));
						layeredpanes.get(i).add(contenuItems.get(i).getCIT(), new Integer(2));
						contenuItems.get(i).getCIT().setText(Character.toString(arg0.getKeyChar()));
						contenuItems.get(i).getCIT().requestFocus();

						items.get(i).redefinirApparence();

						formule.revalidate();
						formule.repaint();
					}
					
					else if (items.get(i).getSelected() && layeredpanes.get(i).highestLayer() == 2){
						insererItem(i+1);
						contenuItems.set(i+1, new ContenuItemTexteIntermediaire(layeredpanes.get(i+1)));
						layeredpanes.get(i+1).add(contenuItems.get(i+1).getCIT(), new Integer(2));
						contenuItems.get(i+1).getCIT().setText(Character.toString(arg0.getKeyChar()));
						contenuItems.get(i+1).getCIT().requestFocus();

						items.get(i+1).redefinirApparence();

						formule.revalidate();
						formule.repaint();
						break;
					}
				}
				
	            lastPressProcessed = System.currentTimeMillis();
	        }   
			
			
		}
		
		/*
		 * Sur pression de la touche Entr�e, on sort de l'�dition de la formule et 
		 * on cr�e un nouveau paragraphe en dessous
		 */
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
		
			this.editeur.addEditeurParagraphe();
			
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
	
	/**
	 * Assigner une image � l'item s�lectionn�
	 * @param fileImage L'image � assigner
	 */
	public void addImage(File fileImage) {
		// TODO Auto-generated method stub
		try {
			image = ImageIO.read(fileImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i = 0; i < items.size(); i++){
			if (items.get(i).getSelected() && layeredpanes.get(i).highestLayer() == 1){
				contenuItems.set(i, new ContenuItemImageIntermediaire(layeredpanes.get(i)));
				layeredpanes.get(i).add(contenuItems.get(i).getCII(), new Integer(2));
				contenuItems.get(i).getCII().setText(fileImage.getName().split("\\.")[0]);
				contenuItems.get(i).getCII().setImage(image);
				contenuItems.get(i).getCII().repaint();
				formule.revalidate();
				formule.repaint();
			}
			
			else if (items.get(i).getSelected() && layeredpanes.get(i).highestLayer() == 2){
				insererItem(i + 1);
				contenuItems.set(i+1, new ContenuItemImageIntermediaire(layeredpanes.get(i+1)));
				layeredpanes.get(i+1).add(contenuItems.get(i+1).getCII(), new Integer(2));
				contenuItems.get(i+1).getCII().setText(fileImage.getName().split("\\.")[0]);
				contenuItems.get(i+1).getCII().setImage(image);
				contenuItems.get(i+1).getCII().repaint();
				formule.revalidate();
				formule.repaint();
				break;
			}

		}

	}
	
	/**
	 * Assigner une structure � l'item s�lectionn�
	 * @param string Le type de structure � ins�rer (pour l'instant, seules les fractions sont disponibles)
	 */
	public void addStructure(String string) {
		// TODO Auto-generated method stub


		if (string .equals("structure_fraction")){
			for (int i=0; i< items.size(); i++){


				if ((items.get(i).getSelected()) && (layeredpanes.get(i).highestLayer() == 1) ){



					if (layeredPaneParent != null  && (this.layeredPaneParent.getDepth() > 1 || ! this.layeredPaneParent.getAgrandi())){

						this.layeredPaneParent.setAgrandi(true);
						if (this.depth >= 3){
							layeredPaneParent.agrandirEnCascade();
						}
						else{
							layeredPaneParent.agrandirEnCascade(3, 2);
						}

					}

					if (this.depth >= 3){
						layeredpanes.get(i).setHeight(layeredpanes.get(i).getPreferredSize().height + 28);					
					}
					else{
						layeredpanes.get(i).setHeight(layeredpanes.get(i).getPreferredSize().height * 3/2 + 5);
					}

					layeredpanes.get(i).redefinirApparence();
					items.get(i).redefinirApparence();
					boutonsInvisibles.get(i).redefinirApparence();



					contenuItems.set(i, new ContenuItemSplitIntermediaire(layeredpanes.get(i), "split-fraction"));

					layeredpanes.get(i).add(contenuItems.get(i).getCIS(), new Integer(2));


					formule.revalidate();
					formule.repaint();
				}

				else if (items.get(i).getSelected() && layeredpanes.get(i).highestLayer() == 2){

					insererItem(i+1);

					if (layeredPaneParent != null){

						if (this.depth >= 3){
							layeredPaneParent.agrandirEnCascade();
						}
						else{
							layeredPaneParent.agrandirEnCascade(3, 2);
						}

					}

					if (this.depth >= 3){
						layeredpanes.get(i+1).setHeight(layeredpanes.get(i+1).getPreferredSize().height + 28);					
					}
					else{
						layeredpanes.get(i+1).setHeight(layeredpanes.get(i+1).getPreferredSize().height * 3/2 + 5);
					}

					layeredpanes.get(i+1).redefinirApparence();
					items.get(i+1).redefinirApparence();
					boutonsInvisibles.get(i+1).redefinirApparence();



					contenuItems.set(i+1, new ContenuItemSplitIntermediaire(layeredpanes.get(i+1), "split-fraction"));

					layeredpanes.get(i+1).add(contenuItems.get(i+1).getCIS(), new Integer(2));


					formule.revalidate();
					formule.repaint();
					break;
				}
			}

		}
	}

	

}


