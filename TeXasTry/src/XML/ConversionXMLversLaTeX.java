package XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ConversionXMLversLaTeX {

	static String codeLaTeX;

	static org.jdom2.Document document;
	static Element racine;

	private static boolean problemeCompilation = false;



	public static void toLaTeX(){
		codeLaTeX = "\\documentclass[a4paper,10pt]{report} \n"+
				"\\usepackage[T1]{fontenc} \n"+
				"\\usepackage[french]{babel} \n"+ 
				"\\usepackage{lmodern}  \n  \n"+
				"\\begin{document} \n \n";

		SAXBuilder sxb = new SAXBuilder();
		try
		{
			document = sxb.build(new File("Document.xml"));
		}
		catch(Exception e){}

		racine = document.getRootElement();

		List<Element> listElement = racine.getChildren();

		Iterator<Element> iterator = listElement.iterator();

		while(iterator.hasNext()){
			Element courant = iterator.next();
			if(courant.getName().equals("Titre")){
				gestionTitre(courant);
			}
			if(courant.getName().equals("p")){
				gestionParagraphe(courant);
			}
			if(courant.getName().equals("formule")){
				codeLaTeX = codeLaTeX + "$$" + " ";

				parseFormule(courant);

				codeLaTeX = codeLaTeX + "$$ \n \n";

			}

		}


		codeLaTeX = codeLaTeX + "\\end{document}";



		FileOutputStream fop = null;
		File file;

		try {

			file = new File("Document.tex");
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = codeLaTeX.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			//String[] cmd = {"/usr/local/texlive/2011/bin/x86_64-darwin//pdflatex", file.getAbsolutePath()};
			String[] cmd = {"pdflatex", file.getAbsolutePath()};

			RunExternal.launch(cmd);

			if(!problemeCompilation){
				java.awt.Desktop.getDesktop().open(new File("Document.pdf"));
			}
			
			problemeCompilation = false;


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}



	}

	private static void parseFormule(Element courant) {
		// TODO Auto-generated method stub
		List<Element> listFormuleElement = courant.getChildren();
		Iterator<Element> iteratorFormule = listFormuleElement.iterator();

		while(iteratorFormule.hasNext()){
			Element courantFormule = iteratorFormule.next();
			if(courantFormule.getName().equals("symbole")){
				codeLaTeX = codeLaTeX + "\\" + courantFormule.getText() + " ";
			}
			if(courantFormule.getName().equals("texte")){
				codeLaTeX = codeLaTeX + courantFormule.getText() + " ";
			}
			if (courantFormule.getName().equals("fraction")){
				List<Element> listFractionElement = courantFormule.getChildren();
				Iterator<Element> iteratorFraction = listFractionElement.iterator();

				Element courantFraction = iteratorFraction.next();
				codeLaTeX = codeLaTeX + "\\frac{";
				parseFormule(courantFraction);
				courantFraction = iteratorFraction.next();
				codeLaTeX = codeLaTeX + "}{";
				parseFormule(courantFraction);
				codeLaTeX = codeLaTeX + "}" + " ";

			}
		}

	}

	private static void gestionParagraphe(Element courant) {
		codeLaTeX = codeLaTeX + " " +courant.getText()+ "\n \n";
	}

	private static void gestionTitre(Element courant) {
		String attribute = courant.getAttributeValue("Type");
		if(attribute.equals("part")){
			codeLaTeX = codeLaTeX + "\\part{"+courant.getText()+"} \n \n";
		}
		if(attribute.equals("chapter")){
			codeLaTeX = codeLaTeX + "\\chapter{"+courant.getText()+"} \n \n";
		}
		if(attribute.equals("section")){
			codeLaTeX = codeLaTeX + "\\section{"+courant.getText()+"} \n \n";
		}
		if(attribute.equals("subsection")){
			codeLaTeX = codeLaTeX + "\\subsection{"+courant.getText()+"} \n \n";
		}
		if(attribute.equals("subsubsection")){
			codeLaTeX = codeLaTeX + "\\subsubsection{"+courant.getText()+"} \n \n";
		}
	}

	public static void setProblemeCompilation(boolean b) {
		problemeCompilation = b;
	}
}
