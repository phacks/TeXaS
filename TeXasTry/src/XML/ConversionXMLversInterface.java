package XML;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import EcranEditionCentral.ContenuEditable;
import EcranEditionCentral.Editeur;

public class ConversionXMLversInterface {

	static Element racine;
	static org.jdom2.Document document;

	public static void fromXML()	{
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			document = sxb.build(new File("Document.xml"));
		}
		catch(Exception e){}

		racine = document.getRootElement();

		List<Element> listElement = racine.getChildren();

		Iterator<Element> iteratorElement = listElement.iterator();

		while(iteratorElement.hasNext()){
			Element courant = iteratorElement.next();
			if(courant.getName().equals("Titre")){
				gestionTitre(courant);
			}
			if(courant.getName().equals("p")){
				gestionParagraphe(courant);
			}
			
			if(courant.getName().equals("formule")){
				gestionFormule(courant);
			}
		}

	}

	private static void gestionFormule(Element courant) {
		// POUR NICOLAS
		
	}

	private static void gestionParagraphe(Element courant) {
		ContenuEditable.addEditeurParagrapheAtTheEnd(courant.getText());
	}

	private static void gestionTitre(Element courant) {
		String attribute = courant.getAttributeValue("Type");

		if(attribute.equals("part")){
			ContenuEditable.addEditeurTitre(0, true, courant.getText());
		}
		if(attribute.equals("chapter")){
			ContenuEditable.addEditeurTitre(1, true, courant.getText());
		}
		if(attribute.equals("section")){
			ContenuEditable.addEditeurTitre(2, true, courant.getText());	
		}
		if(attribute.equals("subsection")){
			ContenuEditable.addEditeurTitre(3, true, courant.getText());	
		}
		if(attribute.equals("subsubsection")){
			ContenuEditable.addEditeurTitre(4, true, courant.getText());
		}
	}

}