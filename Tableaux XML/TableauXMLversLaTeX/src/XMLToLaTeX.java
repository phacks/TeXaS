import java.io.File;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;


public class XMLToLaTeX {


	static org.jdom2.Document document;
	static Element racine;

	public static void main(String[] args)
	{
		//On cr�e une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			//On cr�e un nouveau document JDOM avec en argument le fichier XML
			document = sxb.build(new File("tableau.xml"));
		}
		catch(Exception e){}

		//On initialise un nouvel �l�ment racine avec l'�l�ment racine du document.
		racine = document.getRootElement();
		TableauLaTeX2 monTableau = new TableauLaTeX2(racine);

	}
}
