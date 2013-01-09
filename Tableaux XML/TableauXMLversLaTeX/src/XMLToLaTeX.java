import java.io.File;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;


public class XMLToLaTeX {


	static org.jdom2.Document document;
	static Element racine;

	public static void main(String[] args)
	{
		//On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			//On crée un nouveau document JDOM avec en argument le fichier XML
			document = sxb.build(new File("tableau.xml"));
		}
		catch(Exception e){}

		//On initialise un nouvel élément racine avec l'élément racine du document.
		racine = document.getRootElement();
		TableauLaTeX2 monTableau = new TableauLaTeX2(racine);

	}
}
