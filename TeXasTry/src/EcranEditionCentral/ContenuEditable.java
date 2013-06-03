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
 * <b>ContenuEditable est la classe repr�sentant les Editeurs affich�s dans le logiciel</b>
 * <p>
 * Le contenu est repr�sent� par les informations suivantes
 * <ul>
 * <li>Une liste des contenus affich�s.</li>
 * <li>Un tableau de liste des contenus masqu�.</li>
 * <li>Un tableau des coordonn�es hi�rarchiques de l'�diteur actuel</li>
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
     * Retourne la liste des editeurs affich�s � l'�cran central
     */
	public static List<Editeur> getListeContenu() {
		return listeContenu;
	}

	  /**
     * Modifie la liste des �diteurs affich�s � l'�cran central
     * 
     * @param listeContenu
     * 			La nouvelle liste des �diteurs
     */
	public static void setListeContenu(List<Editeur> listeContenu) {
		ContenuEditable.listeContenu = listeContenu;
	}
	
	  /**
     * Retourne la taille de la liste des editeurs masqu�s � l'�cran central
     */
	public static int getSizeListeContenuMasque(int i) {
		return tabListeContenuMasque[i].size();
	}

	 /**
     * Retourne le premier conteneur masqu� au niveau hierarchique i
     * 
     * @param i
     * 			Le niveau hierarchique consid�r�
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
     * Reconstruit l'�cran central, renumerote les composant, r�tablit les indentations.
     */
	public static void revalider(){
		conteneurGeneral.removeAll();
		// Recalcul de la num�rotation
		for (int i = 0; i < coordHierarchieActuelle.length; i++) {
			coordHierarchieActuelle[i]=0;
		}
		// Parcours de la liste des composants, ajout et gestion de leur num�rotation
		ListIterator<Editeur> iterator = listeContenu.listIterator();
		while(iterator.hasNext()){
			Editeur tmp = iterator.next();
			// Gestion num�rotation composant
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
			// Ajout de l'�lement
			conteneurGeneral.add(tmp);
			conteneurGeneral.add(Box.createVerticalStrut(5));
		}
		conteneurGeneral.revalidate();
	}
	/**
	* Fonction ajout �diteur paragraphe apr�s l'�diteur c.
	* @param c
	* 			L'�diteur apr�s lequel on d�sire ajouter l'�diteur de paragraphe.
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
	 * Fonction ajout �diteur paragraphe apr�s tous les �diteurs.
	 * @param textEditeur
	 * 			Le texte que l'on veut mettre dans l'�diteur de paragraphe.
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
	 * Fonction ajout �diteur de paragraphe apr�s l'�diteur c, contenant le texte textEditeur
	 * @param c
	 * 			L'�diteur apr�s lequel on d�sire ajouter l'�diteur de paragraphe.
	 * @param textEditeur
	 * 			Le texte que l'on d�sire ajouter dans l'�diteur de paragraphe
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
	 * Fonction d'ajout d'une formule, apr�s le conteneur s�lectionn� actuellement
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
	 *  Fonction ajout �diteur titre, de niveau hierarchique i, num�rot� ou non, titr� title
	 * @param i
	 * 			Le niveau hierarchique consid�r�
	 * @param numerotation
	 * 			Indique si il y a num�rotation
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
	 *  Fonction de destruction de l'�diteur C
	 * @param c
	 * 			Editeur � d�truire
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
	 *  Fonction de gestion des focus, refocus sur l'�diteur c
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
	 *  Fonction de gestion des focus, refocus sur l'�diteur suivant l'�diteur c
	 *  
	 * @param c
	 * 			Editeur r�f�rence
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
	 * Fonction de gestion des focus, refocus sur l'�diteur pr�c�dent l'�diteur c
	 * 
	 * @param c
	 * 				Editeur ref�rence
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
	 *  Fonction masquant/affichant les �diteurs suivant l'�diteur c, de niveau hierarchique inf�rieur � celui de l'�diteur c
	 *  
	 *  @param c
	 *  		Editeur � masquer/afficher
	 *  @param b
	 *  		VRAI pour masquer le composant, FALSE pour l'afficher
	 */
	public static void masquer(Editeur c, boolean b) {
		if(b){

			int numHierarchie = ((EditeurTitre)c).getNumeroHierarchie();
			((EditeurTitre)c).setMasque(true);
			int indiceCListeContenu = listeContenu.indexOf(c);
			int numActuel = numHierarchie+1;

			// Parcours des �l�ments de niveau hierarchique inf�rieur pour les masquer

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
