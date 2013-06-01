package XML;

import java.io.FileOutputStream;
import java.util.List;
import java.util.ListIterator;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import EcranEditionCentral.ContenuEditable;
import EcranEditionCentral.Editeur;
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