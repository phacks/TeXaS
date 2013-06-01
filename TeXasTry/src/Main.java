import javax.swing.JFrame;
import javax.swing.UIManager;

import EcranEditionCentral.ContenuEditable;
import EcranEditionCentral.EcranEditionCentral;
import XML.ConversionXMLversInterface;


public class Main {


	public static void main(String[] args) {
	
		 try {
		      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		 
		Fenetre maFen = new Fenetre();

		ConversionXMLversInterface.fromXML();
		
		
		
		maFen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}

}
