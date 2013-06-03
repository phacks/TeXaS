package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.Box;

import briquesElementaires.JPanelDef;


/**
 * <b>ContenuEditable est la classe représentant les Editeurs affichés dans le logiciel</b>
 * <p>
 * Le contenu est représenté par les informations suivantes
 * <ul>
 * <li>Une liste des contenus affichés.</li>
 * <li>Un tableau de liste des contenus masqué.</li>
 * <li>Un tableau des coordonnées hiérarchiques de l'éditeur actuel</li>
 * </ul>
 * </p>
 * 
 * @author Kilian
 **/
public class ContenuEditable extends JPanelDef {

	private static List<Editeur> listeContenu = new LinkedList<Editeur>();
	private static Box conteneurGeneral = Box.createVerticalBox();

	private static LinkedList[] tabListeContenuMasque = {new LinkedList<Editeur>(), 
		new LinkedList<Editeur>(), 
		new LinkedList<Editeur>(), 
		new LinkedList<Editeur>(), 
		new LinkedList<Editeur>()};

	private static int[] coordHierarchieActuelle = { 0 , 0 , 0 , 0 , 0};


	// >>>>>>>>>>> Getters and setters
	  /**
     * Retourne la liste des editeurs affichés à l'écran central
     */
	public static List<Editeur> getListeContenu() {
		return listeContenu;
	}

	  /**
     * Modifie la liste des éditeurs affichés à l'écran central
     * 
     * @param listeContenu
     * 			La nouvelle liste des éditeurs
     */
	public static void setListeContenu(List<Editeur> listeContenu) {
		ContenuEditable.listeContenu = listeContenu;
	}
	
	  /**
     * Retourne la taille de la liste des editeurs masqués à l'écran central
     */
	public static int getSizeListeContenuMasque(int i) {
		return tabListeContenuMasque[i].size();
	}

	 /**
     * Retourne le premier conteneur masqué au niveau hierarchique i
     * 
     * @param i
     * 			Le niveau hierarchique considéré
     */
	public static Editeur getFirstMasque(int i){
		return (Editeur) tabListeContenuMasque[i].get(0);
	}
	// >>>>>>>>>>> Fin getters and setters

	// Constructeur de classe
	public ContenuEditable(){
		super(new BorderLayout());

		listeContenu.add(new EditeurParagraphe());

		conteneurGeneral.add(listeContenu.get(0));

		this.add(conteneurGeneral, BorderLayout.NORTH);

	}

	/**
     * Reconstruit l'écran central, renumerote les composant, rétablit les indentations.
     */
	public static void revalider(){
		conteneurGeneral.removeAll();
		// Recalcul de la numérotation
		for (int i = 0; i < coordHierarchieActuelle.length; i++) {
			coordHierarchieActuelle[i]=0;
		}
		// Parcours de la liste des composants, ajout et gestion de leur numérotation
		ListIterator<Editeur> iterator = listeContenu.listIterator();
		while(iterator.hasNext()){
			Editeur tmp = iterator.next();
			// Gestion numérotation composant
			if(tmp.getClass().toString().contains("EditeurTitre")){
				EditeurTitre tmp2 = (EditeurTitre) tmp;
				if(tmp2.isNumerote()){
					int numeroHierarchieTmp = tmp2.getNumeroHierarchie();
					coordHierarchieActuelle[numeroHierarchieTmp]++;
					for (int i = numeroHierarchieTmp+1; i < 5; i++) {
						coordHierarchieActuelle[i]=0;
					}
					tmp2.setCoordHierarchie(coordHierarchieActuelle);
				}
				tmp2.renumeroter();
			}
			// Gestion indentation bloc paragraphe
			if(tmp.getClass().toString().contains("EditeurParagraphe")){
				EditeurParagraphe tmp2 = (EditeurParagraphe) tmp;
				int numeroHierarchie = 4;
				while (numeroHierarchie > 0 && coordHierarchieActuelle[numeroHierarchie]==0){
					numeroHierarchie--;
				}
				tmp2.setNumeroHierarchie(numeroHierarchie);
				tmp2.reindenter();
			}
			// Ajout de l'élement
			conteneurGeneral.add(tmp);
			conteneurGeneral.add(Box.createVerticalStrut(5));
		}
		conteneurGeneral.revalidate();
	}
	/**
	* Fonction ajout éditeur paragraphe après l'éditeur c.
	* @param c
	* 			L'éditeur après lequel on désire ajouter l'éditeur de paragraphe.
	*/
	public static void addEditeurParagraphe(Editeur c){
		int indice = listeContenu.indexOf(c);
		listeContenu.add(indice+1, new EditeurParagraphe());
		revalider();
		EditeurParagraphe tmp = (EditeurParagraphe) listeContenu.get(indice+1);
		refocus(tmp);
		tmp.reindenter();
	}

