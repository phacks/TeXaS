import java.util.Iterator;
import java.util.List;

import org.jdom2.*;


public class TableauLaTeX {

	boolean bordure;
	int nbColonnes;

	String codeLaTeXTableau;

	public TableauLaTeX(Element tableauXML){

		// Cr�ation du boole�en repr�sentant si le tableau sera encadr� ou non
		this.bordure = tableauXML.getAttributeValue("border").equals("true");

		// D�compte du nombre de colonne, prenant en compte l'�ventuelle fusion de colonne
		List listeCellules = tableauXML.getChild("tr").getChildren();
		Iterator i = listeCellules.iterator();
		while (i.hasNext()){
			Element courant = (Element)i.next();
			String nbColonnesFusionnee = courant.getAttributeValue("colspan");
			if (!(nbColonnesFusionnee == null)){
				nbColonnes += Integer.decode(nbColonnesFusionnee);
			}
			else nbColonnes++;
		}

		//Ecriture de la balise ouvrante du code LaTeX
		if (bordure){
			codeLaTeXTableau = "\\begin{tabular}{|";
			for (int j = 1; j <= nbColonnes; j++) {
				codeLaTeXTableau = codeLaTeXTableau + "c|";
			}
			codeLaTeXTableau = codeLaTeXTableau + "} \n \\hline \n";
		}

		else{
			codeLaTeXTableau = "\\begin{tabular}{";
			for (int j = 1; j < nbColonnes; j++) {
				codeLaTeXTableau = codeLaTeXTableau + "c ";
			}
			codeLaTeXTableau = codeLaTeXTableau + "c} \n";
		}

		//Ecriture du contenu du tableau (NB : il faut �crire la partie SANS bordure)
		List listeLignes = tableauXML.getChildren("tr");
		Iterator iterLignes = listeLignes.iterator();
		while(iterLignes.hasNext()){
			Element courantLignes = (Element)iterLignes.next();
			listeCellules = courantLignes.getChildren("td");
			Iterator iterCellules = listeCellules.iterator();
			while(iterCellules.hasNext()){
				Element courant = (Element)iterCellules.next();
				String nbColonnesFusionnee = courant.getAttributeValue("colspan");
				//Traitement des colonnes fusionn�es
				if (!(nbColonnesFusionnee == null)){
					codeLaTeXTableau = codeLaTeXTableau + " \\mutlicolumn{"+Integer.decode(nbColonnesFusionnee)+"}{c|}{"+courant.getText()+"} &";
				}
				//Traitement des colonnes "classiques"
				else{
					codeLaTeXTableau = codeLaTeXTableau + " " + courant.getText() + " &";
				}
			}
			// Retrait du dernier "&" en trop, remplac� par le retour ligne et ajout de la bordure
			codeLaTeXTableau = codeLaTeXTableau.substring(0,codeLaTeXTableau.length()-2) + " \\\\ \n \\hline \n";
		}


		//Ecriture de la balise fermante du code LaTeX
		codeLaTeXTableau = codeLaTeXTableau + "\\end{tabular}";

		System.out.println(codeLaTeXTableau);

	}

}
