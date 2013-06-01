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
				Element paragraphe = new Element("p");
				String contenuParagraphe = ((EditeurParagraphe)editeur).getText();
				paragraphe.setText(contenuParagraphe);
				racine.addContent(paragraphe);
			}
			if(editeur.getClass().toString().contains("EditeurTitre")){
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
				Element titre = new Element(typeTitre);
				String contenuTitre = tmp.getText();
				titre.setText(contenuTitre);
				racine.addContent(titre);
			}
		}

		enregistre("Document.xml");
		ConversionXMLversLaTeX.toLaTeX();
	}

	//Ajouter ces deux méthodes à notre classe JDOM1
	static void affiche()
	{
		try
		{
			//On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, System.out);
		}
		catch (java.io.IOException e){}
	}

	static void enregistre(String fichier)
	{
		try
		{
			//On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			//Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
			//avec en argument le nom du fichier pour effectuer la sérialisation.
			sortie.output(document, new FileOutputStream(fichier));
		}
		catch (java.io.IOException e){}
	}
}