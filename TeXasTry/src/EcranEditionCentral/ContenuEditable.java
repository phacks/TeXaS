package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.Box;
import javax.swing.JComponent;

import briquesElementaires.JPanelDef;

public class ContenuEditable extends JPanelDef {

	private static List<Editeur> listeContenu = new LinkedList<Editeur>();
	private static Box conteneurGeneral = Box.createVerticalBox();

	private static int[] coordHierarchieActuelle = { 0 , 0 , 0 , 0 , 0};
	private static int scrollPanePosition;


	public ContenuEditable(){
		super(new BorderLayout());

		listeContenu.add(new EditeurParagraphe());

		conteneurGeneral.add(listeContenu.get(0));

		this.add(conteneurGeneral, BorderLayout.NORTH);

	}

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

	public static void addEditeurParagraphe(Editeur c){
		int indice = listeContenu.indexOf(c);
		listeContenu.add(indice+1, new EditeurParagraphe());
		revalider();
		EditeurParagraphe tmp = (EditeurParagraphe) listeContenu.get(indice+1);
		refocus(tmp);
		tmp.reindenter();
	}
	
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

	public static void refocus(Component c) {
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

	public static void focusNext(Component c) {
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

}
