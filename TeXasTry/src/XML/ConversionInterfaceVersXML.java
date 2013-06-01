package XML;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import formule.ContenuItem;
import formule.ContenuItemImage;
import formule.ContenuItemSplit;

import EcranEditionCentral.ContenuEditable;
import EcranEditionCentral.Editeur;
import EcranEditionCentral.EditeurFormule;
import EcranEditionCentral.EditeurParagraphe;
import EcranEditionCentral.EditeurTitre;

public class ConversionInterfaceVersXML{

	static Element racine;

	static org.jdom2.Document document;

	private static List<Editeur> contenuDocument;

	public static void toXML()	{

		racine = new Element("document");
		document = new Document(racine);
		contenuDocument = ContenuEditable.getListeContenu();
		ListIterator<Editeur> iterator = contenuDocument.listIterator();
		while(iterator.hasNext()){
			Editeur editeur = iterator.next();
			if(editeur.getClass().toString().contains("EditeurParagraphe")){
				gestionParagraphe(editeur);
			}
			if(editeur.getClass().toString().contains("EditeurTitre")){
				gestionTitre(editeur);

			}
			
			if(editeur.getClass().toString().contains("EditeurFormule")){
				EditeurFormule tmp = (EditeurFormule) editeur;
				
				
				ArrayList<ContenuItem> contenuItemArrayList = editeur.getFormule().getContenuItemArrayList();
				
				Element formule = parseFormule(contenuItemArrayList, "formule");
				
				
				racine.addContent(formule);
			}
		}

		enregistre("Document.xml");
		ConversionXMLversLaTeX.toLaTeX();
	}

	private static void gestionTitre(Editeur editeur) {
		EditeurTitre tmp = (EditeurTitre) editeur;
		int niveauHierarchique = tmp.getNumeroHierarchie();
		String typeTitre;
		switch (niveauHierarchique) {
		case 0:
			typeTitre = "part";
			break;
		case 1:
			typeTitre = "chapter";
			break;
		case 2:
			typeTitre = "section";
			break;
		case 3:
			typeTitre = "subsection";
			break;
		default:
			typeTitre = "subsubsection";
			break;
		}
		Element titre = new Element("Titre");
		titre.setAttribute("Type",typeTitre);
		String contenuTitre = tmp.getText();
		titre.setText(contenuTitre);
		racine.addContent(titre);
	}

	private static Element parseFormule(ArrayList<ContenuItem> contenuItemArrayList, String type) {
		// TODO Auto-generated method stub
		Element formule = new Element(type);
		
		for (int i = 0; i < contenuItemArrayList.size(); i++){
			
			if (contenuItemArrayList.get(i).getType().equals("fraction")){
				Element fraction = new Element("fraction");
				Element fractionHaut = new Element("fraction-haut");
				Element fractionBas = new Element("fraction-bas");
				
				ArrayList<ContenuItem> contenuItemArrayListHaut = (contenuItemArrayList.get(i)).getArraySplit()[0].getContenuItemArrayList();
				ArrayList<ContenuItem> contenuItemArrayListBas = (contenuItemArrayList.get(i)).getArraySplit()[1].getContenuItemArrayList();
				
				fractionHaut = parseFormule(contenuItemArrayListHaut, "fractionHaut");
				fractionBas = parseFormule(contenuItemArrayListBas, "fractionBas");
				
				fraction.addContent(fractionHaut);
				fraction.addContent(fractionBas);
				formule.addContent(fraction);
				
			}	
			if (contenuItemArrayList.get(i).getType().equals("symbole")){
				Element symbole = new Element("symbole");
				symbole.setText((contenuItemArrayList.get(i)).getText());
				
				formule.addContent(symbole);
			}
			if (contenuItemArrayList.get(i).getType().equals("texte")){
				Element texte = new Element("texte");
				texte.setText((contenuItemArrayList.get(i)).getText());
				
				formule.addContent(texte);
			}
					
		}
		
		return formule;	
		
	}

	

	private static void gestionParagraphe(Editeur editeur) {
		Element paragraphe = new Element("p");
		String contenuParagraphe = ((EditeurParagraphe)editeur).getText();
		paragraphe.setText(contenuParagraphe);
		racine.addContent(paragraphe);
	}


	static void enregistre(String fichier)
	{
		try
		{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(fichier));
		}
		catch (java.io.IOException e){}
	}
}