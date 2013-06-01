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
			if(courant.getName().equals("part")){
				codeLaTeX = codeLaTeX + "\\part{"+courant.getText()+"} \n \n";
			}
			if(courant.getName().equals("chapter")){
				codeLaTeX = codeLaTeX + "\\chapter{"+courant.getText()+"} \n \n";
			}
			if(courant.getName().equals("section")){
				codeLaTeX = codeLaTeX + "\\section{"+courant.getText()+"} \n \n";
			}
			if(courant.getName().equals("subsection")){
				codeLaTeX = codeLaTeX + "\\subsection{"+courant.getText()+"} \n \n";
			}
			if(courant.getName().equals("subsubsection")){
				codeLaTeX = codeLaTeX + "\\subsection{"+courant.getText()+"} \n \n";
			}
			if(courant.getName().equals("p")){
				codeLaTeX = codeLaTeX + " " +courant.getText()+ "\n \n";
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

			RunExternal.launch("cmd /c pdflatex "+file.getAbsolutePath());
			java.awt.Desktop.getDesktop().open(new File("Document.pdf"));
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
}