	/** 
	 * Fonction ajout éditeur paragraphe après tous les éditeurs.
	 * @param textEditeur
	 * 			Le texte que l'on veut mettre dans l'éditeur de paragraphe.
	 */
	public static void addEditeurParagrapheAtTheEnd(String textEditeur){
		if(listeContenu.size()==1 && listeContenu.get(0).getClass().toString().contains("EditeurParagraphe")){
			EditeurParagraphe tmp = (EditeurParagraphe) listeContenu.get(0);
			if (tmp.getText().equals("Votre texte ici...") || tmp.getText().equals("")){
				listeContenu.remove(0);
			}
		}
		listeContenu.add(new EditeurParagraphe(textEditeur));
		revalider();
		EditeurParagraphe tmp = (EditeurParagraphe) listeContenu.get(listeContenu.size()-1);
		refocus(tmp);
		tmp.reindenter();
	}


	/**
	 * Fonction ajout éditeur de paragraphe après l'éditeur c, contenant le texte textEditeur
	 * @param c
	 * 			L'éditeur après lequel on désire ajouter l'éditeur de paragraphe.
	 * @param textEditeur
	 * 			Le texte que l'on désire ajouter dans l'éditeur de paragraphe
	 */
	public static void addEditeurParagraphe(Editeur c, String textEditeur) {
		int indice = listeContenu.indexOf(c);
		listeContenu.add(indice+1,new EditeurParagraphe(textEditeur));
		revalider();
		EditeurParagraphe tmp = (EditeurParagraphe) listeContenu.get(indice+1);
		refocus(tmp);
		tmp.reindenter();
		tmp.setCarretPosition(0);
	}


	/**
	 * Fonction d'ajout d'une formule, après le conteneur sélectionné actuellement
	 */
	public static void addEditeurFormule(){
		int indice=-1;
		for (int j = 0; j < listeContenu.size(); j++) {
			Editeur tmp = listeContenu.get(j);
			if (tmp.isSelected()){
				indice = j;
			}
		}
		if(indice==-1){
			indice = listeContenu.size()-1;
		}
		if(indice >= 0 && listeContenu.get(indice).getClass().toString().contains("EditeurParagraphe")){
			EditeurParagraphe tmp = (EditeurParagraphe) listeContenu.get(indice);
			if (tmp.getText().equals("Votre texte ici...") || tmp.getText().equals("")){
				listeContenu.remove(indice);
				indice--;
			}
		}
		listeContenu.add(indice+1, new EditeurFormule());
		revalider();
		EditeurFormule tmp = (EditeurFormule) listeContenu.get(indice+1);
		tmp.setSelected(true);
	}

	/**
	 *  Fonction ajout éditeur titre, de niveau hierarchique i, numéroté ou non, titré title
	 * @param i
	 * 			Le niveau hierarchique considéré
	 * @param numerotation
	 * 			Indique si il y a numérotation
	 * @param title
	 * 			Le titre de la partie
	 */
	public static void addEditeurTitre(int i,boolean numerotation, String title ){
		int indice=listeContenu.size()-1;
		for (int j = 0; j < listeContenu.size(); j++) {
			Editeur tmp = listeContenu.get(j);
			if (tmp.isSelected()){
				indice = j;
			}
		}
		if(listeContenu.get(indice).getClass().toString().contains("EditeurParagraphe")){
			EditeurParagraphe tmp = (EditeurParagraphe) listeContenu.get(indice);
			if (tmp.getText().equals("Votre texte ici...") || tmp.getText().equals("")){
				listeContenu.remove(indice);
				indice--;
			}
		}
		EditeurTitre nouveauTitre = new EditeurTitre(i, numerotation, title);
		listeContenu.add(indice+1, nouveauTitre);
		revalider();
		refocus(nouveauTitre);
		nouveauTitre.renumeroter();
	}

	/**
	 *  Fonction de destruction de l'éditeur C
	 * @param c
	 * 			Editeur à détruire
	 */
	public static void detruire(Editeur c) {
		int indice = listeContenu.indexOf(c);
		listeContenu.remove(indice);
		if(listeContenu.size()==0){
			listeContenu.add(new EditeurParagraphe());
		}
		revalider();
		if(indice>0){
			refocus(listeContenu.get(indice-1));
		}
		else{
			refocus(listeContenu.get(0));
		}
	}

