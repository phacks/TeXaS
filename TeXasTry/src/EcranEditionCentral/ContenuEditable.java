package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.Box;

import briquesElementaires.JPanelDef;

public class ContenuEditable extends JPanelDef {

	private static List<Component> listeContenu = new LinkedList<Component>();
	private static Box conteneurGeneral = Box.createVerticalBox();

	public ContenuEditable(){
		super(new BorderLayout());

		listeContenu.add(new EditeurParagraphe());

		conteneurGeneral.add(listeContenu.get(0));

		this.add(conteneurGeneral, BorderLayout.NORTH);

	}

	public static void revalider(){
		conteneurGeneral.removeAll();
		ListIterator<Component> iterator = listeContenu.listIterator();
		while(iterator.hasNext()){
			conteneurGeneral.add(iterator.next());
			conteneurGeneral.add(Box.createVerticalStrut(10));
		}
		conteneurGeneral.revalidate();
	}

	public static void addEditeurParagraphe(Component c){
		int indice = listeContenu.indexOf(c);
		listeContenu.add(indice+1, new EditeurParagraphe());
		revalider();
	}



	public static void addEditeurTitre(int i,boolean numerotation, String title ){
		if(listeContenu.size()==1){
			if(listeContenu.get(0).getClass().toString().contains("EditeurParagraphe")){
				EditeurParagraphe tmp = (EditeurParagraphe) listeContenu.get(0);
				if (tmp.getText().equals("Votre texte ici...") || tmp.getText().equals("")){
					listeContenu.remove(0);
				}
			}
		}
		listeContenu.add(new EditeurTitre(i,numerotation, title));
		revalider();
	}

	public static void detruire(Component c) {
		int indice = listeContenu.indexOf(c);
		listeContenu.remove(indice);
		if(listeContenu.size()==0){
			listeContenu.add(new EditeurParagraphe());
		}
		revalider();
	}

}