	/**
	 *  Fonction de gestion des focus, refocus sur l'éditeur c
	 *  
	 * @param c
	 * 			Editeur que l'on focus
	 */
	public static void refocus(Editeur c) {
		ListIterator<Editeur> iterator = listeContenu.listIterator();
		while(iterator.hasNext()){
			Editeur tmp = iterator.next();
			if(!(tmp.equals(c))){
				tmp.setSelected(false);
			}
			else{
				tmp.setSelected(true);
			}
		}
	}

	/**
	 *  Fonction de gestion des focus, refocus sur l'éditeur suivant l'éditeur c
	 *  
	 * @param c
	 * 			Editeur référence
	 */
	public static void focusNext(Editeur c) {
		int indiceNext = listeContenu.indexOf(c)+1;
		if(indiceNext != listeContenu.size()){
			for (int i = 0; i < listeContenu.size(); i++) {
				Editeur tmp = listeContenu.get(i);
				if(i != indiceNext){
					tmp.setSelected(false);
				}
				else{
					tmp.setSelected(true);
				}
			}
		}
	}

	/**
	 * Fonction de gestion des focus, refocus sur l'éditeur précédent l'éditeur c
	 * 
	 * @param c
	 * 				Editeur reférence
	 */
	public static void focusPrevious(Editeur c) {
		int indiceNext = listeContenu.indexOf(c)-1;
		if(indiceNext != -1){
			for (int i = 0; i < listeContenu.size(); i++) {
				Editeur tmp = listeContenu.get(i);
				if(i != indiceNext){
					tmp.setSelected(false);
				}
				else{
					tmp.setSelected(true);
				}
			}
		}
	}

	/**
	 *  Fonction masquant/affichant les éditeurs suivant l'éditeur c, de niveau hierarchique inférieur à celui de l'éditeur c
	 *  
	 *  @param c
	 *  		Editeur à masquer/afficher
	 *  @param b
	 *  		VRAI pour masquer le composant, FALSE pour l'afficher
	 */
	public static void masquer(Editeur c, boolean b) {
		if(b){

			int numHierarchie = ((EditeurTitre)c).getNumeroHierarchie();
			((EditeurTitre)c).setMasque(true);
			int indiceCListeContenu = listeContenu.indexOf(c);
			int numActuel = numHierarchie+1;

			// Parcours des éléments de niveau hierarchique inférieur pour les masquer

			tabListeContenuMasque[numHierarchie].add(c);

			ListIterator<Editeur> iterator = listeContenu.listIterator(indiceCListeContenu);

			Editeur tmp = iterator.next();
			((EditeurTitre)tmp).setBoutonMasque(true);
			int nbElementaEnlever=0;
			while(numActuel>numHierarchie && iterator.hasNext()){
				tmp = iterator.next();
				if(tmp.getClass().toString().contains("EditeurTitre")){
					EditeurTitre tmp2 = (EditeurTitre) tmp;
					numActuel = tmp2.getNumeroHierarchie();
				}
				if(numActuel>numHierarchie){
					nbElementaEnlever++;
				}
			}
			for (int i = 0; i < nbElementaEnlever; i++) {
				tabListeContenuMasque[numHierarchie].add(listeContenu.get(indiceCListeContenu+1));
				listeContenu.remove(indiceCListeContenu+1);
			}
			revalider();
			refocus(c);
		}
		else{
			int numHierarchie = ((EditeurTitre)c).getNumeroHierarchie();
			((EditeurTitre)c).setMasque(false);
			int indiceCListeContenuMasque = tabListeContenuMasque[numHierarchie].indexOf(c);
			int indiceCListeContenu = listeContenu.indexOf(c);
			if(indiceCListeContenuMasque!=-1){
				ListIterator<Editeur> iterator = tabListeContenuMasque[numHierarchie].listIterator(indiceCListeContenuMasque);
				Editeur tmp = iterator.next();
				((EditeurTitre)tmp).setBoutonMasque(false);
				int nbElementaEnlever=1;
				boolean estPresent = false;
				while(!estPresent && iterator.hasNext()){
					tmp = iterator.next();
					estPresent = listeContenu.indexOf(tmp) != -1 ;
					if(!estPresent){
						listeContenu.add(indiceCListeContenu+nbElementaEnlever, tmp);
						nbElementaEnlever++;
					}
				}
				for (int i = 1; i <= nbElementaEnlever; i++) {
					tabListeContenuMasque[numHierarchie].remove(indiceCListeContenuMasque);
				}
				revalider();
				refocus(c);
				}
		}
	}

	
}
